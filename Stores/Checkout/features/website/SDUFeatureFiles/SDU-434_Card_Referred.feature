#Author: Stores Domain Checkout Team
   #Story: SDU-434- Checkout :: Card Referred
   #Date Created: 24/09/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-434
Feature: As an associate, I want to know if my customer's card was referred, so I can take the appropriate action.

  @Macy's @Send
  Scenario Outline: Macys - In a Place Order When I swipe a prop card then I see the spinner,the card is authorized successfully and no error message is displayed
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    When I see the same as Shipping prompt
    Then I click on Next Step button
    And I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "<CardType>" at the tendering screen
    Then The Authorization spinner closes
    And No overlay and error message are displayed
    When I call Cancel
    Then I can see the sales trans landing page

    Examples:
      | CardType               |
      | Macy's Prop            |
      | Macy's Classic Cobrand |

  @Macy's @Send
  Scenario: Macys - In case the card was declined,the card is not authorized and error message is displayed accordingly
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    When I see the same as Shipping prompt
    Then I click on Next Step button
    And I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "referred_Card_Macy's" at the tendering screen
    Then The Authorization spinner closes
    And The overlay indicating the card was referred and error message are displayed properly
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Take
  Scenario: Macys- In a Take When I swipe a prop card That declines then I see the decline message
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "referred_Card_Macy's" at the tendering screen
    Then I see the authorization spinner
    Then The Authorization spinner closes
    And The overlay indicating the card was referred and error message are displayed properly
    When I call Cancel
    Then I can see the sales trans landing page


  @Macy's @Send
  Scenario: Macy's Sales Trans - I can close the overlay and return to the bag screen
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    When I see the same as Shipping prompt
    Then I click on Next Step button
    And I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "referred_Card_Macy's" at the tendering screen
    Then The Authorization spinner closes
    And The overlay indicating the card was referred and error message are displayed properly
    And Close button is properly displayed and clickable
    When I close the overlay
    Then I can see the mock tendering screen
    And No overlay and error message are displayed
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario Outline: Bloomingdale's Sales Trans - In a Place Order When I swipe a prop card then I see the spinner,the card is authorized successfully and no error message is displayed
    Given I am on "Bloomingdale's Sales Trans"
    When I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    When I see the same as Shipping prompt
    Then I click on Next Step button
    And I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "<CardType>" at the tendering screen
    Then The Authorization spinner closes
    And No overlay and error message are displayed
    When I call Cancel
    Then I can see the sales trans landing page

    Examples:
      | CardType                           |
      | Bloomingdale's Classic Cobrand     |
      | Bloomingdale's Single Line Cobrand |


  @Bloomingdale's @Send
  Scenario: Bloomingdale's Sales Trans -  In case the card was declined,the card is not authorized and error message is displayed accordingly
    Given I am on "Bloomingdale's Sales Trans"
    When I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    When I see the same as Shipping prompt
    Then I click on Next Step button
    And I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "referred_Card_Bloomingdale's" at the tendering screen
    Then The Authorization spinner closes
    And The overlay indicating the card was referred and error message are displayed properly
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's In a Take when I swipe a prop card then I see the spinner and the card is authorized successfully over $30.
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "20714001940" item to the Checkout bag
    When I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I press the checkout button
#*******************************************************************************
#* Overnight automation now running in store 166 where bag fees are turned off *
#*******************************************************************************
#    Then I can see the bag fee overlay
#    When I add "0" bags
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "referred_Card_Bloomingdale's" at the tendering screen
    Then I see the authorization spinner
    Then The Authorization spinner closes
    And The overlay indicating the card was referred and error message are displayed properly
    When I call Cancel
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's Sales Trans - I can close the overlay and return to the tendering screen
    Given I am on "Bloomingdale's Sales Trans"
    When I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I press the checkout button
    And I see the Shipping Method Overlay
    And I can see the Standard option selected
    When I click on Next Step button
    Then I see the Shipping information Overlay
    And I input the Shipping Information
    And I click on Next Step button
    When I see the same as Shipping prompt
    Then I click on Next Step button
    And I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I tap on the Complete button
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "referred_Card_Bloomingdale's" at the tendering screen
    Then The Authorization spinner closes
    And The overlay indicating the card was referred and error message are displayed properly
    And Close button is properly displayed and clickable
    When I close the overlay
    Then I can see the mock tendering screen
    When I call Cancel
    Then I can see the sales trans landing page

