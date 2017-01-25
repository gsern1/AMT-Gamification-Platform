package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.BadgeRule;
import ch.heigvd.gamification.api.dto.BadgeRuleWithLocation;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.BadgeRepository;
import ch.heigvd.gamification.database.dao.BadgeRuleRepository;
import ch.heigvd.gamification.database.dao.PointScaleRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.Badge;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * BadgeRule endoint. Implements CRUD actions for this endpoint.
 */
@RestController
public class BadgeRulesEndPoint implements BadgeRulesApi {

    @Autowired
    BadgeRuleRepository badgeRuleRepository;
    
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    /**
     * Add a badge rule to this endpoint.
     *
     * @param badgeRule: the badge rule
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      422 if the badgeRule type name is null or empty
     *      201 otherwise
     */
    public ResponseEntity<Void> addBadgeRule(
            @ApiParam(value = "badgeRule object to add to the store" ,required=true ) @RequestBody BadgeRule badgeRule,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(badgeRule == null || badgeRule.getType() == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PointScale pointScale = null;
        if(badgeRule.getPointScale() != null) {
            pointScale = pointScaleRepository.findByIdAndApplication(badgeRule.getPointScale(), application);
        }

        Badge badge = badgeRepository.findOne(badgeRule.getBadge());
        if(badge == null || badgeRule.getType() == null){
            return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        ch.heigvd.gamification.database.model.BadgeRule newBadgeRule = new ch.heigvd.gamification.database.model.BadgeRule();
        try{
            newBadgeRule.setThreshold(badgeRule.getThreshold());
            newBadgeRule.setApplication(application);
            newBadgeRule.setType(badgeRule.getType());
            newBadgeRule.setPointscale(pointScale);
            newBadgeRule.setBadge(badge);
            try {
                badgeRuleRepository.save(newBadgeRule);
            } catch (DataIntegrityViolationException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/badgeRules/" + newBadgeRule.getId());
            return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<Void> deleteBadgeRule(
            @ApiParam(value = "Id of the badgeRule that needs to be deleted",required=true ) @PathVariable("badgeRuleId") Long badgeRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.BadgeRule badgeRule = badgeRuleRepository.findOne(badgeRuleId);
        if(badgeRule == null)
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(badgeRule.getApplication().getId() != application.getId())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try{
            badgeRuleRepository.delete(badgeRule);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<BadgeRule> findBadgeRule(
            @ApiParam(value = "ID of badgeRule to fetch",required=true ) @PathVariable("badgeRuleId") Long badgeRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try{
            // TODO : FIND BY APPLICATION NAME !
            ch.heigvd.gamification.database.model.BadgeRule badgeRuleModel = badgeRuleRepository.findOne(badgeRuleId);
            if(badgeRuleModel == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            BadgeRule badgeRule = new BadgeRule();
            // TODO Mettre en long dans le model
            badgeRule.setBadge((long)badgeRuleModel.getBadge().getId());
            badgeRule.setType(badgeRuleModel.getType());
            if(badgeRuleModel.getPointscale() != null) {
                badgeRule.setPointScale(badgeRuleModel.getPointscale().getId());
            }
            badgeRule.setThreshold(badgeRuleModel.getThreshold());
            return new ResponseEntity<>(badgeRule, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<List<BadgeRuleWithLocation>> findBadgeRules(
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<ch.heigvd.gamification.database.model.BadgeRule> list = badgeRuleRepository.findByApplication(application);
        List<BadgeRuleWithLocation> toReturn = new LinkedList<>();
        Iterator i = list.iterator();
        while(i.hasNext())
        {
            BadgeRuleWithLocation tmp = new BadgeRuleWithLocation();
            ch.heigvd.gamification.database.model.BadgeRule b = (ch.heigvd.gamification.database.model.BadgeRule)i.next();

            tmp.setBadge((long)b.getBadge().getId());
            tmp.setType(b.getType());
            if(b.getPointscale() != null){
                tmp.setPointScale(b.getPointscale().getId());
            }
            tmp.setThreshold(b.getThreshold());
            tmp.setLocation("/badgeRules/" + b.getId());
            toReturn.add(tmp);
        }
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateBadgeRule(
            @ApiParam(value = "badgeRule object to add to the store" ,required=true ) @RequestBody BadgeRule badgeRule,
            @ApiParam(value = "Id of the badgeRule that needs to be updated",required=true ) @PathVariable("badgeRuleId") Long badgeRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        PointScale pointScale = pointScaleRepository.findByIdAndApplication(badgeRule.getPointScale(), application);

        Badge badge = badgeRepository.findOne(badgeRule.getBadge());
        if(badge == null){
            return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        long tmp = badgeRuleId;
        try {
            // TODO : UPDATE BY APPLICATION NAME !
            ch.heigvd.gamification.database.model.BadgeRule badgeRuleModel = badgeRuleRepository.findOne(tmp);
            if (badgeRuleModel == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            badgeRuleModel.setThreshold(badgeRule.getThreshold());
            badgeRuleModel.setType(badgeRule.getType());
            badgeRuleModel.setPointscale(pointScale);
            badgeRuleModel.setBadge(badge);

            try {
                badgeRuleRepository.save(badgeRuleModel);
            } catch (DataIntegrityViolationException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
