   #Author: Stores Domain Checkout Team
   #Story: SDU-666 - Checkout :: Take sale - Bag view - pressing the Checkout button doesn't take the user on tendering screen
   #Date Created: 06/07/2017
   #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-666
 Feature: A user makes a "take" sale and while on the bag view clicks the Checkout button,
   nothing happens. The expected result would be that the user is redirected on tendering screen.

   @Macy's @Take
   Scenario: Macy's - An associate tries to Checkout a Take transaction
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the bag header title
     When I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#     Then I can see the bag fee overlay
#     When I add "5" bags
     Then I can see the mock tendering screen
     When I call Cancel
     Then I can see the sales trans landing page


   @Bloomingdale's @Take
   Scenario: Bloomingdale's - An associate tries to Checkout a Take transaction
     Given I am on "Bloomingdale's Sales Trans"
     When I select Take option
     And I add "20714001940" item to the Checkout bag
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the bag header title
     When I press the checkout button
     Then I can see the bag fee overlay
     When I add "5" bags
     Then I can see the mock tendering screen
     When I call Cancel
     Then I can see the sales trans landing page
