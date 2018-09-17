# Author: Stores Domain Checkout Team
# Story: SDC-191 - Bag Header Title
# Date Created: 12/08/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-191
Feature: As an associate, I want to see the title of the page I am viewing,
  so that it is clear where I am in the application.

  Scenario: An associate wants to view the title of the page they are viewing.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "672275307384" in home page
    Then I can see the product card
    And I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I can view the PDP Header
    And I can see the bag header title

  Scenario: An associate wants to view the title of the page they are viewing.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "672275307384" in home page
    Then I can see the product card
    And I press the Add to bag
    And I can see the added to bag confirmation layer
    And I clear the confirmation layer manually
    Then I can see the product card
    When I press the Bag in the Header
    Then I should be able to see the bag view
    When I remove an item from UAT
    Then I can see Phase one Checkout empty bag view
