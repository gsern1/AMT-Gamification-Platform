Feature: Authentication

Background:
  Given a new gamified application and its credentials
  And I POST it to the /application endpoint
  And I receive a 201 status code


  Scenario: Submit credentials to recieve a token, an succesfully send an event related to this application
    When I POST the credentials to the /auth endpoint
    Then I receive a 200 status code

  Scenario: Submit a pointscale using the recieved token
    Given I POST the credentials to the /auth endpoint
    And I receive a 200 status code
    And I have an pointscale payload
    When I POST a pointscale for that application to the /pointScales endpoint with the recieved token
    Then I receive a 201 status code


  Scenario: Submit an event using the recieved token
    Given I POST the credentials to the /auth endpoint
    And I receive a 200 status code
    And I have an pointscale payload
    And I POST a pointscale for that application to the /pointScales endpoint with the recieved token
    And I receive a 201 status code
    And I have an event payload
    When I POST an event for that application to the /events endpoint with the recieved token
    Then I receive a 201 status code
