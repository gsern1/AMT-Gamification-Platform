package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.*;
import ch.heigvd.gamification.api.dto.PointScale;
import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.api.dto.UserPointScale;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.UserPointScaleRepository;
import ch.heigvd.gamification.database.dao.UserRepository;
import ch.heigvd.gamification.database.model.*;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * User endoint. Handle users GET request.
 */
@RestController
public class UserEndpoint implements UsersApi {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserPointScaleRepository userPointScaleRepository;

    /**
     * Returns the list of badges of the user with the specified username
     *
     * @param userName
     * @param token
     * @return
     *      403 if your token is invalid.
     *      404 if the user doesn't exist
     *      200 otherwise
     */
    @Override
    public ResponseEntity<List<BadgeWithLocation>> findUserBadges(@ApiParam(value = "name of the user", required = true) @PathVariable("userName") String userName, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<BadgeWithLocation> badges = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.User user = userRepository.findByUsernameAndApplication(userName, application);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for(ch.heigvd.gamification.database.model.Badge badge : user.getBadges()){
            BadgeWithLocation badgeDto = new BadgeWithLocation();
            badgeDto.setName(badge.getName());
            badgeDto.setLocation("/badges/" + badge.getId());
            badges.add(badgeDto);
        }
        return ResponseEntity.ok(badges);
    }

    /**
     * Returns the list of pointScales of the user with the specified username
     *
     * @param userName
     * @param token
     * @return
     *      403 if your token is invalid.
     *      404 if the user doesn't exist
     *      200 otherwise
     */
    @Override
    public ResponseEntity<List<UserPointScale>> findUserPointScales(@ApiParam(value = "name of the user", required = true) @PathVariable("userName") String userName, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<PointScale> pointscales = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.User user = userRepository.findByUsername(userName);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<ch.heigvd.gamification.database.model.UserPointScale> userPointScales = userPointScaleRepository.findByUser(user);
        List<UserPointScale> userPointScalesDto = new ArrayList<>();
        for(ch.heigvd.gamification.database.model.UserPointScale ups : userPointScales){
            UserPointScale userPointScaleDto = new UserPointScale();
            userPointScaleDto.setName((ups.getPointScale().getName()));
            userPointScaleDto.setPoints(ups.getPoints());
            userPointScaleDto.setLocation("/pointScales/" + ups.getId());
            userPointScalesDto.add(userPointScaleDto);
        }
        return ResponseEntity.ok(userPointScalesDto);
    }

    /**
     * Returns the list of users
     *
     * @param token
     * @return
     *      403 if your token is invalid.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<List<User>> findUsers(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<User> users = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        for(ch.heigvd.gamification.database.model.User user : userRepository.findByApplication(application)){
            User userDto = new User();
            userDto.setUsername(user.getUsername());
            userDto.setId(user.getId());
            users.add(userDto);
        }
        return ResponseEntity.ok(users);
    }
}
