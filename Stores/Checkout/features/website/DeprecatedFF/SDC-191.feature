# Author: Stores Domain Checkout Team
# Story: SDC-191 - Bag Header Title
# Date Created: 12/08/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-191
Feature: As an associate, I want to see the title of the page I am viewing,
  so that it is clear where I am in the application.

  Scenario: An associate wants to view the title of the page they are viewing.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the bag header title

  Scenario: An associate wants to view the title of the page they are viewing.
    Given I am on Sales Trans
    When I click on the bag action button
    Then I can see the bag header title
    And I see an icon above the verbiage indicating the bag is empty
