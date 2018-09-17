# Author: Stores Domain Checkout Team
# Story: SDC-142 - Checkout :: Fee Details in Bag View
# Date Created: 11/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-142
Feature: As an associate, I want to see Fee Details, so that I can clearly communicate these amounts to my customer.

  @manual
  Scenario: An associate adds an item (with eWaste fee) to the bag, navigates to the bag,
  and sees fee details for an item with eWaste fee.
    Given I am on Sales Trans
    When I add an item with eWaste fee to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
    And I should be able to see item details with eWaste fee

  @manual
  Scenario: An associate adds an item (with CDA FCLTY CHG fee) to the bag, navigates to the bag,
  and sees fee details for an item with CDA FCLTY CHG fee.
    Given I am on Sales Trans
    When I add an item with CDA FCLTY CHG fee to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
    And I should be able to see item details with CDA FCLTY CHG fee

  @manual
  Scenario: An associate adds an item (with PIF fee) to the bag, navigates to the bag,
  and sees fee details for an item with PIF fee.
    Given I am on Sales Trans
    When I add an item with PIF fee to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
    And I should be able to see item details with PIF fee

  @manual
  Scenario: An associate adds an item (with RSF fee) to the bag, navigates to the bag,
  and sees fee details for an item with RSF fee.
    Given I am on Sales Trans
    When I add an item with RSF fee to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
    And I should be able to see item details with RSF fee

  @wip
  Scenario: An associate adds multiples items (with associated fee) to the bag, navigates to the bag,
  and sees fee details for all items.
    Given I am on Sales Trans
   	When I add an item to the Checkout bag that has an associated fee
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I add an item to the Checkout bag that has an associated fee
   	Then I can see the added to bag toast message
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
   	And I can see the Item Information
   	And I can see the associated fee added to the bag for all items

  @wip
  Scenario: An associate adds an item (without fee) to the bag, navigates to the bag,
  and sees fee details for an item without fee.
    Given I am on Sales Trans
    When I add an item without applicable fee to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
   	Then I should be able to see the bag view
   	And I see the item added to the bag
	  And I should be able to see item details with Dept/Class number
