# Author: Stores Domain Checkout Team
# Story: SDC-138 - Checkout :: No Image Available
# Date Created: 11/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-138
Feature: As an associate, I want to see a default image when an image of an item is not available,
  so that I can clearly understand when images are not available for items.

  Scenario: An associate adds an item (no image available) to the bag, sees the default image on added to bag confirmation layer,
  navigates to the bag, and sees the default image on the bag view.
    Given I am on product discovery
    When I search for "672275307384" in home page
    Then I can see the product card
    When I press the Add to bag
    Then I can see the added to bag confirmation layer with default image
    When I click the View Bag button and navigate to the bag
    Then I should be able to see the bag view
    And I see the item added to the bag
    And I see the bag view item with default image
