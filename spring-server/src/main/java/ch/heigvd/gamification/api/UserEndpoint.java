package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.PointScale;
import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.api.dto.UserPointScale;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.BadgeRepository;
import ch.heigvd.gamification.database.dao.UserPointScaleRepository;
import ch.heigvd.gamification.database.dao.UserRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    // TODO : Faire des dtos pour les autres endpoint pour retourner l'id
    // TODO : Utiliser les validators pour valider les données genre login
    // TODO : Retourner au POST la location des badges ou pointscales dans les headers...

    @Override
    public ResponseEntity<List<Badge>> findUserBadges(@ApiParam(value = "ID of user", required = true) @PathVariable("userId") Long userId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<Badge> badges = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);

        ch.heigvd.gamification.database.model.User user = userRepository.findByIdAndApplication(userId, application);

        for(ch.heigvd.gamification.database.model.Badge badge : user.getBadges()){
            Badge badgeDto = new Badge();
            badgeDto.setName(badge.getName());
            badges.add(badgeDto);
        }
        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<List<UserPointScale>> findUserPointScales(@ApiParam(value = "ID of user", required = true) @PathVariable("userId") Long userId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<PointScale> pointscales = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);

        List<Object[]> userPointScales = userPointScaleRepository.findSumPointScalePerUser(userId);
        List<UserPointScale> userPointScalesDto = new ArrayList<>();
        for(Object[] userPointScale : userPointScales){
            UserPointScale userPointScaleDto = new UserPointScale();
            userPointScaleDto.setName((String)userPointScale[0]);
            userPointScaleDto.setPoints(((BigDecimal)userPointScale[1]).longValue());
            userPointScalesDto.add(userPointScaleDto);
        }
        return ResponseEntity.ok(userPointScalesDto);
    }

    @Override
    public ResponseEntity<List<User>> findUsers(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<User> users = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);
        for(ch.heigvd.gamification.database.model.User user : userRepository.findByApplication(application)){
            User userDto = new User();
            userDto.setName(user.getName());
            userDto.setId(user.getId());
            users.add(userDto);
        }
        return ResponseEntity.ok(users);
    }
}
