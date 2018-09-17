           #Author: Stores Domain Checkout Team
           #Story: SDU-802- Checkout :: Change Price by $ Off
           #Date Created: 9/7/2017
           #Date Signed Off:

  @domain_stores @project_checkout @release_17 @story_SDU-802
  Feature: As an associate, I want the ability to change the price of an item, so that I can ensure customer satisfaction and accurate pricing.

    @Macy's @Send
    Scenario: Macy's - An associate can close the overlay and return to the bag screen
      Given I am on "Macy's Sales Trans"
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

    @Macy's @Take
    Scenario: Macy's - Take a dollar off on a Take sale
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      And I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I select More menu option
      When I select "Change Price" from the More menu options
      Then The change price overlay displays properly
      And Check that Only one of the three options can be used
      Then I complete dollar change and overlay closes
      And Price of the item is updated in bag
      And I should be able to see subtotals information is updated
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

    @Macy's @Take
    Scenario: Macys - The update button should be disabled if there is no value filled in the input field
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      When I click on the bag icon
      Then I should be able to see subtotal for the added items
      When I swipe the item from right to left
      Then I select More menu option
      When I select "Change Price" from the More menu options
      And The update button should be disabled
      And I can close the change price overlay
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page


  @Macy's @Take
    Scenario: Macys - When user enters 0 as a changed price an error message is displayed
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      When I click on the bag icon
      Then I should be able to see subtotal for the added items
      When I swipe the item from right to left
      Then I select More menu option
      When I select "Change Price" from the More menu options
      Then I complete price change with "0"
      When I click the update button
      Then an Invalid price value error message is displayed
      And I can close the change price overlay
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

    @Macy's @Take
    Scenario: Macys - When doing a Dollar off Price Change to less then 0 an error message is displayed
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      And I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I select More menu option
      When I select "Change Price" from the More menu options
      Then The change price overlay displays properly
      And Check that Only one of the three options can be used
      When I select dollar Off from the Change Price options
      Then an Invalid dollar off value error message is displayed
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

    @Bloomingdale's @Send
    Scenario: Bloomingdale's - An associate can close the overlay and return to the bag screen
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

    @Bloomingdale's @Take
    Scenario: Bloomingdale's - Take a dollar off on a Take sale
      Given I am on "Bloomingdale's Sales Trans"
      When I select Take option
      And I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      And I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I select More menu option
      When I select "Change Price" from the More menu options
      Then The change price overlay displays properly
      And Check that Only one of the three options can be used
      Then I complete dollar change and overlay closes
      And Price of the item is updated in bag
      And I should be able to see subtotals information is updated
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

