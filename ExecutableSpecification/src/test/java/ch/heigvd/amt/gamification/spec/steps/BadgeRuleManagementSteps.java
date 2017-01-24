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
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 *
 * @author guillaume
 */
public class BadgeRuleManagementSteps {

    private final DefaultApi api = new DefaultApi();

    private Token token;
    private Token tokenSaved;
    private Badge badge;
    private ApiResponse response;
    private SharedData world;
    private BadgeRule badgeRule;
    private long badgeRuleNbr;

    public BadgeRuleManagementSteps(SharedData world){
        this.world = world;
    }




    @Given("^a badgeRule payload concerning the previously posted badge and pointScale$")
    public void a_badgeRule_payload_concerning_the_previously_posted_badge_and_pointScale() throws Throwable {
        badgeRule = new BadgeRule();
        badgeRule.setType(SharedData.BADGES_RULE_NAME1);
        badgeRule.setBadge(world.getBadgeNbr());
        badgeRule.setPointScale(world.getPointScaleNbr());
        badgeRule.setThreshold(1l);
    }


    @When("^I POST it to the /badgeRule endpoint$")
    public void i_POST_it_to_the_badgeRule_endpoint() throws Throwable {
        try {
            response = api.addBadgeRuleWithHttpInfo(badgeRule,world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
            badgeRuleNbr = Integer.valueOf((String)response.getHeaders().get("location").toString().replaceAll("[^\\d]",""));
            world.setBadgeRuleNbr(badgeRuleNbr);

        }catch (ApiException e){
            world.setStatusCode(e.getCode());
        }
    }






}


