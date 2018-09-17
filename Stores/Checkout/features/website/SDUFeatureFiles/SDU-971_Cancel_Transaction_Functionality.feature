 #Author:Ana - Stores Domain Checkout Team
        #Story: SDU-971: Correct the cancel transaction functionality
        #Date Created: 08/23/2017
        #Date Signed Off:

 @domain_stores @project_checkout @release_1716 @story_SDU-971
 Feature: As a developer I need to make sure Cancel transaction works in all the scenarios

   @Macy's @Send
   Scenario: Macy's - The bag is cleared if I cancel the transaction from the add product screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Macy's @Take
   Scenario: Macy's - The bag is cleared if I cancel the transaction from the bag screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     Then I close the CRL Overlay
     When I click on the bag icon
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Macy's @Take
   Scenario: Macy's - The bag is cleared if I cancel the transaction from the tendering screen
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I am on "Tendering" page
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - The bag is cleared if I cancel the transaction after refreshing the add product page
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I refresh current page
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Macy's @Take
   Scenario: Macy's - The bag is cleared if I cancel the transaction after refreshing the bag screen
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     Then I close the CRL Overlay
     When I click on the bag icon
     And I refresh current page
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - The bag is cleared if I cancel the transaction after refreshing the tendering screen
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I am on "Tendering" page
#     When I refresh current page ====> For some reason this refresh step does not work in this scenario. Commented out 10/19/17 by M. Wenzel
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page
     And I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Bloomingdale's @Take
   Scenario: Bloomingdale's - The bag is cleared if I cancel the transaction from the add product screen
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     Then I close the CRL Overlay
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The bag is cleared if I cancel the transaction from the bag screen
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I click on the bag icon
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Bloomingdale's @Take
   Scenario: Bloomingdale's - The bag is cleared if I cancel the transaction from the tendering screen
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I am on "Tendering" page
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The bag is cleared if I cancel the transaction after refreshing the add product page
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 3 seconds
     When I refresh current page
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Bloomingdale's @Take
   Scenario: Bloomingdale's - The bag is cleared if I cancel the transaction after refreshing the bag screen
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     Then I close the CRL Overlay
     When I click on the bag icon
     And I refresh current page
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - The bag is cleared if I cancel the transaction after refreshing the tendering screen
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     Then I am on "Tendering" page
#     When I refresh current page ====> For some reason this refresh step does not work in this scenario. Commented out 10/19/17 by M. Wenzel
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page
     When I click on the bag icon
     And  I can see Checkout empty bag view
     When I click the product icon
     Then I can see the sales trans landing page