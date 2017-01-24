package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.database.dao.PointScaleRepository;
import ch.heigvd.gamification.database.dao.PointScaleRuleRepository;
import ch.heigvd.gamification.database.dao.UserPointScaleRepository;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.database.model.PointScaleRule;
import ch.heigvd.gamification.database.model.User;
import ch.heigvd.gamification.database.model.UserPointScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Async
    @Transactional
    public void processEvent(User user, Event event) {
        PointScaleRule pointScaleRule = pointScaleRuleRepository.findByType(event.getType());

        if(pointScaleRule != null) //if it's a pointscale event
        {
            List<UserPointScale> userPointScales = userPointScaleRepository.findByUser(user);

            //if the user dosent exists
            if(userPointScales.isEmpty())
            {
                //create the relation between the user and the pointscale
                UserPointScale tmp = userPointScaleRepository.save(new UserPointScale(user, pointScaleRule.getPointscale(), pointScaleRule.getIncrement()));
                userPointScales.add(tmp);
            }

            //check all user's pointscale
            userPointScales.stream().filter(userPointScale -> userPointScale.getPointScale() == pointScaleRule.getPointscale()).forEach(userPointScale -> {
                //when we have found what we have to modify
                userPointScale.setPoints(userPointScale.getPoints() + pointScaleRule.getIncrement());
            });

            //now we have to check if a badge has trigger.

        }
    }
}
