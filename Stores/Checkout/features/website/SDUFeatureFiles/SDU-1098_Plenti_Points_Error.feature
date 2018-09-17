#Author: Stores Domain Checkout Team
   #Story: SDU-1098 Checkout :: Plenti Points Error
   #Date Created: 09/10/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_19 @story_SDU-1098
Feature: Plenti points error toast on bag screen is not displayed after after unsuccessful authorization

  @Macy's @Send
  Scenario: Macys Send -Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "declined_Card_Macy's" at the tendering screen
    Then The Authorization spinner closes
    And An error indicating that the card was not approved is displayed
    When I click on I Got this button
    Then I am on "Tendering" page
    When I click on the bag icon
    Then Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @take
  Scenario: Macys take - Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "declined_Card_Macy's" at the tendering screen
    Then The Authorization spinner closes
    And Error message is displayed accordingly and UI elements are the expected ones
    Then The Authorization spinner closes
    And An error indicating that the card was not approved is displayed
    When I click on I Got this button
    Then I am on "Tendering" page
    When I click on the bag icon
    Then Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdales Send - Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "declined_Card_Bloomingdale's" at the tendering screen
    Then The Authorization spinner closes
    And An error indicating that the card was not approved is displayed
    When I click on I Got this button
    Then I am on "Tendering" page
    When I click on the bag icon
    And Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @take
  Scenario: Bloomingdales Take - Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "declined_Card_Bloomingdale's" at the tendering screen
    Then The Authorization spinner closes
    And Error message is displayed accordingly and UI elements are the expected ones
    Then The Authorization spinner closes
    And An error indicating that the card was not approved is displayed
    When I click on I Got this button
    Then I am on "Tendering" page
    When I click on the bag icon
    And Plenti points error toast on bag screen is not displayed after after unsuccessful authorization
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page