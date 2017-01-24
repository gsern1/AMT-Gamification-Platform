Feature: pointScaleRule registration

  Background:
    Given a token for a new gamified application and its credentials
    And I have a poinScale payload
    And I POST a pointscale for that application to the /pointScales endpoint with the recieved token



  Scenario: Submit a pointScaleRule rule using the recieved token
    Given a pointScaleRule payload concerning the previously posted poinScale
    When I POST it to the /pointScaleRule endpoint
    Then I receive a 201 status code







