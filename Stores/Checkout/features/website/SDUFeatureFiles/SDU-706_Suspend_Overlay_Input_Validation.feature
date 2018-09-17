# Author: Dorin Pop - Stores Domain Checkout Team
# Story: SDU-706 - Suspend Overlay Input Validation
# Date Created: 07/19/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1713 @bug_SDU-706
Feature: When inputting characters into the Suspend customer name overlay
  an error is thrown on every character entered.

  @Macy's @Send
  Scenario: Macy's - Send Sale - Associate submits valid values in the suspend overlay text box
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input "Josh Norman" in the suspend text box
    Then I do not see the red error rectangle
    And I can see "Josh Norman" in the suspend text box
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - Send sale - Associate submits invalid data in the suspend overlay text box
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input "Josh Norman24" in the suspend text box
    Then I see the red error rectangle
    And I can see "Josh Norman" in the suspend text box
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Send Sale - Associate submits valid values in the suspend overlay text box
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input "Josh Norman" in the suspend text box
    Then I do not see the red error rectangle
    And I can see "Josh Norman" in the suspend text box
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Send sale - Associate submits invalid data in the suspend overlay text box
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input "Josh Norman24" in the suspend text box
    Then I see the red error rectangle
    And I can see "Josh Norman" in the suspend text box
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page





