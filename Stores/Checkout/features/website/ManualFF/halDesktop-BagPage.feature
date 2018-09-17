#Author: Stores Domain Checkout Team
       #Story: SDU-1234 - Bag page on fixed register
       #Date Created: 11/06/2017
       #Date Signed Off:

@release_1721 @story_SDU-1234
Feature: As an associate using a fixed register, I want an organized interface
          when I add products to a bag, so I have a clear view of the items she wants to purchase and their values.

  @manual @HALDesktop @Macy's @Take
  Scenario:  The user can scan an item and add it to the bag, access the more menu and remove the item
    Given I am on Hal Desktop         #Will include signing in and verifying you landed in the bag
    When I scan UPC "91709352521" into the bag
    And I close the CRL Overlay
    Then the toast message fades away after 2 seconds
    When I click on the bag icon
    And I can see the bag header title
    Then I click the remove button
    And I can see Checkout empty bag view

  @manual @HALDesktop @Macy's @Take
  Scenario:  The user can scan an item and add it to the bag, press the Checkout button and be taken to the Tendering page.
    Given I am on Hal Desktop         #Will include signing in and verifying you landed in the bag
    When I scan UPC "91709352521" into the bag
    And I close the CRL Overlay
    Then the toast message fades away after 2 seconds
    When I click on the bag icon
    And I can see the bag header title
    Then I press the checkout button
    And I add "1" bags
    Then I can see the mock tendering screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @manual @HALDesktop @Macy's @Send
  Scenario:  The user can scan an item and add it to the bag, access the more menu and remove the item
    Given I am on Hal Desktop         #Will include signing in and verifying you landed in the bag
    And I navigate to PDP search page
    When I search for "12345000010" on product discovery
    And I can see the product card
    Then I press the Add to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I can see the bag header title
    Then I click the remove button
    And I can see Checkout empty bag view

  @manual @HALDesktop @Macy's @Send
  Scenario:  The user can scan an item and add it to the bag, press the Checkout button and be taken to the Tendering page.
    Given I am on Hal Desktop         #Will include signing in and verifying you landed in the bag
    And I navigate to PDP search page
    When I search for "12345000010" on product discovery
    And I can see the product card
    Then I press the Add to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I can see the bag header title
    Then I press the checkout button
    And I click on Next Step button
    When I input the Shipping Information
    And I click on Next Step button
    And I press Next to confirm same as shipping address
    And I tap on the Complete button
    Then I can see the mock tendering screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page