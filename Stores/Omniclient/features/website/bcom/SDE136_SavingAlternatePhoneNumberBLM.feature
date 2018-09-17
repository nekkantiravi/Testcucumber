# Author: Traci Morris
# Story: SDE-136 - OmniClient :: Saving Alternate Phone Number
# Date Created: 06/12/2017
# Date Signed Off:

Feature: As an associate, I want the phone number provided by the customer during the add to book
  process to be the preferred phone number so I know what phone number is best to contact them when
  viewing their profile/information.

  @manual @domain_stores @omniclient @story_SDE-136 @website @bcom
  Scenario: Add Client that is in CC2 but not in our DB via a Credit Card swipe and update phone # returned
  from CC2.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen
    When I select Customer Name Link on Search Results Screen
    Then I should see Create New Customer Screen
    When I enter "4402562566" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section

  @manual @domain_stores @omniclient @story_SDE-136 @website @bcom
  Scenario: Add Client to My Book (in OCL DB), but DOES NOT have a relationship with logged on Associate
  and update address info
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen
    When I select Add to Book on Search Results Screen
    Then I should see Create New Customer Screen
    When I enter "4407857855" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Customers Profile Screen
    When I select the Manage Customer tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section

  @manual @domain_stores @omniclient @story_SDE-136 @website @bcom
  Scenario: Add Client that is in CC2 but not in our DB via a phone # search and update the phone #
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I enter "ALTERNATE" in the "first name" textbox
    And I enter "PHONE" in the "last name" textbox
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
    And I swipe an individualized Credit Card
    And I select Save button
    Then I should see update overlay (side by side comparison)
    When I select ok
    Then I should see the Customers Profile Screen
    When I select the Manage Customer tab
    Then I should see the Primary Info Screen
    And I should see the original Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section



