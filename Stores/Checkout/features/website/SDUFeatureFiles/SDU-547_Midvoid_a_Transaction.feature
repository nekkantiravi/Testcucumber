# Author: Ingrid - Stores Domain Checkout Team
# Story: SDU-547 - Midvoid a Transaction
# Date Created: 05/22/2017
# Date Signed Off:

@StorySDU-547
Feature: As an associate I want a quick and easy way to clear a transaction, so that I can start a new one if my customer changers her mind, or if I make a mistake

  @Macy's @Send
  Scenario: Macy's - An associate cancels the transaction
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Take
  Scenario: Macy's - An associate deletes all items from the bag
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I remove an item from the Checkout bag
    Then I can see Checkout empty bag view
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate cancels the transaction
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - An associate deletes all items from the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I remove an item from the Checkout bag
    Then I can see Checkout empty bag view
    When I call Cancel
    Then I can see the sales trans landing page