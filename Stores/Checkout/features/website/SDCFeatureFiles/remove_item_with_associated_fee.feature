# Author: Stores Domain Checkout Team
# Story: SDC-43 - Testing Only: remove item with associated fee
# Date Created: 11/17/2016
# Date Signed Off:

@deprecated @domain_stores @project_checkout @release_17 @story_SDC-43
Feature: As an associate, I want the ability to remove an item from a bag,
  so that I can easily continue with a transaction if a customer changes her mind.

  @Macy's @Take
  Scenario: Macy's - An associate wants to Remove an item from the bag that has an associated fee.
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    And I click on the bag icon
    Then I should see the fee associated in the bag
    When I remove an item from the Checkout bag
    Then I can see that the fee associated is removed from the bag
    And I can see the sales trans landing page

 #This scenario would fail till the product fees are added back to Bloomys
  @Bloomingdale's @Take
  Scenario: Bloomingdale's - An associate wants to Remove an item from the bag that has an associated fee.
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "20714001940" item to the Checkout bag
    And I click on the bag icon
    Then I should see the fee associated in the bag
    When I remove an item from the Checkout bag
    Then I can see that the fee associated is removed from the bag
    And I can see the sales trans landing page


#  Scenario: Macy's - An associate wants to add two items to the bag. Only one contains a fee. when the
#    associate removes merchandise that has an associated fee the bag is not empty.
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag that has an associated fee
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I add a second item to the Checkout bag
#    Then I can see the added to bag toast message
#    When I press the view bag button
#    Then I can see the item information
#    And I can see the associated fee added to the bag
#    When I remove an item from the Checkout bag
#    Then I can view that the item and associated fee is removed from the bag
#    And I can view the second item is still in the bag
