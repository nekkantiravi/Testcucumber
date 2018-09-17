# Author: Claudiu Chirila
# Story: SDE-58 - OmniClient : Name/Zip Search
# Date Created: 07/07/2017
# Date Signed Off:

Feature: As an associate, I want to search for customers using their Name/Zip Code.so that I can locate customer
  profiles and add customers to my book

  @domain_stores @omniclient @story_SDE-58 @website @mcom
  Scenario: Error popup is received when user performs a search without having information added in all fields
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "Search" "SDE58" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "search" in the input field
    And I click on the omniclient search button
    Then error popup with required fields is received "Zip Code must be"
    When I click on ok button from error popup search client
    And I enter last name "SDE58" in the input field
    And I click on the omniclient search button
    Then error popup with required fields is received "Zip Code must be"
    When I click on ok button from error popup search client
    And I enter zip code "12" in the input field
    And I click on the omniclient search button
    Then error popup with required fields is received "Zip Code must be"
    When I click on ok button from error popup search client
    And I erase first and last name
    And I enter zip code "12345" in the input field
    And I click on the omniclient search button
    Then error popup with required fields is received "A minimum of 1 letter of a First and Last "

  @domain_stores @omniclient @story_SDE-58 @website @mcom
  Scenario: Search is successfully done after the user inputs all required fields
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
#    And I add a new Macys Client: "Success1" "SDE58"
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "44053" in the input field
    And I click on the omniclient search button
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "TEST1 TEST ACCOUNT"
    When I click on the searched client "TEST1 TEST ACCOUNT"
    Then the Create new Client page is displayed

  @domain_stores @omniclient @story_SDE-58 @website @mcom
  Scenario: Search results doesn't return any existing data
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
#    And I add a new Macys Client: "NOT" "Aclient"
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "NOT CLIENT" in the input field
    And I enter last name "unexisting" in the input field
    And I enter zip code "22211" in the input field
    And I click on the omniclient search button
    Then the search client no results page is displayed
