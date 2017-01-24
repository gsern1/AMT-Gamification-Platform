package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.User;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lux on 30.11.16.
 */
@RestController
public class LeaderboardEndpoint implements LeaderboardApi {
    @Override
    public ResponseEntity<List<User>> leaderboard(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }
}