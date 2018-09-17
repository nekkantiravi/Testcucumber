#Author: Stores Domain Checkout Team
   #Story: SDU-649 - Remove Customer Icon in Header
   #Date Created: 06/13/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-649
Feature:  As an associate, I want a single access point to navigate to the Bag,
  so that I am not confused.

  @Macy's @Send
  Scenario: Macy's - Second Bag icon removed
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can see the second bag icon removed
    And I can see the Bag/Send icons display
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Second Bag icon removed
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can see the second bag icon removed
    And I can see the Bag/Send icons display
    When I call Cancel
    Then I can see the sales trans landing page