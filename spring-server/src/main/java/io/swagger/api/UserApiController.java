package io.swagger.api;

import io.swagger.model.ErrorModel;
import io.swagger.model.Badge;
import io.swagger.model.PointScale;

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
public class UserApiController implements UserApi {

    public ResponseEntity<List<Badge>> findUserBadges(@ApiParam(value = "ID of user",required=true ) @PathVariable("userId") Long userId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<List<Badge>>(HttpStatus.OK);
    }

    public ResponseEntity<List<PointScale>> findUserPointScales(@ApiParam(value = "ID of user",required=true ) @PathVariable("userId") Long userId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<List<PointScale>>(HttpStatus.OK);
    }

}
