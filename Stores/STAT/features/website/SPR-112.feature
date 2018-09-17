# Author: STAT Team
# Story: SPR-112-PR - Create and Trigger - Database Table (tax_dtl_delta) - NEW
# Date Created: 11/10/2017
# Date Signed Off:

@STAT @pending_returns @SPR-111 @manual
Feature:As an application I want to create a TRANS_DTL_DELTA table and create a trigger to capture the
  current data from TRANS_DTL prior to the update so I can have a historical view of this data


  Scenario: QE to verify TRANS_DTL_DELTA Table is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Tables
    Then I should see the TRANS_DTL_DELTA table is created

  Scenario: QE to verify TRANS_DTL_SEQ Sequences is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Sequences
    Then I should see the TRANS_DTL_SEQ

  Scenario: QE to verify TRANS_DTL_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Triggers
    Then I can see the TRANS_DTL_AFTER_UPD