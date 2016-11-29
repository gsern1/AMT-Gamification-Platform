package io.swagger.api;

import io.swagger.model.Application;
import io.swagger.model.PointScale;
import io.swagger.model.ErrorModel;

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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T21:16:10.066Z")

@Api(value = "application", description = "the application API")
public interface ApplicationApi {

    @ApiOperation(value = "Post new application on the platform", notes = "Creates a new application ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "application response", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Void.class) })
    @RequestMapping(value = "/application",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addApplication(@ApiParam(value = "application object to add to the platform" ,required=true ) @RequestBody Application application);


    @ApiOperation(value = "Delete an existing application", notes = "Delete an existing application ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "application deleted successfully", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid Id supplied", response = Void.class),
        @ApiResponse(code = 404, message = "application not found", response = Void.class) })
    @RequestMapping(value = "/application",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteApplication(@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token);


    @ApiOperation(value = "Returns a the application token", notes = "Returns the application token ", response = PointScale.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScale response", response = PointScale.class),
        @ApiResponse(code = 200, message = "unexpected error", response = PointScale.class) })
    @RequestMapping(value = "/application",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PointScale> loginApplication(@ApiParam(value = "application object to add to the store" ,required=true ) @RequestBody Application application);

}
