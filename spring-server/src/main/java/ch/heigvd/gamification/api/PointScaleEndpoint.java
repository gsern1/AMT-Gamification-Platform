package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.PointScale;
import ch.heigvd.gamification.api.dto.PointScaleWithLocation;
import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.PointScaleRepository;
import ch.heigvd.gamification.database.dao.UserRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * PointScales endoint. Implements CRUD actions for this endpoint.
 */
@RestController
public class PointScaleEndpoint implements PointScalesApi {
    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    /**
     * Add a pointScale to your application.
     *
     * @param pointScale: the pointScale to be added.
     * @param token: the token.
     * @return
     *      403 if your token is invalid.
     *      422 if the pointScale name is null or empty
     *      201 otherwise
     */
    @Override
    public ResponseEntity<Void> addPointScale(@ApiParam(value = "pointScale object to add to the store", required = true) @RequestBody PointScale pointScale, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(pointScale.getName() == null){
            return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        ch.heigvd.gamification.database.model.PointScale newPointScale = new ch.heigvd.gamification.database.model.PointScale();
        newPointScale.setName(pointScale.getName());
        newPointScale.setApplication(application);
        try{
            pointScaleRepository.save(newPointScale);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/pointScales/" + newPointScale.getId());
            return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).build();
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    /**
     * Delete this pointScale
     *
     * @param pointScaleId: the id of the pointScale to be deleted.
     * @param token: the token
     *
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this pointScale.
     *      422 if the database fails to delete it.
     *      204 otherwise
     */
    @Override
    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "Id of the pointScale that needs to be deleted", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            ch.heigvd.gamification.database.model.PointScale pointScale = pointScaleRepository.findByIdAndApplication(pointScaleId, application);

            if(pointScale == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            pointScaleRepository.delete(pointScale);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * find a pointScale with the given pointScale id.
     *
     * @param pointScaleId: the id of the pointScale to be retrieved
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this pointScale.
     *      401 if you don't own this pointScale.
     *      422 if the database fails to read it.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<PointScale> findPointScale(@ApiParam(value = "ID of pointScale to fetch", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.PointScale pointScale = pointScaleRepository.findByIdAndApplication(pointScaleId, application);
        if(pointScale == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PointScale pointScaleDto = new PointScale();
        pointScaleDto.setName(pointScale.getName());
        return new ResponseEntity<>(pointScaleDto, HttpStatus.OK);
    }

    /**
     * Get a list of the pointScales of this application.
     *
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<List<PointScaleWithLocation>> findPointScales(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<PointScaleWithLocation> pointScales = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        for(ch.heigvd.gamification.database.model.PointScale pointScale : pointScaleRepository.findByApplication(application)){
            PointScaleWithLocation pointScaleDto = new PointScaleWithLocation();
            pointScaleDto.setName(pointScale.getName());
            pointScaleDto.setLocation("/pointscales/" + pointScale.getId());
            pointScales.add(pointScaleDto);
        }
        return ResponseEntity.ok(pointScales);
    }

    /**
     * Updates this pointScale.
     *
     * @param pointScale: the new pointScale
     * @param pointScaleId: the pointScale to be updated
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this pointScale.
     *      401 if you don't own this pointScale.
     *      422 if the database fails to update it.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<Void> updatePointScale(@ApiParam(value = "pointScale object to add to the store", required = true) @RequestBody PointScale pointScale, @ApiParam(value = "Id of the pointScale that needs to be updated", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.PointScale updatePointScale = pointScaleRepository.findByIdAndApplication(pointScaleId, application);
        if(updatePointScale == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatePointScale.setName(pointScale.getName());
        pointScaleRepository.save(updatePointScale);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
