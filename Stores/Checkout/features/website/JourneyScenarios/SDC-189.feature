# Author: Stores Domain Checkout Team
# Story: SDC-189 - Checkout :: Add 8-digit UPC validation
# Date Created: 12/06/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-189
Feature: As an associate, I want to check the validation process for adding 8-digit UPC.

  Scenario: An associate verifies error message while adding 8-digit UPC.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "43739967" in home page
    Then I should see a message that no items were found

  Scenario Outline: An associate successfully adds a 8-digit UPC with leading zeros.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "<UPC>" in home page
    Then I can see the product card
    When I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
   	Then I see the item added to the bag

   	Examples:
   	| UPC            |
   	| 00043739967    |
   	| 000043739967   |
   	| 0000043739967  |
   	| 00000043739967 |
