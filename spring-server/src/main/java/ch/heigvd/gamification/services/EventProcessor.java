package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.database.dao.*;
import ch.heigvd.gamification.database.model.*;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

/**
 * Concurrent service that provides the needs to handle events. It is based on optimistic locking.
 */
@Service
public class EventProcessor {

    @Autowired
    private UserPointScaleRepository userPointScaleRepository;

    @Autowired
    private PointScaleRuleRepository pointScaleRuleRepository;

    @Autowired
    private BadgeRuleRepository badgeRuleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Handle the process of this event. Throw an ObjectOptimisticLockingFailureException if a concurrency modification has
     * been detected. The caller must then catch this exception and retry to call this method to give her an other chance of
     * succeeding.
     *
     * @param user: the user to be processed with the event.
     * @param event: the event to be processed.
     * @throws ObjectOptimisticLockingFailureException
     */
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean processEvent(User user, Event event) throws ObjectOptimisticLockingFailureException {
        PointScaleRule pointScaleRule = pointScaleRuleRepository.findByType(event.getType());

        if (pointScaleRule != null) //if it's a pointscale event
        {
            List<UserPointScale> userPointScales = userPointScaleRepository.findByUser(user);

            //check if this user dosent have have this point scale
            List<UserPointScale> up = userPointScaleRepository.findByUserAndPointScale(user, pointScaleRule.getPointscale());

            //if the user dosen't exists
            if (userPointScales.isEmpty() || up.isEmpty()) {
                //create the relation between the user and the pointscale
                UserPointScale tmp = userPointScaleRepository.save(new UserPointScale(user, pointScaleRule.getPointscale(), pointScaleRule.getIncrement()));
                userPointScales.add(tmp);
                return true;
            }

            //check all user's pointscale
            userPointScales.stream()
                    .filter(userPointScale -> userPointScale.getPointScale() == pointScaleRule.getPointscale())
                    .forEach(userPointScale -> {
                        //when we have found what we have to modify
                        userPointScale.setPoints(userPointScale.getPoints() + pointScaleRule.getIncrement());
                        //now we have to check if a badge has trigger.
                        List<BadgeRule> badgeRules = badgeRuleRepository.findByPointscale(userPointScale.getPointScale());
                        badgeRules.stream()
                                .filter(badgeRule -> badgeRule.getThreshold() <= userPointScale.getPoints())
                                .forEach(badgeRule -> {
                                    userPointScale.getUser().getBadges().add(badgeRule.getBadge()); //we give the badge to the user
                                });
                    });
            return true;
        }
        BadgeRule badgeRule = badgeRuleRepository.findByType(event.getType());
        if (badgeRule != null) //it's a badge event!
        {
            user.getBadges().add(badgeRule.getBadge());//we give the badge to the user
            return true;
        }
        return false;
    }
}
