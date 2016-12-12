package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.Credentials;
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
public class Authentication {

    private final DefaultApi api = new DefaultApi();
    private Credentials credentials;
    Application application;
    private int statusCode;
    private Token token;
    private Token tokenBis;

    @Given("^a new gamified application and its credentials$")
    public void aNewGamifiedApplication() throws Throwable {

        application = new Application();
        String randomAppName = "random-app-1-" + System.currentTimeMillis();
        application.setName(randomAppName);

        String password = "56789";
        application.setPassword(password);

        credentials = new Credentials();
        credentials.setName(randomAppName);
        credentials.setPassword(password);

    }

    @When("^I POST the credentials to the /auth endpoint$")
    public void iPOSTTheCredentialsToTheAuthEndpoint() throws Throwable {
      try{
            ApiResponse response = api.loginApplicationWithHttpInfo(credentials);
            token = new Token();
            token.setToken(response.getData().toString());
            statusCode = response.getStatusCode();

        }catch(ApiException e){
            statusCode = e.getCode();

        }

    }


    @And("^I POST the same credentials again to the /auth endpoint$")
    public void iPOSTTheSameCredentialsAgainToTheAuthEndpoint() throws Throwable {
        try{
            ApiResponse response = api.loginApplicationWithHttpInfo(credentials);
            tokenBis = new Token();
            tokenBis.setToken(response.getData().toString());
            statusCode = response.getStatusCode();

        }catch(ApiException e){
            statusCode = e.getCode();

        }
    }

    @Then("^I recieve the same token twice$")
    public void iRecieveTheSameTokenTwice() throws Throwable {
        assertEquals(token,tokenBis);
    }
}