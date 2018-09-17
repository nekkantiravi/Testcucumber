# Author: Stores Domain Checkout Team
# Story: SDC-163 - Checkout :: Error Handling when Adding to the Bag
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-163
Feature: As an associate, I want to know if an item was not added to a bag, so that I can try again to add it.

  Scenario: An associate verifies error message when adding to bag call fails.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    And Add Item call fails
    Then I should see an error overlay with error message
    And I should see error overlay title as "Unable to Add Item"
    And I should see error message as "This item could not be added to the bag. Please try again."
    And I should be able to clear the overlay
