$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("application_registration.feature");
formatter.feature({
  "line": 1,
  "name": "Application registration",
  "description": "",
  "id": "application-registration",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Register a new application",
  "description": "",
  "id": "application-registration;register-a-new-application",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I have an application payload",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I POST it to the /application endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.i_have_an_application_payload()"
});
formatter.result({
  "duration": 124564348,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 172755105,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 12
    }
  ],
  "location": "ApplicationSteps.i_receive_a_status_code(int)"
});
formatter.result({
  "duration": 2235599,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Check that the application has been registered",
  "description": "",
  "id": "application-registration;check-that-the-application-has-been-registered",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "I have an application payload",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "I POST it to the /application endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I ask for a list of registered apps with a GET on the /application endpoint",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I see my app in the list",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.i_have_an_application_payload()"
});
formatter.result({
  "duration": 25998,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 6175169,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_application_endpoint()"
});
formatter.result({
  "duration": 1589788,
  "error_message": "cucumber.api.PendingException: TODO: implement me\n\tat ch.heigvd.amt.gamification.spec.steps.ApplicationSteps.i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_application_endpoint(ApplicationSteps.java:48)\n\tat ✽.And I ask for a list of registered apps with a GET on the /application endpoint(application_registration.feature:11)\n",
  "status": "pending"
});
formatter.match({
  "location": "ApplicationSteps.i_see_my_app_in_the_list()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "line": 14,
  "name": "Check that it is not possible to create two apps with the same name",
  "description": "",
  "id": "application-registration;check-that-it-is-not-possible-to-create-two-apps-with-the-same-name",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 15,
  "name": "I have an application payload",
  "keyword": "Given "
});
formatter.step({
  "line": 16,
  "name": "I POST it to the /application endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I POST it to the /application endpoint",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "I receive a 422 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.i_have_an_application_payload()"
});
formatter.result({
  "duration": 30878,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 6958696,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 6079299,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 12
    }
  ],
  "location": "ApplicationSteps.i_receive_a_status_code(int)"
});
formatter.result({
  "duration": 232653,
  "error_message": "java.lang.AssertionError: expected:\u003c422\u003e but was:\u003c200\u003e\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat org.junit.Assert.failNotEquals(Assert.java:834)\n\tat org.junit.Assert.assertEquals(Assert.java:645)\n\tat org.junit.Assert.assertEquals(Assert.java:631)\n\tat ch.heigvd.amt.gamification.spec.steps.ApplicationSteps.i_receive_a_status_code(ApplicationSteps.java:41)\n\tat ✽.Then I receive a 422 status code(application_registration.feature:18)\n",
  "status": "failed"
});
formatter.uri("badge_management.feature");
formatter.feature({
  "line": 1,
  "name": "Badges Registration",
  "description": "\nBackground\n  Given a token for a new gamified application A1",
  "id": "badges-registration",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 6,
  "name": "register a badge for the gamified application",
  "description": "",
  "id": "badges-registration;register-a-badge-for-the-gamified-application",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "I have an badge payload",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I POST it to the /badges endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "BadgeManagementSteps.i_have_an_badge_payload()"
});
formatter.result({
  "duration": 154796,
  "error_message": "cucumber.api.PendingException: TODO: implement me\n\tat ch.heigvd.amt.gamification.spec.steps.BadgeManagementSteps.i_have_an_badge_payload(BadgeManagementSteps.java:21)\n\tat ✽.Given I have an badge payload(badge_management.feature:7)\n",
  "status": "pending"
});
formatter.match({
  "location": "BadgeManagementSteps.i_POST_it_to_the_badges_endpoint()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 12
    }
  ],
  "location": "ApplicationSteps.i_receive_a_status_code(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.uri("event_processing.feature");
formatter.feature({
  "line": 1,
  "name": "Event processing",
  "description": "\nBackground\n  Given a token for a new gamified application A1",
  "id": "event-processing",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 6,
  "name": "send the first event for a user of the gamified application",
  "description": "",
  "id": "event-processing;send-the-first-event-for-a-user-of-the-gamified-application",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "a user U1 of the gamified application A1",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "the application A1 POSTs 1 payload for events associated to user U1 on the /events endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "the application A1 GETs user U1 from the /users/ endpoint",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "it receives a 200 status code",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "the payload in the response has a property numberOfEvents with a value of 1",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 8
    },
    {
      "val": "1",
      "offset": 39
    }
  ],
  "location": "EventProcessingSteps.a_user_U_of_the_gamified_application_A(int,int)"
});
formatter.result({
  "duration": 196000,
  "error_message": "cucumber.api.PendingException: TODO: implement me\n\tat ch.heigvd.amt.gamification.spec.steps.EventProcessingSteps.a_user_U_of_the_gamified_application_A(EventProcessingSteps.java:22)\n\tat ✽.Given a user U1 of the gamified application A1(event_processing.feature:7)\n",
  "status": "pending"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 17
    },
    {
      "val": "1",
      "offset": 25
    },
    {
      "val": "1",
      "offset": 66
    }
  ],
  "location": "EventProcessingSteps.the_application_A_POSTs_payload_for_events_associated_to_user_U_on_the_events_endpoint(int,int,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 17
    },
    {
      "val": "1",
      "offset": 30
    }
  ],
  "location": "EventProcessingSteps.the_application_A_GETs_user_U_from_the_users_endpoint(int,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 14
    }
  ],
  "location": "EventProcessingSteps.it_receives_a_status_code(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 74
    }
  ],
  "location": "EventProcessingSteps.the_payload_in_the_response_has_a_property_numberOfEvents_with_a_value_of(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "line": 13,
  "name": "send the first 2 events for a user of the gamified application",
  "description": "",
  "id": "event-processing;send-the-first-2-events-for-a-user-of-the-gamified-application",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 14,
  "name": "a user U1 of the gamified application A1",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "the application A1 POSTs 2 payloads for events associated to user U1 on the /events endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "the application A1 GETs user U1 from the /users/ endpoint",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "it receives a 200 status code",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "the payload in the response has a property numberOfEvents with a value of 2",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 8
    },
    {
      "val": "1",
      "offset": 39
    }
  ],
  "location": "EventProcessingSteps.a_user_U_of_the_gamified_application_A(int,int)"
});
formatter.result({
  "duration": 178743,
  "error_message": "cucumber.api.PendingException: TODO: implement me\n\tat ch.heigvd.amt.gamification.spec.steps.EventProcessingSteps.a_user_U_of_the_gamified_application_A(EventProcessingSteps.java:22)\n\tat ✽.Given a user U1 of the gamified application A1(event_processing.feature:14)\n",
  "status": "pending"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 17
    },
    {
      "val": "2",
      "offset": 25
    },
    {
      "val": "1",
      "offset": 67
    }
  ],
  "location": "EventProcessingSteps.the_application_A_POSTs_payloads_for_events_associated_to_user_U_on_the_events_endpoint(int,int,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 17
    },
    {
      "val": "1",
      "offset": 30
    }
  ],
  "location": "EventProcessingSteps.the_application_A_GETs_user_U_from_the_users_endpoint(int,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 14
    }
  ],
  "location": "EventProcessingSteps.it_receives_a_status_code(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 74
    }
  ],
  "location": "EventProcessingSteps.the_payload_in_the_response_has_a_property_numberOfEvents_with_a_value_of(int)"
});
formatter.result({
  "status": "skipped"
});
});