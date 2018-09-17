# Author: Traci Morris
# Story: SDE-63 - OmniClient :: Add to Wallet
# Date Created: 07/17/2017
# Date Signed Off:

Feature: As a developer, I want to call the new enterprise level CC2 service when adding a card to the
  wallet so that the "best address" logic can live at the enterprise level.

  @manual @domain_stores @omniclient @story_SDE-63 @website @mcom
  Scenario: Add Operational Card to a client that does not have a CC2ID.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I add a new Macys Client: "NewClientMACYsTest" "UpdateData1Test"
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overly
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an operational Credit Card
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the Operational Account added to the Wallet


  @manual @domain_stores @omniclient @story_SDE-63 @website @mcom
  Scenario: Add Operational Card to an Individualized client already in the OCL DB.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overly
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an operational Credit Card
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the Operational Account added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-63 @website @mcom
  Scenario: Add Individualized Card to an Individualized client already in the OCL DB - Error msg should display.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen
    When I select the name link
    Then I should see the Client Profile Screen
    When I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overly
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an individualized Credit Card
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see an error message


  @manual @domain_stores @omniclient @story_SDE-63 @website @mcom
  Scenario: Add Individualized Card to a client that does not have a CC2ID - side by side comparison.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I add a new Macys Client: "NewClientMACYsTest" "UpdateData1Test"
    And I navigate to Manage Client tab
    And I select the Wallet tab
    Then I should see the Wallet Screen
    When I select the Add Account button
    Then I should see the Attention overly
    When I select the Add button
    Then I should see the Add Account Overlay
    When I swipe an individualized Credit Card
    Then I should see the last four digits of the Credit Card
    When I select the Add Purchasing Options box
    And I select Save on the Add Account Overlay
    Then I should see the CC2 Overlay - Side by side comparison (Before & After)
    When I select SAVE
    Then I should see the Individualized Account added to the Wallet


