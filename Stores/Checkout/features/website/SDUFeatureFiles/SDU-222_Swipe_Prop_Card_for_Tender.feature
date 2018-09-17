 #Author: Stores Domain Checkout Team
   #Story: SDU-222 - Checkout :: Swipe Prop Card for Tender
   #Date Created: 08/07/2017
   #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-222
 Feature: As a customer, I want to pay with my Macy's/Bloomingdale's Card, so that I can reap the rewards of being a loyal cardholder.

   @Macy's @send
    Scenario Outline: Macys- In a Send When I swipe a prop card then I see the spinner and the card is authorized successfully over $30.
     Given I am on "Macy's Sales Trans"
      When I checkout an item for a send sale
      Then I can see the mock tendering screen
      And I can verify the authorize button was removed
      When I swipe the "<CardType>" at the tendering screen
      Then I see the authorization spinner
      When The Authorization spinner closes
      Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

     Examples:
       |CardType                   |
       | Macy's Prop               |
       | Macy's Classic Cobrand    |
       | Macy's Single Line Cobrand|
       | Macy's Employee Prop      |
       | Macy's pre-Citi Prop      |

   @Macy's @take
   Scenario Outline: Macys- In a Take When I swipe a prop card then I see the spinner and the card is authorized successfully over $30.
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
      When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page


     Examples:
       |CardType                   |
       | Macy's Prop               |
       | Macy's Classic Cobrand    |
       | Macy's Single Line Cobrand|
       | Macy's Employee Prop      |
       | Macy's pre-Citi Prop      |

   @Macy's @send
   Scenario Outline: Macys- In a Send When I swipe a prop card then I see the spinner and the card is authorized successfully under $30.
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag that costs less than "$30 for Macy's"
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     And I select Free Shipping
     And I press next steps
     And I input the Shipping Information
     And I press next steps
     And I press next steps
     Then I see the Order Review Overlay
     When I press next steps
     Then I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I do not see the signature view
     And I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

     Examples:
       |CardType                   |
       | Macy's Prop               |
       | Macy's Classic Cobrand    |
       | Macy's Single Line Cobrand|
       | Macy's Employee Prop      |
       | Macy's pre-Citi Prop      |


   @Macy's @take
   Scenario Outline: Macys- In a Take When I swipe a prop card then I see the spinner and the card is authorized successfully under $30.
     Given I am on "Macy's Sales Trans"
     When I select Take option
     And I add an item to the Checkout bag that costs less than "$30 for Macy's"
     Then I see the CRL Overlay
     When I scan "123456789" in to the CRL Overlay
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#     Then I can see the bag fee overlay
#     When I add "0" bags
     Then I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I do not see the signature view
     And I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

     Examples:
       |CardType                   |
       | Macy's Prop               |
       | Macy's Classic Cobrand    |
       | Macy's Single Line Cobrand|
       | Macy's Employee Prop      |
       | Macy's pre-Citi Prop      |


   @Bloomingdale's @send
   Scenario Outline: Bloomingdale's- In a Place Order When I swipe a prop card then I see the spinner and the card is authorized successfully over $30.
     Given I am on "Bloomingdale's Sales Trans"
     When I add "20714001940" item to the Checkout bag
     Then I can see the added to bag toast message
     And I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     And I select Free Shipping
     And I press next steps
     And I input the Shipping Information
     And I press next steps
     And I press next steps
     Then I see the Order Review Overlay
     When I press next steps
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page


     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |
       | Bloomingdale's pre-Citi Prop        |
       | Bloomingdale's Prop                 |
       | Bloomingdale's Employee Prop        |

   @Bloomingdale's @take
   Scenario Outline: Bloomingdale's In a Take when I swipe a prop card then I see the spinner and the card is authorized successfully over $30.
     Given I am on "Bloomingdale's Sales Trans"
     When I select Take option
     And I add "20714001940" item to the Checkout bag
     When I close the CRL Overlay
     And I click on the bag icon
     Then I should be able to see the bag view
     When I press the checkout button
     Then I can see the bag fee overlay
     When I add "0" bags
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page


     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |
       | Bloomingdale's pre-Citi Prop        |
       | Bloomingdale's Prop                 |
       | Bloomingdale's Employee Prop        |

   @Bloomingdale's @send
   Scenario Outline: Bloomingdale's- In a Place Order When I swipe a prop card then I see the spinner and the card is authorized successfully under $30.
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag that costs less than "$30 for Bloomingdale's"
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     And I select Free Shipping
     And I press next steps
     And I input the Shipping Information
     And I press next steps
     And I press next steps
     Then I see the Order Review Overlay
     When I press next steps
     Then I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I do not see the signature view
     And I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |
       | Bloomingdale's pre-Citi Prop        |
       | Bloomingdale's Prop                 |
       | Bloomingdale's Employee Prop        |

   @Bloomingdale's @take
   Scenario Outline: Bloomingdale's- In a Take When I swipe a prop card then I see the spinner and the card is authorized successfully under $30.
     Given I am on "Bloomingdale's Sales Trans"
     When I select Take option
     And I add an item to the Checkout bag that costs less than "$30 for Bloomingdale's"
     And I close the CRL Overlay
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I can see the bag fee overlay
     When I add "0" bags
     Then I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     Then I do not see the signature view
     And I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |
       | Bloomingdale's pre-Citi Prop        |
       | Bloomingdale's Prop                 |
       | Bloomingdale's Employee Prop        |