/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.BadgeRule;
import ch.heigvd.gamification.api.dto.PointScaleRule;
import ch.heigvd.gamification.api.dto.Token;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static ch.heigvd.amt.gamification.spec.steps.SharedData.INCREMENT;

/**
 * @author guillaume
 */
public class PointScaleRuleManagementSteps {

    private final DefaultApi api = new DefaultApi();


    private ApiResponse response;
    private SharedData world;
    private PointScaleRule pointScaleRule;

    private long pointScaleRuleNbr;

    public PointScaleRuleManagementSteps(SharedData world) {
        this.world = world;
    }


    @Given("^a pointScaleRule payload concerning the previously posted pointScale$")
    public void a_pointScaleRule_payload_concerning_the_previously_posted_pointScale() throws Throwable {
        pointScaleRule = new PointScaleRule();
        String ruleName = SharedData.POINTSCALE_RULE_NAME1 + System.currentTimeMillis();
        pointScaleRule.setType(ruleName);
        world.setPointScaleRuleName(ruleName);
        pointScaleRule.setPointScale(world.getPointScaleNbr());
        pointScaleRule.setIncrement(INCREMENT);
    }

    @When("^I POST it to the /pointScaleRule endpoint$")
    public void i_POST_it_to_the_pointScaleRule_endpoint() throws Throwable {
        try {
            response = api.addPointScaleRuleWithHttpInfo(pointScaleRule, world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
            pointScaleRuleNbr = Integer.valueOf((String) response.getHeaders().get("location").toString().replaceAll("[^\\d]", ""));
            world.setPointScaleRuleNbr(pointScaleRuleNbr);

        } catch (ApiException e) {
            world.setStatusCode(e.getCode());
        }
    }

    @Then("^I DELETE it to the /pointScaleRule/id endpoint$")
    public void i_DELETE_it_to_the_pointScaleRule_id_endpoint() throws Throwable {
        try {
            response = api.deletePointScaleRuleWithHttpInfo(world.getPointScaleRuleNbr(), world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
        } catch (ApiException e) {
            world.setStatusCode(e.getCode());
        }
    }

    @Then("^I modify the pointScaleRule payload$")
    public void i_modify_the_pointScaleRule_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pointScaleRule.setIncrement(INCREMENT + 1);
    }

    @Then("^I PUT it to the /pointScaleRule/id endpoint$")
    public void i_PUT_it_to_the_pointScaleRule_id_endpoint() throws Throwable {
        try {
            response = api.updatePointScaleRuleWithHttpInfo(pointScaleRule, world.getPointScaleRuleNbr(), world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
        } catch (ApiException e) {
            world.setStatusCode(e.getCode());
        }
    }


}


