package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.Token;
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

    private SharedData world;

    private Application application;

    public ApplicationSteps(SharedData world){
        this.world = world;
    }

    @Given("^I have an application payload$")
    public void i_have_an_application_payload() throws Throwable {
        application = new Application();
        String randomAppName = "random-app-tata" + (applicationsCounter)+ "-" + System.currentTimeMillis();
        application.setName(randomAppName);
        application.setPassword("12345");
    }

    @When("^I POST it to the /application endpoint$")
    public void i_POST_it_to_the_registrations_endpoint() throws Throwable {
        try{
            ApiResponse response = api.addApplicationWithHttpInfo(application);
            world.setStatusCode(response.getStatusCode());

        }catch(ApiException e){
            world.setStatusCode(e.getCode());

        }
    }


    @When("^I DELETE that application using that token$")
    public void iDELETEThatApplicationUsingThatToken() throws Throwable {
        try{
            ApiResponse response = api.deleteApplicationWithHttpInfo(world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());

        }catch(ApiException e){
            world.setStatusCode(e.getCode());

        }
    }
}
