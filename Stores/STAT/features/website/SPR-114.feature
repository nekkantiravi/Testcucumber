# Author: STAT Team
# Story: SPR-114 -Create and Trigger - Database Table(trans_hdr_delta) - NEW
# Date Created: 11/02/2017
# Date Signed Off:

@STAT @pending_returns @SPR-114 @manual
Feature:As an application I want to create a TRANS_HDR_DELTA table and create a trigger to capture the
  current data from TRANS_HDR prior to the update so I can have a historical view of this data


  Scenario: QE to verify TRANS_HDR_DELTA Table is created
    Given I am in SQL Developer
    When I am in PS_DEV
    And I select Tables
    Then I can see the TRANS_HDR_DELTA Table

  Scenario: QE to verify TRANS_HDR_SEQ Sequence is created
    Given I am in SQL Developer
    When I am in PS_DEV
    And I select Sequences
    Then I can see the TRANS_HDR_SEQ Table

  Scenario: QE to verify TRANS_HDR_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PS_DEV
    And I select Triggers
    Then I can see the TRANS_HDR_AFTER_UPD



      