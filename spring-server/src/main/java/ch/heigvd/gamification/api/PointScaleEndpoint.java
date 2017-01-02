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
 * Created by lux on 30.11.16.
 */
@RestController
public class PointScaleEndpoint implements PointScalesApi {
    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Void> addPointScale(@ApiParam(value = "pointScale object to add to the store", required = true) @RequestBody PointScale pointScale, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);

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

    @Override
    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "Id of the pointScale that needs to be deleted", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Application application = applicationRepository.findByName(name);
        try {
            pointScaleRepository.delete(pointScaleRepository.findByIdAndApplication(pointScaleId, application));
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PointScale> findPointScale(@ApiParam(value = "ID of pointScale to fetch", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);
        ch.heigvd.gamification.database.model.PointScale pointScale = pointScaleRepository.findByIdAndApplication(pointScaleId, application);
        PointScale pointScaleDto = new PointScale();
        pointScaleDto.setName(pointScale.getName());
        return new ResponseEntity<>(pointScaleDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PointScaleWithLocation>> findPointScales(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        List<PointScaleWithLocation> pointScales = new ArrayList<>();
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);
        for(ch.heigvd.gamification.database.model.PointScale pointScale : pointScaleRepository.findByApplication(application)){
            PointScaleWithLocation pointScaleDto = new PointScaleWithLocation();
            pointScaleDto.setName(pointScale.getName());
            pointScaleDto.setLocation("/pointscales/" + pointScale.getId());
            pointScales.add(pointScaleDto);
        }
        return ResponseEntity.ok(pointScales);
    }

    @Override
    public ResponseEntity<Void> updatePointScale(@ApiParam(value = "pointScale object to add to the store", required = true) @RequestBody PointScale pointScale, @ApiParam(value = "Id of the pointScale that needs to be updated", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Application application = applicationRepository.findByName(name);
        ch.heigvd.gamification.database.model.PointScale updatePointScale = pointScaleRepository.findByIdAndApplication(pointScaleId, application);
        updatePointScale.setName(pointScale.getName());
        pointScaleRepository.save(updatePointScale);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
