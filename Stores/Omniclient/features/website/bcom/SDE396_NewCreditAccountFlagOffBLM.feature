# Author: Traci Morris
# Story: SDE-396 - OmniClient : New Credit Account - Flag Off
# Date Created: 11/15/2017
# Date Signed Off:

Feature: As an associate, I want to automatically add a customer who is opening a New Credit Account to my client book
  so that I can reduce the number of times I have to ask for information and the amount of steps it takes to service my customer.

  @manual @domain_stores @omniclient @story_SDE-396 @website @bcom
  Scenario: Open New Account - APPROVES - Creates New OCL client (DID NOT exist in OCL DB prior to New Account being opened) PROP CARD.
    POS flag is OFF & OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account for a client not in OCL DB
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    And I search on client created at POS
    Then I verify that a To Do was created
    And I verify that the FYI was created
    And I verify that a Y is displayed in the New Account Column on the My Customers tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-396 @website @bcom
  Scenario: Open New Account - APPROVES - Creates New Affiliation (DID exist in OCL DB prior to New Account being opened) PROP CARD.
  POS flag is OFF & OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account for a client in OCL DB no relation to logged on associate
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    When I search on client affiliated at POS
    Then I verify logged on associate has a relationship with client
    And I verify that a To Do was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-396 @website @bcom
  Scenario: Open New Account - APPROVES - Promotes Contact to Customer (Contact exists in OCL DB prior to New Account being opened) PROP CARD.
  POS flag is OFF & OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account for a contact in OCL DB - relation to logged on associate
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    When I search on customer
    Then I verify contact has been promoted to customer
    And I verify that a To Do was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-396 @website @bcom
  Scenario: Open New Account in Ringer Mode - APPROVES - Creates New Client (DID NOT exist in OCL DB prior to New Account being opened) PROP CARD
  POS flag is OFF, OCL New Account Opt-In flag is ON and RINGER MODE is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I turn Ringer Mode ON
    And I open a new account for a client not in OCL DB
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as Selling Associate not Ringing Associate
    When I search on client created at POS
    And I verify that a To Do was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-396 @website @bcom
  Scenario: Verify opening a New Account - DECLINES
  POS flag is OFF, OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    And I open a new account for a client not in OCL DB
    And the New Account has been declined
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    And I search on client declined at POS
    Then I should not see results for declined client at POS
    When I select FYI's link
    Then I should see a FYI for declined client at POS

  @manual @domain_stores @omniclient @story_SDE-396 @website @bcom
  Scenario: Verify opening a New Account - PENDING
  POS flag is OFF, OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    And I open a new account for a client not in OCL DB
    And the New Account is pending
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    And I search on client whose account is pending
    Then I should not see results for pending client
    When I select FYI's link
    Then I should see a PENDING FYI for pending client at POS