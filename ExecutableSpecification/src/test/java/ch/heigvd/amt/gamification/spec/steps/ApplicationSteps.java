package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Application;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class ApplicationSteps {
    
    private final DefaultApi api = new DefaultApi();
    
    private int statusCode;
    private Application application;

    @Given("^I have an application payload$")
    public void i_have_an_application_payload() throws Throwable {
        application = new Application();
        application.setName("MyApp");
        application.setPassword("12345");
    }

    @When("^I POST it to the /registrations endpoint$")
    public void i_POST_it_to_the_registrations_endpoint() throws Throwable {
        api.addApplication(application);
        ApiResponse response = api.addApplicationWithHttpInfo(application);
        statusCode = response.getStatusCode();
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
    assertEquals(arg1, statusCode);

    }
}
