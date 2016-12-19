package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by guillaume on 12.12.16.
 */
public class Authentication {

    private final DefaultApi api = new DefaultApi();
    private Credentials credentials;
    Application application;
    private Token token;
    private SharedData world;
    private Event event;
    private PointScale pointScale;
    private int pointScaleId;

    public Authentication(SharedData world) {
        this.world = world;
    }


    @Given("^a new gamified application and its credentials$")
    public void aNewGamifiedApplication() throws Throwable {

        world.setApplication(new Application());
        world.incrementApplicationCounter();

        String randomAppName = "random-app-1-" + world.getApplicationCounter() + "-" + System.currentTimeMillis();
        world.getApplication().setName(randomAppName);

        String password = "56789";
        world.getApplication().setPassword(password);

        credentials = new Credentials();
        credentials.setName(randomAppName);
        credentials.setPassword(password);

    }

    @When("^I POST the credentials to the /auth endpoint$")
    public void iPOSTTheCredentialsToTheAuthEndpoint() throws Throwable {
        try {
            ApiResponse<Token> response = api.loginApplicationWithHttpInfo(credentials);
            world.setStatusCode(response.getStatusCode());
            token = response.getData();

        } catch (ApiException e) {
            world.setStatusCode(e.getCode());

        }

    }


    @And("^I have an event payload$")
    public void iHaveAnEventPayload() throws Throwable {
        event = new Event();

        event.setUsername("Toto");
        event.setIncrease(2l);
        event.setPointScale(1l);
    }

    @When("^I POST an event for that application to the /events endpoint with the recieved token$")
    public void iPostAnEventForThatApplicationToTheEventsEndpointWithTheRecievedToken() throws Throwable {
        try {
            ApiResponse response = api.addEventWithHttpInfo(event,token.getToken());
            world.setStatusCode(response.getStatusCode());

        } catch (ApiException e) {
            world.setStatusCode(e.getCode());

        }
    }


    @And("^I have an pointscale payload$")
    public void iHaveAnPointscalePayload() throws Throwable {
        pointScale = new PointScale();
        pointScale.setName("Hardcore Coder");

    }


    @When("^I POST a pointscale for that application to the /pointScales endpoint with the recieved token$")
    public void iPOSTAPointscaleForThatApplicationToThePointScalesEndpointWithTheRecievedToken() throws Throwable {
        try {
            ApiResponse response = api.addPointScaleWithHttpInfo(pointScale, token.getToken());
            world.setStatusCode(response.getStatusCode());


            Map<String, List<String>> map = response.getHeaders();
            pointScaleId = Integer.parseInt(map.get("location").get(0));
            /*
            System.out.print("Hello");
            System.out.print(map.keySet());*/
             //map.get("location").get(0);
             //System.out.print(map.get("location").get(0));

        } catch (ApiException e) {
            world.setStatusCode(e.getCode());

        }
    }


}