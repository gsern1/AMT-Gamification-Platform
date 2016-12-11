package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.PointScale;
import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.database.dao.PointScaleRepository;
import ch.heigvd.gamification.database.dao.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lux on 30.11.16.
 */
@RestController
public class PointScaleEndpoint implements PointScalesApi {
    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Void> addPointScale(@ApiParam(value = "pointScale object to add to the store", required = true) @RequestBody PointScale pointScale, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "Id of the pointScale that needs to be deleted", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<PointScale> findPointScale(@ApiParam(value = "ID of pointScale to fetch", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<List<PointScale>> findPointScales(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updatePointScale(@ApiParam(value = "pointScale object to add to the store", required = true) @RequestBody PointScale pointScale, @ApiParam(value = "Id of the pointScale that needs to be updated", required = true) @PathVariable("pointScaleId") Long pointScaleId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }
}
