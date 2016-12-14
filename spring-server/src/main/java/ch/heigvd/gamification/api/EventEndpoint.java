package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.PointScaleRepository;
import ch.heigvd.gamification.database.dao.UserRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.database.model.User;
import ch.heigvd.gamification.services.EventProcessor;
import ch.heigvd.gamification.utils.JWTutils;
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
    EventProcessor eventProcessor;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Void> addEvent(@ApiParam(value = "event object to add to the platform", required = true) @RequestBody Event event, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        PointScale pointScale = pointScaleRepository.findOne(event.getPointScale());
        if(pointScale == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        User user = userRepository.findByName(event.getUsername());
        if(user == null)
        {
            user = new User(event.getUsername(), app);
            userRepository.save(user);
        }

        eventProcessor.processEvent(user, pointScale, event.getIncrease());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
