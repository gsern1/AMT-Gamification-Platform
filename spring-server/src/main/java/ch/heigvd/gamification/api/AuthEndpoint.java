package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Credentials;
import ch.heigvd.gamification.api.dto.Token;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
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

    @Override
    public ResponseEntity<Token> loginApplication(@ApiParam(value = "application object to add to the store", required = true) @RequestBody Credentials credentials) {

        ch.heigvd.gamification.database.model.Application application = applicationRepository.findByName(credentials.getName());

        if(application == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(!application.getPassword().equals(credentials.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Token token = new Token();
        token.setToken(createToken(application.getName()));

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private String createToken(String name)
    {
        String token;
        try {
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("name", name) //on passe le nom de l'app dans le Json web Token
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
