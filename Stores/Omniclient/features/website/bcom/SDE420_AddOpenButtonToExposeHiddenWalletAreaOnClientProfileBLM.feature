# Author: Claudiu Chirila
# Story: SDE-420 - Add Open button to expose hidden wallet area on client profile
# Date Created:
# Date Signed Off:

Feature: As BLM Clienteling User I need to have the wallet area of the client profile hidden from view
  so that customers do not get nervous about all of the credit accounts that they see listed in the application.

  @domain_stores @omniclient @story_SDE-420 @website @bcom
  Scenario: Hide Shopping Passes , Hide Shopping Passes buttons and Wallet link are displayed in the Client Profile page

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "45040" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST1 TEST ACCOUNT"
    Then the link named Wallet is displayed in the header of the wallet section
    And the Show Shopping Passes link is displayed in the header of the wallet section
    When I click on the Show Shopping Passes link
    Then the Wallet info is displayed in Client Profile view
    And the Show Shopping Passes button changes to Hide Shopping Passes
    And Only Credits Cards with Shopping Passes will display
    When I click on the Hide Shopping Passes button
    Then the Wallet info is not displayed in Client Profile view


  @domain_stores @omniclient @story_SDE-420 @website @bcom
  Scenario: Hide Shopping Passes , Hide Shopping Passes buttons and Wallet link are displayed in the Client Profile page
  AND Credit Card without Shopping Pass does NOT display.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KANDY" in the input field
    And I enter last name "BALL" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    And I click on the searched client "KANDY BALL"
    And I click on the Show Shopping Passes link
    And Credit Card will not be displayed


  @domain_stores @omniclient @story_SDE-420 @website @bcom
  Scenario: Wallet page is displayed when Wallet link is clicked
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "45040" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST1 TEST ACCOUNT"
    And I click on the Wallet link from the header of the wallet section
    Then the Wallet page is displayed