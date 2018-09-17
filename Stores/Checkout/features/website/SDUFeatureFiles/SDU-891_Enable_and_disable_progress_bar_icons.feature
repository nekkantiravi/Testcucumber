 #Author:Ana - Stores Domain Checkout Team
      #Story: SDU-891: Enable and disable progress bar icons
      #Date Created: 08/23/2017
      #Date Signed Off:

 @domain_stores @project_checkout @release_1716 @story_SDU-891
 Feature: As a developer I need to make sure only the appropriate actions are available at the different stages of the transaction.

   @Macy's @Send
   Scenario: Macy's - The payment and receipt buttons are disabled
   until the associate clicks on the checkout button in bag screen - for a send sale
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I click on the disabled "payment" icon
     Then I am on "Bag" page
     When I click on the disabled "receipt" icon
     And I am on "Bag" page
     When I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - The receipt button is disabled until the payment is authorized - for a send sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     And I click on the disabled "receipt" icon
     Then I am on "Tendering" page
     When I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - The bag and payment buttons are disabled after the transaction is authorized
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I am on "Tendering" page
     When I swipe the "Macy's Prop" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the receipt information on a send
     When I click on the disabled "bag" icon
     Then  I can see the receipt information on a send
     When I click on the disabled "payment" icon
     Then  I can see the receipt information on a send
     When I click posttender print button
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - The payment and receipt buttons are disabled
   if the transaction is canceled when I am on bag screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I click on the bag icon
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     When I click on the disabled "payment" icon
     Then I am on "Add Product" page
     When I click on the disabled "receipt" icon
     Then I am on "Add Product" page
     When I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - The payment and receipt buttons are disabled
   if the transaction is suspended from the bag screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I press the suspend button
     Then I should see the customer name overlay
     When I input the customers name
     And I click customer name overlay continue button
     Then I see the suspended bag confirmation overlay
     When I press OK on the suspended confirmation overlay
     Then I can see the sales trans landing page
     When I click on the disabled "payment" icon
     Then I am on "Add Product" page
     When I click on the disabled "receipt" icon
     And I am on "Add Product" page


   @Macy's @Send
   Scenario: Macy's - The payment and receipt buttons are disabled
   if the transaction is canceled from the tendering screen
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I am on "Tendering" page
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the disabled "payment" icon
     Then I am on "Add Product" page
     When I click on the disabled "receipt" icon
     And I am on "Add Product" page


   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The payment and receipt buttons are disabled
   until the associate clicks on the checkout button in bag screen - for a send sale
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I click on the disabled "payment" icon
     Then I am on "Bag" page
     When I click on the disabled "receipt" icon
     And I am on "Bag" page
     When I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The receipt button is disabled until the payment is authorized - for a send sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     And I click on the disabled "receipt" icon
     Then I am on "Tendering" page
     When I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The bag and payment buttons are disabled after the transaction is authorized
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I am on "Tendering" page
     When I swipe the "Macy's Prop" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the receipt information on a send
     When I click on the disabled "bag" icon
     Then  I can see the receipt information on a send
     When I click on the disabled "payment" icon
     Then  I can see the receipt information on a send
     When I click posttender print button
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The payment and receipt buttons are disabled if the transaction is canceled when I am on bag screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I click on the bag icon
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     When I click on the disabled "payment" icon
     Then I am on "Add Product" page
     When I click on the disabled "receipt" icon
     Then I am on "Add Product" page
     When I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The payment and receipt buttons are disabled
   if the transaction is suspended from the bag screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I press the suspend button
     Then I should see the customer name overlay
     When I input the customers name
     And I click customer name overlay continue button
     Then I see the suspended bag confirmation overlay
     When I press OK on the suspended confirmation overlay
     Then I can see the sales trans landing page
     When I click on the disabled "payment" icon
     Then I am on "Add Product" page
     When I click on the disabled "receipt" icon
     And I am on "Add Product" page


   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The payment and receipt buttons are disabled
   if the transaction is canceled from the tendering screen
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I am on "Tendering" page
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the disabled "payment" icon
     Then I am on "Add Product" page
     When I click on the disabled "receipt" icon
     And I am on "Add Product" page
