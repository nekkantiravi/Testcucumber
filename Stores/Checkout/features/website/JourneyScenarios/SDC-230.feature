# Author: Stores Domain Checkout Team
# Story: SDC-230 - Checkout :: Header | Clear bag | Empty Bag page not rendering
# Date Created: 01/26/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-230 @wip
Feature: As an associate, I want to view empty bag after clear all items on bag view from header.

  Scenario: An associate clears all items from header and see an empty bag view.
    Given I am on product discovery
    Then I can see the landing page
    When I add 3 items by webid to bag via product discovery
    And I click the View Bag button and navigate to the bag
    Then I can view the PDP Header
    When I press the customer icon
    Then I can see the clear button
    When I press the Header "Clear" button
    Then I can see Phase one Checkout empty bag view
