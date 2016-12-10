Feature: Application registration

Scenario: Register a new application
Given I have an application payload
When I POST it to the /registrations endpoint
Then I receive a 200 status code
