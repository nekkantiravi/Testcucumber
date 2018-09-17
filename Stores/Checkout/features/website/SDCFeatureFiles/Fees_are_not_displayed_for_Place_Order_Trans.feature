# Author: Stores Domain Checkout Team
# Story: SDC-267 - Checkout :: Don't display Fees in Place Order Trans
# Date Created: 01/26/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-263 @wip
Feature: As an system owner, I want the fees to follow the same rules
  that they do in production today, so that I am not wrongfully charging customers.

  @Macy's @Send
  Scenario: Macy's - An associate wants to add an item to a bag and can view item details;
  A system owner wants the fees to follow the same rules.
    Given I am on "Macy's Sales Trans"
     # Associate has added items to a bag that would qualify for Fees if it were a "Take" transaction
    When I add an item to the Checkout bag that has an associated fee
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
     # Check if: Fees do NOT display, because we are only doing Place Order Trans for Phase 1
    Then I can see the item information on Sales Trans
    And I do not see the fee
    When I call Cancel
    Then I am on "Macy's Sales Trans"

  @Bloomingdales @Send
  Scenario: Bloomingdale's - An associate wants to add an item to a bag and can view item details;
  A system owner wants the fees to follow the same rules for bloomingdales.
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I click on the bag icon
   # Check if: Fees do NOT display, because we are only doing Place Order Trans for Phase 1
    And I can see the item information on Sales Trans
    And I do not see the fee
    When I call Cancel
    Then I am on "Bloomingdale's Sales Trans"