# Author: Traci Morris
# Story: SDE-451 - Hide Credit cards on the Manage Customer Wallet tab
# Date Created: 01/09/2018
# Date Signed Off:

Feature: As a Macy's user, I want the Credit Cards in the Manage Customer Wallet tab to be hidden, and only exposed
  when I click into them, to prevent customer information from being exposed and to help customers feel better about
  the way we are protecting their data.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button

  @domain_stores @omniclient @story_SDE-451 @website @mcom
  Scenario: Show Credit Cards - Shopping Pass and No Shopping Pass
#    Search on client that has a Shopping Pass Card & a No Shopping Pass Card
#    Initial test will be in QA (TEST1 TEST ACCOUNT 44102)- will need to update info once it is in UA (     )
#    Update client before running test
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "44102" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST1 TEST ACCOUNT"
    And I click on the Wallet link from the header of the wallet section
    Then the Wallet page is displayed
    And the Show Other Cards link is displayed in the header of the Wallet screen
    And Cash option is displayed on Wallet Screen MCY
    And only Credit Cards with Shopping Passes will display on Wallet Screen BLM
    When I click on the Show Other Cards link
    Then the other Credit Cards are displayed on Wallet screen
    And the Show Other Cards link changes to Hide Other Cards
    When I click on the Hide Other Cards link
    Then only Credit Cards with Shopping Passes will display on Wallet Screen BLM


    @domain_stores @omniclient @story_SDE-451 @website @mcom
  Scenario: Show Credit Cards - No Shopping Pass
#    Search on client that has a Credit Card, but it is NOT flagged as a Shopping Pass
#    Initial test will be in QA (KYLE BAGEL 44055) - will need to update info once it is in UA
#    Update client before running test
    And I enter first name "KYLE" in the input field
    And I enter last name "BAGEL" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "KYLE BAGEL"
    And I click on the Wallet link from the header of the wallet section
    Then the Wallet page is displayed
    And the Show Other Cards link is displayed in the header of the Wallet screen
    And Cash option is displayed on Wallet Screen MCY
    And no Credit Cards are displayed on Wallet Screen
    When I click on the Show Other Cards link
    Then the other Credit Cards are displayed on Wallet screen
    And the Show Other Cards link changes to Hide Other Cards
    When I click on the Hide Other Cards link
    Then no Credit Cards are displayed on Wallet Screen