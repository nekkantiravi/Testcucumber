#Author: Anamaria Oanea - Stores Domain Checkout Team
       #Story: SDU-451 - Loyalty guaranteed earning
       #Date Created: 08/08/2017
       #Date Signed Off:

@release_1716 @story_SDU-451
Feature: As a customer shopping in a Bloomingdale's or Macy's store,
  I want to earn my points even if my points balance is not updated in real time,
  so that I can view and use rewards at a later time

  @manual @Macy's @Send
  Scenario:  A customer earns Loyalty points when making a purchase at Macy's but the Loyalty system is not available
  and he sees his points ont he next transaction

    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Macy's Prop" with "17167" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    Then I press the Confirm signature button
    When I'm redirected to receipt page
    Then I can see the error overlay
    When the loyalty error message overlay is cleared
    Then the receipt page is displayed
    When the receipt is printed
    Then a message on the receipt is indicating that the points were not updated
    When I make the Loyalty system available
    And I checkout an item for a take sale
    Then I print the receipt
    And I see the Loyalty points from both transactions on the receipt
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page

  @manual @Bloomingdale's @Send
  Scenario:  A customer earns Loyalty points when making a purchase at Bloomingdale's but the Loyalty system is not available

    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Bloomingdale's Prop" with "17207" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    Then I press the Confirm signature button
    When I'm redirected to receipt page
    Then I can see the error overlay
    When the loyalty error message overlay is cleared
    Then the receipt page is displayed
    When the receipt is printed
    Then a message on the receipt is indicating that the points were not updated
    When I make the Loyalty system available
    And I checkout an item for a take sale
    Then I print the receipt
    And I see the Loyalty points from both transactions on the receipt
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page

