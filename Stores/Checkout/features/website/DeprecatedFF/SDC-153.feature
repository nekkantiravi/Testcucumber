# Author: Stores Domain Checkout Team
# Story: SDC-153 - Checkout :: Error Handling for Suspend
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-153
Feature: As an associate, I want to know if an bag was not suspended and I want to try again.

  Scenario: An associate verifies error message when suspending a bag call fails.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    And I access Checkout bag
    When I click Suspend button
    And Suspend call fails
    Then I should see an error overlay with error message
    And I should see error overlay title as "Unable to Suspend"
    And I should see error message as "Tap OK and try to Suspend again"
    And I should be able to clear the overlay
