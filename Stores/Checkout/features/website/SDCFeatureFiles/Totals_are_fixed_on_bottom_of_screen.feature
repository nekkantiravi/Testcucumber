# Author: Stores Domain Checkout Team
# Story: SDC-258 - Checkout :: Fixed Totals on Bottom of Screen
# Date Created: 26/01/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-258
Feature: As an associate, I want to have a clear view of the customers total, so that I can easily communicate it to my customer

  @Macy's @Send
  Scenario: Macy's - An associate adds an item to the bag, sees the added to bag confirmation layer, and navigates to the bag.
    Given I am on "Macy's Sales Trans"
    When I add 2 items to bag
    And I click on the bag icon
    Then I should be able to see the bag view
    And I should be able to see subtotals information is updated
    When I scroll up and down inside the bag
    Then I should be able to see subtotals information is updated
    When I call Cancel
    Then I am on "Macy's Sales Trans"

    @Bloomingdales @Send
    Scenario: Bloomingdale's - An associate adds an item to the bag, sees the added to bag confirmation layer, and navigates to the bag for bloomingdales.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 2 items to bag
    And I click on the bag icon
    Then I should be able to see the bag view
    And I should be able to see subtotals information is updated
    When I scroll up and down inside the bag
    Then I should be able to see subtotals information is updated
    When I call Cancel
    Then I am on "Bloomingdale's Sales Trans"
