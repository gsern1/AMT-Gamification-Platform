/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.BadgeWithLocation;
import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.api.dto.UserPointScale;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author guillaume
 */
public class EventProcessingSteps {


    private final DefaultApi api = new DefaultApi();

    private SharedData world;

    public EventProcessingSteps(SharedData world){
        this.world = world;
    }

    private User[] users = null;

    private ApiResponse[] responses;

    @Given("^I have a list of (\\d+) new user$")
    public void iHaveAListOfNewUser(int number) throws Throwable {
        users = new User[number];
        for(int i = 0; i < number ;++i){
            users[i] = new User();
            users[i].setUsername("User-" + System.currentTimeMillis()+i);
        }

    }

    @When("^(\\d+) user POST an BadgeTyped event$")
    public void userPOSTAnBadgeTypedEvent(int number) throws Throwable {
        responses = new ApiResponse[number];
        for(int i = 0; i < number ;++i){
            Event userEvent = new Event();
            userEvent.setUsername(users[i].getUsername());
            userEvent.setType(world.getBadgeRuleName());
            responses[i] = api.addEventWithHttpInfo(userEvent, world.getToken().getToken());

        }
    }

    @Then("^Each user should have a badge$")
    public void eachUserShouldHaveABadge() throws Throwable {
        for(User u : users){
            List<BadgeWithLocation> list = api.findUserBadges(u.getUsername(),world.getToken().getToken());

            assertFalse(list.isEmpty());

        }

    }

    @When("^(\\d+) user POST an BadgeTyped event simultaneously$")
    public void userPOSTAnBadgeTypedEventSimultaneously(int number) throws Throwable {
        responses = new ApiResponse[number];
        for(int i = 0; i < number ;++i){
            final int ii = i;
            new Thread(() -> {
                Event userEvent = new Event();
                userEvent.setUsername(users[ii].getUsername());
                userEvent.setType(world.getBadgeRuleName());
                try {
                    responses[ii] = api.addEventWithHttpInfo(userEvent, world.getToken().getToken());
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
    }

    @When("^(\\d+) user POST an PointScaleTyped event$")
    public void userPOSTAnPointScaleTypedEvent(int number) throws Throwable {
        responses = new ApiResponse[number];
        for(int i = 0; i < number ;++i){
            Event userEvent = new Event();
            userEvent.setUsername(users[i].getUsername());
            userEvent.setType(world.getPointScaleRuleName());
            responses[i] = api.addEventWithHttpInfo(userEvent, world.getToken().getToken());

        }
    }

    @Then("^Each user should have a pointscale score$")
    public void eachUserShouldHaveAPointscaleScore() throws Throwable {
        System.out.println(users.length);
        for(User u : users){
            List<UserPointScale> list = api.findUserPointScales(u.getUsername(),world.getToken().getToken());
            for(UserPointScale ps : list){
                assertTrue(ps.getPoints() > 0);
            }
        }
    }

    @Then("^Each response should return (\\d+)$")
    public void eachResponseShouldReturn(int number) throws Throwable {
        for(ApiResponse r : responses ){
            assertEquals(r.getStatusCode(),number);
        }
    }

    @When("^(\\d+) user POST an PointScaleTyped event simultaneously$")
    public void userPOSTAnPointScaleTypedEventSimultaneously(int number) throws Throwable {
        responses = new ApiResponse[number];
        for(int i = 0; i < number ;++i){
            final int ii = i;
            new Thread(() -> {
                Event userEvent = new Event();
                userEvent.setUsername(users[ii].getUsername());
                userEvent.setType(world.getPointScaleRuleName());
                try {
                    responses[ii] = api.addEventWithHttpInfo(userEvent, world.getToken().getToken());
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
    }

    @When("^a user POST (\\d+) times an event for a pointscale$")
    public void aUserPOSTTimesAnEventForAPointscale(int number) throws Throwable {
        for(int i = 0 ; i < number; ++i){
            Event userEvent = new Event();
            userEvent.setUsername(users[0].getUsername());
            userEvent.setType(world.getPointScaleRuleName());
            api.addEvent(userEvent,world.getToken().getToken());
        }
    }

    @Then("^The user shouldn't have a badge$")
    public void theUserShouldnTHaveABadge() throws Throwable {

        for(User u : users){
            List<BadgeWithLocation> list = api.findUserBadges(u.getUsername(),world.getToken().getToken());
            assertTrue(list.isEmpty());
        }
    }
}
