#Author: Dorin - Stores Domain Checkout Team
    #Story: SDU-976 - Checkout :: Remove cancel from receipt screen
    #Date Created: 08/22/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-976
Feature: Once a transaction has been tendered and the app is at the receipt summary screen,
  the option to cancel should not be available since the transaction is complete

  @Macy's @Take
  Scenario: Macy's - Take sale - After arriving on the receipt screen,
  the Cancel Transaction button is no longer available
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I should not be able to see the cancel transaction button
    When I click posttender print button
    Then I am on "Add Product" page

  @Macy's @Send
  Scenario: Macy's - Send sale - After arriving on the receipt screen,
  the Cancel Transaction button is no longer available
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I should not be able to see the cancel transaction button
    When I click posttender print button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Take sale - After arriving on the receipt screen,
  the Cancel Transaction button is no longer available
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I should not be able to see the cancel transaction button
    When I click posttender print button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Send sale - After arriving on the receipt screen,
  the Cancel Transaction button is no longer available
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I should not be able to see the cancel transaction button
    When I click posttender print button
    Then I am on "Add Product" page