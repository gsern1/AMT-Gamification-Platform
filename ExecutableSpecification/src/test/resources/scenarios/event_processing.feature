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
  Then Each response should return 200
  And Each user should have a badge

Scenario: some users POST an event and obtain a badge simultaneously
  Given I have a list of 15 new user
  When 15 user POST an BadgeTyped event simultaneously
  Then Each response should return 200
  And Each user should have a badge

Scenario: some users POST an event for a pointscale
  Given I have a list of 15 new user
  When 15 user POST an PointScaleTyped event
  Then Each response should return 200
  And Each user should have a pointscale score

Scenario: some users POST an event for a pointscale simultaneously
  Given I have a list of 15 new user
  When 15 user POST an PointScaleTyped event simultaneously
  Then Each response should return 200
  And Each user should have a pointscale score

Scenario: an application post pointscale wining event while user don't have a badge
  Given I have a list of 1 new user
  When a user POST 3 times an event for a pointscale
  Then Each user should have a badge

Scenario: an application post pointscale wining event but the user don't reach the treshold
  Given I have a list of 1 new user
  When a user POST 2 times an event for a pointscale
  Then The user shouldn't have a badge

Scenario: a user POST simultaneously 1000 event on a PointScale
  Given I have a list of 1 new user
  When a user POST 4 x 250 times an event for a pointscale simultaneously
  Then the user should have 1000 times the increment of the pointscale