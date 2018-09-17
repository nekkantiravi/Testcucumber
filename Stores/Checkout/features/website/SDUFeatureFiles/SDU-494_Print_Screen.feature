 #Author: Stores Domain Checkout Team
     #Story: SDU-494 - Checkout :: Mock Print screen/button
     #Date Created: 05/19/2017
     #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-494
 Feature:As a PdM, I want to print a receipt with sample data, so that I understand the functional flow of the MVP transaction.

   @Macy's
   Scenario: Macy's - Associate sees the Mock Print Screen and the find printer in button bar in a Place Order
       Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I press next steps
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I press Next to confirm same as shipping address
     Then I see the Order Review Overlay
     When I tap on the Complete button
     Then I can see the mock tendering screen
     When I swipe the "Macy's Prop" at the tendering screen
     Then I see the authorization spinner
     And The Authorization spinner closes
     And I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then the printer screen displays
     And I can see the find printer button in the button bar
     When I click on Hamburger icon
     Then I should not be able to see the cancel transaction button
     When I click posttender print button
     Then I can see the sales trans landing page


   @Bloomingdale's
   Scenario: Bloomingdale's - Associate sees the Mock Print Screen and the find printer in button bar in a Place Order
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I press next steps
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I press Next to confirm same as shipping address
     Then I see the Order Review Overlay
     When I tap on the Complete button
     Then I can see the mock tendering screen
     When I swipe the "Bloomingdale's Prop" at the tendering screen
     Then I see the authorization spinner
     And The Authorization spinner closes
     And I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then the printer screen displays
     And I can see the find printer button in the button bar
     When I click on Hamburger icon
     Then I should not be able to see the cancel transaction button
     When I click posttender print button
     Then I can see the sales trans landing page
  #
  #   @manual
  #   Scenario: Associate sees the Mock Print Screen and the find printer in button bar in a Take Sale
  #     Given I am on Sales Trans
  #     When I add an item to the Checkout bag for a take sale
  #     And I close the CRL Overlay
   #    Then I can see the added to bag toast message
    #   And the toast message fades away after 2 seconds
  #     When I click on the bag icon
  #     Then I can see the checkout button
  #     When I press the checkout button
  #     Then I can see the mock tendering screen
  #     And I can see the Authorize button
  #     When I click on the Authorize button
  #     Then the printer screen displays
  #     And I can see the find printer button in the button bar
