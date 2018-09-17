# Author: Traci Morris
# Story: SDE-393 - OmniClient : New Credit Account - Create Customer
# Date Created: 11/14/2017
# Date Signed Off:

Feature: As an associate, I want to automatically add a customer who is opening a New Credit Account to my client book
  so that I can reduce the number of times I have to ask for information and the amount of steps it takes to service my customer.

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Open New Account - APPROVES - Creates New OCL client (DID NOT exist in OCL DB prior to New Account being opened) PROP CARD.
    POS flag is ON & OCL New Account Opt-In flag is ON
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

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Open New Account w/transaction - APPROVES - Creates New OCL client (DID NOT exist in OCL DB prior to New Account being opened) PROP CARD.
  POS flag is ON & OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account during a transaction for a client not in OCL DB
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    And I search on client created at POS
    Then I verify that a To Do was created
    And I verify that the FYI was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Open New Account - APPROVES - Creates New OCL client (DID NOT exist in OCL DB prior to New Account being opened) COBRAND CARD.
  POS flag is ON & OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account for a client not in OCL DB
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    And I search on client created at POS
    Then I verify that a To Do was created
    And I verify that the FYI was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Open New Account w/transaction - APPROVES - Creates New OCL client (DID NOT exist in OCL DB prior to New Account being opened) COBRAND CARD.
  POS flag is ON & OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account during a transaction for a client not in OCL DB
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as associate
    And I search on client created at POS
    Then I verify that a To Do was created
    And I verify that the FYI was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Open New Account in Ringer Mode - APPROVES - Creates New OCL client (DID NOT exist in OCL DB prior to New Account being opened) PROP CARD.
  POS flag is ON, OCL New Account Opt-In flag is ON and RINGER MODE is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I turn Ringer Mode ON
    And I open a new account during a transaction for a client not in OCL DB
    And the New Account has been approved
    When I launch the bloomingdales's omniclient page
    Then I sign into Omniclient BLM application as Selling Associate not Ringing Associate
    And I search on client created at POS
    Then I verify that a To Do was created
    And I verify that the FYI was created
    And I verify that a Y is displayed in the New Account Column on the My Clients tab
    And verify Credit Card was added to the Wallet

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Verify opening a New Account - DECLINES
  POS flag is ON, OCL New Account Opt-In flag is ON
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

  @manual @domain_stores @omniclient @story_SDE-393 @website @bcom
  Scenario: Verify opening a New Account - PENDING
  POS flag is ON, OCL New Account Opt-In flag is ON
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

