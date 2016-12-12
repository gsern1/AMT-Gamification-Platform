package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dto.Event;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by antoi on 11.12.2016.
 */
@Service
public class EventProcessor {
    @Async
    @Transactional
    public void processEvent(Event event) {
    }
}
