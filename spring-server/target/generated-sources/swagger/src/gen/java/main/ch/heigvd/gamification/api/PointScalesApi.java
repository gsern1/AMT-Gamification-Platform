package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.PointScale;
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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-10T13:17:26.974+01:00")

@Api(value = "pointScales", description = "the pointScales API")
public interface PointScalesApi {

    @ApiOperation(value = "Creates a new pointScale", notes = "Creates a new pointScale ", response = PointScale.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScale response", response = PointScale.class),
        @ApiResponse(code = 200, message = "unexpected error", response = PointScale.class) })
    @RequestMapping(value = "/pointScales",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PointScale> addPointScale(

@ApiParam(value = "pointScale object to add to the store" ,required=true ) @RequestBody PointScale pointScale

,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Delete an existing pointScale", notes = "Delete an existing pointScale ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "pointScale deleted successfully", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid Id supplied", response = Void.class),
        @ApiResponse(code = 404, message = "pointScale not found", response = Void.class) })
    @RequestMapping(value = "/pointScales/{pointScaleId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePointScale(
@ApiParam(value = "Id of the pointScale that needs to be deleted",required=true ) @PathVariable("pointScaleId") Long pointScaleId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Returns a single pointScale", notes = "Returns a single pointScale ", response = PointScale.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScale response", response = PointScale.class),
        @ApiResponse(code = 200, message = "unexpected error", response = PointScale.class) })
    @RequestMapping(value = "/pointScales/{pointScaleId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PointScale> findPointScale(
@ApiParam(value = "ID of pointScale to fetch",required=true ) @PathVariable("pointScaleId") Long pointScaleId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Returns every pointScales", notes = "Returns every pointScales ", response = PointScale.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScales response", response = PointScale.class),
        @ApiResponse(code = 200, message = "unexpected error", response = PointScale.class) })
    @RequestMapping(value = "/pointScales",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<PointScale>> findPointScales(
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);


    @ApiOperation(value = "Update an existing pointScale", notes = "Update an existing pointScale ", response = PointScale.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScale response", response = PointScale.class),
        @ApiResponse(code = 200, message = "unexpected error", response = PointScale.class) })
    @RequestMapping(value = "/pointScales/{pointScaleId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<PointScale> updatePointScale(

@ApiParam(value = "pointScale object to add to the store" ,required=true ) @RequestBody PointScale pointScale

,
@ApiParam(value = "Id of the pointScale that needs to be updated",required=true ) @PathVariable("pointScaleId") Long pointScaleId


,
@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token


);

}
