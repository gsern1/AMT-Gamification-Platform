Feature: pointScaleRule registration

  Background:
    Given a token for a new gamified application and its credentials
    And I have a pointScale payload
    When I POST it to the /pointScales endpoint


  Scenario: Submit a pointScaleRule rule using the recieved token
    Given a pointScaleRule payload concerning the previously posted poinScale
    When I POST it to the /pointScaleRule endpoint
    Then I receive a 201 status code







