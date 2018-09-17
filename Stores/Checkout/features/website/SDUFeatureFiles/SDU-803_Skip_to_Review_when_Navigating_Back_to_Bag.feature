    #Author: Stores Domain Checkout Team
    #Story: SDC-803 - Checkout :: Skip to Review when Navigating Back to Bag
    #Date Created: 07/27/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-803
Feature: As an associate, I want to be able to move about the transaction as needed, without having to step through each
  screen every time, so that I can quickly execute a transaction.

  @Macy's @Send
  Scenario: Macy's - As an associate, I am returned to the shipping method overlay
  if I navigate away from the checkout steps.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I close the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Send
  Scenario: Macy's - As an associate, I am returned to the shipping information overlay
  if I navigate away from the checkout steps.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    When I close the overlay
    Then I should be able to see the bag view
    When I press the checkout button
    Then I see the same as Shipping prompt
    When I press the Back button
    Then I see the pre-populated Shipping information screen
    When I close the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Send
  Scenario: Macy's - As an associate, I am returned to the shipping information overlay
  if I navigate away from the checkout steps.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    When I uncheck the same as shipping checkbox
    Then I can see the Input Billing Information screen
    When I input Billing Information
    And I click on Next Step button
    Then I see the Order Review Overlay
    When I close the overlay
    Then I should be able to see the bag view
    When I press the checkout button
    Then I see the Order Review Overlay
    When I press the Back button
    Then I see the pre-populated Billing information screen
    When I close the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


#  @Macy's @Send
#  Scenario: Macy's - As an associate, I am returned to the order review overlay screen
# if I navigate away from the checkout steps.
#    Given I am on "Macy's Sales Trans"
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    Then I click on the bag icon
#    And I press the checkout button
#    Then I see the Shipping Method Overlay
#    And I can see the Standard option selected
#    When I click on Next Step button
#    Then I see the Shipping information Overlay
#    And I input the Shipping Information
#    And I click on Next Step button
#    Then I see the same as Shipping prompt
#    And I click on Next Step button
#    Then I see the Order Review Overlay
#    When I close the Order Review Overlay
#    Then I click on the bag icon
#    Then I can see the checkout button
#    When I press the checkout button
#    Then I see the Order Review Overlay
#    And I can see the selected Shipping Address in the Review overlay
#    When I click on Hamburger icon
#    And I click on Cancel Transaction button
#    Then I am on "Add Product" page

#  @Macy's @Send
#  Scenario: Macy's - As an associate, if I cancel the transaction,
#  my information entered will not remain on the next transaction
#    Given I am on "Macy's Sales Trans"
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    Then I click on the bag icon
#    And I press the checkout button
#    Then I see the Shipping Method Overlay
#    And I can see the Standard option selected
#    When I click on Next Step button
#    Then I see the Shipping information Overlay
#    And I input the Shipping Information
#    And I click on Next Step button
#    Then I see the same as Shipping prompt
#    And I click on Next Step button
#    Then I see the Order Review Overlay
#    When I close the Order Review Overlay
#    Then I click on Hamburger icon
#    And I cancel the transaction
#    Then I am on Sales Trans
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    Then I click on the bag icon
#    And I press the checkout button
#    Then I see the Shipping Method Overlay
#    When I click on Hamburger icon
#    And I click on Cancel Transaction button
#    Then I am on "Add Product" page

#  @Macy's @Send
#    Scenario: Macy's - As an associate, I am returned to the order review overlay screen
#     if I navigate away from the payment screen
#      Given I am on "Macy's Sales Trans"
#      When I add an item to the Checkout bag
#      Then I can see the added to bag toast message
#      And the toast message fades away after 2 seconds
#      Then I click on the bag icon
#      And I press the checkout button
#      Then I see the Shipping Method Overlay
#      And I can see the Standard option selected
#      When I click on Next Step button
#      Then I see the Shipping information Overlay
#      And I input the Shipping Information
#      And I click on Next Step button
#      Then I see the same as Shipping prompt
#      When I uncheck the same as shipping checkbox
#      Then I can see the Input Billing Information screen
#      And I input Billing Information
#      And I click on Next Step button
#      Then I see the Order Review Overlay
#      And I close the Order Review Overlay
#      Then I click on the bag icon
#      And I can see the checkout button
#      When I press the checkout button
#      Then I see the Order Review Overlay
#      When I tap on the Complete button
#      Then I can see the mock tendering screen
#      When I click on the bag icon
#      Then I can see the checkout button
#      When I press the checkout button
#      Then I see the Order Review Overlay
#      And I can see the selected Shipping Address in the Review overlay
#      And I can see the selected Billing Address in the Review overlay
#      When I click on Hamburger icon
#      And I click on Cancel Transaction button
#      Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate, I am returned to the shipping method overlay
  if I navigate away from the checkout steps.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I close the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate, I am returned to the shipping information overlay
  if I navigate away from the checkout steps.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    When I close the overlay
    Then I should be able to see the bag view
    When I press the checkout button
    Then I see the same as Shipping prompt
    When I press the Back button
    Then I see the pre-populated Shipping information screen
    When I close the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate, I am returned to the shipping information overlay
  if I navigate away from the checkout steps.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    When I uncheck the same as shipping checkbox
    Then I can see the Input Billing Information screen
    When I input Billing Information
    And I click on Next Step button
    Then I see the Order Review Overlay
    When I close the overlay
    Then I should be able to see the bag view
    When I press the checkout button
    Then I see the Order Review Overlay
    When I press the Back button
    Then I see the pre-populated Billing information screen
    When I close the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


#  @Bloomingdale's @Send
#  Scenario: Bloomingdale's - As an associate, I am returned to the order review overlay screen
#  if I navigate away from the checkout steps.
#    Given I am on "Bloomingdale's Sales Trans"
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    Then I click on the bag icon
#    And I press the checkout button
#    Then I see the Shipping Method Overlay
#    And I can see the Standard option selected
#    When I click on Next Step button
#    Then I see the Shipping information Overlay
#    And I input the Shipping Information
#    And I click on Next Step button
#    Then I see the same as Shipping prompt
#    And I click on Next Step button
#    Then I see the Order Review Overlay
#    When I close the Order Review Overlay
#    Then I click on the bag icon
#    Then I can see the checkout button
#    When I press the checkout button
#    Then I see the Order Review Overlay
#    And I can see the selected Shipping Address in the Review overlay
#    When I click on Hamburger icon
#    And I click on Cancel Transaction button
#    Then I am on "Add Product" page

#  @Bloomingdale's @Send
#  Scenario: Bloomingdale's - As an associate, if I cancel the transaction,
#   my information entered will not remain on the next transaction
#    Given I am on "Bloomingdale's Sales Trans"
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    Then I click on the bag icon
#    And I press the checkout button
#    Then I see the Shipping Method Overlay
#    And I can see the Standard option selected
#    When I click on Next Step button
#    Then I see the Shipping information Overlay
#    And I input the Shipping Information
#    And I click on Next Step button
#    Then I see the same as Shipping prompt
#    And I click on Next Step button
#    Then I see the Order Review Overlay
#    When I close the Order Review Overlay
#    Then I click on Hamburger icon
#    And I cancel the transaction
#    Then I am on Sales Trans
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    Then I click on the bag icon
#    And I press the checkout button
#    Then I see the Shipping Method Overlay
#    When I click on Hamburger icon
#    And I click on Cancel Transaction button
#    Then I am on "Add Product" page

#  @Bloomingdale's @Send
#    Scenario: Bloomingdale's - As an associate, I am returned to the order review overlay screen
#     if I navigate away from the payment screen
#      Given I am on "Bloomingdale's Sales Trans"
#      When I add an item to the Checkout bag
#      Then I can see the added to bag toast message
#      And the toast message fades away after 2 seconds
#      Then I click on the bag icon
#      And I press the checkout button
#      Then I see the Shipping Method Overlay
#      And I can see the Standard option selected
#      When I click on Next Step button
#      Then I see the Shipping information Overlay
#      And I input the Shipping Information
#      And I click on Next Step button
#      Then I see the same as Shipping prompt
#      When I uncheck the same as shipping checkbox
#      Then I can see the Input Billing Information screen
#      And I input Billing Information
#      And I click on Next Step button
#      Then I see the Order Review Overlay
#      And I close the Order Review Overlay
#      Then I click on the bag icon
#      And I can see the checkout button
#      When I press the checkout button
#      Then I see the Order Review Overlay
#      When I tap on the Complete button
#      Then I can see the mock tendering screen
#      When I click on the bag icon
#      Then I can see the checkout button
#      When I press the checkout button
#      Then I see the Order Review Overlay
#      And I can see the selected Shipping Address in the Review overlay
#      And I can see the selected Billing Address in the Review overlay
#      When I click on Hamburger icon
#      And I click on Cancel Transaction button
#      Then I am on "Add Product" page
#
