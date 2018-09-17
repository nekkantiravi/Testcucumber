# Author: Stores Domain Checkout Team
# Story: SDC-39 - Items added to bag in under 1 second 
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-39
Feature: As an associate, I want items added to a bag in under one second, 
so that I can help make customers make purchases as quickly and efficiently as possible.

  Scenario: An associate adds an item (with eWaste fee) to the bag, navigates to the bag,
  and sees fee details for an item with eWaste fee.
    Given I am on Sales Trans
    When I add an item with eWaste fee to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
   	Then I can view the items were added to the bag in under one second using the Kibana dashboard
