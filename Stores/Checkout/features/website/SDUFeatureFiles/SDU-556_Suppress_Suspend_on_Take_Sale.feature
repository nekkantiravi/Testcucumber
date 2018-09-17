 #Author: Stores Domain Checkout Team
     #Story: SDU-556 - Checkout :: Suppress Suspend on Take Sale
     #Date Created: 06/07/2017
     #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-556
 Feature: As an associate, I want the Suspend button suppressed when I cannot use it, so that I am clear on what I can and cannot Suspend to be completed at SPOS.

   @Macy's @Take
   Scenario: Macy's - An associate processes a Take order transaction
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the bag header title
     Then I should not see the suspend button
     And the checkout button is centered
     When I call Cancel
     Then I am on "Add Product" page

   @Macy's @Send
   Scenario: Macy's - An associate processes a Place Order transaction
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I can see the bag header title
     Then I can view the suspend button
     When I call Cancel
     Then I am on "Add Product" page

   @Bloomingdale's @Take
   Scenario: Bloomingdale's - An associate processes a Take order transaction
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the bag header title
     Then I should not see the suspend button
     And the checkout button is centered
     When I call Cancel
     Then I am on "Add Product" page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - An associate processes a Place Order transaction
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I can see the bag header title
     Then I can view the suspend button
     When I call Cancel
     Then I am on "Add Product" page
