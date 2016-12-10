package ch.heigvd.gamification.api;

import ch.heigvd.gamification.BDD.ApplicationRepository;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.PointScale;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

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

    @Override
    public ResponseEntity<PointScale> loginApplication(@ApiParam(value = "application object to add to the store", required = true) @RequestBody Application application) {
        long id = application.getId();
        //TODO CHANGER LE INT EN LONG DANS LA DB
        ch.heigvd.gamification.BDD.Entities.Application DBapp = applicationRepository.findOne((int)id);

        if(DBapp == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(!DBapp.getName().equals(application.getName()) || !DBapp.getPassword().equals(application.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        PointScale tmp = new PointScale();
        tmp.setName(application.getName());
        tmp.setApp(application.getId());
        tmp.setDescription("NON IMPLEMENTE");
        tmp.setToken(createToken((int)id));
        tmp.setUserId((long)-1);

        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    private String createToken(int id)
    {
        String token;
        try {
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", id) //on passe l'id de l'app dans le Json web Token
                    //TODO CONFIGURER LE SECRET EN UNE CLE RSA
                    .sign(Algorithm.HMAC256("secret"));
        } catch (JWTCreationException exception){
            throw new RuntimeException("You need to enable Algorithm.HMAC256");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return token;
    }
}
