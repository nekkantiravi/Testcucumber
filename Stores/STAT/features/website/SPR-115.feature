# Author: STAT Team
# Story: PR - Create and Trigger - Database Table (upb_dtl_delta) - NEW
# Date Created: 11/10/2017
# Date Signed Off:

@STAT @pending_returns @SPR-111 @manual
Feature:As an application I want to create a UPB_DTL_DELTA table and create a trigger to capture
  the current data from UPB_DTL prior to the update so I can have a historical view of this data


  Scenario: QE to verify UPB_DTL_DELTA  Table is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Tables
    Then I should see the UPB_DTL_DELTA  table is created

  Scenario: QE to verify UPB_DTL_SEQ Sequences is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Sequences
    Then I should see the UPB_DTL_SEQ

  Scenario: QE to verify UPB_DTL_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Triggers
    Then I can see the UPB_DTL_AFTER_UPD