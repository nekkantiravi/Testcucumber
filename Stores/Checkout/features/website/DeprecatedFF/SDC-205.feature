# Author: Stores Domain Checkout Team
# Story: SDC-205 - Checkout :: Bag Icon in Empty Bag
# Date Created: 12/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-205
Feature: As an associate, I want to see an icon in an empty bag,
  so that I can have a visual indication that the bag is empty.

  Scenario: An associate verifies the icon for empty bag without adding item.
    Given I am on Sales Trans
    When I click on the bag icon
    Then I can see Checkout empty bag view

  Scenario: An associate verifies the icon for empty bag after add/remove item.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I see the item added to the bag
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view
#
  Scenario: An associate verifies the icon for empty bag after add item / suspend bag.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay suspend button
    Then I see the suspended bag confirmation overlay
    And I press OK on the suspended confirmation overlay
    And I am returned to New POS Sample webapp


  Scenario: An associate verifies that pressed state of header Bag Icon matches the state of progress bar Bag Icon
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I can see Checkout empty bag view
    And I can see that bag icon is bolded
    When I click the product icon
    Then I can see that bag icon is not bolded
    When I click on the bag icon
    Then I can see that bag icon is bolded
