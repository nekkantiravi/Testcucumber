# Author: Stores Domain Checkout Team
# Story: SDC-120 - Checkout :: TESTING: Suspending from Header
# Date Created: 12/05/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-120
Feature: As an associate, I want to Suspend a Bag from the Header in the Customer menu,
  so that I can quickly service my customers from one device to another.

  Scenario: An associate suspends a bag and is returned to the home screen.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
#    When I click the View Bag button and navigate to the bag
#    Then I can view the PDP Header
#    When I press the customer icon
#    Then I can see the customer bag
#    When I press the Header Suspend button
    And I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay suspend button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
#    Then I am returned to New POS Sample webapp

  Scenario: An associate attempts to suspend a bag and then presses cancel they are returned to the bag screen.
    Given I am on Sales Trans
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
#    When I click the View Bag button and navigate to the bag
#    Then I can view the PDP Header
#    When I press the customer icon
#    Then I can see the customer bag
#    When I press the Header Suspend button
    And I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen

  Scenario: An associate suspends a bag on the PDP side of the application.
    Given I am on Sales Trans
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
#    When I click the View Bag button and navigate to the bag
#    Then I can view the PDP Header
#    When I press the customer icon
#    Then I can see the customer bag
#    When I press the Header Suspend button
    And I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay suspend button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
#    Then I am returned to New POS Sample webapp

  Scenario: An associate attempts to suspend a bag and then presses cancel on the PDP side of the application.
    Given I am on Sales Trans
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
#    When I click the View Bag button and navigate to the bag
#    Then I can view the PDP Header
#    When I press the customer icon
#    Then I can see the customer bag
#    When I press the Header Suspend button
    And I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen
