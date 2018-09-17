# Author: Stores Domain Checkout Team
# Story: SDC-113 - Checkout :: View Bag Header Info
# Date Created: 10/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-113
Feature: As an associate, I want to view information about a bag with items,
  so that I can easily gather information about my transaction.

  Scenario: An associate verifies the bag header info.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
#    When I click the View Bag button and navigate to the bag
    When I click on the bag icon
   	Then I see the item added to the bag
   	And I can see bag information displayed below the navigation header bar
