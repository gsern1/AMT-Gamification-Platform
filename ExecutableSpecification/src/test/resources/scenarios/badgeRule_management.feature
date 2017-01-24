Feature: badgeRule registration

  Background:
    Given a token for a new gamified application and its credentials
    And I have a badge payload
    And I POST it to the /badges endpoint
    And I have a poinScale payload
    And I POST a pointscale for that application to the /pointScales endpoint with the recieved token



  Scenario: Submit a badgeRule using the recieved token
    Given a badgeRule payload concerning the previously posted badge and poinScale
    When I POST it to the /badgeRule endpoint
    Then I receive a 201 status code







