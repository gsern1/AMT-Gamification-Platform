Feature: Badges Registration

Background:
  Given a token for a new gamified application

#Test about badge registering
# =====================================================================
Scenario: register a badge for the gamified application
  Given I have a badge payload
  When I POST it to the /badges endpoint
  Then I receive a 201 status code
  And I receive a reference about the created badge

Scenario: register a badge with a bad payload
  When I perform a "POST" on "/badges/" endpoint with a wrong payload that return "null"
  Then I receive a 400 status code

Scenario: register a badge with null
  Given I have null badge payload
  When I POST it to the /badges endpoint
  Then I receive a 422 status code

Scenario: register a badge without permission
  Given I have a badge payload
  And I have a bad token
  When I POST it to the /badges endpoint
  Then I receive a 403 status code

#Tests about GET on /badges
# =====================================================================
Scenario: get all the badges
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I GET in to the /badges endpoint
  Then I receive a 200 status code
  And I receive a list of badges

Scenario: get all the badges (no badges registered)
  When I GET in to the /badges endpoint
  Then I receive a 200 status code
  And I don't receive any bages

Scenario: get all the badges with a wrong payload
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I perform a "GET" on "/badges" endpoint with a wrong payload that return "PointScales"
  Then I receive a 200 status code
  And I receive a list of badges
  
Scenario: get all the badges with a wrong token
  Given I have a bad token
  When I perform a "GET" on "/badges" endpoint with an empty payload tha return "PointScales"
  Then I receive a 403 status code
  
#Tests about PUT on /badges/id
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
  Then I receive a 403 status code
  And The badge is unchanged

Scenario: modify badge with an empty payload
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I have null badge payload
  And I PUT in to the /badge/id endpoint
  Then I receive a 404 status code
  And The badge is unchanged

Scenario: modify badge with a bad payload
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I perform a "PUT" on "/badges/12" endpoint with a wrong payload that return "null"
  Then I receive a 400 status code

#Test about DELETE on /badges/id
# =====================================================================
Scenario: delete a badge
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I perform a "DELETE" on "/badges" endpoint with id that return "null"
  Then I receive a 200 status code


Scenario: delete a badge with a wrong token
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I have a bad token
  And I perform a "DELETE" on "/badges" endpoint with id that return "null"
  Then I receive a 403 status code


#Test about GET on /badges/id
# =====================================================================
Scenario: get a badge
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I perform a "GET" on "/badges" endpoint with id that return "Badge"
  Then I receive a 200 status code

Scenario: get a badge with a wrong token
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I have a bad token
  And I perform a "GET" on "/badges" endpoint with id that return "Badge"
  Then I receive a 403 status code

Scenario: get a badge with an incorrect ID
  Given I have a badge payload
  When I POST it to the /badges endpoint
  And I have a bad item id
  And I perform a "GET" on "/badges" endpoint with id that return "Badge"
  Then I receive a 404 status code





