# Author: Stores Domain Checkout Team
# Story: SDC-140 - Item Details in Bag view Part-2
# Date Created: 11/09/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-140
Feature: As an associate, I want to view items and details in a bag,
  so that I can validate my customers purchase contents.

  Scenario: An associate wants to add an item to a bag and can view item details.
    Given I am on Sales Trans
    When I add an item to the Checkout bag that has an associated fee
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
   	Then I can see the item information

  ######CLEAR ALL FROM HEADER

#  Scenario: An associate wants to add an item to a bag and can view item details.
#    Given I am on Sales Trans
#    When I add 2 items to bag
#    And I press the Bag icon
#    And I press the header clear button
#    Then I can see Checkout empty bag view