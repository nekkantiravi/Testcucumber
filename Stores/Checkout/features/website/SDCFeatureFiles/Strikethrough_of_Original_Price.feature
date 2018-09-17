# Author: Stores Domain Checkout Team
# Story: SDC-201 - Checkout :: Strikethrough of Original Price
# Date Created: 12/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-201 @wip
Feature: As an associate, I want to see the original price of an item,
  so that I can convey the savings to my customer.

  @Macy's @Send
  Scenario: Macy's - An associate verifies the item prices (original and actual) verbiage.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I see the item is in a markdown status -the price of the item is lower than the original on Sales Trans
    And I see the original price of the item has a strikethrough on Sales Trans
    And I see the actual price of the item also displays on Sales Trans
    When I call Cancel
    Then I am on "Macy's Sales Trans"

#@Bloomingdales @Send
#Scenario: Bloomingdale's - An associate verifies the item prices (original and actual) verbiage on bloomingdales.
#    Given I am on "Bloomingdale's Sales Trans"
#  When I add "20714001940" item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I see the item is in a markdown status -the price of the item is lower than the original on Sales Trans
#    And I see the original price of the item has a strikethrough on Sales Trans
#    And I see the actual price of the item also displays on Sales Trans
#    When I call Cancel
#    Given I am on "Bloomingdale's Sales Trans"