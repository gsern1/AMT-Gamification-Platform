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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
 * Event endoint. Handle new events POST requests.
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

    /**
     * Handle new event post request.
     * There is an optimistic lock on rules which is reruned automatically if it fails.
     * The specified user is created if it doesn't exist.
     * A Conflict 409 status code if there is a conflict with the creation of the user and the gamified app must
     * rerun the request.
     *
     * @param event
     * @param token
     * @return
     *      409 if your the user already exists
     *      404 if the system has not found the application.
     *      403 if the application doesn't exist
     *      400 if the type specified in the event doesn't exist
     *      200 otherwise
     */
    @Override
    public ResponseEntity<Void> addEvent(@ApiParam(value = "event object to add to the platform", required = true) @RequestBody Event event, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

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
            try {
                userRepository.save(user);
            }
            catch(Exception e)
            {
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
            }
        }

        boolean done = false;
        while (!done) {
            try {
                if(!eventProcessor.processEvent(user, event)){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                done = true;
            } catch (ObjectOptimisticLockingFailureException e) {
                System.out.println("FAILED TO UPDATE (concurrency)... LET'S GO AGAIN");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
