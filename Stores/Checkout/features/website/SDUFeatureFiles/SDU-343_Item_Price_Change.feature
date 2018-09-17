#Author: Stores Domain Checkout Team
   #Story: SDU-343- Checkout :: Change Price of an Item
   #Date Created: 07/08/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-343
Feature:As an associate, I want the ability to change the price of an item, so that I can ensure customer satisfaction and accurate pricing.

  @Macy's @send
  Scenario: Macys - An associate can close the overlay and return to the bag screen
    Given I am on "Macy's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And I can close the change price overlay
    And Associate can return to the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @send
  Scenario: Macys - Check if UI elements are properly displayed.
    Given I am on "Macy's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check if change price title text is present and it's the expected one
    And Check that Only one of the three options can be used
    When I can close the change price overlay
    Then Associate can return to the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @send
  Scenario: Macys - Change price of an item by using price option and the price of an item is updated
    Given I am on "Macy's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    Then Check that Only one of the three options can be used
    When I type a new price value
    Then Complete the price change and check default symbol
    When Price of the item is updated in bag
    And I see the original price of the item has a strikethrough on Sales Trans
    And I see the actual price of the item also displays on Sales Trans
    Then Associate can return to the bag screen
    And Subtotal reflects the new price of item
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    When I click on Next Step button
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Macy's Prop" at the tendering screen
    Then The Authorization spinner closes
    And No overlay and error message are displayed
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @send
  Scenario Outline: Macys - Change price of an item by using price option and check invalid input
    Given I am on "Macy's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    And The change price overlay displays properly
    Then Check that Only one of the three options can be used
    When I complete price change with "<invalid input>"
    Then No input is displayed
    When I can close the change price overlay
    Then Associate can return to the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


    Examples:
      | invalid input |
      | -             |
      | sfdsfds       |
      | #$#@          |
      | *             |


  @Macy's @take
  Scenario: Macys - An associate can close the overlay and return to the bag screen for a Take Sale
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And I can close the change price overlay
    And Associate can return to the bag screen when a take transaction is completed
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @take
  Scenario: Macys - Check if UI elements are properly displayed for a Take Sale
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check if change price title text is present and it's the expected one
    And Check that Only one of the three options can be used
    When I can close the change price overlay
    Then I remain on the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @take
  Scenario: Macys - Change price of an item by using price option and the price of an item is updated for a Take Sale
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    When I type a new price value
    Then Complete the price change and check default symbol
    When Price of the item is updated in bag
    And I see the original price of the item has a strikethrough on Sales Trans
    And I see the actual price of the item also displays on Sales Trans
    Then I remain on the bag screen
    And I should be able to see subtotals information is updated
    Then I can see the checkout button
    When I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#    Then I can see the bag fee overlay
#    When I add "0" bags
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I can see the add printer button and print button
    When I click posttender print button
    Then I am on "Add Product" page


  @Macy's @take
  Scenario Outline: Macys - Change price of an item by using price option and check invalid input for a Take Sale
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I can see the slide out menu options
    And I select More menu option
    When I select "Change Price" from the More menu options
    And The change price overlay displays properly
    Then Check that Only one of the three options can be used
    When I complete price change with "<invalid input>"
    Then No input is displayed
    And I close the overlay
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


    Examples:
      | invalid input |
      | -             |
      | sfdsfds       |
      | #$#@          |

  @Bloomingdale's @send
  Scenario Outline: Bloomingdale's - Change price of an item by using price option and check invalid input for a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    And The change price overlay displays properly
    Then Check that Only one of the three options can be used
    When I complete price change with "<invalid input>"
    Then No input is displayed
    And I close the overlay
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


    Examples:
      | invalid input |
      | -             |
      | sfdsfds       |
      | #$#@          |
      | *             |

  @Bloomingdale's @take
  Scenario: Bloomingdale's - Change price of an item by using price option and the price of an item is updated for a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    When I type a new price value
    Then Complete the price change and check default symbol
    When Price of the item is updated in bag
    And I see the original price of the item has a strikethrough on Sales Trans
    And I see the actual price of the item also displays on Sales Trans
    Then I remain on the bag screen
    And I should be able to see subtotals information is updated
    Then I can see the checkout button
    When I press the checkout button
    Then I can see the bag fee overlay
    When I add "0" bags
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I can see the add printer button and print button
    When I click posttender print button
    Then I am on "Add Product" page


  @Bloomingdale's @send
  Scenario: Bloomingdales - An associate can close the overlay and return to the bag screen
    Given I am on "Bloomingdale's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And I can close the change price overlay
    And Associate can return to the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @send
  Scenario: Bloomingdales - Check if UI elements are properly displayed.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check if change price title text is present and it's the expected one
    And Check that Only one of the three options can be used
    When I can close the change price overlay
    Then Associate can return to the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @send
  Scenario: Bloomingdales - Change price of an item by using price option and the price of an item is updated
    Given I am on "Bloomingdale's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    When I type a new price value
    Then Complete the price change and check default symbol
    When Price of the item is updated in bag
    And I see the original price of the item has a strikethrough on Sales Trans
    And I see the actual price of the item also displays on Sales Trans
    Then Associate can return to the bag screen
    And Subtotal reflects the new price of item
    And I press the checkout button
    Then I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    Then I see the same as Shipping prompt
    When I click on Next Step button
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then The Authorization spinner closes
    And No overlay and error message are displayed


  @Bloomingdale's @send
  Scenario Outline: Bloomingdales - Change price of an item by using price option and check invalid input
    Given I am on "Bloomingdale's Sales Trans"
    When I add 1 items to bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    And The change price overlay displays properly
    Then Check that Only one of the three options can be used
    When I complete price change with "<invalid input>"
    Then No input is displayed
    And I close the overlay
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


    Examples:
      | invalid input |
      | -             |
      | sfdsfds       |
      | #$#@          |
      | *             |


  @Bloomingdale's @take
  Scenario: Bloomingdales - An associate can close the overlay and return to the bag screen for a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And I can close the change price overlay
    And Associate can return to the bag screen when a take transaction is completed
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @take
  Scenario: Bloomingdales - Check if UI elements are properly displayed for a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    When I click on the bag icon
    Then I should be able to see subtotal for the added items
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check if change price title text is present and it's the expected one
    And Check that Only one of the three options can be used
    When I can close the change price overlay
    Then I remain on the bag screen
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page