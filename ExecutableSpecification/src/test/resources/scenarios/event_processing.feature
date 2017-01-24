Feature: Event processing

Background:
  Given a token for a new gamified application and its credentials
  And I have a badge payload
  And I POST it to the /badges endpoint
  And I have a pointScale payload
  And I POST it to the /pointScales endpoint
  And a badgeRule payload concerning the previously posted badge and pointScale
  And I POST it to the /badgeRule endpoint
  And a pointScaleRule payload concerning the previously posted pointScale
  And I POST it to the /pointScaleRule endpoint

Scenario: some users POST an event and obtain a badge
  Given I have a list of 15 new user
  When 15 user POST an BadgeTyped event
  Then Each user should have a badge

Scenario: some users POST an event and obtain a badge simultaneously
  Given I have a list of 15 new user
  When 15 user POST an BadgeTyped event simultaneously
  Then Each user should have a badge
