package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.Badge;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

/**
 * Created by guillaume on 18.12.16.
 */
public class SharedSteps {

    private SharedData world;

    public SharedSteps(SharedData world){
        this.world = world;
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, world.getStatusCode());

    }

    @When("^I perform a \"([^\"]*)\" on \"([^\"]*)\" endpoint with a wrong payload that return \"([^\"]*)\"$")
    public void iPerformAOnEndpointWithAWrongPayloadThatReturn(String action, String endpoint, String returnType) throws Throwable {
        class WrongDTO{
            private int a;
            private boolean b;
            private char c;
        }
        try{
            world.setResponse(world.getApi().callWithParams(action,endpoint, new WrongDTO(),world.getToken().getToken(),returnType));
            world.setStatusCode(world.getResponse().getStatusCode());
        }
        catch (ApiException e){
            world.setStatusCode(e.getCode());
        }
    }

    @When("^I perform a \"([^\"]*)\" on \"([^\"]*)\" endpoint with an empty payload tha return \"([^\"]*)\"$")
    public void iPerformAOnEndpointWithAnEmptyPayloadThaReturn(String action, String endpoint, String returnType) throws Throwable {
        try{
            world.setResponse(world.getApi().callWithParams(action,endpoint, null,world.getToken().getToken(),returnType));
            world.setStatusCode(world.getResponse().getStatusCode());
        }
        catch (ApiException e){
            world.setStatusCode(e.getCode());
        }
    }

    @And("^I perform a \"([^\"]*)\" on \"([^\"]*)\" endpoint with id that return \"([^\"]*)\"$")
    public void iPerformAOnEndpointWithIdThatReturn(String action, String endpoint, String returnType) throws Throwable {
        try{
            world.setResponse(world.getApi().callWithParams(action,endpoint+ "/" + world.getBadgeNbr(), null,world.getToken().getToken(),returnType));
            world.setStatusCode(world.getResponse().getStatusCode());
        }
        catch (ApiException e){
            world.setStatusCode(e.getCode());
        }
    }
}



