 #Author: Stores Domain Checkout Team
   #Story: SDU-718 - Checkout :: Customer swipes an Invalid Card
   #Date Created: 07/25/2017
   #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-718
 Feature: As an associate, I want to see an error when an invalid card is swiped for Tender,
   so that I can let my customer know we aren't currently accepting that type of Tender on uPOS.

   @Macy's @Send
    Scenario Outline: Macy's - In a Place Order When I swipe a card that is not a Macy's Prop
   or Macy's Cobrand Card Then I see the error message.
      Given I am on "Macy's Sales Trans"
      When I checkout an item for a send sale
      And I can see the mock tendering screen
      When I swipe the "<CardType>" at the tendering screen
      Then I see the "<Division>" Invalid Card error message
      When I close the invalid swipe error message
      Then I can see the mock tendering screen
      When I call Cancel
      Then I can see the sales trans landing page

      Examples:
        |CardType                             | Division      |
        | AMEX                                |  Macy's Send  |
        | VISA                                |  Macy's Send  |
        | Discover                            |  Macy's Send  |
        | Mastercard                          |  Macy's Send  |
        | JCB                                 |  Macy's Send  |
        | CUP                                 |  Macy's Send  |
        | Bloomingdale's Classic Cobrand      |  Macy's Send  |
        | Bloomingdale's Single Line Cobrand  |  Macy's Send  |
        | Bloomingdale's pre-Citi Prop        |  Macy's Send  |
        | Bloomingdale's Prop                 |  Macy's Send  |
        | Bloomingdale's Employee Prop        |  Macy's Send  |

   @Macy's @Take
   Scenario Outline: Macy's - In Take Transaction When I swipe a card that is not a Macy's Prop
   or Macy's Cobrand Card Then I see the error message.
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     And I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the "<Division>" Invalid Card error message
     When I close the invalid swipe error message
     Then I can see the mock tendering screen
     When I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |CardType                             | Division      |
       | AMEX                                |  Macy's Take  |
       | VISA                                |  Macy's Take  |
       | Discover                            |  Macy's Take  |
       | Mastercard                          |  Macy's Take  |
       | JCB                                 |  Macy's Take  |
       | CUP                                 |  Macy's Take  |
       | Bloomingdale's Classic Cobrand      |  Macy's Take  |
       | Bloomingdale's Single Line Cobrand  |  Macy's Take  |
       | Bloomingdale's pre-Citi Prop        |  Macy's Take  |
       | Bloomingdale's Prop                 |  Macy's Take  |
       | Bloomingdale's Employee Prop        |  Macy's Take  |


   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - In a Place Order When I swipe a card that is not a Bloomingdale's Prop
   or Bloomingdale's Cobrand Card Then I see the error message.
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     And I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the "<Division>" Invalid Card error message
     When I close the invalid swipe error message
     Then I should be able to see the bag view
     Then I can see the mock tendering screen
     When I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |CardType                      | Division            |
       | AMEX                         | Bloomingdale's Send |
       | VISA                         | Bloomingdale's Send |
       | Discover                     | Bloomingdale's Send |
       | Mastercard                   | Bloomingdale's Send |
       | JCB                          | Bloomingdale's Send |
       | CUP                          | Bloomingdale's Send |
       | Macy's Prop                  | Bloomingdale's Send |
       | Macy's Classic Cobrand       | Bloomingdale's Send |
       | Macy's Single Line Cobrand   | Bloomingdale's Send |
       | Macy's Employee Prop	      | Bloomingdale's Send |
       | Macy's pre-Citi Prop	      | Bloomingdale's Send |

   @Bloomingdale's @Take
   Scenario Outline: Bloomingdale's - In Take Transaction When I swipe a card that is not a Bloomingdale's Prop
   or Bloomingdale's Cobrand Card Then I see the error message.
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     And I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the "<Division>" Invalid Card error message
     When I close the invalid swipe error message
     Then I should be able to see the bag view
     Then I can see the mock tendering screen
     When I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |CardType                      | Division            |
       | AMEX                         | Bloomingdale's Take |
       | VISA                         | Bloomingdale's Take |
       | Discover                     | Bloomingdale's Take |
       | Mastercard                   | Bloomingdale's Take |
       | JCB                          | Bloomingdale's Take |
       | CUP                          | Bloomingdale's Take |
       | Macy's Prop                  | Bloomingdale's Take |
       | Macy's Classic Cobrand       | Bloomingdale's Take |
       | Macy's Single Line Cobrand   | Bloomingdale's Take |
       | Macy's Employee Prop	      | Bloomingdale's Take |
       | Macy's pre-Citi Prop	      | Bloomingdale's Take |


   @Macy's @Send
   Scenario Outline: Macy's - In a Place Order, I swipe a card that is not a Bloomingdale's Prop
   or Bloomingdale's Cobrand Card Then I suspend.
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     And I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the "<Division>" Invalid Card error message
     When I press the error overlay suspend button
     Then I should see the customer name overlay
     When I input the customers name
     And I click customer name overlay continue button
     Then I see the suspended bag confirmation overlay
     And I press OK on the suspended confirmation overlay
     Then I can see the sales trans landing page

     Examples:
       |CardType                             | Division     |
       | AMEX                                |  Macy's Send |
       | VISA                                |  Macy's Send |
       | Discover                            |  Macy's Send |
       | Mastercard                          |  Macy's Send |
       | JCB                                 |  Macy's Send |
       | CUP                                 |  Macy's Send |

   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - In a Place Order, I swipe a card that is not a Bloomingdale's Prop
   or Bloomingdale's Cobrand Card Then I suspend.
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     And I can see the mock tendering screen
     When I swipe the "<CardType>" at the tendering screen
     Then I see the "<Division>" Invalid Card error message
     When I press the error overlay suspend button
     Then I should see the customer name overlay
     When I input the customers name
     And I click customer name overlay continue button
     Then I see the suspended bag confirmation overlay
     And I press OK on the suspended confirmation overlay
     Then I can see the sales trans landing page

     Examples:
       |CardType                             | Division             |
       | AMEX                                |  Bloomingdale's Send |
       | VISA                                |  Bloomingdale's Send |
       | Discover                            |  Bloomingdale's Send |
       | Mastercard                          |  Bloomingdale's Send |
       | JCB                                 |  Bloomingdale's Send |
       | CUP                                 |  Bloomingdale's Send |
