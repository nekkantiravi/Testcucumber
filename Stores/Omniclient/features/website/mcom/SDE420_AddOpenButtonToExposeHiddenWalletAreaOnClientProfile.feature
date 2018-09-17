# Author: Claudiu Chirila
# Story: SDE-420 - Add Open button to expose hidden wallet area on client profile
# Date Created:
# Date Signed Off:

Feature: As a Macy's Clienteling User I need to have the wallet area of the client profile hidden from view
  so that customers do not get nervous about all of the credit accounts that they see listed in the application.

  @domain_stores @omniclient @story_SDE-420 @website @mcom
  Scenario: Hide Shopping Passes , Hide Shopping Passes buttons and Wallet link are displayed in the Client Profile page
    AND Credit Card with Shopping Pass DOES display
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST4" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST4 TEST ACCOUNT"
    Then the link named Wallet is displayed in the header of the wallet section
    And the Show Shopping Passes link is displayed in the header of the wallet section
    When I click on the Show Shopping Passes link
    Then the Wallet info is displayed in Client Profile view
    And the Show Shopping Passes button changes to Hide Shopping Passes
    And Only Credits Cards with Shopping Passes will display
    When I click on the Hide Shopping Passes button
    Then the Wallet info is not displayed in Client Profile view

  @domain_stores @omniclient @story_SDE-420 @website @mcom
  Scenario: Hide Shopping Passes , Hide Shopping Passes buttons and Wallet link are displayed in the Client Profile page
    AND Credit Card without Shopping Pass does NOT display.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KELLY" in the input field
    And I enter last name "BASS" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    And I click on the searched client "KELLY BASS"
    And I click on the Show Shopping Passes link
    And Credit Card will not be displayed


  @domain_stores @omniclient @story_SDE-420 @website @mcom
  Scenario: Wallet page is displayed when Wallet link is clicked
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST4" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST4 TEST ACCOUNT"
    When I click on the Wallet link from the header of the wallet section
    Then the Wallet page is displayed