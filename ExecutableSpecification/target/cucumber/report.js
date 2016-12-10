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
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.i_have_an_application_payload()"
});
formatter.result({
  "duration": 149467819,
  "status": "passed"
});
formatter.match({
  "location": "ApplicationSteps.i_POST_it_to_the_registrations_endpoint()"
});
formatter.result({
  "duration": 347602080,
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
  "duration": 3080701,
  "status": "passed"
});
});