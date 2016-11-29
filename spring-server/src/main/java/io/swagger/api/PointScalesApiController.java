package io.swagger.api;

import io.swagger.model.PointScale;
import io.swagger.model.ErrorModel;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T21:16:10.066Z")

@Controller
public class PointScalesApiController implements PointScalesApi {

    public ResponseEntity<PointScale> addPointScale(@ApiParam(value = "pointScale object to add to the store" ,required=true ) @RequestBody PointScale pointScale,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<PointScale>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "Id of the pointScale that needs to be deleted",required=true ) @PathVariable("pointScaleId") Long pointScaleId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<PointScale> findPointScale(@ApiParam(value = "ID of pointScale to fetch",required=true ) @PathVariable("pointScaleId") Long pointScaleId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<PointScale>(HttpStatus.OK);
    }

    public ResponseEntity<List<PointScale>> findPointScales(@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<List<PointScale>>(HttpStatus.OK);
    }

    public ResponseEntity<PointScale> updatePointScale(@ApiParam(value = "pointScale object to add to the store" ,required=true ) @RequestBody PointScale pointScale,
        @ApiParam(value = "Id of the pointScale that needs to be updated",required=true ) @PathVariable("pointScaleId") Long pointScaleId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<PointScale>(HttpStatus.OK);
    }

}
