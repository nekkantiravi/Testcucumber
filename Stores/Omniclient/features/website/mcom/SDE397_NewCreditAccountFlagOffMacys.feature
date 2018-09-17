# Author: Traci Morris
# Story: SDE-397 - OmniClient : New Credit Account - Macy's Flag Off
# Date Created: 11/15/2017
# Date Signed Off:

Feature: As an associate, I do not want to automatically add a customer who is opening a New Credit Account to my client
  book so that I can personally manage and decide which relationship I need to save.

  @manual @domain_stores @omniclient @story_SDE-397 @website @mcom
  Scenario: Open New Account - APPROVES - New client is NOT created in OCL - PROP CARD.
    POS flag is OFF & OCL New Account Opt-In flag is OFF
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account for a client not in OCL DB
    And the New Account has been approved
    When I launch the macy's omniclient page
    Then I sign into Omniclient application as associate
    And I search on client with new account POS
    And I should not see results for new account client created at POS

  @manual @domain_stores @omniclient @story_SDE-397 @website @mcom
  Scenario: Open New Account - APPROVES - New affiliation is NOT created - PROP CARD.
  POS flag is OFF & OCL New Account Opt-In flag is OFF
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I open a new account for client in OCL DB no relation to logged on associate
    And the New Account has been approved
    When I launch the macy's omniclient page
    Then I sign into Omniclient application as associate
    And I search on client with new account POS
    And I should not see results for new account client created at POS

  @manual @domain_stores @omniclient @story_SDE-397 @website @mcom
  Scenario: Open New Account in Ringer Mode - APPROVES - New client is NOT created in OCL - PROP CARD.
  POS flag is OFF, OCL New Account Opt-In flag is OFF and RINGER MODE is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    Then I turn Ringer Mode ON
    And I open a new account for a client not in OCL DB
    And the New Account has been approved
    When I launch the macy's omniclient page
    Then I sign into Omniclient application as Selling Associate not Ringing Associate
    And I search on client with new account POS
    And I should not see results for new account client created at POS

  @manual @domain_stores @omniclient @story_SDE-397 @website @mcom
  Scenario: Verify opening a New Account - DECLINES
  POS flag is OFF, OCL New Account Opt-In flag is OFF
    Given I launch the POS application
    When I sign into the POS application as associate
    And I open a new account for a client not in OCL DB
    And the New Account has been declined
    When I launch the macy's omniclient page
    Then I sign into Omniclient application as associate
    And I search on client declined at POS
    Then I should not see results for declined client at POS

  @manual @domain_stores @omniclient @story_SDE-397 @website @mcom
  Scenario: Verify opening a New Account - PENDING
  POS flag is ON, OCL New Account Opt-In flag is ON
    Given I launch the POS application
    When I sign into the POS application as associate
    And I open a new account for a client not in OCL DB
    And the New Account is pending
    When I launch the macy's omniclient page
    Then I sign into Omniclient application as associate
    And I search on client whose account is pending
    Then I should not see results for pending client