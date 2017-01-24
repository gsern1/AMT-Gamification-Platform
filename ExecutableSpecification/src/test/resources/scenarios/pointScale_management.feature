Feature: pointScale registration

  Background:
    Given a token for a new gamified application and its credentials


  Scenario: Submit a pointSccale using the recieved token
    Given I have a pointScale payload
    When I POST it to the /pointScales endpoint
    Then I receive a 201 status code



