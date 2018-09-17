# Author: Stores Domain Checkout Team
# Story: SDC-205 - Checkout :: Bag Icon in Empty Bag
# Date Created: 12/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-205
Feature: As an associate, I want to see an icon in an empty bag,
  so that I can have a visual indication that the bag is empty.

#  Scenario: An associate verifies the icon for empty bag without adding item.
#    Given I am on product discovery
#    Then I can see the landing page
#    When I press the Bag in the Header
#    Then I can see Phase one Checkout empty bag view
#
#
#  Scenario: An associate verifies the icon for empty bag after remove item.
#    Given I am on product discovery
#    Then I can see the landing page
#    When I search for "672275307384" in home page
#    Then I can see the product card
#    And I press the Add to bag
#    Then I can see the added to bag confirmation layer
#    When I click the View Bag button and navigate to the bag
#    When I press the Bag in the Header
#    Then I should be able to see the bag view
#    When I remove an item from UAT
#    Then I can see Phase one Checkout empty bag view


  Scenario: An associate verifies the icon for empty bag after add item / suspend bag.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "672275307384" in home page
    Then I can see the product card
    And I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can view the UAT suspend button
    When I press the UAT suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press OK on the suspended confirmation overlay
    Then I see the suspended bag confirmation overlay
    And I press OK on the suspended confirmation overlay
    Then I can see Phase one Checkout empty bag view
