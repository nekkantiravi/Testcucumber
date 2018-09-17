# Author: Stores Domain Checkout Team
# Story: SDC-140 - Item Details in Bag view Part-2
# Date Created: 11/09/2016
# Date Signed Off:

@domain_stords @project_checkout @release_17 @story_SDC-140
Feature: Automate Gift
  Scenario: Login and verify the link
    Given I am on Gift
    When I login
    Then I can see the Gift Home Page
    When I click the hamburger menu
    Then I can see the Link

  Scenario Outline: Using Multiple roles
    Given I am on Gift
    When I login as an "<AssociateRole>"
    Then I can see the Gift Home Page
    When I click the hamburger menu
    Then I "<Result>" see the Link

    Examples:
      |AssociateRole  |Result|
      |    Adviser    |Do    |
      |    Director   |DoNot |

#Feature: As an associate, I want to view items and details in a bag,
#  so that I can validate my customers purchase contents.
#
#  Scenario: An associate wants to add an item to a bag and can view item details.
#    Given I am on "product discovery"
#    When I search for "672275307384" in home page
#    Then I can see the product card
#    When I press the Add to bag
#    Then I can see the added to bag confirmation layer
#    When I click the View Bag button and navigate to the bag
#    Then I can see the item information on Product Discovery