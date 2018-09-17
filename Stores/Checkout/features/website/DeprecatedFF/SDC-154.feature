# Author: Stores Domain Checkout Team
# Story: SDC-154 - Checkout :: Error Handling when Adding to the Bag
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-154
Feature: As an associate, I want to know when totals cannot be calculated,
  so that I know when a system error has occurred, and can take appropriate action.

  Scenario: An associate verifies error message when system error occurs in totals calculation when adding item.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    And I access Checkout bag
    And if one or more service calls fail
    Then I should see error message in the totals area
    And I should see error message read "Totals could not be displayed here, but will appear on the register after you Suspend."
    And totals should have dashes (--) instead of numeric value
    And I should see Suspend button active
    And I should be able to continue using the application

  Scenario: An associate verifies error message when system error occurs in totals calculation when deleteing item.
    Given I am on Sales Trans
    When I add items to the Checkout bag
    And I access Checkout bag
    And I delete an item from the bag
    And if one or more service calls fail
    Then I should see error message in the totals area
    And I should see error message read "Totals could not be displayed here, but will appear on the register after you Suspend."
    And totals should have dashes (--) instead of numeric value
    And I should see Suspend button active
    And I should be able to continue using the application
