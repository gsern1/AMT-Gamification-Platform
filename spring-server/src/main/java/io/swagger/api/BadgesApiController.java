package io.swagger.api;

import io.swagger.model.ErrorModel;
import io.swagger.model.Badge;

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
public class BadgesApiController implements BadgesApi {

    public ResponseEntity<Badge> addBadge(@ApiParam(value = "Badge object to add to the store" ,required=true ) @RequestBody Badge badge,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<Badge>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBadge(@ApiParam(value = "Id of the badge that needs to be deleted",required=true ) @PathVariable("badgeId") Long badgeId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Badge> findBadge(@ApiParam(value = "ID of badge to fetch",required=true ) @PathVariable("badgeId") Long badgeId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<Badge>(HttpStatus.OK);
    }

    public ResponseEntity<List<Badge>> findBadges(@ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<List<Badge>>(HttpStatus.OK);
    }

    public ResponseEntity<Badge> updateBadge(@ApiParam(value = "Badge object to add to the store" ,required=true ) @RequestBody Badge badge,
        @ApiParam(value = "Id of the badge that needs to be updated",required=true ) @PathVariable("badgeId") Long badgeId,
        @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<Badge>(HttpStatus.OK);
    }

}
