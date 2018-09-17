# Author: Stores Domain Checkout Team
# Story: SDU-371 - Checkout :: Change Totals View in Bag
# Date Created: 05/03/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1708 @story_SDU-371
Feature: As an associate, I want to see the subtotal of my customer's items,
  so that I can clearly communicate expected cost

  @Macy's
  Scenario: Macy's - An associate wants to view subtotals information of a customers bag.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate wants to view subtotals information of a customers bag whenever an item is added
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotals information is updated
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate wants to view subtotals information of a customers bag whenever an item is removed
    Given I am on "Macy's Sales Trans"
    When I add 2 items to bag
    When I click on the bag icon
    When I remove an item from the Checkout bag
    Then I should be able to see subtotals information is updated
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - Totals and estimated sales tax are removed from the bag view
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    And I scroll down inside the bag
#    When I scroll to the bottom of the bag view
    Then I should not see the totals
    And I should not see the estimated sales tax
