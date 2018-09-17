# Author: Stores Domain Checkout Team
# Story: SDC-157 - Checkout :: SmartPE Timeout Error
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-157
Feature: As an associate, I want to know when a bag has expired in SmartPE,
  so that I can re-add items if necessary to continue the transaction for my customer.

  Scenario: An associate verifies error message when bag has expired in SMARTPE by adding a new item.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    And I access Checkout bag screen
    And I do not action on the bag for 30 mins
    When I add an item to the Checkout bag
    Then I should see an error overlay with error message
    And I should see error overlay title as "Bag Has Expired"
    And I should see error message as "A new bag has been created"

  Scenario: An associate verifies error message when bag has expired in SMARTPE by removing an item.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    And I access Checkout bag screen
    And I do not action on the bag for 30 mins
    When I remove an item from the Checkout bag
    Then I should see an error overlay with error message
    And I should see error overlay title as "Bag Has Expired"
    And I should see error message as "A new bag has been created"
