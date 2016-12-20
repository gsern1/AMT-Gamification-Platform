/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.amt.gamification.ExtendedAPI;
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
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 *
 * @author guillaume
 */
public class BadgeManagementSteps {

    private final DefaultApi api = new DefaultApi();
    private final ExtendedAPI api2 = new ExtendedAPI();

    private Token token;
    private Token tokenSaved;
    private Badge badge;
    private ApiResponse response;
    private SharedData world;
    private int badgeNbr;

    public BadgeManagementSteps(SharedData world){
        this.world = world;
    }

    @Given("^a token for a new gamified application$")
    public void aTokenForANewGamifiedApplication() throws Throwable {
        Application application = new Application();

        String randomAppName = "random-app-1-" + System.currentTimeMillis();
        application.setName(randomAppName);

        String password = "56789";
        application.setPassword(password);

        Credentials credentials = new Credentials();
        credentials.setName(randomAppName);
        credentials.setPassword(password);

        api.addApplicationWithHttpInfo(application);

        ApiResponse<Token> response = api.loginApplicationWithHttpInfo(credentials);
        token = response.getData();
        tokenSaved = new Token();
        tokenSaved.setToken(response.getData().getToken());

        badgeNbr = api.findBadges(token.getToken()).size() + 1;

    }

    @Given("^I have an badge payload$")
    public void i_have_an_badge_payload() throws Throwable {

        badge = new Badge();
        badge.setName("badge-" + System.currentTimeMillis());

    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        response = api.addBadgeWithHttpInfo(badge,token.getToken());
        world.setStatusCode(response.getStatusCode());
        badgeNbr = Integer.valueOf((String)response.getHeaders().get("location").toString().replaceAll("[^\\d]",""));

    }



    @And("^I receive a reference about the created badge$")
    public void iReceiveAReferenceAboutTheCreatedBadge() throws Throwable {
        String location = (response.getHeaders().get("location")).toString();
        location = location.substring(1,location.length()-1);
        System.out.println(location);
        assertTrue(location.matches("/badges/(?:\\d++\\w)*+\\d++"));


    }


    @When("^I GET in to the /badges endpoint$")
    public void iGETInToTheBadgesEndpoint() throws Throwable {
        response = api.findBadgesWithHttpInfo(token.getToken());
        world.setStatusCode(response.getStatusCode());
    }

    @And("^I receive a list of badges$")
    public void iReceiveAListOfBadges() throws Throwable {
        assertNotNull(response.getData());
    }


    @When("^I PUT in to the /badge/id endpoint$")
    public void iPUTInToTheBadgeIdEndpoint() throws Throwable {
        badge.setName("modified-" + System.currentTimeMillis());
        try{
            response = api.updateBadgeWithHttpInfo(badge, (long) badgeNbr, token.getToken());
            world.setStatusCode(response.getStatusCode());
        }catch (ApiException e){
            world.setStatusCode(e.getCode());
        }

    }

    @And("^The badge has been modified$")
    public void theBadgeHasBeenModified() throws Throwable {
        assertEquals(api.findBadge((long) badgeNbr,token.getToken()).getName(),badge.getName());
    }

    @And("^I have a bad token$")
    public void iHaveABadToken() throws Throwable {
        token.setToken("badToken");
    }

    @And("^The badge is unchanged$")
    public void theBadgeIsUnchanged() throws Throwable {
        assertNotEquals(api.findBadge((long) badgeNbr,tokenSaved.getToken()).getName(),badge.getName());

    }
}
