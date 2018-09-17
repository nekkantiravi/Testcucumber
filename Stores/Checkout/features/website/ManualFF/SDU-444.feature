 #Author: Stores Domain Checkout Team
       #Story: SDU-444 - Loyalty - unavailable message
       #Date Created: 06/12/2017
       #Date Signed Off:

 @release_1711 @story_SDU-444
 Feature: As an associate, I want to know if the loyalty system is offline so that I can inform the customer and take appropriate action.

   @manual @Macy's @Take
   Scenario: The customer is a loyalty customer and her ID has been captured, but the Loyalty system is unable to be reached

     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I make the Loyalty system unavailable
     Then I can see the checkout button
     When I press the checkout button
     And I add <number> bags
     Then I swipe the prop card at the tendering screen
     And I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When the transaction is succesfully tendered
     And I press Complete button
     Then I can see loyalty points were not updated error message
     When the error message is cleared
     Then the receipt page is displayed
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page

   @manual @Macy's @Take
   Scenario: Take - Message on receipt prints indicating the customer's points were not updated

     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I make the Loyalty system unavailable
     Then I can see the checkout button
     When I press the checkout button
     And I add <number> bags
     Then I swipe the prop card at the tendering screen
     And I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When the transaction is succesfully tendered
     And I press Complete button
     Then I can see loyalty points were not updated error message
     When the error message is cleared
     Then the receipt page is displayed
     When the Print button is pressed
     And the receipt is printed
     Then a message on the receipt is indicating that the points were not updated
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page




   @manual @Bloomingdale's @Take
   Scenario: Bloomingdale's - The customer is a loyalty customer and her ID has been captured, but the Loyalty system is unable to be reached

     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I make the Loyalty system unavailable
     Then I can see the checkout button
     When I press the checkout button
     And I add <number> bags
     Then I swipe the prop card at the tendering screen
     And I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When the transaction is succesfully tendered
     And I press Complete button
     Then I can see loyalty points were not updated error message
     When the error message is cleared
     Then the receipt page is displayed
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page

   @manual @Bloomingdale's @Take
   Scenario: Bloomingdale's - Message on receipt prints indicating the customer's points were not updated

     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag for a Take Sale
     And I close the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     And I make the Loyalty system unavailable
     Then I can see the checkout button
     When I press the checkout button
     And I add <number> bags
     Then I swipe the prop card at the tendering screen
     And I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When the transaction is succesfully tendered
     And I press Complete button
     Then I can see loyalty points were not updated error message
     When the error message is cleared
     Then the receipt page is displayed
     When the Print button is pressed
     And the receipt is printed
     Then a message on the receipt is indicating that the points were not updated
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page

    

