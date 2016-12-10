$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("scenario.feature");
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
  "name": "I POST it to the /registrations endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I receive a 201 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.i_have_an_application_payload()"
});
formatter.result({
  "duration": 164533266,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 201339512,
  "error_message": "ch.heigvd.gamification.ApiException: Method Not Allowed\n\tat ch.heigvd.gamification.ApiClient.handleResponse(ApiClient.java:1058)\n\tat ch.heigvd.gamification.ApiClient.execute(ApiClient.java:981)\n\tat ch.heigvd.gamification.ApiClient.execute(ApiClient.java:964)\n\tat ch.heigvd.gamification.api.DefaultApi.registrationsPostWithHttpInfo(DefaultApi.java:233)\n\tat ch.heigvd.gamification.api.DefaultApi.registrationsPost(DefaultApi.java:221)\n\tat ch.heigvd.amt.gamification.spec.steps.ApplicationSteps.i_POST_it_to_the_registrations_endpoint(ApplicationSteps.java:36)\n\tat ✽.When I POST it to the /registrations endpoint(scenario.feature:5)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 12
    }
  ],
  "location": "ApplicationSteps.i_receive_a_status_code(int)"
});
formatter.result({
  "status": "skipped"
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
  "name": "I POST it to the /registrations endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I ask for a list of registered apps with a GET on the /registrations endpoint",
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
  "duration": 33242,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 5553097,
  "error_message": "ch.heigvd.gamification.ApiException: Method Not Allowed\n\tat ch.heigvd.gamification.ApiClient.handleResponse(ApiClient.java:1058)\n\tat ch.heigvd.gamification.ApiClient.execute(ApiClient.java:981)\n\tat ch.heigvd.gamification.ApiClient.execute(ApiClient.java:964)\n\tat ch.heigvd.gamification.api.DefaultApi.registrationsPostWithHttpInfo(DefaultApi.java:233)\n\tat ch.heigvd.gamification.api.DefaultApi.registrationsPost(DefaultApi.java:221)\n\tat ch.heigvd.amt.gamification.spec.steps.ApplicationSteps.i_POST_it_to_the_registrations_endpoint(ApplicationSteps.java:36)\n\tat ✽.When I POST it to the /registrations endpoint(scenario.feature:10)\n",
  "status": "failed"
});
formatter.match({
  "location": "ApplicationSteps.i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_registrations_endpoint()"
});
formatter.result({
  "status": "skipped"
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
  "name": "I POST it to the /registrations endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I POST it to the /registrations endpoint",
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
  "duration": 26652,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 4872540,
  "error_message": "ch.heigvd.gamification.ApiException: Method Not Allowed\n\tat ch.heigvd.gamification.ApiClient.handleResponse(ApiClient.java:1058)\n\tat ch.heigvd.gamification.ApiClient.execute(ApiClient.java:981)\n\tat ch.heigvd.gamification.ApiClient.execute(ApiClient.java:964)\n\tat ch.heigvd.gamification.api.DefaultApi.registrationsPostWithHttpInfo(DefaultApi.java:233)\n\tat ch.heigvd.gamification.api.DefaultApi.registrationsPost(DefaultApi.java:221)\n\tat ch.heigvd.amt.gamification.spec.steps.ApplicationSteps.i_POST_it_to_the_registrations_endpoint(ApplicationSteps.java:36)\n\tat ✽.When I POST it to the /registrations endpoint(scenario.feature:16)\n",
  "status": "failed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.scenario({
  "line": 20,
  "name": "Delete an application",
  "description": "",
  "id": "application-registration;delete-an-application",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 21,
  "name": "I know the name of an application",
  "keyword": "Given "
});
formatter.step({
  "line": 22,
  "name": "I send a DELETE on the /registrations endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.i_know_the_name_of_an_application()"
});
formatter.result({
  "duration": 1063166,
  "error_message": "cucumber.api.PendingException: TODO: implement me\n\tat ch.heigvd.amt.gamification.spec.steps.ApplicationSteps.i_know_the_name_of_an_application(ApplicationSteps.java:63)\n\tat ✽.Given I know the name of an application(scenario.feature:21)\n",
  "status": "pending"
});
formatter.match({
  "location": "ApplicationSteps.i_send_a_DELETE_on_the_registrations_endpoint()"
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
});