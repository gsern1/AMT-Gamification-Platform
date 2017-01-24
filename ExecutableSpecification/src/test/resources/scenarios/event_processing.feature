Feature: Event processing

Background:
  Given a token for a new gamified application and its credentials
  And I have a badge payload
  And I POST it to the /badges endpoint
  And I have a pointScale payload
  And I POST a pointscale for that application to the /pointScales endpoint with the recieved token
  And a badgeRule payload concerning the previously posted badge and pointScale
  And I POST it to the /badgeRule endpoint
  And a pointScaleRule payload concerning the previously posted poinScale
  And I POST it to the /pointScaleRule endpoint

Scenario: some userd POST an event and obtain a badge
  Given I have the number of badges obtained
  Given 15 user POST an BadgeTyped event
  Then There should be 15 more badges

