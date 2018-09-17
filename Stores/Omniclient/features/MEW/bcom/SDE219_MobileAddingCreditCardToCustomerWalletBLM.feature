# Author: Traci Morris
# Story: SDE-219 - OmniClient :: MOBILE: Adding Credit card to Customer Wallet
# Date Created: 08/07/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want the ability to add a Client/Customer/Contact's
  Credit Card to their wallet so that I can fully service the purchasing needs of my customer.

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Operational Card (same name) to an Individualized client already in the OCL DB.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Individualized Credit Card on the Credit Card search page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card - name on the credit card is the same as client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Operational Card (different name) to an Individualized client already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Individualized Credit Card on the Credit Card search page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Cancel button on Confirmation overlay
    Then I see the Wallet screen
    And the account has not been added to the Wallet
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Individualized Card (same name) to a contact (NO CC2ID) who is already in the OCL DB
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I enter a contacts name in the Search box
    Then I should see the Contact display in italics
    When I select the Contacts name link
    Then I should see Contacts profile
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Individualized Credit Card - name on the credit card is the same as client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the CC2 Overlay - Side by side comparison (Before & After)
    When I select SAVE
    Then I should see the Individualized Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Individualized Card (different name) to a contact (NO CC2ID) who is already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I enter a contacts name in the Search box
    Then I should see the Contact display in italics
    When I select the Contacts name link
    Then I should see Contacts profile
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Individualized Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Cancel button on Confirmation overlay
    Then I see the Wallet screen
    And the account has not been added to the Wallet
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the CC2 Overlay - Side by side comparison (Before & After)
    When I select SAVE
    Then I should see the Individualized Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Individualized Card (same name) to an Operational client who is already in the OCL DB
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Operational Credit Card on the Credit Card search page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Individualized Credit Card - name on the credit card is the same as client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see an error message

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Individualized Card (different name) to an Operational client who is already in the OCL DB - CANCEL & CONTINUE
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Operational Credit Card on the Credit Card search page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Individualized Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Cancel button on Confirmation overlay
    Then I see the Wallet screen
    And the account has not been added to the Wallet
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see an error message

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Operational Card (same name) to an Operational client already in the OCL DB.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Operational Credit Card on the Credit Card search page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card - name on the credit card is the same as client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Operational Card (different name) to an Operational client already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Operational Credit Card on the Credit Card search page
    Then I should see the client listed on the Search Results page
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Cancel button on Confirmation overlay
    Then I see the Wallet screen
    And the account has not been added to the Wallet
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the Operational Account added to the Wallet


  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Individualized Card (different name) to an Individualized client already in the OCL DB.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Individualized Credit Card on the Credit Card search page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Individualized Credit Card - name on the credit card is the same as client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see an error message

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Operational Card (same name) to a contact (NO CC2ID) who is already in the OCL DB
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I enter a contacts name in the Search box
    Then I should see the Contact display in italics
    When I select the Contacts name link
    Then I should see Contacts profile
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card - name on the credit card is the same as client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-219 @bcom @MEW
  Scenario: Add Operational Card (different name) to a contact (NO CC2ID) who is already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I enter a contacts name in the Search box
    Then I should see the Contact display in italics
    When I select the Contacts name link
    Then I should see Contacts profile
    When I select the Wallet icon
    Then I should see Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Screen.
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Cancel button on Confirmation overlay
    Then I see the Wallet screen
    And the account has not been added to the Wallet
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the Account information in the Add Account screen
      |Credit Card Type  | Masked Credit card #   |   Expiration Date (if applicable)  |
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Screen
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the Individualized Account added to the Wallet