      #Author: Stores Domain Checkout Team
      #Story: SDU-565 - Checkout :: TECH: Bag prompts for CRL only if needed
      #Date Created: 06/21/2017
      #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-565
   Feature: The bag client should only prompt for CRL if the CRL flag is set to true in the item.

     @Macy's
     Scenario: Macy's - The Associate adds a take item that requires a CRL
       Given I am on "Macy's Sales Trans"
       When I add an item to the Checkout bag for a Take Sale
       Then I see the CRL Overlay
       When I close the CRL Overlay
       Then I can see the added to bag toast message
       When I call Cancel
       Then I can see the sales trans landing page

     @Macy's
     Scenario: Macy's - The Associate adds a take item that does not require a CRL
       Given I am on "Macy's Sales Trans"
       When I select Take option
       And I add an item to the Checkout bag that "does not have CRL"
       Then I can see the added to bag toast message
       When I call Cancel
       Then I can see the sales trans landing page

     @Macy's
     Scenario: Macy's - The Associate adds a send item that does not require a CRL
       Given I am on "Macy's Sales Trans"
       When I add an item to the Checkout bag
       Then I can see the added to bag toast message
       When I call Cancel
       Then I can see the sales trans landing page


     @Bloomingdale's
     Scenario: Bloomingdale's - The Associate adds a take item that requires a CRL
       Given I am on "Bloomingdale's Sales Trans"
       When I add an item to the Checkout bag for a Take Sale
       Then I see the CRL Overlay
       When I close the CRL Overlay
       Then I can see the added to bag toast message
       When I call Cancel
       Then I can see the sales trans landing page

     @Bloomingdale's
     Scenario: Bloomingdale's - The Associate adds a take item that does not require a CRL
       Given I am on "Bloomingdale's Sales Trans"
       When I select Take option
       And I add an item to the Checkout bag that "does not have CRL"
       Then I can see the added to bag toast message
       When I call Cancel
       Then I can see the sales trans landing page

     @Bloomingdale's
     Scenario: Bloomingdale's - The Associate adds a send item that does not require a CRL
       Given I am on "Bloomingdale's Sales Trans"
       When I add an item to the Checkout bag
       Then I can see the added to bag toast message
       When I call Cancel
       Then I can see the sales trans landing page