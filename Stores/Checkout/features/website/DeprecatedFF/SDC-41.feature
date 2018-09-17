# Author: Stores Domain Checkout Team
# Story: SDC-41 - Associate Authorization in under 1 second
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-41
Feature: As an associate, I want to access the POS application in under one second,
  so that I can quickly begin ringing transactions or performing tasks.

  Scenario: Associate Authorization occurrs in under one second.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click the View Bag button and navigate to the bag
    Then I should be able to see the bag view
    And I see the item added to the bag
    Then I can view the the Associate Authorization occurred in under one second using the Kibana dashboard

  Scenario: Invalid UPC error displays in under one second.
    Given I am on Sales Trans
    When I add an invalid UPC to the Checkout bag
    Then I can see the Invalid UPC error Message
    And I can verify the error message displays within one second

  Scenario: Unable to Suspend bag error message displays in under one second.
    Given I am on Sales Trans
    And I add an item to the Checkout bag
    Then I can see the add to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can view the Suspend button
    When I press the Suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay suspend button
    Then I see the Unable to suspend error overlay
    And I can verify the error message displays within one second
