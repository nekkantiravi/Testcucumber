# Author: Traci Morris
# Story: SDE-61 - OmniClient :: Add Client for Operational Credit Cards
# Date Created: 07/07/2017
# Date Signed Off:

Feature: As a developer, I want the add client process to create an operational CC2 record once an
  OmniClient ID has been created for a customer being added with a credit card that is not in the system
  so that purchases for that customer will still be fed into sales posting.

  @manual @domain_stores @omniclient @story_SDE-61 @website @bcom
  Scenario: Add Client that is NOT in CC2 and is NOT in our DB via an Operational Card swipe.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an operational Credit Card
    Then I should see Search Results Screen and see the following:
      | Name           |
    When I select Client Name Link on Search Results Screen
    Then I should see Create New Client Screen
    When I enter "25 MAIN ST" in "address" textbox
    And I enter "LORAIN" in the "city" textbox
    And I enter "OH" in the "state" textbox
    And I enter "44052" in the "zip" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    When I enter "Credit Card Testing" in "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Transactions tab
    Then I should see the transactions displayed

  @manual @domain_stores @omniclient @story_SDE-61 @website @bcom
  Scenario: Add Client that is NOT in CC2 and is NOT in our DB via an Operational Card swipe on Create New Customer screen
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I enter "OPERATIONAL" in the "first name" textbox
    And I enter "ADD" in the "last name" textbox
    And I enter "95147" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the No Search results screen is displayed in Omniclient search results page
    And I click the here icon to create a new client
    Then I should see Create New Customer Screen
    When I enter "985 STATE RD" in "address" textbox
    And I enter "STATEVILLE" in the "city" textbox
    And I enter "FL" in the "state" textbox
    And I enter "4164164166" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I swipe an operational Credit Card
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Transactions tab
    Then I should see the transactions displayed

