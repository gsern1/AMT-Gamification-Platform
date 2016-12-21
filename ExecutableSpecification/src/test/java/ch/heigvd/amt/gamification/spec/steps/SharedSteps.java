package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.Credentials;
import ch.heigvd.gamification.api.dto.Token;
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
    private Token token;

    private final DefaultApi api = new DefaultApi();


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

    @Given("^a token for a new gamified application and its credentials$")
    public void aTokenForANewGamifiedApplicationAndItsCredentials() throws Throwable {
       world.setApplication(new Application());

        String randomAppName = "random-app-1-" + System.currentTimeMillis();
        world.getApplication().setName(randomAppName);

        String password = "56789";
        world.getApplication().setPassword(password);

        Credentials credentials =new Credentials();
        credentials.setName(randomAppName);
        credentials.setPassword(password);
        world.setCredentials(credentials);


        api.addApplicationWithHttpInfo(world.getApplication());

        ApiResponse<Token> response = api.loginApplicationWithHttpInfo(world.getCredentials());
        token = response.getData();
        assertEquals(200,response.getStatusCode());

        world.setToken(token);

    }
}



