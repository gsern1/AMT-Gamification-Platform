package ch.heigvd.gamification.api;

import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.api.dto.Application;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lux on 07.12.16.
 */
@RestController
public class ApplicationEndpoint implements ApplicationApi {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Void> addApplication(@ApiParam(value = "application object to add to the platform", required = true) @RequestBody Application application) {

        return null;
    }

    @Override
    public ResponseEntity<Void> deleteApplication(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }
}
