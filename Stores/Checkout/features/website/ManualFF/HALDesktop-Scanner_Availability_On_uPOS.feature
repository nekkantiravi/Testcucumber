# Author: Ana Ghergut - Stores Domain Checkout Team
# Story: SDU-117 - Scanner availability on uPOS
# Date Created: 12/05/2017
# Date Signed Off:

@domain_stores @project_checkout @manual @HALDesktop @release_1723 @story_SDU-117
Feature:  As an associate using uPOS at a wrap stand, I want to be able to scan merchandise and CRLs and for the scanner to be disabled when I do not need to scan,
  so my path through the transaction is clear

  Scenario: HALDesktop - The scanner is enabled if I am on the bag screen
    Given I am on Hal Desktop
    When I open the Bag screen
    And I scan an UPC using the scanner
    Then The product is added in the bag
    And The Bag screen is displayed
    When I scan again an UPC using the scanner
    Then The product is added in the bag
    And The Bag screen is displayed

  Scenario: HALDesktop - The scanner is disabled if I am on the payment screen
    Given I am on Hal Desktop
    When I add a product in the bag
    And I click on Checkout and add the number of bags
    And The payment tab is displayed
    And I scan an UPC using the scanner
    Then The product is not added in the bag

  Scenario: HALDesktop - The scanner is disabled if I am on the receipt screen
    Given I am on Hal Desktop
    When I add a product in the bag
    And I checkout the item
    And I simulate a swipe
    And The Receipt Screen is displayed
    And I scan an UPC using the scanner
    Then The product is not added in the bag

