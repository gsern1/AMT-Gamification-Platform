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


  Scenario: Submit a badgeRule with a wrond token
    Given a badgeRule payload concerning the previously posted badge and pointScale
    And I have a wrong token
    When I POST it to the /badgeRule endpoint
    Then I receive a 403 status code




