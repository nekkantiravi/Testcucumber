# Author: STAT Team
# Story: SPR-103 -Create and Trigger - Database Table(loyalty_delta) - NEW
# Date Created: 11/02/2017
# Date Signed Off:

@STAT @pending_returns @SPR-103 @manual
Feature:As an application I want to create a LOYALTY_DELTA table and create a trigger to capture the current
  data from LOYALTY prior to the update so I can have a historical view of this data

  Scenario: QE to verify LOYALTY_DELTA Table is created
    Given I am in SQL Developer
    When I am in PS_DEV
    And I select Tables
    Then I can see the LOYALTY_DELTA Table

  Scenario: QE to verify LOYALTY_SEQ Sequence is created
    Given I am in SQL Developer
    When I am in PS_DEV
    And I select Sequences
    Then I can see the LOYALTY_SEQ Table

  Scenario: QE to verify LOYALTY_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PS_DEV
    And I select Triggers
    Then I can see the LOYALTY_AFTER_UPD


