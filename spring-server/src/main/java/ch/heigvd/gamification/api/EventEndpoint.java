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
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.TreeSet;

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
        // TODO : GESTION de la transaction ? On utilise pas le service processor ?

        // TODO : Mettre tout ça dans le service event processor

        // TODO : Fix pour gérer avec les types les badge rules et les pointscales rules

        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        User user = userRepository.findByUsername(event.getUsername());
        if(user == null)
        {
            user = new User(event.getUsername(), app, new HashSet<>());
            userRepository.save(user);
        }

        boolean done = false;
        while (!done) {
            try {
                eventProcessor.processEvent(user, event);
                done = true;
            } catch (ObjectOptimisticLockingFailureException e) {
                System.out.println("FAILED TO UPDATE... LET'S GO AGAIN");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
