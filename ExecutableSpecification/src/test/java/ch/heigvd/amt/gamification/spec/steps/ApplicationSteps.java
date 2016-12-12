package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
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

    private int applicationsCounter = 1;


    private int statusCode;
    private Application application;

    @Given("^I have an application payload$")
    public void i_have_an_application_payload() throws Throwable {
        application = new Application();
        String randomAppName = "random-app-" + (applicationsCounter)+ "-" + System.currentTimeMillis();
        application.setName(randomAppName);
        application.setPassword("12345");
    }

    @When("^I POST it to the /application endpoint$")
    public void i_POST_it_to_the_registrations_endpoint() throws Throwable {
        try{
            ApiResponse response = api.addApplicationWithHttpInfo(application);
            statusCode = response.getStatusCode();

        }catch(ApiException e){
            statusCode = e.getCode();

        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);

    }

    @When("^I ask for a list of registered apps with a GET on the /application endpoint$")
    public void i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_application_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I see my app in the list$")
    public void i_see_my_app_in_the_list() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I try something new$")
    public void iTrySomethingNew() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
