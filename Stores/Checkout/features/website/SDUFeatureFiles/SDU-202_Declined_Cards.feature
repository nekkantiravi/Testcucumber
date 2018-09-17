   #Author: Stores Domain Checkout Team
   #Story: SDU-202- Checkout :: Card declined
   #Date Created: 24/09/2017
   #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-202
 Feature: As a customer, I want to know if my card was declined, so I can take the appropriate action and/or choose another form of tender.

   @Macy's @send
  Scenario Outline: Macy's - In a Place Order When I swipe a prop card then I see the spinner,the card is authorized successfully and no error message is displayed
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "<CardType>" at the tendering screen
     Then The Authorization spinner closes
     And No overlay and error message are displayed
    And I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

    Examples:
      | CardType               |
      | Macy's Prop            |
      | Macy's Classic Cobrand |

 @Macy's @send
   Scenario: Macy's - In case the card was declined,the card is not authorized and error message is displayed accordingly
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "declined_Card_Macy's" at the tendering screen
     Then The Authorization spinner closes
     And Error message is displayed accordingly and UI elements are the expected ones
     When I close the overlay
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page

   @Macy's @take
   Scenario: Macy's - In a Take When I swipe a prop card That declines then I see the decline message
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "declined_Card_Macy's" at the tendering screen
     Then The Authorization spinner closes
     And Error message is displayed accordingly and UI elements are the expected ones
     When I close the overlay
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page

 @Macy's @send
   Scenario: Macy's - I can close the overlay and return to the tendering screen
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "declined_Card_Macy's" at the tendering screen
     Then The Authorization spinner closes
     And Close button is properly displayed and clickable
     When I close the overlay
     Then I can see the mock tendering screen
     And No overlay and error message are displayed
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page


   @Bloomingdale's @send
   Scenario Outline: Bloomingdale's Sales Trans - In a Place Order When I swipe a prop card then I see the spinner,the card is authorized successfully and no error message is displayed
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "<CardType>" at the tendering screen
     Then The Authorization spinner closes
     And No overlay and error message are displayed
     And I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I can see the sales trans landing page

     Examples:
       |CardType                             |
       | Bloomingdale's Classic Cobrand      |
       | Bloomingdale's Single Line Cobrand  |

 @Bloomingdale's @send
   Scenario: Bloomingdale's Sales Trans -  In case the card was declined,the card is not authorized and error message is displayed accordingly
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "declined_Card_Bloomingdale's" at the tendering screen
    Then The Authorization spinner closes
    And Error overlay is displayed accordingly when card is declined
    When I close the overlay
   And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


   @Bloomingdale's @take
   Scenario: Bloomingdale's In a Take when I swipe a prop card then I see the spinner and the card is authorized successfully over $30.
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     When I swipe the "declined_Card_Bloomingdale's" at the tendering screen
     Then I see the authorization spinner
     And The Authorization spinner closes
     And Error overlay is displayed accordingly when card is declined
     When I close the overlay
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page


   @Bloomingdale's @send
   Scenario: Bloomingdale's Sales Trans - I can close the overlay and return to the bag screen
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I can verify the authorize button was removed
     When I swipe the "declined_Card_Bloomingdale's" at the tendering screen
     Then The Authorization spinner closes
     And Error overlay is displayed accordingly when card is declined
     And Close button is properly displayed and clickable
     When I close the overlay
     Then I can see the mock tendering screen
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page