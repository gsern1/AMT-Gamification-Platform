Feature: pointScale registration

  Background:
    Given a token for a new gamified application and its credentials


  Scenario: Submit a pointScale using the recieved token
    Given I have a pointScale payload
    When I POST it to the /pointScales endpoint
    Then I receive a 201 status code


  Scenario: Submit a pointScale with a wrond token
    Given I have a pointScale payload
    And I have a wrong token
    When I POST it to the /pointScales endpoint
    Then I receive a 403 status code


  Scenario: Submit a pointScale with a null payload using the recieved token
    Given I have a null pointScale payload
    When I POST it to the /pointScales endpoint
    Then I receive a 422 status code