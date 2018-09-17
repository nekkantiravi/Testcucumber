# Author: Traci Morris
# Story: SDE-136 - OmniClient :: Saving Alternate Phone Number
# Date Created: 06/12/2017
# Date Signed Off:

Feature: As an associate, I want the phone number provided by the customer during the add to book
  process to be the preferred phone number so I know what phone number is best to contact them when
  viewing their profile/information.

  @manual @domain_stores @omniclient @story_SDE-136 @website @mcom
  Scenario: Add Client that is in CC2 but not in our DB via a Credit Card swipe and update phone # returned
  from CC2.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen
    When I select Client Name Link on Search Results Screen
    Then I should see Create New Client Screen
    When I enter "4402562566" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section

  @manual @domain_stores @omniclient @story_SDE-136 @website @mcom
  Scenario: Add Client to My Book (in OCL DB), but DOES NOT have a relationship with logged on Associate
  and update address info
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen
    When I select Add to Book on Search Results Screen
    Then I should see Create New Client Screen
    When I enter "4407857855" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section

  @manual @domain_stores @omniclient @story_SDE-136 @website @mcom
  Scenario: Add Client that is in CC2 but not in our DB via a phone # search and update the phone #
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I enter "9639639632" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then I should see Search Results Screen
    When I select Client Name Link on Search Results Screen
    Then I should see Create New Client Screen
    When I enter updated phone # "4164164166" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section



  @manual @domain_stores @omniclient @story_SDE-136 @website @mcom
  Scenario: Log on as another associate and add client to your book via a phone # search and update the phone #
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I enter "4406529874" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then I should see Search Results Screen
    When I select the Add to Book button
    Then I should see Create New Client Screen
    When I enter updated phone # "4164164166" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section


  @manual @domain_stores @omniclient @story_SDE-136 @website @mcom
  Scenario: Log on as another associate and add client to your book from the clients profile screen
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I enter "4406529874" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then I should see Search Results Screen
    When I select Client Name Link on Search Results Screen
    Then I should see the Clients Profile Screen
    When I select the Add to Book button
    Then I should see Create New Client Screen
    When I enter updated phone # "4754754755" in the "phone" textbox
    And I enter "TESTING" in the "hint" textbox
    And I select Phone as Preferred Contact Method
    And I select Save button
    Then I should see the Clients Profile Screen
    When I select the Manage Client tab
    Then I should see the Primary Info Screen
    And I should see the updated Phone # displayed in the Preferred Information section
    And I should see the original Phone # displayed in the Primary Information section