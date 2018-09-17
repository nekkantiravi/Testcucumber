   #Author: Stores Domain Checkout Team
   #Story: SDU-629 - TECH: Create "toast" notification
   #Date Created: 06/20/2017
   #Date Signed Off:

    @domain_stores @project_checkout @release_17 @story_SDU-629
Feature:  Toast notifications will be used as lightweight messaging overlays that appear
  briefly and fade away without user action.

  @Macy's @Send
  Scenario: Macy's - Toast notifications will first be used to replace the
  current "Added to Bag" confirmation layer (which slides down over the PDP).
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I call Cancel
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Toast notifications will first be used to replace the
  current "Added to Bag" confirmation layer (which slides down over the PDP).
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I call Cancel
    Then I can see the sales trans landing page
