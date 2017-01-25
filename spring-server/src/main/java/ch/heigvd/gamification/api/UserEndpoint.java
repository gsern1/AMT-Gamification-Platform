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
 * Created by lux on 30.11.16.
 */
@RestController
public class UserEndpoint implements UsersApi {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserPointScaleRepository userPointScaleRepository;

    // TODO : Retourner et utiliser l'id précisé dans l'event pour identifier le user ?
    // TODO : Javadoc, leaderboardendpoint
    // TODO : Utiliser les validators pour valider les données genre login


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

        List<Object[]> userPointScales = userPointScaleRepository.findSumPointScalePerUser(user.getId());
        List<UserPointScale> userPointScalesDto = new ArrayList<>();
        for(Object[] userPointScale : userPointScales){
            UserPointScale userPointScaleDto = new UserPointScale();
            userPointScaleDto.setName((String)userPointScale[1]);
            userPointScaleDto.setPoints(((BigDecimal)userPointScale[2]).longValue());
            userPointScaleDto.setLocation("/pointScales/" + ((BigInteger)userPointScale[0]).longValue());
            userPointScalesDto.add(userPointScaleDto);
        }
        return ResponseEntity.ok(userPointScalesDto);
    }

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
