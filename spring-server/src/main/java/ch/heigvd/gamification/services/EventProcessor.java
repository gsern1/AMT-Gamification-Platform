package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.database.dao.*;
import ch.heigvd.gamification.database.model.*;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by antoi on 11.12.2016.
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processEvent(User user, Event event) {
        PointScaleRule pointScaleRule = pointScaleRuleRepository.findByType(event.getType());

        if (pointScaleRule != null) //if it's a pointscale event
        {
            List<UserPointScale> userPointScales = userPointScaleRepository.findByUser(user);
            System.out.println(userPointScales.size());

            //if the user dosen't exists
            if (userPointScales.isEmpty()) {
                //create the relation between the user and the pointscale
                UserPointScale tmp = userPointScaleRepository.save(new UserPointScale(user, pointScaleRule.getPointscale(), pointScaleRule.getIncrement()));
                userPointScales.add(tmp);
                return;
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
        }
        BadgeRule badgeRule = badgeRuleRepository.findByType(event.getType());
        if (badgeRule != null) //it's a badge event!
        {
            user.getBadges().add(badgeRule.getBadge());//we give the badge to the user
        }
    }
}
