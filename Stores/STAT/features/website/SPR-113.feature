# Author: STAT Team
# Story: SPR-113 - PR - Create and Trigger - Database Table (trans_dtl_offsets_delta) - NEW
# Date Created: 11/13/2017
# Date Signed Off:

@STAT @pending_returns @SPR-113 @manual
Feature:As an application I want to create a TRANS_DTL_OFFSETS_DELTA table and create a trigger to capture the current
  data from TRANS_DTL_OFFSETS prior to the update so I can have a historical view of this data

  Scenario:Scenario: QE to verify TRANS_DTL_OFFSETS_DELTA Table is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Table
    Then I should see the TRANS_DTL_OFFSETS_DELTA table is created

  Scenario: QE to verify TRANS_DTL_OFFSETS_SEQ Sequences is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Sequences
    Then I should see the TRANS_DTL_OFFSETS_SEQ

  Scenario: QE to verify TRANS_DTL_OFFSETS_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Triggers
    Then I can see the TRANS_DTL_OFFSETS_AFTER_UPD