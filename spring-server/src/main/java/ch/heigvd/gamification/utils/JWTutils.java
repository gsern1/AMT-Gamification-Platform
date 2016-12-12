package ch.heigvd.gamification.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.io.UnsupportedEncodingException;

/**
 * Created by lux on 12.12.16.
 */
public class JWTutils {

    public static String createToken(String name)
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

    public static String getAppNameInToken(String token)
    {
        String name = null;
        try {
            JWT jwt = JWT.decode(token);
            name = jwt.getClaim("name").asString();
        } catch (JWTDecodeException exception){ //Invalid token
            return null;
        }
        return name;
    }
}
