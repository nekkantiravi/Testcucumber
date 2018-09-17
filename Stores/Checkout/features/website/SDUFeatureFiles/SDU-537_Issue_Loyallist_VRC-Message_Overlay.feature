      #Author: Stores Domain Checkout Team
      # Story: SDU-537 Checkout :: Issue Loyallist VRC-Message Overlay
      #Date Created: 8/21/2017
      #Date Signed Off:


   @domain_stores @project_checkout @release_17 @SDU-537
   Feature: As a Bloomingdale's customer shopping in-store, I want to earn a Virtual Rewards Card
            when I meet my loyalty points threshold so that I can use that card to make future purchases.

     @Bloomingdale's @Send
     Scenario: Bloomingdale's - Send transaction above the threshold
         Given I am on "Bloomingdale's Sales Trans"
         When I add "91709543745" item to the Checkout bag
         Then I can see the added to bag toast message
         When I click on the bag icon

         And I swipe the item from right to left
#         Then I select More menu option
#         When I select "Change Price" from the More menu options
#         Then The change price overlay displays properly
#         When I complete price change and overlay closes
#         And Price of the item is updated in bag
#
#         Then I can see the checkout button
#         When I press the checkout button
#         And I select Free Shipping
#         And I press next steps
#         And I input the Shipping Information
#         And I press next steps
#         And I press next steps
#         Then I see the Order Review Overlay
#         When I press next steps
#         Then I can see the mock tendering screen
#         When I swipe the "Bloomingdale's Classic Cobrand" at the tendering screen
#         Then I see the authorization spinner
#         When The Authorization spinner closes
#         Then I can see the signature view
#         When I input my signature
#         And I press the Confirm signature button
#         Then I can see the postTender screen
#         And I see the Loyallist VRC Overlay
#         When I close the Loyallist VRC Overlay
#         Then I do not see the VRC Overlay
#         When I click posttender print button
#         Then I can see the sales trans landing page

         When I click on Hamburger icon
         And I click on Cancel Transaction button
         Then I am on "Add Product" page

#     @Bloomingdale's @Take
#     Scenario: Take Sale above the Threshold
#       Given I am on "Bloomingdale's Sales Trans"
#       When I select Take option
#       And I add "91709543745" item to the Checkout bag
#       And I close the CRL Overlay
#       And I click on the bag icon
#       When I press the checkout button
#       Then I can see the bag fee overlay
#       When I add "0" bags
#       Then I can see the mock tendering screen
#       When I swipe the "New Bloomies Card" at the tendering screen
#       Then I see the authorization spinner
#       When The Authorization spinner closes
#       Then I can see the signature view
#       When I input my signature
#       And I press the Confirm signature button
#       Then I can see the postTender screen
#       And I see the Loyallist VRC Overlay
#       When I close the Loyallist VRC Overlay
#       Then I do not see the VRC Overlay
#       When I click posttender print button
#        Then I can see the sales trans landing page

#
#     @Bloomingdale's
#     Scenario: Bloomingdale's - Take Sale Under the Threshold
#       Given I am on "Bloomingdale's Sales Trans"
#       When I add "20714001940" item to the Checkout bag
#       Then I can see the mock tendering screen
#       When I swipe the "<CardType>" with "<KeyVersion>" at the tendering screen
#       Then I see the authorization spinner
#       When The Authorization spinner closes
#       Then I can see the postTender screen
#       And I do not see the VRC Overlay


              #      @Bloomingdale's
      #      Scenario: Bloomingdale's - Send transaction Under the threshold
      #       Given I am on "Bloomingdale's Sales Trans"
      #        When I add "20714001940" item to the Checkout bag
      #        And I click on the bag icon
      #       Then I can see the checkout button
      #       When I press the checkout button
      #       And I select Free Shipping
      #       And I press next steps
      #       And I input the Shipping Information
      #       And I press next steps
      #       And I press next steps
      #       Then I see the Order Review Overlay
      #       When I press next steps
      #       Then I can see the mock tendering screen
      #       When I swipe the "Bloomingdale's Prop Card" with "17207" at the tendering screen
      #       Then I see the authorization spinner
      #       When The Authorization spinner closes
      #        Then I can see the postTender screen
      #       And I do not see the VRC Overlay
      #
