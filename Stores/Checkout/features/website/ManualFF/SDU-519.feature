#Author: Anamaria Oanea - Stores Domain Checkout Team
       #Story: SDU-519 - Authorization Offline
       #Date Created: 08/23/2017
       #Date Signed Off:

@release_1716 @story_SDU-519
Feature: As an associate, I want to know if the system is offline,
  so that I can take the appropriate action and service my customer.

  @Macy's @manual
  Scenario:  The system is offline and the card cannot be authorized - Macy's

    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Macy's Prop" with "17167" at the tendering screen
    Then an error message is displayed
    When I should be able to clear the error message
    Then I can see the mock tendering screen




  @Bloomingdale's @manual
  Scenario:  The system is offline and the card cannot be authorized - Bloomingdale's

    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Bloomingdale's Prop" with "17207" at the tendering screen
    Then an error message is displayed
    When I should be able to clear the error message
    Then I can see the mock tendering screen

