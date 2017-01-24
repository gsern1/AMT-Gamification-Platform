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
import cucumber.api.java.en.When;

/**
 *
 * @author guillaume
 */
public class PointScaleRuleManagementSteps {

    private final DefaultApi api = new DefaultApi();

    private Token token;
    private Token tokenSaved;
    private Badge badge;
    private ApiResponse response;
    private SharedData world;
    private BadgeRule badgeRule;
    private PointScaleRule pointScaleRule;

    private long badgeRuleNbr;
    private long pointScaleRuleNbr;

    public PointScaleRuleManagementSteps(SharedData world){
        this.world = world;
    }



    @Given("^a pointScaleRule payload concerning the previously posted poinScale$")
    public void a_pointScaleRule_payload_concerning_the_previously_posted_poinScale() throws Throwable {
        pointScaleRule = new PointScaleRule();
        pointScaleRule.setType("5 issues résolues ");
        pointScaleRule.setPointScale(world.getPointScaleNbr());
        pointScaleRule.setIncrement(5l);
    }

    @When("^I POST it to the /pointScaleRule endpoint$")
    public void i_POST_it_to_the_pointScaleRule_endpoint() throws Throwable {
        try {
            response = api.addPointScaleRuleWithHttpInfo(pointScaleRule,world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
            pointScaleRuleNbr = Integer.valueOf((String)response.getHeaders().get("location").toString().replaceAll("[^\\d]",""));
            world.setPointScaleRuleNbr(pointScaleRuleNbr);

        }catch (ApiException e){
            world.setStatusCode(e.getCode());
        }
    }






}


