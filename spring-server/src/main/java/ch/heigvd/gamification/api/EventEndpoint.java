package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.services.EventProcessor;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lux on 30.11.16.
 */
@RestController
public class EventEndpoint implements EventsApi {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventProcessor eventProcessor;

    @Override
    public ResponseEntity<Void> addEvent(@ApiParam(value = "event object to add to the platform", required = true) @RequestBody Event event, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        ch.heigvd.gamification.database.model.Event newEvent = new ch.heigvd.gamification.database.model.Event();
        newEvent.setIncrease(event.getIncrease());
        // TODO : Retrieve applciation from token
        // TODO : Create if not exist user and set point scale
        //newEvent.setPointScale(event.getPointScale());
        //newEvent.setUser(event.getUserId());
        try{
            eventRepository.save(newEvent);
            eventProcessor.processEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
