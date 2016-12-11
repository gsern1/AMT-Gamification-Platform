Feature: Badges Registration

Background
    Given a token for a new gamified application A1

Scenario: register a badge for the gamified application
  Given I have an badge payload
  When I POST it to the /badges endpoint
  Then I receive a 200 status code

