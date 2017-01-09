package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.Credentials;
import ch.heigvd.gamification.api.dto.PointScale;
import ch.heigvd.gamification.api.dto.Token;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

/**
 * Created by guillaume on 12.12.16.
 */
public class AuthenticationSteps {

    private SharedData world;


    private final DefaultApi api = new DefaultApi();
    private Credentials credentials;
    Application application;
    private int statusCode;
    private Token token;
    private Token tokenBis;
    private PointScale pointScale;

    public AuthenticationSteps(SharedData world){
        this.world = world;
    }


    @Given("^a new gamified application and its credentials$")
    public void aNewGamifiedApplication() throws Throwable {

        application = new Application();
        String randomAppName = "random-app-1-" + System.currentTimeMillis();
        application.setName(randomAppName);

        String password = "56789";
        application.setPassword(password);

        world.setCredentials(new Credentials());
        world.getCredentials().setName(randomAppName);
        world.getCredentials().setPassword(password);

    }

    @When("^I POST the credentials to the /auth endpoint$")
    public void iPOSTTheCredentialsToTheAuthEndpoint() throws Throwable {
      try{

          ApiResponse<Token> response = api.loginApplicationWithHttpInfo(world.getCredentials());
          world.setToken(response.getData());
          world.setStatusCode(response.getStatusCode());

        }catch(ApiException e){
            world.setStatusCode(e.getCode());

        }

    }


    @Given("^I have an pointscale payload$")
    public void iHaveAnPointscalePayload() throws Throwable {
        pointScale = new PointScale();
        pointScale.setName("Assiduité");
    }

    @When("^I POST a pointscale for that application to the /pointScales endpoint with the recieved token$")
    public void iPOSTAPointscaleForThatApplicationToThePointScalesEndpointWithTheRecievedToken() throws Throwable {


        try{
            ApiResponse response = api.addPointScaleWithHttpInfo(pointScale,world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());

        }catch(ApiException e){
            world.setStatusCode(e.getCode());

        }
    }

    @Given("^some credentials with a wrong application name$")
    public void some_credentials_with_a_wrong_application_name() throws Throwable {
        world.getCredentials().setName("wrong-app-name");
    }

    @Given("^some credentials with an empty application name$")
    public void some_credentials_with_an_empty_application_name() throws Throwable {
        world.getCredentials().setName("");
    }

    @Given("^some credentials with a wrong password$")
    public void some_credentials_with_a_wrong_password() throws Throwable {
        world.getCredentials().setPassword("wrong-password");
    }

    @Given("^some credentials with an empty password$")
    public void some_credentials_with_an_empty_password() throws Throwable {
        world.getCredentials().setPassword("");
    }

}