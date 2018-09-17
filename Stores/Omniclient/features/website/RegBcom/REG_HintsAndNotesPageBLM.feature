# Author: Chirila Claudiu
# Story:
# Date Created:
# Date Signed Off:

Feature: Hints and Notes sections are displayed on Customer Profile

  @RegMcom @domain_stores @omniclient @website
  Scenario: Hints and Notes sections are displayed on Customer Profile
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as Selling Manager
    And I add a new BLM Client: "TEST" "HINTS AND NOTES" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL" "blm@macys.com"
    And I click yes button on the credit card required attention popup
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST" in the input field
    And I enter last name "HINTS AND NOTES" in the input field
    And I enter zip code "12345" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST HINTS AND NOTES"
    And I navigate to Manage Customer tab
    When I hover over exclamation mark HINTS and NOTES links are displayed
    When I click on HINTS and NOTES tab from Client Profile
    Then HINTS and NOTES page is displayed
    And hints section has "HINTS" title
    And notes section has "NOTES" title


#   TODO: add validation for maximum allowed characters once have have the correct number
  @RegMcom @domain_stores @omniclient @website
  Scenario: Maximum 200 characters are displayed in HINTS and NOTES section from MANAGE CLIENT tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as Selling Manager
    And I add a new BLM Client: "TEST" "HINTS AND NOTES" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL" "blm@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    When I hover over exclamation mark HINTS and NOTES links are displayed
    When I click on HINTS and NOTES tab from Client Profile
    And I click on Edit button from "NOTES" section website
    And I enter 201 characters in the NOTES section website
    And I click on SAVE button from "NOTES" section website
    When I hover over exclamation mark HINTS and NOTES links are displayed
    Then upon hovering the exclamation mark 200 character are displayed in the NOTES section
