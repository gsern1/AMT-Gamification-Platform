Feature: Application registration

Scenario: Register a new application
Given I have an application payload
When I POST it to the /application endpoint
Then I receive a 422 status code

Scenario: Check that the application has been registered
Given I have an application payload
When I POST it to the /application endpoint
And I ask for a list of registered apps with a GET on the /application endpoint
Then I see my app in the list

Scenario: Check that it is not possible to create two apps with the same name
Given I have an application payload
When I POST it to the /application endpoint
And I POST it to the /application endpoint
Then I receive a 422 status code

