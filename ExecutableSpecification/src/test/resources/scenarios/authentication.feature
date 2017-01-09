Feature: Authentication

  Background:
    Given a token for a new gamified application and its credentials


  Scenario: Submit credentials to recieve a token
    When I POST the credentials to the /auth endpoint
    Then I receive a 200 status code

  Scenario: Submit wrong username in credentials and observe authentication fail
    Given some credentials with a wrong application name
    When I POST the credentials to the /auth endpoint
    Then I receive a 400 status code

  Scenario: Submit empty username in credentials and observe authentication fail
    Given some credentials with an empty application name
    When I POST the credentials to the /auth endpoint
    Then I receive a 400 status code

  Scenario: Submit wrong password in credentials and observe authentication fail
    Given some credentials with a wrong password
    When I POST the credentials to the /auth endpoint
    Then I receive a 401 status code

  Scenario: Submit empty password in credentials and observe authentication fail
    Given some credentials with an empty password
    When I POST the credentials to the /auth endpoint
    Then I receive a 401 status code


  Scenario: Submit a pointscale using the recieved token
    Given I have an pointscale payload
    When I POST a pointscale for that application to the /pointScales endpoint with the recieved token
    Then I receive a 201 status code



