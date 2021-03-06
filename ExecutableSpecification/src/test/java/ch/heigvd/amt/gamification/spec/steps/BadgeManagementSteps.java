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
import ch.heigvd.gamification.api.dto.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.assertj.core.util.Compatibility;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
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
    private long badgeNbr;

    public BadgeManagementSteps(SharedData world) {
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

        world.setApi(api2);
        world.setToken(token);

    }

    @Given("^I have a badge payload$")
    public void iHaveABadgePayload() throws Throwable {
        badge = new Badge();
        badge.setName("badge-" + System.currentTimeMillis());
        world.setBadge(badge);
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        try {
            response = api.addBadgeWithHttpInfo(world.getBadge(), world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
            badgeNbr = Integer.valueOf((String) response.getHeaders().get("location").toString().replaceAll("[^\\d]", ""));
            world.setBadgeNbr(badgeNbr);

        } catch (ApiException e) {
            world.setStatusCode(e.getCode());
        }


    }


    @And("^I receive a reference about the created badge$")
    public void iReceiveAReferenceAboutTheCreatedBadge() throws Throwable {
        String location = (response.getHeaders().get("location")).toString();
        location = location.substring(1, location.length() - 1);
        assertTrue(location.matches("/badges/(?:\\d++\\w)*+\\d++"));


    }


    @When("^I GET in to the /badges endpoint$")
    public void iGETInToTheBadgesEndpoint() throws Throwable {
        response = api.findBadgesWithHttpInfo(token.getToken());
        world.setStatusCode(response.getStatusCode());
    }

    @And("^I receive a list of badges$")
    public void iReceiveAListOfBadges() throws Throwable {
        if (world.getResponse() != null)
            assertNotNull(world.getResponse().getData());
        else
            assertNotNull(response.getData());
    }


    @When("^I PUT in to the /badge/id endpoint$")
    public void iPUTInToTheBadgeIdEndpoint() throws Throwable {
        if (badge.getName() != null)
            badge.setName("modified-" + System.currentTimeMillis());
        try {
            response = api.updateBadgeWithHttpInfo(badge, badgeNbr, token.getToken());
            world.setStatusCode(response.getStatusCode());
        } catch (ApiException e) {
            world.setStatusCode(e.getCode());
        }

    }

    @And("^The badge has been modified$")
    public void theBadgeHasBeenModified() throws Throwable {
        assertEquals(api.findBadge(badgeNbr, token.getToken()).getName(), badge.getName());
    }

    @And("^I have a bad token$")
    public void iHaveABadToken() throws Throwable {
        token.setToken("badToken");
    }

    @And("^The badge is unchanged$")
    public void theBadgeIsUnchanged() throws Throwable {
        assertNotEquals(api.findBadge(badgeNbr, tokenSaved.getToken()).getName(), badge.getName());

    }


    @Given("^I have null badge payload$")
    public void iHaveNullBadgePayload() throws Throwable {
        badge = new Badge();
        badge.setName(null);
        world.setBadge(badge);
    }

    @And("^I GET on smth$")
    public void iGETOnSmth() throws Throwable {
        response = api.findBadgeWithHttpInfo(badgeNbr, token.getToken());
    }

    @And("^I don't receive any bages$")
    public void iDonTReceiveAnyBages() throws Throwable {
        if (response != null)
            assertEquals(response.getData(), new ArrayList<>());
        else
            assertEquals(world.getResponse().getData(), new ArrayList<>());
    }

    @And("^I have a bad item id$")
    public void iHaveABadItemId() throws Throwable {
        world.setBadgeNbr(System.currentTimeMillis());
        badgeNbr = System.currentTimeMillis();
    }
}
