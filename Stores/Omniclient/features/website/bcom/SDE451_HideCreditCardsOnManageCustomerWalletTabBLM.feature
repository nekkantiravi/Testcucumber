# Author: Traci Morris
# Story: SDE-451 - Hide Credit cards on the Manage Customer Wallet tab
# Date Created: 01/09/2018
# Date Signed Off:

Feature: As a Bloomingdale's user, I want the Credit Cards in the Manage Customer Wallet tab to be hidden, and only exposed
  when I click into them, to prevent customer information from being exposed and to help customers feel better about
  the way we are protecting their data.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button

  @domain_stores @omniclient @story_SDE-451 @website @bcom
  Scenario: Show Credit Cards - Shopping Pass and No Shopping Pass
#    Search on client that has a Shopping Pass Card & a No Shopping Pass Card
#    Initial test will be in QA (KLONDIKE BARZ 44055) - will need to update info once it is in UA
#    Update client before running test
    And I enter first name "KLONDIKE" in the input field
    And I enter last name "BARZ" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "KLONDIKE BARZ"
    And I click on the Wallet link from the header of the wallet section
    Then the Wallet page is displayed
    And the Show Other Cards link is displayed in the header of the Wallet screen
    And only Credit Cards with Shopping Passes will display on Wallet Screen BLM
    When I click on the Show Other Cards link
    Then the other Credit Cards are displayed on Wallet screen
    And the Show Other Cards link changes to Hide Other Cards
    When I click on the Hide Other Cards link
    Then only Credit Cards with Shopping Passes will display on Wallet Screen BLM


    @domain_stores @omniclient @story_SDE-451 @website @bcom
  Scenario: Show Credit Cards - No Shopping Pass
#    Search on client that has a Credit Card, but it is NOT flagged as a Shopping Pass
#    Initial test will be in QA (KRISTEN BROWN 44055) - will need to update info once it is in UA
#    Update client before running test
    And I enter first name "KRISTEN" in the input field
    And I enter last name "BROWN" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "KRISTEN BROWN"
    And I click on the Wallet link from the header of the wallet section
    Then the Wallet page is displayed
    And the Show Other Cards link is displayed in the header of the Wallet screen
    And no Credit Cards are displayed on Wallet Screen
    When I click on the Show Other Cards link
    Then the other Credit Cards are displayed on Wallet screen
    And the Show Other Cards link changes to Hide Other Cards
    When I click on the Hide Other Cards link
    Then no Credit Cards are displayed on Wallet Screen