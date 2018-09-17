 #Author: Stores Domain Checkout Team
    #Story: Citi Certification - Checkout :: Approve
    #Date Created: 09/26/2017
    #Date Signed Off:

 @domain_stores @project_checkout
 Feature: As a QE, I want to authorize proprietary accounts so that we can pass Citi Certification.

   @Macy's @Send
   Scenario Outline: In a Send transaction, I successfully complete a transaction with a proprietary account that approves.
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
     And I can see the add printer button and print button
     When I click posttender print button
     Then I am on "Add Product" page

     Examples:
       |CardType                   |
       | Macy's Prop               |
       | Macy's Classic Cobrand    |
       | Macy's Single Line Cobrand|
       | Macy's Employee Prop      |
       | Macy's pre-Citi Prop      |
       | Macy's Make Magic         |


   @Macy's @Take
   Scenario Outline: In a Take transaction, I successfully complete a transaction with a proprietary account that approves.
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
     And I can see the add printer button and print button
     When I click posttender print button
     Then I am on "Add Product" page

     Examples:
       |CardType                   |
       | Macy's Prop               |
       | Macy's Classic Cobrand    |
       | Macy's Single Line Cobrand|
       | Macy's Employee Prop      |
       | Macy's pre-Citi Prop      |
       | Macy's Make Magic         |
       | Bloomingdale's Employee Prop |

   @Bloomingdale's @Send
   Scenario Outline: In a Send transaction, I successfully complete a transaction with a proprietary account that approves.
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
     And I can see the add printer button and print button
     When I click posttender print button
     Then I am on "Add Product" page


     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |
       | Bloomingdale's pre-Citi Prop        |
       | Bloomingdale's Prop                 |
       | Bloomingdale's Employee Prop        |
       | Bloomingdale's Citi Employee        |

   @Bloomingdale's @Take
   Scenario Outline: In a Take transaction, I successfully complete a transaction with a proprietary account that approves.
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
     And I can see the add printer button and print button
     When I click posttender print button
     Then I am on "Add Product" page


     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |
       | Bloomingdale's pre-Citi Prop        |
       | Bloomingdale's Prop                 |
       | Bloomingdale's Employee Prop        |
       | Bloomingdale's Citi Employee        |
       | Macy's Employee Prop                |