# Author: Traci Morris
# Story: SDE-143 - OmniClient :: Saving Alternate Address
# Date Created: 06/13/2017
# Date Signed Off:

Feature: As an associate, I want the address provided by the customer during the add to book process to be
  the preferred address so I can clearly identify their preferred information when viewing their profile.

  @manual @domain_stores @omniclient @story_SDE-143 @website @bcom
  Scenario: Add Client that is in CC2 but not in our DB via a Credit Card swipe and update address info
  returned from CC2.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe the Credit Card
    Then I should see Search Results Screen
    When I select Customer Name Link on Search Results Screen
    Then I should see Create New Customer Screen
    When I enter "985 STATE RD" in "address" textbox
    And I enter "STATEVILLE" in the "city" textbox
    And I enter "FL" in the "state" textbox
    And I enter "45897" in the "zip" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Customer Profile Screen
    When I select the Manage Customer tab
    Then I should see the Primary Info Screen
    And I should see the updated Address displayed in the Preferred Information section
    And I should see the original Address displayed in the Primary Information section

  @manual @domain_stores @omniclient @story_SDE-143 @website @mcom
  Scenario: Add Client to My Book (in OCL DB), but DOES NOT have a relationship with logged on Associate
  and update address info.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe the Credit Card
    Then I should see Search Results Screen
    When I select Add to Book on Search Results Screen
    Then I should see Add New Client Screen
    When I enter "234 TESTING WAY" in "address" textbox
    And I enter "GREENVILLE" in the "city" textbox
    And I enter "TX" in the "state" textbox
    And I enter "44067" in the "zip" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Address displayed in the Preferred Information section
    And I should see the original Address displayed in the Primary Information section

