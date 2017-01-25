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
import ch.heigvd.gamification.api.dto.PointScale;
import ch.heigvd.gamification.api.dto.Token;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * @author guillaume
 */
public class PointScaleManagementSteps {

    private final DefaultApi api = new DefaultApi();

    private ApiResponse response;
    private SharedData world;
    private long pointScaleNbr;

    private PointScale pointScale;

    public PointScaleManagementSteps(SharedData world) {
        this.world = world;
    }

    @Given("^I have a pointScale payload$")
    public void i_have_a_pointScale_payload() throws Throwable {
        pointScale = new PointScale();
        pointScale.setName("pointScale-" + System.currentTimeMillis());
    }

    @Given("^I have a null pointScale payload$")
    public void i_have_a_null_pointScale_payload() throws Throwable {
        pointScale = new PointScale();
        pointScale.setName(null);
    }

    @When("^I POST it to the /pointScales endpoint$")
    public void i_POST_it_to_the_pointScales_endpoint() throws Throwable {
        try {
            response = api.addPointScaleWithHttpInfo(pointScale, world.getToken().getToken());
            world.setStatusCode(response.getStatusCode());
            pointScaleNbr = Integer.valueOf((String) response.getHeaders().get("location").toString().replaceAll("[^\\d]", ""));
            world.setPointScaleNbr(pointScaleNbr);

        } catch (ApiException e) {
            world.setStatusCode(e.getCode());
        }
    }


}


