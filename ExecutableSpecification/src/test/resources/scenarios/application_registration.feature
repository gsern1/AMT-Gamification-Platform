Feature: Application registration

  Scenario: Register a new application
    Given I have an application payload
    When I POST it to the /application endpoint
    Then I receive a 201 status code

  Scenario: Register a new application with no name
    Given I have an application payload with no name
    When I POST it to the /application endpoint
    Then I receive a 400 status code

  Scenario: Delete an existing application
    Given a bad token for a new gamified application
    When I DELETE that application using that token
    Then I receive a 403 status code

  Scenario: Delete an existing application
    Given a token for a new gamified application
    When I DELETE that application using that token
    Then I receive a 204 status code

  Scenario: Check that it is not possible to create two apps with the same name
    Given I have an application payload
    When I POST it to the /application endpoint
    And I POST it to the /application endpoint
    Then I receive a 409 status code

