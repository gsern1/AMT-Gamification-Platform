/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gamification.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 *
 * @author guillaume
 */
public class EventProcessingSteps {

    @Given("^a user U(\\d+) of the gamified application A(\\d+)$")
    public void a_user_U_of_the_gamified_application_A(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the application A(\\d+) POSTs (\\d+) payload for events associated to user U(\\d+) on the /events endpoint$")
    public void the_application_A_POSTs_payload_for_events_associated_to_user_U_on_the_events_endpoint(int arg1, int arg2, int arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the application A(\\d+) GETs user U(\\d+) from the /users/ endpoint$")
    public void the_application_A_GETs_user_U_from_the_users_endpoint(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Then("^the payload in the response has a property numberOfEvents with a value of (\\d+)$")
    public void the_payload_in_the_response_has_a_property_numberOfEvents_with_a_value_of(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the application A(\\d+) POSTs (\\d+) payloads for events associated to user U(\\d+) on the /events endpoint$")
    public void the_application_A_POSTs_payloads_for_events_associated_to_user_U_on_the_events_endpoint(int arg1, int arg2, int arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
