package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.ErrorModel;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.PointScale;

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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-10T13:17:26.974+01:00")

@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "Returns a user's badges", notes = "Returns a user's badges ", response = Badge.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badges response", response = Badge.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Badge.class) })
    @RequestMapping(value = "/user/{userId}/badges",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Badge>> findUserBadges(
@ApiParam(value = "ID of user",required=true ) @PathVariable("userId") Long userId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Returns a user's pointScales", notes = "Returns a user's pointScales ", response = PointScale.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScales response", response = PointScale.class),
        @ApiResponse(code = 200, message = "unexpected error", response = PointScale.class) })
    @RequestMapping(value = "/user/{userId}/pointScales",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<PointScale>> findUserPointScales(
@ApiParam(value = "ID of user",required=true ) @PathVariable("userId") Long userId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);

}
