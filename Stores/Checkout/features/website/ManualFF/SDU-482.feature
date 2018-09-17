#Author: Stores Domain Checkout Team
   #Story: SDU-482- Checkout :: Print Sample Data
   #Date Created: 6/7/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-482
Feature: As a Product Manager, I want to print a receipt with sample data so that I understand the functional flow of
  the MVP transaction.

  @manual @Macy's @Send
  Scenario: Product manager or user is able to print a receipt with sample data.
   Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the bag header title
    When I press the checkout button
    Then I see the Shipping Method Overlay
    And I press next steps
    Then I see the Shipping information Overlay
    When I input the Shipping Information
    And I press next steps
    Then I can see the Input Billing Information screen
    When I press next steps
    Then I see the Order Review Overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can see the Authorize button
    When I click on the Authorize button
    Then I can see the find printer button
    And I click on the find printer button
      #Then I can see the sample data receipt print
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page

  @manual @Bloomingdale's @Send
  Scenario: Product manager or user is able to print a receipt with sample data.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the bag header title
    When I press the checkout button
    Then I see the Shipping Method Overlay
    And I press next steps
    Then I see the Shipping information Overlay
    When I input the Shipping Information
    And I press next steps
    Then I can see the Input Billing Information screen
    When I press next steps
    Then I see the Order Review Overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can see the Authorize button
    When I click on the Authorize button
    Then I can see the find printer button
    And I click on the find printer button
    #Then I can see the sample data receipt print
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page