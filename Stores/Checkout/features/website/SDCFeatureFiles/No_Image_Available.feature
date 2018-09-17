# Author: Stores Domain Checkout Team
# Story: SDC-138 - Checkout :: No Image Available
# Date Created: 11/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-138
Feature: As an associate, I want to see a default image when an image of an item is not available,
  so that I can clearly understand when images are not available for items.

  @Macy's @Send
  Scenario: Macy's - An associate adds an item (no image available) to the bag, sees the default image on added to bag confirmation layer,
  navigates to the bag, and sees the default image on the bag view.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I see the item added to the bag
    And I see the bag view item with default image
    When I call Cancel
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate adds an item (no image available) to the bag, sees the default image on added to bag confirmation layer,
  navigates to the bag, and sees the default image on the bag view.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I see the item added to the bag
    And I see the bag view item with default image
    When I call Cancel
    Then I can see the sales trans landing page
