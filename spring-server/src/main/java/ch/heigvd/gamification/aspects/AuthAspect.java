package ch.heigvd.gamification.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by lux on 24.01.17.
 */
@Aspect
@Component
public class AuthAspect {

    @Before("execution(* com.gkatzioura.spring.ao (java.lang.String)) && args(sampleName)")
    public void beforeSampleCreation(String sampleName) {

        System.out.println("A request was issued for a sample name: "+sampleName);
    }
}
