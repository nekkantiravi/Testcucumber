# Author: Stores Domain Checkout Team
# Story: SDC-17 - Checkout :: Add Item to bag
# Date Created: 10/02/2017
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-17
Feature: As an associate, I want to add an item to a bag by scanning the UPC barcode, so that I can quickly add items and expedite a purchase for my customers.

  Scenario: An associate scans invalid UPC barcode using peripherals
    Given I am on Sales Trans
    When I access Checkout bag screen
    Then I can see Checkout empty bag view
    And I scan invalid UPC barcode using peripherals(printers, scanners, Cru)
    Then I should see an error message
    And I should be able to clear the error message
    And I should be redirected to bag screen

  Scenario: An associate scans valid UPC barcode using peripherals and verifies automatic scrolling to bottom
    Given I am on Sales Trans
    When I access Checkout bag screen
    Then I can see Checkout empty bag view
    And I scan valid UPC barcode using peripherals(printers, scanners, Cru)
    Then I should see item added to bag
    And I should see application automatically scrolls to the bottom
    And I can see the item information

  Scenario: An associate scans valid UPC barcode using peripherals and verifies if total updates
    Given I am on Sales Trans
    When I access Checkout bag screen
    Then I can see Checkout empty bag view
    And I scan valid UPC barcode using peripherals(printers, scanners, Cru)
    Then I should see item added to bag
    And I should see application automatically scrolls to the bottom
    And I can see the item information
    And I should be able to see totals information updated

  Scenario: An associate scans "50" valid UPC barcode using peripherals
    Given I am on Sales Trans
    When I access Checkout bag screen
    Then I can see Checkout empty bag view
    And I scan "50" valid UPCs barcode using peripherals(printers, scanners, Cru)
    Then I should see 50 items added to bag
    When I scan 51st item into the bag
    Then I should view the item limit error message
    And I should be able to clear the error message
    And I should be redirected to bag screen
