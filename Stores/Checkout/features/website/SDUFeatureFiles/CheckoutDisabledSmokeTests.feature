#Author: Stores Domain Checkout Team
   #Story: SDU-Showcase Checkout ::Showcase
   #Date Created: 8/10/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @showcase
Feature: As an associate I want to showcase my team's work
##### Macy's Smoke Tests #####


  @Macy'sCheckoutDisabled
  Scenario: Macy's - An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on "Macy's Sales Trans Checkout Disabled"
    When I add "51153819872" into PD for a "DisabledSend" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Macy'sCheckoutDisabled
  Scenario: Macy's - An associate suspends a bag and is returned to the home screen.
    Given I am on "Macy's Sales Trans Checkout Disabled"
    When I add "51153819872" into PD for a "DisabledSend" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the landing page

  @Macy'sCheckoutDisabled
  Scenario: Macy's - An associate attempts to suspend a bag and then presses cancel they are returned to the bag screen.
    Given I am on "Macy's Sales Trans Checkout Disabled"
    When I add "51153819872" into PD for a "DisabledSend" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Macy'sCheckoutDisabled
  Scenario: Add Another
    Given I am on "Macy's Sales Trans Checkout Disabled"
    When I add "51153819872" into PD for a "DisabledSend" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    When I swipe the item from right to left
    And I click on the more button
    Then I tap Add another item button
    Then I should see 2 items in the bag
    And I can see the item information on Sales Trans
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Macy'sCheckoutDisabled
  Scenario: Macy's - An associate wants to suspend for the maximum number of items in a transaction.
    Given I am on "Macy's Sales Trans Checkout Disabled"
    When I add 50 items by webid to bag via product discovery
    And I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the landing page

  @Macy'sCheckoutDisabled
  Scenario: Macy's - An associate wants to be notified for the maximum number of items in a transaction.
    Given I am on "Macy's Sales Trans Checkout Disabled"
    When I add 51 items by webid to bag via product discovery
    Then I should view the item limit error message
    When I should be able to clear the error message
    Then I can see the product card
    When I click on Hamburger icon
    Then I see the Cancel Transaction button
    And I click on Cancel Transaction button
    Then I can see the landing page

  ### There is a PD defect related to update bag that prevents this functionality.. FF is currently off
#  @Macy'sCheckoutDisabled @Send @SDC-35 @Macy's
#  Scenario: Edit Item
#    Given I am on "Macy's Sales Trans Checkout Disabled"
#    When I add "51153819872" into PD for a "DisabledSend" sale
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag action button
#    Then I should be able to see the bag view
#    When I swipe the item from right to left
#    And I click on the more button
#    Then I tap Edit item button
#    Then I verify Edit item call URL with UPC
#    When I navigate back to the bag
#    And I call Cancel
#    Then I can see the sales trans landing page


    ######Bloomies Checkout Disabled########

  @Bloomingdale'sCheckoutDisabled @Send @SDC-24
  Scenario:  Bloomingdales - An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Bloomingdale'sCheckoutDisabled @Send @SDC-24
  Scenario:  Bloomingdales- An associate wants to delete an item from the bag and view the items is removed.
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    When I add 2 items to bag
    And I click on the bag action button
    Then I should be able to see the bag view
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view


  @Bloomingdale'sCheckoutDisabled @Send @SDC-35
  Scenario:  Bloomingdales - An associate suspends a bag and is returned to the home screen.
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page

  @Bloomingdale'sCheckoutDisabled @Send @SDC-35
  Scenario:  Bloomingdales - An associate attempts to suspend a bag and then presses cancel they are returned to the bag screen.
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view

  @Bloomingdale'sCheckoutDisabled @Send @SDC-35
  Scenario: Edit Item
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    When I swipe the item from right to left
    And I click on the more button
    Then I tap Edit item button
    Then I verify Edit item call URL with UPC
    When I navigate back to the bag
    And I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale'sCheckoutDisabled @Send @SDC-35
  Scenario: Add Another
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    When I swipe the item from right to left
    And I click on the more button
    Then I tap Add another item button
    Then I should see 2 items in the bag
    And I can see the item information on Sales Trans
    And I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale'sCheckoutDisabled @Send @SDC-35
  Scenario:  Bloomingdales - An associate wants to suspend for the maximum number of items in a transaction.
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    When I add 50 to quantity
    And I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    When the toast message fades away after 5 seconds
    And I click on the bag action button
    And I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page

  @Bloomingdale'sCheckoutDisabled @Send @SDC-31
  Scenario: Bloomingdales - An associate wants to be notified for the maximum number of items in a transaction.
    Given I am on "Bloomingdale's Sales Trans Checkout Disabled"
    When I add 50 to quantity
    And I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    When the toast message fades away after 5 seconds
    And I add "20714001940" item to the Checkout bag
    Then I should view the item limit error message
    And I should be able to clear the error message
    When I call Cancel
    Then I can see the sales trans landing page

    ### Production Disabled Tests ###

  @Macy'sProductionDisabled
  Scenario: Macy's - An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on "Macy's Production"
    When I add "51153819872" into PD for a "DisabledSend" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view


  @Macy'sProductionDisabled
  Scenario: Macy's - An associate suspends a bag and is returned to the home screen.
    Given I am on "Macy's Production"
    When I add "51153819872" into PD for a "DisabledSend" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the landing page



