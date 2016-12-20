Feature: Badges Registration

Background:
  Given a token for a new gamified application

#Tests pour l'ajout de badge
# =====================================================================
Scenario: register a badge for the gamified application
  Given I have a badge payload
  When I POST it to the /badges endpoint
  Then I receive a 201 status code
  And I receive a reference about the created badge

Scenario: register a badge with a bad payload
  When I perform a "POST" on "/badges/" endpoint with a wrong payload
  Then I receive a 400 status code

Scenario: register a badge with null
  Given I have null badge payload
  When I POST it to the /badges endpoint
  Then I receive a 400 status code

Scenario: register a badge without permission
  Given I have a badge payload
  And I have a bad token
  When I POST it to the /badges endpoint
  Then I receive a 403 status code

#Tests pour le get sur /badges
# =====================================================================
Scenario: get all the badges
  When I GET in to the /badges endpoint
  Then I receive a 200 status code
  And I receive a list of badges

Scenario:


#Test pour la modification des badges
# =====================================================================
Scenario: modify badge with permission
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I PUT in to the /badge/id endpoint
  Then I receive a 200 status code
  And The badge has been modified

Scenario: modify badge without permission
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I have a bad token
  And I PUT in to the /badge/id endpoint
  Then I receive a 401 status code
  And The badge is unchanged

Scenario: modify badge with a bad payload

Scenario: modify badge with a bad payload

Scenario: modify badge with a bad payload

Scenario: modify badge with a bad payload

Scenario: modify badge with a bad payload