# Author: STAT Team
# Story: SPR-102-Create and Trigger - Database Table (fee_dtl_delta) - NEW
# Date Created: 10/25/2017
# Date Signed Off:

@STAT @pending_returns @SPR-102 @manual
Feature:As an application I want to create a FEE_DTL__DELTA table and create a trigger to capture
  the current data from FEE_DTL prior to the update so I can have a historical view of this data


  Scenario: QE to verify END_OF_DAY_DELTA Table is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    Then I can see the FEE_DTL__DELTA Table




