Feature: Event processing

Background
  Given a token for a new gamified application and its credentials
  And I have a badge payload
  And I POST it to the /badges endpoint
  And I have a poinScale payload
  And I POST a pointscale for that application to the /pointScales endpoint with the recieved token
  And a badgeRule payload concerning the previously posted badge and poinScale
  And I POST it to the /badgeRule endpoint
  And a pointScaleRule payload concerning the previously posted poinScale
  And I POST it to the /pointScaleRule endpoint

Scenario: send the first event for a user of the gamified application


Scenario: send the first 2 events for a user of the gamified application
  Given a user U1 of the gamified application A1
  When the application A1 POSTs 2 payloads for events associated to user U1 on the /events endpoint
  And the application A1 GETs user U1 from the /users/ endpoint
  Then I receive a 200 status code
  And the payload in the response has a property numberOfEvents with a value of 2