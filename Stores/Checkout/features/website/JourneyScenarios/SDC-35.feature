 #Author: Stores Domain Checkout Team
 #Story: SDC-35 - Checkout :: Suspend a Bag
 #Date Created: 09/22/2016
 #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDC-35
Feature: As an associate, I want to suspend a bag to be retrieved at a later time,
  so that I can seamlessly complete a transaction for a customer regardless of which device
  I start the transaction on.

  Scenario: An associate suspends a bag and is returned to the home screen.
    Given I am on product discovery
    When I search for "672275307384" in home page
    Then I can see the product card
    Then I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay suspend button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I am returned to product discovery

  Scenario: An associate attempts to suspend a bag and then presses cancel they are returned to the bag screen.
    Given I am on product discovery
    When I search for "672275307384" in home page
    Then I can see the product card
    Then I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  Scenario: An associate wants to suspend for the maximum number of items in a transaction.
    Given I am on product discovery
    When I add 50 items by webid to bag via product discovery
    And I access Checkout bag screen
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay suspend button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I am returned to product discovery

