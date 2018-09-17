# Author: Traci Morris
# Story: SDE-186 - OmniClient :: MOBILE: Add to Wallet
# Date Created: 07/25/2017
# Date Signed Off:

Feature: As a developer, I want to call the new enterprise level CC2 service when adding a card to the
  wallet so that the "best address" logic can live at the enterprise level.

  @manual @domain_stores @omniclient @story_SDE-186 @mcom @MEW
  Scenario: Add Operational Card to an Individualized client already in the OCL DB.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients page
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an individualized credit card on the Credit Card search page
    Then I should see the client listed on the Search Results page
    When I select the Name link of the client
    Then I should see the Client Profile Screen
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an operational Credit Card
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable) |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-186 @mcom @MEW
  Scenario: Add Individualized Card to an Individualized client already in the OCL DB - Error msg should display.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients page
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an individualized credit card on the Credit Card search page
    Then I should see the client listed on the Search Results page
    When I select the Name link of the client
    Then I should see the Client Profile Screen
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an individualized Credit Card
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see an error message


  @manual @domain_stores @omniclient @story_SDE-186 @mcom @MEW
  Scenario: Add Individualized Card to client (no CC2ID) - side by side comparison.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Cients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "<tel_nr>" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    Then I should see the client listed on the Search Results page
    When I select the Name link of the client
    Then I should see the Client Profile Screen
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an individualized Credit Card
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the CC2 Overlay - Side by side comparison (Before & After)
    When I select SAVE on the Add Account Screen
    Then I should see the Individualized Account added to the Wallet



  @manual @domain_stores @omniclient @story_SDE-186 @bcom @MEW
  Scenario: Add Operational Card to a client (no CC2ID).
   Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Cients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "<tel_nr>" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    Then I should see the client listed on the Search Results page
    When I select the Name link of the client
    Then I should see the Client Profile Screen
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    When I select SAVE on the Add Account Screen
    Then I should see the Operational Account added to the Wallet