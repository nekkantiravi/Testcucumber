# Author: STAT Team
# Story: SPR-105 -Create and Trigger - Database Table(reward_cards_delta) - NEW
# Date Created: 11/02/2017
# Date Signed Off:

@STAT @pending_returns @SPR-105 @manual
Feature:As an application I want to create a REWARD_CARDS_DELTA table and create a trigger to capture the
  current data from REWARD_CARDS prior to the update so I can have a historical view of this data

  Scenario: QE to verify REWARD_CARDS_DELTA Table is created
    Given I am in SQL Developer
    When I am in PS_DEV
    And I select Tables
    Then I can see the REWARD_CARDS_DELTA Table

  Scenario: QE to verify REWARD_CARDS_SEQ Sequence is created
    Given I am in SQL Developer
    When I am in PS_DEV
    And I select Sequences
    Then I can see the REWARD_CARDS_SEQ Table

  Scenario: QE to verify REWARD_CARDS_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PS_DEV
    And I select Triggers
    Then I can see the REWARD_CARDS_AFTER_UPD


