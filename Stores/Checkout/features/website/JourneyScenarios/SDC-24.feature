# Author: Stores Domain Checkout Team
# Story: SDC-24 - Checkout :: Remove an Item
# Date Created: 10/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-24
Feature: As an associate, I want the ability to remove an item from a bag,
  so that I can easily continue with a transaction if a customer changes her mind.

  Scenario: An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on product discovery
    When I search for "672275307384" in home page
    Then I can see the product card
    And I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I should be able to see totals information of all items added
    When I remove an item from UAT
    Then I can see Checkout empty bag view

  Scenario: An associate wants to delete an item from the bag and view the items is removed.
    Given I am on product discovery
    When I add 2 items by webid to bag via product discovery
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I should be able to see totals information of all items added
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view
