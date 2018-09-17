# Author: Stores Domain Checkout Team
# Story: SDC-24 - Checkout :: Remove an Item
# Date Created: 10/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-24
Feature: As an associate, I want the ability to remove an item from a bag,
  so that I can easily continue with a transaction if a customer changes her mind.

  @Macy's @Send
  Scenario: Macy's - An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Macy's @Send
  Scenario: Macy's - An associate wants to delete an item from the bag and view the items is removed.
    Given I am on "Macy's Sales Trans"
    When I add 2 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate wants to delete an item from the bag and view the items is removed.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 2 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view



