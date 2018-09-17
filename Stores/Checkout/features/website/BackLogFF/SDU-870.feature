 #Author:Ana - Stores Domain Checkout Team
        #Story: SDU-870: Enhance Price Change Overlay
        #Date Created: 09/20/2017
        #Date Signed Off:

 @domain_stores @project_checkout @release_1717 @story_SDU-870
 Feature: As a customer, I want to know in real-time the new price of my item when a $ or % off price change is initiated, so that I can be more informed of how much I am spending.

  @Macy's @send
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Macy's Sales Trans"
   When I add an item to the Checkout bag
   And I can see the added to bag toast message
   And the toast message fades away after 3 seconds
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   Then I see the item name
   And I can see the old item price
   And I can see the new item price

  @Macy's @take
  Scenario: As a customer I can see the changes I make to the item price if I use the % OFF option
   Given I am on "Macy's Sales Trans"
   When I add an item to the Checkout bag for a Take Sale
   And I close the CRL Overlay
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   And I select "%OFF" option
   And I add 5 in the text field
   Then The New price is changed
   And Savings section is displayed
   And Savings represents 5% of the item price
   When I add 0 in the text field
   Then The New price is changed
   And Savings represents 50% of the item price
   When I empty the text field
   Then The New price is empty
   And The Savings section is not displayed
   When I add 10 in the text field
   And I want to add 0 in the text field
   Them I am not allowed

  @Macy's @send
  Scenario: As a customer I can see the changes I make to the item price if I use the $ OFF option
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Macy's Sales Trans"
   When I add an item to the Checkout bag
   And I can see the added to bag toast message
   And the toast message fades away after 3 seconds
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   And I select "$OFF" option
   And I add 5 in the text field
   Then The New price is changed
   And Savings section is displayed
   And Savings represents $5
   When I add 0 in the text field
   Then The New price is changed
   And Savings represents $50
   When I empty the text field
   Then The New price is empty
   And The Savings section is not displayed
   When I want to add the item price in the text field
   Then I am not allowed

  @Macy's @take
  Scenario: As a customer I can see the changes I make to the item price if I use the Price option
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Macy's Sales Trans"
   When I add an item to the Checkout bag for a Take Sale
   And I close the CRL Overlay
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   And I select "Price" option
   And I add 5 in the text field
   Then The New price is now $5
   And Savings section is displayed
   And Savings represents $5
   When I add 0 in the text field
   Then The New price is changed
   And Savings represents $50
   When I empty the text field
   Then The New price is empty
   And The Savings section is not displayed
   When I want to add 0 in the text field
   Then I am not allowed

  @Bloomingdale's @take
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Bloomingdale's Sales Trans"
   When I add an item to the Checkout bag for a Take Sale
   And I close the CRL Overlay
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   Then I see the item name
   And I can see the old item price
   And I can see the new item price

  @Bloomingdale's @send
  Scenario: As a customer I can see the changes I make to the item price if I use the % OFF option
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Bloomingdale's Sales Trans"
   When I add an item to the Checkout bag
   And I can see the added to bag toast message
   And the toast message fades away after 3 seconds
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   And I select "%OFF" option
   And I add 5 in the text field
   Then The New price is changed
   And Savings section is displayed
   And Savings represents 5% of the item price
   When I add 0 in the text field
   Then The New price is changed
   And Savings represents 5% of the item price
   When I empty the text field
   Then The New price is empty
   And The Savings section is not displayed
   When I add 10 in the text field
   And I want to add 0 in the text field
  Them I am not allowed

  @Bloomingdale's @take
  Scenario: As a customer I can see the changes I make to the item price if I use the $ OFF option
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Bloomingdale's Sales Trans"
   When I add an item to the Checkout bag for a Take Sale
   And I close the CRL Overlay
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   And I select "$OFF" option
   And I add 5 in the text field
   Then The New price is changed
   And Savings section is displayed
   And Savings represents $5
   When I add 0 in the text field
   Then The New price is changed
   And Savings represents $50
   When I empty the text field
   Then The New price is empty
   And The Savings section is not displayed
   When I want to add the item price in the text field
   Then I am not allowed

  @Bloomingdale's @send
  Scenario: As a customer I can see the changes I make to the item price if I use the Price option
  Scenario: The Change Price overlay has the name of the item, its current price and its new price
   Given I am on "Bloomingdale's Sales Trans"
   When I add an item to the Checkout bag
   And I can see the added to bag toast message
   And the toast message fades away after 3 seconds
   And I click on the bag icon
   And I swipe the item from right to left
   And I click on the more button
   Then I can see the options 'Edit Item','Add Another' and 'Price Change'
   When I click Change Price
   And I select "Price" option
   And I add 5 in the text field
   Then The New price is now $5
   And Savings section is displayed
   And Savings represents $5
   When I add 0 in the text field
   Then The New price is changed
   And Savings represents $50
   When I empty the text field
   Then The New price is empty
   And The Savings section is not displayed
   When I want to add 0 in the text field
   Then I am not allowed