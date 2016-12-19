/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author guillaume
 */
public class BadgeManagementSteps {

    private final DefaultApi api = new DefaultApi();

    private Token token;
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

        badgeNbr = api.findBadges(token.getToken()).size();

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

    }



    @And("^I receive a reference about the created badge$")
    public void iReceiveAReferenceAboutTheCreatedBadge() throws Throwable {
        String location = (String)response.getHeaders().get("location");
        assertEquals(location, "/api/badges/" + badgeNbr);
        throw new PendingException();
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
}
