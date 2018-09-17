# Author: Stores Domain Checkout Team
# Story: SDC-230 - Checkout :: Header | Clear bag | Empty Bag page not rendering
# Date Created: 01/26/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-230 @wip
Feature: As an associate, I want to view empty bag after clear all items on bag view from header.

  Scenario: An associate clears all items from header and see an empty bag view.
    Given I am on Sales Trans
    And I add 3 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can view the PDP Header
    When I press the customer icon
    Then I can see the clear button
    When I press the header clear button
    Then I can see Checkout empty bag view
