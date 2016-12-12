package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dto.Token;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.model.Application;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by antoi on 12/12/2016.
 */
@Service
public class TokenManager {
    @Autowired
    ApplicationRepository applicationRepository;

    public String createToken(String name)
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

    public Application getApplication(String token){
        String applicationName = JWT.decode(token).toString();
        return applicationRepository.findByName(applicationName);
    }
}
