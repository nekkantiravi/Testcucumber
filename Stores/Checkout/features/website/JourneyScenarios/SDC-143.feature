# Author: Stores Domain Checkout Team
# Story: SDC-143 - Testing Only: Hide Field if Unavailable
# Date Created: 11/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-143
Feature: As an associate, I dont want to view empty fields in item details,
      so that I am no confused on the actual details that apply to an item.

  Scenario: Add UPC that does not have color, size or WebID.
    Given I am on "product discovery"
    When I add an item to the Checkout bag that "does not have a color size WebID"
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can see the color size and WebID titles do not display

  Scenario: Add UPC that does not have color.
    Given I am on "product discovery"
    When I add an item to the Checkout bag that does not have a color
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can see the color title does not display

  Scenario: Add UPC that does not have size.
    Given I am on "product discovery"
    When I add an item to the Checkout bag that does not have a size
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can see the size title does not display

  Scenario: Add UPC that does not have WebID.
    Given I am on "product discovery"
    When I add an item to the Checkout bag that does not have a WebID
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can see the WebID title does not display




