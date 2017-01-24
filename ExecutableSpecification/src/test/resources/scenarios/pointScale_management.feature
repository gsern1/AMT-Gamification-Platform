Feature: pointScale registration

  Background:
    Given a token for a new gamified application and its credentials


  Scenario: Submit a pointscale using the recieved token
    Given I have an pointscale payload
    When I POST a pointscale for that application to the /pointScales endpoint with the recieved token
    Then I receive a 201 status code



