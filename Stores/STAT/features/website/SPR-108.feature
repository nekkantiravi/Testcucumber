# Author: STAT Team
# Story: SPR-108 -Create and Trigger - Database Table (reference_dtl_delta) - NEW
# Date Created: 11/08/2017
# Date Signed Off:

@STAT @pending_returns @SPR-108 @manual

Feature:As an application I want to create a REFERENCE_DTL_DELTA table and create a trigger to capture the current data
  from REFERENCE_DTL prior to the update so I can have a historical view of this data

  Scenario: QE to verify REFERENCE_DTL_DELTA table is created
    Given I am a SQL Developer
    When I am in PT_DEV
    Then I can see the REFERENCE_DTL_DELTA Table

  Scenario: QE to verify trigger REFERENCE_DTL_AFTER_UPD is created
    Given I am a SQL developer
    When I am in PT_DEV
    And I select Triggers
    Then I should see REFERENCE_DTL_AFTER_UPD 