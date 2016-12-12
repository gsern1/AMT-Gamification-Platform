package ch.heigvd.gamification.api;

import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.html.HTMLTableCaptionElement;

/**
 * Created by lux on 07.12.16.
 */
@RestController
public class ApplicationEndpoint implements ApplicationApi {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Void> addApplication(@ApiParam(value = "application object to add to the platform", required = true) @RequestBody Application application) {
        ch.heigvd.gamification.database.model.Application newApplication = new ch.heigvd.gamification.database.model.Application();
        newApplication.setName(application.getName());
        newApplication.setPassword(application.getPassword());
        try{
            applicationRepository.save(newApplication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteApplication(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        applicationRepository.delete(applicationRepository.findByName(name));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
