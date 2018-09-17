# Author: Traci Morris
# Story: SDE-174 - OmniClient :: Adding Credit Card to Customer Wallet
# Date Created: 08/02/2017
# Date Signed Off:

Feature: As an associate, I want the ability to add a customer's Credit card to their wallet so that I can
  fully service the purchasing needs of my customer.

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Operational Card (same name) to an Individualized client already in the OCL DB.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Individualized Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is the same as client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Operational Card (different name) to an Individualized client already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Individualized Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
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
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Individualized Card (same name) to a contact (NO CC2ID) who is already in the OCL DB
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I add a new BLM Client: "TEST1" "TEST ACCOUNT"
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit card - name on the credit card is the same as client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
   Then I should see the CC2 Overlay - Side by side comparison (Before & After)
    When I select SAVE
    Then I should see the Individualized Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Individualized Card (different name) to a contact (NO CC2ID) who is already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I add a new BLM Client: "NewClientBLMTest" "UpdateData1Test"
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit card - name on the credit card is different from the client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
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
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the CC2 Overlay - Side by side comparison (Before & After)
    When I select SAVE
    Then I should see the Individualized Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Individualized Card (same name) to an Operational client who is already in the OCL DB
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Operational Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit card - name on the credit card is the same as client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
   Then I should see an error message

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Individualized Card (different name) to an Operational client who is already in the OCL DB - CANCEL & CONTINUE
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Operational Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit card - name on the credit card is different from the client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
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
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see an error message

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Operational Card (same name) to an Operational client already in the OCL DB.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Operational Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is the same as client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Operational Card (different name) to an Operational client already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Operational Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit Card - name on the credit card is different from the client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
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
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the Operational Account added to the Wallet


  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Individualized Card (different name) to an Individualized client already in the OCL DB.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an Individualized Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Individualized Credit Card - name on the credit card is the same as client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see an error message

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Operational Card (same name) to a contact (NO CC2ID) who is already in the OCL DB
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I add a new BLM Client: "TEST2" "TEST ACCOUNT"
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit card - name on the credit card is the same as client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-174 @website @bcom
  Scenario: Add Operational Card (different name) to a contact (NO CC2ID) who is already in the OCL DB - CANCEL & CONTINUE.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I add a new BLM Client: "NewClientBLMTest" "UpdateData1Test"
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overlay
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an Operational Credit card - name on the credit card is different from the client
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
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
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the confirmation overlay
      |    Name on Credit Card: |    Profile Name    |
      |   __MAGSTRIPE NAME__    |  __PROFILE NAME__  |
    When I select the Continue button on Confirmation overlay
    Then I should see the Individualized Account added to the Wallet