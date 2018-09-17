# Author: Stores Domain Checkout Team
# Story: SDC-202 - Checkout :: Truck Icon Verbiage
# Date Created: 12/06/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-202
Feature: As an associate, I want to see an icon indicating the item in my customer
  bag is being sent, so that I can clearly identify them.

  Scenario: An associate verifies the verbiage for truck icon in bag has changed.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click the View Bag button and navigate to the bag
   	Then I see the item added to the bag
   	And I see truck icon displayed in item details
   	And I see the text "Sending this item" next to truck icon
