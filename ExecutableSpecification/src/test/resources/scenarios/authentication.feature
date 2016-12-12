Feature: Authentication

  Background
    Given a new gamified application and its credentials

  Scenario: Submit credentials and recieve a token
    When I POST the credentials to the /auth endpoint
    And I POST the same credentials again to the /auth endpoint
    Then I recieve the same token twice
