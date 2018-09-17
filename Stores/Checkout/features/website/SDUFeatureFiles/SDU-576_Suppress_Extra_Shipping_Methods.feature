      #Author: Stores Domain Checkout Team
      #Story: SDU-576 - Suppress Extra Shipping Methods
      #Date Created: 06/09/2017
      #Date Signed Off:

   @release_1711 @SDU-576
   Feature: As a business, I only want to offer 3 options for Shipping Methods, so that my customer is not wrongfully charged,
     and they receive their product in the timeframe they choose.

     @Macy's @Send
   Scenario: Macy's - Only the first 3 Shipping Options are displayed.
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the bag header title
     When I press the checkout button
     Then I see the Shipping Method Overlay
     And I can see only 3 Shipping Methods
     And I can see the Shipping Methods are in the correct order
     When I call Cancel
       Then I can see the sales trans landing page

     @Bloomingdale's @Send
     Scenario: Bloomingdale's - Only the first 3 Shipping Options are displayed.
       Given I am on "Bloomingdale's Sales Trans"
       When I add an item to the Checkout bag
       Then I can see the added to bag toast message
       And the toast message fades away after 2 seconds
       When I click on the bag icon
       Then I can see the bag header title
       When I press the checkout button
       Then I see the Shipping Method Overlay
       And I can see only 3 Shipping Methods
       And I can see the Shipping Methods are in the correct order
       When I call Cancel
       Then I can see the sales trans landing page