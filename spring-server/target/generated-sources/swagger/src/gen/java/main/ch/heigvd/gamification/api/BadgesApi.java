package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.ErrorModel;
import ch.heigvd.gamification.api.dto.Badge;

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

@Api(value = "badges", description = "the badges API")
public interface BadgesApi {

    @ApiOperation(value = "Creates a new badge", notes = "Creates a new badge ", response = Badge.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badge response", response = Badge.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Badge.class) })
    @RequestMapping(value = "/badges",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Badge> addBadge(

@ApiParam(value = "Badge object to add to the store" ,required=true ) @RequestBody Badge badge

,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Delete an existing badge", notes = "Delete an existing badge ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "badge deleted successfully", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid Id supplied", response = Void.class),
        @ApiResponse(code = 404, message = "Badge not found", response = Void.class) })
    @RequestMapping(value = "/badges/{badgeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteBadge(
@ApiParam(value = "Id of the badge that needs to be deleted",required=true ) @PathVariable("badgeId") Long badgeId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Returns a single badge", notes = "Returns a single badge ", response = Badge.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badge response", response = Badge.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Badge.class) })
    @RequestMapping(value = "/badges/{badgeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Badge> findBadge(
@ApiParam(value = "ID of badge to fetch",required=true ) @PathVariable("badgeId") Long badgeId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Returns every badges", notes = "Returns every badges ", response = Badge.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badges response", response = Badge.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Badge.class) })
    @RequestMapping(value = "/badges",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Badge>> findBadges(
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Update an existing badge", notes = "Update an existing badge ", response = Badge.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badge response", response = Badge.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Badge.class) })
    @RequestMapping(value = "/badges/{badgeId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Badge> updateBadge(

@ApiParam(value = "Badge object to add to the store" ,required=true ) @RequestBody Badge badge

,
@ApiParam(value = "Id of the badge that needs to be updated",required=true ) @PathVariable("badgeId") Long badgeId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);

}
