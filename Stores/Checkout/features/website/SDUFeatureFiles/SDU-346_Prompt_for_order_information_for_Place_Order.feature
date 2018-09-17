# Author: Stores Domain Checkout Team
# Story: SDU-346 - Prompt for order information for Place Order
# Date Created: 04/13/2017
# Date Signed Off:

@story_SDU-346 @project_checkout @release_1708
Feature: As an associate, I want to be prompted to input shipping information if my transaction is of
  type "order"

  @Macy's @take
  Scenario: Macy's - I proceed to checkout with a transaction that is not "place order"
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add an item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#    Then I can see the bag fee overlay
#    When I add "0" bags
    Then I can see the mock tendering screen
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @send
  Scenario: Macy's - I proceed to checkout with a transaction that is "place order"
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - I proceed to checkout with a transaction that is not "place order"
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add an item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I press the checkout button
    Then I can see the bag fee overlay
    When I add "0" bags
    Then I can see the mock tendering screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @send
  Scenario: Bloomingdale's - I proceed to checkout with a transaction that is "place order"
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page