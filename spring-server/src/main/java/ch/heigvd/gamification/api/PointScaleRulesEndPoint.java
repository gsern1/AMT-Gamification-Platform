package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.PointScaleRule;
import ch.heigvd.gamification.api.dto.PointScaleRuleWithLocation;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.PointScaleRuleRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PointScaleRulesEndPoint implements PointScaleRulesApi {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PointScaleRuleRepository pointScaleRuleRepository;

    public ResponseEntity<Void> addPointScaleRule(
            @ApiParam(value = "pointScaleRule object to add to the store" ,required=true ) @RequestBody PointScaleRule pointScaleRule,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();




        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePointScaleRule(
            @ApiParam(value = "Id of the pointScaleRule that needs to be deleted",required=true ) @PathVariable("pointScaleRuleId") Long pointScaleRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<PointScaleRule> findPointScaleRule(
            @ApiParam(value = "ID of pointScaleRule to fetch",required=true ) @PathVariable("pointScaleRuleId") Long pointScaleRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<PointScaleRule>(HttpStatus.OK);
    }

    public ResponseEntity<List<PointScaleRuleWithLocation>> findPointScaleRules(
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<List<PointScaleRuleWithLocation>>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updatePointScaleRule(
            @ApiParam(value = "pointScaleRule object to add to the store" ,required=true ) @RequestBody PointScaleRule pointScaleRule,
            @ApiParam(value = "Id of the pointScaleRule that needs to be updated",required=true ) @PathVariable("pointScaleRuleId") Long pointScaleRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
