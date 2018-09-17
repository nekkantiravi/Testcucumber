# Author: Stores Domain Checkout Team
# Story: SDC-31 - Checkout :: 50 Item Limitations
# Date Created: 09/22/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-31
Feature: As an associate, I want to be notified if I have reached the maximum number of items in a transaction,
  so that I know when to finish the transaction and begin another.

  @Macy's @Send
  Scenario: Macy's - An associate wants to be notified for the maximum number of items in a transaction.
    Given I am on "Macy's Sales Trans"
    When I add 50 items to bag
   	When I add 51st item into the bag
   	Then I should view the item limit error message
    And I should be able to clear the error message
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate wants to be notified for the maximum number of items in a transaction.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 50 items to bag
    When I add 51st item into the bag
    Then I should view the item limit error message
    And I should be able to clear the error message
    When I call Cancel
    Then I can see the sales trans landing page
