package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.BadgeRule;
import ch.heigvd.gamification.api.dto.BadgeRuleWithLocation;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
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
public class BadgeRulesEndPoint implements BadgeRulesApi {

    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<Void> addBadgeRule(
            @ApiParam(value = "badgeRule object to add to the store" ,required=true ) @RequestBody BadgeRule badgeRule,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBadgeRule(
            @ApiParam(value = "Id of the badgeRule that needs to be deleted",required=true ) @PathVariable("badgeRuleId") Long badgeRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<BadgeRule> findBadgeRule(
            @ApiParam(value = "ID of badgeRule to fetch",required=true ) @PathVariable("badgeRuleId") Long badgeRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<BadgeRule>(HttpStatus.OK);
    }

    public ResponseEntity<List<BadgeRuleWithLocation>> findBadgeRules(
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        // do some magic!
        return new ResponseEntity<List<BadgeRuleWithLocation>>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateBadgeRule(
            @ApiParam(value = "badgeRule object to add to the store" ,required=true ) @RequestBody BadgeRule badgeRule,
            @ApiParam(value = "Id of the badgeRule that needs to be updated",required=true ) @PathVariable("badgeRuleId") Long badgeRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}