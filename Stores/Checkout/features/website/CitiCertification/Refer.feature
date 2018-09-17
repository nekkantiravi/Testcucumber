 #Author: Stores Domain Checkout Team
    #Story: Citi Certification - Checkout :: Approve
    #Date Created: 09/26/2017
    #Date Signed Off:

 @domain_stores @project_checkout
 Feature: As a QE, I want to authorize proprietary accounts so that we can pass Citi Certification.

   @Macy's @Take
   Scenario Outline: In a Take transaction, I tender with an account that refers, then cancel the transaction.
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     And The overlay indicating the card was referred and error message are displayed properly
     When I close the overlay
     Then I can see the mock tendering screen
     And I click on Hamburger icon
     And I cancel the transaction

     Examples:
       |CardType                   |
       | referred_Card_Macy's      |
       | Macy's Return Mail        |

   @Macy's @Send
   Scenario Outline: In a Send transaction, I tender with an account that refers, then suspend the transaction.
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "<CardType>" at the tendering screen
     Then I see the authorization spinner
     When The Authorization spinner closes
     And The overlay indicating the card was referred and error message are displayed properly
     When I close the overlay
     Then I can see the mock tendering screen
     And I click on the bag icon
     When I press the suspend button
     And I input "Tricia" in the suspend text box
     And I press OK on the suspended confirmation overlay
     Then I am on "Bag" page

     Examples:
       |CardType                   |
       | referred_Card_Macy's      |
       | Macy's Return Mail        |

   @Bloomingdale's @Take
   Scenario Outline: In a Take transaction, I tender with an account that refers, then cancel the transaction.
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
     And The overlay indicating the card was referred and error message are displayed properly
     When I close the overlay
     Then I can see the mock tendering screen
     And I click on Hamburger icon
     And I cancel the transaction

     Examples:
       |CardType                           |
       | referred_Card_Bloomingdale's      |
       | Bloomingdale's Return Mail        |

   @Bloomingdale's @Send
   Scenario Outline: In a Send transaction, I tender with an account that refers, then suspend the transaction.
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
     And The overlay indicating the card was referred and error message are displayed properly
     When I close the overlay
     Then I can see the mock tendering screen
     And I click on the bag icon
     When I press the suspend button
     And I input "Tricia" in the suspend text box
     And I press OK on the suspended confirmation overlay
     Then I am on "Bag" page

     Examples:
       |CardType                           |
       | referred_Card_Bloomingdale's      |
       | Bloomingdale's Return Mail        |