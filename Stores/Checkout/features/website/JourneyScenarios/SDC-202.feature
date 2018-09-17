# Author: Stores Domain Checkout Team
# Story: SDC-202 - Checkout :: Truck Icon Verbiage
# Date Created: 12/06/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-202
Feature: As an associate, I want to see an icon indicating the item in my customer
  bag is being sent, so that I can clearly identify them.

  Scenario: An associate verifies the verbiagefor truck icon in bag has changed.
    Given I am on "product discovery"
    Then I can see the landing page
    When I search for "672275307384" in home page
    Then I can see the product card
    And I press the Add to bag
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
   	Then I see the item added to the bag
   	And I see truck icon displayed in item details
   	And I see the text "Sending this item" next to truck icon
