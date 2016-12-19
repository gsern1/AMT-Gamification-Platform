Feature: Badges Registration

Background:
  Given a token for a new gamified application

Scenario: register a badge for the gamified application
  Given I have an badge payload
  When I POST it to the /badges endpoint
  Then I receive a 201 status code
  And I receive a reference about the created badge

Scenario: get all the badges
  When I GET in to the /badges endpoint
  Then I receive a 200 status code
  And I receive a list of badges
