# Author: Claudiu Chirila
# Story: SDE-58 - OmniClient : Name/Zip Search
# Date Created: 07/07/2017
# Date Signed Off:


Feature: As an associate, I want to search for customers using their Name/Zip Code.so that I can locate customer profiles and add customers to my book

  @domain_stores @omniclient @story_SDE-58 @website @bcom
  Scenario: Error popup is received when user performs a search without having information added in all fields

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I add a new BLM Client: "BLM1" "TestData" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "BLM1" in the input field
    And I click on the omniclient search button
    Then error popup with required fields is received "Zip Code must be"
    When I click on ok button from error popup search client
    And I enter last name "TestData" in the input field
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


  @domain_stores @omniclient @story_SDE-58 @website @bcom
  Scenario: Search is successfully done after the user inputs all required fields

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
#    And I add a new BLM Client: "BLMsuccess" "TestData"
#    And I click yes button on the credit card required attention popup
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "44053" in the input field
    And I click on the omniclient search button
    Then the search result page is displayed with the following columns BLM:
      | Name                                      |
      | Address                                   |
      | Phone Number                              |
      | Affiliated with Me                        |
      | Affiliated to Other Sales Professional(s) |
    And the searched client is displayed in the search results page "TEST1 TEST ACCOUNT"
    When I click on the searched client "TEST1 TEST ACCOUNT"
    Then the Create new Customer page is displayed BLM


  @domain_stores @omniclient @story_SDE-58 @website @bcom
  Scenario: Search results doesn't return any existing data

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
#    And I add a new BLM Client: "NotAclient1" "TestData"
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "NOT CLIENT" in the input field
    And I enter last name "unexisting" in the input field
    And I enter zip code "22211" in the input field
    And I click on the omniclient search button
    Then the search client no results page is displayed

