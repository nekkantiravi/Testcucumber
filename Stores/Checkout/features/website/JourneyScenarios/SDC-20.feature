# Author: Stores Domain Checkout Team
# Story: SDC-20 - Checkout :: Totals Information in Bag View
# Date Created: 09/22/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-20
Feature: As an associate, I want to view totals information of a customers bag,
  so that can I communicate exactly what she will pay for merchandise.

  @macy's
  Scenario: An associate wants to view totals information of a customers bag.
    Given I am on "product discovery"
    When I search for "672275307384" in home page
   Then I can see the product card
    And I press the Add to bag
   Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I should be able to see totals information of all items added

  @macy's
  Scenario: An associate wants to view totals information of a customers bag whenever a change is made.
    Given I am on "product discovery"
    When I add 2 items by webid to bag via product discovery
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I should be able to see totals information of all items added
    When I remove an item from UAT
    Then I should be able to see totals information of all items added
    And I should be able to see totals information is updated
