 #Author: Stores Domain Checkout Team
    #Story: SDU-370 - Signature for Prop Payment - Mobile
    #Date Created: 06/13/2017
    #Date Signed Off:
   
   @story_SDU-370 @project_checkout @release_17 @domain_stores
Feature: As a customer, I want to sign for my payment, so that I can authorize my transaction.

  @Macy's @take
 Scenario: Macy's - I create an order under the threshold that doesn't need a signature
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add an item to the Checkout bag that costs less than "$30 for Macy's"
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#    Then I can see the bag fee overlay
#    When I add "0" bags
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I do not see the signature view
    When I click posttender print button
    Then I can see the sales trans landing page

  @Macy's @send
  Scenario: Macy's - I create a send order over thirty dollars the requires a signature
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @take
  Scenario: Macy's - I create a take order over thirty dollars the requires a signature
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I click posttender print button
    Then I can see the sales trans landing page

  @Macy's @send
  Scenario: Macy's - I try to press Enter without signing my transaction
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    And I can see Accept signature button is not active
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @take
  Scenario: Macy's - I clear my signature from the overlay
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    Then I can see Accept signature button is active
    When I press Clear signature button
    Then I can see Accept signature button is not active
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - I create an order under the threshold that doesn't need a signature
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add an item to the Checkout bag that costs less than "$30 for Bloomingdale's"
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
    Then I can see the bag fee overlay
    When I add "0" bags
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I do not see the signature view
    When I click posttender print button
    Then I can see the sales trans landing page

  @Bloomingdale's @send
  Scenario: Bloomingdale's - I create a send order over thirty dollars the requires a signature
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - I create a take order over thirty dollars the requires a signature
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I click posttender print button
    Then I can see the sales trans landing page

  @Bloomingdale's @send
  Scenario: Bloomingdale's - I try to press Enter without signing my transaction
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    And I can see Accept signature button is not active
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - I clear my signature from the overlay
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    Then I can see Accept signature button is active
    When I press Clear signature button
    Then I can see Accept signature button is not active
    When I call Cancel
    Then I can see the sales trans landing page