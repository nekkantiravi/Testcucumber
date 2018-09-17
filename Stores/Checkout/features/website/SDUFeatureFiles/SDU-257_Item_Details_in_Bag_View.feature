#Author: Stores Domain Checkout Team
   #Story: SDU-257 - Checkout : Item Details in Bag View - Take Sale
   #Date Created: 05/22/2017
   #Date Signed Off:

@release_1710 @story_SDU-257
Feature: As an associate, I want to see relevant details pertaining to the items in my customer's bag,
  so that I can communicate those details to my customer, and ensure I have the correct items in her bag.

  @Macy's @take
     Scenario: Macy's - As an associate I can see the item details in the bag
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      Then I can see the Take item information
      And I can't see the truck icon displayed in item details
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

  @Macy's @take
    Scenario: Macy's - The shipping overlay is not displayed at checkout, if the item in the bag is Take item
    Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
     When I click on the bag icon
      Then I can see the checkout button
      When I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#      Then I can see the bag fee overlay
#      When I add "2" bags
     Then I can see the mock tendering screen
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page

  @Macy's @take
    Scenario: Macy's - Adding an item to the bag, without CRL
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      Then I see the CRL Overlay
      When I close the CRL Overlay
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      And I can see the bag header title
      And I do not see a CRL in the bag
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

  @Macy's @take
  Scenario: Macy's - Adding an item to the bag, with CRL
     Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      Then I see the CRL Overlay
      When I scan "73829282" in to the CRL Overlay
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      Then I can see the "73829282" CRL in the bag
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

  @Macy's @send
  Scenario: Macy's - Verifying the presence of shipping overlay , for Send items
     Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      Then I can see the checkout button
      When I press the checkout button
      Then I see the Shipping Method Overlay
      When I close Shipping Method overlay
      And I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

  @Macy's @Send
  Scenario: Macy's - An associate wants to add an item to a bag and can view item details.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag that has an associated fee
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   #When I click the View Bag button and navigate to the bag
    When I click on the bag icon
    Then I can see the item information on Sales Trans
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - As an associate I can see the item details in the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the Take item information
    And I can't see the truck icon displayed in item details
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - The shipping overlay is not displayed at checkout, if the item in the bag is Take item
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
    Then I can see the bag fee overlay
    When I add "2" bags
    Then I can see the mock tendering screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - Adding an item to the bag, without CRL
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    When I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I can see the bag header title
    And I do not see a CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @take
  Scenario: Bloomingdale's - Adding an item to the bag, with CRL
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    When I scan "73829282" in to the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the "73829282" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @send
  Scenario: Bloomingdale's - Verifying the presence of shipping overlay , for Send items
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
    Then I see the Shipping Method Overlay
    When I close Shipping Method overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

      @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate wants to add an item to a bag and can view item details.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag that has an associated fee
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
  #When I click the View Bag button and navigate to the bag
    When I click on the bag icon
    Then I can see the item information on Sales Trans
    When I call Cancel
    Then I can see the sales trans landing page
