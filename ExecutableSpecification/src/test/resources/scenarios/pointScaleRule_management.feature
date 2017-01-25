Feature: pointScaleRule registration

  Background:
    Given a token for a new gamified application and its credentials
    And I have a pointScale payload
    When I POST it to the /pointScales endpoint


  Scenario: Submit a pointScaleRule rule using the recieved token
    Given a pointScaleRule payload concerning the previously posted pointScale
    When I POST it to the /pointScaleRule endpoint
    Then I receive a 201 status code

  Scenario: Submit a pointScaleRule using a wrong token
    Given a pointScaleRule payload concerning the previously posted pointScale
    And I have a wrong token
    When I POST it to the /pointScaleRule endpoint
    Then I receive a 403 status code

  Scenario: Submit a pointScaleRule rule using the recieved token then delete it
    Given a pointScaleRule payload concerning the previously posted pointScale
    When I POST it to the /pointScaleRule endpoint
    Then I receive a 201 status code
    And I DELETE it to the /pointScaleRule/id endpoint
    Then I receive a 204 status code

  Scenario: Submit a pointScaleRule rule using the recieved token then update it
    Given a pointScaleRule payload concerning the previously posted pointScale
    When I POST it to the /pointScaleRule endpoint
    Then I receive a 201 status code
    And I modify the pointScaleRule payload
    And I PUT it to the /pointScaleRule/id endpoint
    Then I receive a 200 status code