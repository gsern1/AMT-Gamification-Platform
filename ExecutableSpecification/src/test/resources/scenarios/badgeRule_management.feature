Feature: badgeRule registration

  Background:
    Given a token for a new gamified application and its credentials
    And I have a badge payload
    And I POST it to the /badges endpoint
    And I have a pointScale payload
    When I POST it to the /pointScales endpoint


  Scenario: Submit a badgeRule using the recieved token
    Given a badgeRule payload concerning the previously posted badge and pointScale
    When I POST it to the /badgeRule endpoint
    Then I receive a 201 status code

  Scenario: Submit a bad badgeRule payload
    Given a bad badgerule payload
    When I POST it to the /badgeRule endpoint
    Then I receive a 400 status code

  Scenario: Submit a badgeRule with a wrong token
    Given a badgeRule payload concerning the previously posted badge and pointScale
    And I have a wrong token
    When I POST it to the /badgeRule endpoint
    Then I receive a 403 status code

  Scenario: Submit a badgeRule with a null payload
    Given a badgeRule payload with a null type
    When I POST it to the /badgeRule endpoint
    Then I receive a 400 status code

  Scenario: Submit a badgeRule with a null badge
    Given a badgeRule payload with a null badge
    When I POST it to the /badgeRule endpoint
    Then I receive a 422 status code

  Scenario: I want to get all bagesrules
    When I GET on /badgesRules
    Then I receive a 200 status code

  Scenario: I have a bad token and i want to get all badgerules
    Given I have a wrong token
    When I GET on /badgesRules
    Then I receive a 403 status code



