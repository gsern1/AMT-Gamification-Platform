package ch.heigvd.gamification.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by antoi on 25.01.2017.
 */
public class JWTConfigReader
{
    public static void readProperties() throws Exception
    {
        Properties properties = new Properties();

        InputStream fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("json_web_token.properties");
        properties.load(fileInputStream);

        JWTConfig.secret = properties.getProperty("secret");

        fileInputStream.close();
    }
}
