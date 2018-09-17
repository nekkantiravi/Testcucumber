# Author: STAT Team
# Story: SPR-107 -Create and Trigger - Database Table (redeem_dtl_delta) - NEW
# Date Created: 11/07/2017
# Date Signed Off:

@STAT @pending_returns @SPR-107 @manual
Feature:As an application I want to create a REDEEM_DTL_DELTA table and create a trigger to
  capture the current data from REDEEM_DTL prior to the update so I can have a historical view of this data

  Scenario: QE to verify REDEEM_DTL_DELTA table is created
    Given I am a SQL Developer
    When I am in PT_DEV
    Then I can see the REDEEM_DTL_DELTA Table

  Scenario: QE to verify trigger REDEEM_DTL_AFTER_UPD  is created
    Given I am a SQL developer
    When I am in PT_DEV
    And I select Triggers
    Then I should see REDEEM_DTL_AFTER_UPD  
