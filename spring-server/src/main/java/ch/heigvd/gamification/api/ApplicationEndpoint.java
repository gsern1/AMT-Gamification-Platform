package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.PointScale;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lux on 07.12.16.
 */
@RestController
public class ApplicationEndpoint implements ApplicationApi {
    @Override
    public ResponseEntity<Void> addApplication(@ApiParam(value = "application object to add to the platform", required = true) @RequestBody Application application) {
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteApplication(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<PointScale> loginApplication(@ApiParam(value = "application object to add to the store", required = true) @RequestBody Application application) {
        return new ResponseEntity<PointScale>(HttpStatus.ACCEPTED);
    }
}
