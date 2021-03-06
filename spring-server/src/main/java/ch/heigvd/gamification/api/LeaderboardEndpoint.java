package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.api.dto.UserWithNumberOfBadges;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.UserRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Leaderboard endoint. Returns the users ordered by number of badges.
 */
@RestController
public class LeaderboardEndpoint implements LeaderboardApi {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    /**
     * Handle leaderboard get request.
     *
     * @param token
     * @return
     *      403 if the application doesn't exist
     *      200 otherwise, with the list of users ordered by number of badges
     */
    @Override
    public ResponseEntity<List<UserWithNumberOfBadges>> leaderboard(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<UserWithNumberOfBadges> users = new ArrayList<>();
        /*for(Object[] user : userRepository.findByNumberOfBadges(application.getId())){
            UserWithNumberOfBadges userDto = new UserWithNumberOfBadges();
            userDto.setUsername((String)user[0]);
            userDto.setNumberOfBadges(((BigInteger)user[1]).longValue());
            users.add(userDto);
        }*/
        for(ch.heigvd.gamification.database.model.User u : userRepository.findAll())
        {
            UserWithNumberOfBadges tmp = new UserWithNumberOfBadges();
            tmp.setUsername(u.getUsername());
            tmp.setNumberOfBadges((long)u.getBadges().size());
            users.add(tmp);
        }

        Comparator<UserWithNumberOfBadges> usersComparator = (o1, o2) ->
        {
            if(o1.getNumberOfBadges() == o2.getNumberOfBadges())
                return 0;
            else if(o1.getNumberOfBadges() < o2.getNumberOfBadges())
                return -1;
            else
                return 1;
        };

        Collections.sort(users, usersComparator.reversed());

        return ResponseEntity.ok(users);
    }
}
