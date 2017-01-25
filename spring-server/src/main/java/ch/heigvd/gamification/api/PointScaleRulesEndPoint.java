package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.PointScaleRule;
import ch.heigvd.gamification.api.dto.PointScaleRuleWithLocation;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.PointScaleRepository;
import ch.heigvd.gamification.database.dao.PointScaleRuleRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.utils.JWTutils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * PointScaleRules endoint. Implements CRUD actions for this endpoint.
 */
@RestController
public class PointScaleRulesEndPoint implements PointScaleRulesApi {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PointScaleRuleRepository pointScaleRuleRepository;

    @Autowired
    private PointScaleRepository pointScaleRepository;

    /**
     * Add a pointScaleRule to your application.
     *
     * @param pointScaleRule: the pointScaleRule to be added.
     * @param token: the token.
     * @return
     *      403 if your token is invalid.
     *      422 if the pointScaleRule name is null or empty
     *      201 otherwise
     */
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

        PointScale pointScale = pointScaleRepository.findOne(pointScaleRule.getPointScale());
        if(null == pointScale)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if(pointScaleRule.getType().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        ch.heigvd.gamification.database.model.PointScaleRule pointScaleRuleDB
                = new ch.heigvd.gamification.database.model.PointScaleRule(
                        app,
                        pointScaleRule.getType(),
                        pointScale,
                        pointScaleRule.getIncrement());

        try {
            pointScaleRuleRepository.save(pointScaleRuleDB);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "/pointScaleRules/" + pointScaleRuleDB.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).build();
    }

    /**
     * Delete this pointScaleRule
     *
     * @param pointScaleRuleId: the id of the pointScaleRule to be deleted.
     * @param token: the token
     *
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this pointScaleRule.
     *      422 if the database fails to delete it.
     *      204 otherwise
     */
    public ResponseEntity<Void> deletePointScaleRule(
            @ApiParam(value = "Id of the pointScaleRule that needs to be deleted",required=true ) @PathVariable("pointScaleRuleId") Long pointScaleRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        ch.heigvd.gamification.database.model.PointScaleRule pointScaleRuleDB = pointScaleRuleRepository.findOne(pointScaleRuleId);
        if(pointScaleRuleDB == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if(pointScaleRuleDB.getApplication().getId() != app.getId())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try{
            pointScaleRuleRepository.delete(pointScaleRuleDB);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * find a pointScaleRule with the given pointScaleRule id.
     *
     * @param pointScaleRuleId: the id of the pointScaleRule to be retrieved
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this pointScaleRule.
     *      401 if you don't own this pointScaleRule.
     *      422 if the database fails to read it.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<PointScaleRule> findPointScaleRule(@ApiParam(value = "ID of pointScaleRule to fetch", required = true) @PathVariable("pointScaleRuleId") Long pointScaleRuleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ch.heigvd.gamification.database.model.PointScaleRule pointScaleRule = pointScaleRuleRepository.findOne(pointScaleRuleId);
        if(pointScaleRule == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(app.getId() != pointScaleRule.getApplication().getId())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        PointScaleRule ps = new PointScaleRule();
        ps.setType(pointScaleRule.getType());
        ps.setPointScale(pointScaleRule.getPointscale().getId());
        ps.setIncrement(pointScaleRule.getIncrement());

        return new ResponseEntity<PointScaleRule>(ps, HttpStatus.OK);
    }

    /**
     * Get a list of the pointScaleRules of this application.
     *
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      200 otherwise
     */
    public ResponseEntity<List<PointScaleRuleWithLocation>> findPointScaleRules(
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<PointScaleRuleWithLocation> pointScaleRulesDTO = new LinkedList<>();
        for(ch.heigvd.gamification.database.model.PointScaleRule ps :  pointScaleRuleRepository.findByApplication(app))
        {
            PointScaleRuleWithLocation tmp = new PointScaleRuleWithLocation();
            tmp.setIncrement(ps.getIncrement());
            tmp.setPointScale(ps.getPointscale().getId());
            tmp.setType(ps.getType());
            tmp.setLocation("/pointScaleRules/"+ps.getId());
            pointScaleRulesDTO.add(tmp);
        }

        return new ResponseEntity<List<PointScaleRuleWithLocation>>(pointScaleRulesDTO, HttpStatus.OK);
    }

    /**
     * Updates this pointScaleRule.
     *
     * @param pointScaleRule: the new pointScaleRule
     * @param pointScaleRuleId: the pointScaleRule to be updated
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this pointScaleRule.
     *      401 if you don't own this pointScaleRule.
     *      422 if the database fails to update it.
     *      200 otherwise
     */
    public ResponseEntity<Void> updatePointScaleRule(
            @ApiParam(value = "pointScaleRule object to add to the store" ,required=true ) @RequestBody PointScaleRule pointScaleRule,
            @ApiParam(value = "Id of the pointScaleRule that needs to be updated",required=true ) @PathVariable("pointScaleRuleId") Long pointScaleRuleId,
            @ApiParam(value = "token to be passed as a header" ,required=true ) @RequestHeader(value="token", required=true) String token
    ) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Application app = applicationRepository.findByName(name);
        if(null == app)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        ch.heigvd.gamification.database.model.PointScaleRule pointScaleRuleDB = pointScaleRuleRepository.findOne(pointScaleRuleId);
        if(pointScaleRuleDB == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if(pointScaleRuleDB.getApplication().getId() != app.getId())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        PointScale pointScale = pointScaleRepository.findOne(pointScaleRule.getPointScale());
        if(pointScale==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        pointScaleRuleDB.setType(pointScaleRule.getType());
        pointScaleRuleDB.setIncrement(pointScaleRule.getIncrement());
        pointScaleRuleDB.setPointscale(pointScale);

        try {
            pointScaleRuleRepository.save(pointScaleRuleDB);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
