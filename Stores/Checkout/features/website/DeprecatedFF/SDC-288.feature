# Author: Stores Domain Checkout Team
# Story: SDC-288 - Checkout :: Wait Spinner on Totals
# Date Created: 02/10/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-288
Feature: As a customer, I want to know what the total amount of my bag is, 
so that I can make a final decision on purchasing.

  Scenario: An associate views the bag and sees the spinner until the totals can be calculated
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should see the totals display area with the totals dashed out 
    And I should see the wait spinner overlaying the totals area
    When the wait spinner clears
    Then I should be able to see subtotals information is updated

  Scenario: An associate views the bag and sees the spinner. An error Occurs and the customer sees the existing error.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should see the totals display area with the totals dashed out 
    And I should see the wait spinner overlaying the totals area
    When the totals do not calculate after 5 seconds
    Then I should see the current error message in the totals area
