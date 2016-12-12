package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Credentials;
import ch.heigvd.gamification.api.dto.Token;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.services.TokenManager;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by antoi on 11.12.2016.
 */
@RestController
public class AuthEndpoint implements AuthApi{
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private TokenManager tokenManager;

    @Override
    public ResponseEntity<Token> loginApplication(@ApiParam(value = "application object to add to the store", required = true) @RequestBody Credentials credentials) {

        ch.heigvd.gamification.database.model.Application application = applicationRepository.findByName(credentials.getName());

        if(application == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(!application.getPassword().equals(credentials.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Token token = new Token();
        token.setToken(tokenManager.createToken(application.getName()));

        return new ResponseEntity<>(token, HttpStatus.OK);
    }


}
