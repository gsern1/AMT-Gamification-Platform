package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.api.dto.ErrorModel;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-10T14:12:42.843+01:00")

@Api(value = "leaderboard", description = "the leaderboard API")
public interface LeaderboardApi {

    @ApiOperation(value = "Returns the leaderboard", notes = "Returns the leaderboard ", response = User.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "leaderboard response", response = User.class),
        @ApiResponse(code = 200, message = "unexpected error", response = User.class) })
    @RequestMapping(value = "/leaderboard",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> leaderboard(
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);

}
