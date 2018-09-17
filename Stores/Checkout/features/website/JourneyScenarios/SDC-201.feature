# Author: Stores Domain Checkout Team
# Story: SDC-201 - Checkout :: Strikethrough of Original Price
# Date Created: 12/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-201 @wip
Feature: As an associate, I want to see the original price of an item,
  so that I can convey the savings to my customer.

  Scenario: An associate verifies the item prices (original and actual) verbiage.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "1719317" in home page
    Then I can see the product card
    When I select a Small in Orange
    And I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
   	Then I see the item is in a markdown status -the price of the item is lower than the original-
   	And I see the original price of the item has a strikethrough
   	And I see the actual price of the item also displays
