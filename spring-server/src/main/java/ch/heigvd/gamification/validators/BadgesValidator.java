package ch.heigvd.gamification.validators;

import ch.heigvd.gamification.api.dto.Badge;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by durza9390 on 21.12.2016.
 *
 * REFERENCE : http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html
 * http://www.leveluplunch.com/java/tutorials/017-validate-spring-rest-webservice-request/
 */
public class BadgesValidator implements Validator
{

    @Override
    public boolean supports(Class<?> aClass) {
        return Badge.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
    }
}
