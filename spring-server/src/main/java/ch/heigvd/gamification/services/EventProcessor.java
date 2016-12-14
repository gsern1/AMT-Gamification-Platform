package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.database.dao.UserPointScaleRepository;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.database.model.User;
import ch.heigvd.gamification.database.model.UserPointScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by antoi on 11.12.2016.
 */
@Service
public class EventProcessor {
    @Autowired
    private UserPointScaleRepository userPointScaleRepository;

    @Async
    @Transactional
    public void processEvent(User user, PointScale pointScale, long points) {
        UserPointScale userPointScale = new UserPointScale(user, pointScale, points);
        userPointScaleRepository.save(userPointScale);
    }
}
