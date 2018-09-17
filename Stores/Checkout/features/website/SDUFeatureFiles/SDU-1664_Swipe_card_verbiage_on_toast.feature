#Author: Stores Domain Checkout Team
    #Story: SDU-1664 - Checkout ::  Change swipe card verbiage on toast in checkout
    #Date Created: 05/01/2018
    #Date Signed Off:

@domain_stores @project_checkout @release_25 @story_SDU-1664
Feature: As an associate, I want messaging on the app to be clear so I know what action I need to take.

  @Macy's @Take
  Scenario: Macy's - Change swipe card verbiage on toast in checkout for a take sale
    Given I am on "Macy's Sales Trans"
    When I click the product icon
    And I checkout an item for a take sale
    Then I can see the mock tendering screen

  @Macy's @Send
  Scenario: Macy's - Change swipe card verbiage on toast in checkout for a send sale
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    And I can see the added to bag toast message
    Then the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I click on Next Step button
    And I see the Shipping information Overlay
    When I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    And I click on Next Step button
    And I see the Order Review Overlay
    And I click on Next Step button
    Then I can see the mock tendering screen
