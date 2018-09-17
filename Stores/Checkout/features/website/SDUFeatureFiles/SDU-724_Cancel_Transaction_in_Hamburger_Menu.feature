    #Author: Stores Domain Checkout Team
    #Story: SDC-724 - Checkout :: Move Cancel Transaction to the Hamburger Menu
    #Date Created: 07/28/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-724
Feature: As an associate, I want a quick and easy way to clear a transaction, so that I can start a new one if my
  customer changers their mind, or if I make a mistake.

  @Macy's
  Scenario: Macy's - As an associate, I shouldn't see the cancel transaction button if there are no items in the bag
  Given I am on "Macy's Sales Trans"
  When I click on Hamburger icon
  Then I should not be able to see the cancel transaction button

  @Macy's @Send
  Scenario: Macy's - As an associate, I should see the cancel transaction button when there is an item in the bag.
  Given I am on "Macy's Sales Trans"
  When I add an item to the Checkout bag
  Then I can see the added to bag toast message
  And the toast message fades away after 3 seconds
  Then I click on the bag icon
  And I click on Hamburger icon
  Then I see the Cancel Transaction button
  When I cancel the transaction
  Then I am on "Macy's Sales Trans"
  And I click on Hamburger icon
  Then I should not be able to see the cancel transaction button

  @Bloomingdale's
  Scenario: Bloomingdale's - As an associate,
  I shouldn't see the cancel transaction button if there are no items in the bag
    Given I am on "Bloomingdale's's Sales Trans"
    When I click on Hamburger icon
    Then I should not be able to see the cancel transaction button

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate,
  I should see the cancel transaction button when there is an item in the bag.
    Given I am on "Bloomingdale's's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 3 seconds
    Then I click on the bag icon
    And I click on Hamburger icon
    Then I see the Cancel Transaction button
    When I cancel the transaction
    Then I am on "Bloomingdale's's Sales Trans"
    And I click on Hamburger icon
    Then I should not be able to see the cancel transaction button