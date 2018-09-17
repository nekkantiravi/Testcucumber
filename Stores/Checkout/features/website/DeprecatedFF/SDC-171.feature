# Author: Stores Domain Checkout Team
# Story: SDC-171 - Checkout :: Multiple Confirmation Overlays
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-171
Feature: As an associate, I only ever want to see a single confirmation overlay,
  so that I do not have to clear multiple at a time.

  Scenario: An associate adds multiple items simultaneously.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on "Add" button again while the confirmation layer is still displayed
    Then I should see the first confirmation layers clears
    And confirmation layer for the second item should be displayed
