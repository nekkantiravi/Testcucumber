# Author: STAT Team
# Story: SPR-110 - Create and Trigger - Database Table (store_totals_delta) - NEW
# Date Created: 11/10/2017
# Date Signed Off:

@STAT @pending_returns @SPR-110 @manual
Feature: As an application I want to create a STORE_TOTALS_DELTA table and create a trigger to capture the current
  data from STORE_TOTALS prior to the update so I can have a historical view of this data

  Scenario: QE to verify that STORE_TOTALS_DELTA is created
    Given I am in SQL Developer
    When I am in the PS_DEV
    Then I can see the STORE_TOTALS_DELTA Table

  Scenario: QE to verify STORE_TOTALS_SEQ sequence is created for STORE_TOTALS_DELTA table
    Given I am in SQL Developer
    When I am in the PS_DEV
    Then I can see the STORE_TOTALS_SEQ sequence for STORE_TOTALS_DELTA table

  Scenario: QE to verify that STORE_TOTALS_AFTER_UPD trigger exists for
    Given I am in SQL Developer
    When I am in the PS_DEV
    Then I can see the STORE_TOTALS_AFTER_UPD trigger for STORE_TOTALS_DELTA Table

  Scenario: QE to verify that STORE_TOTALS_DELTA.sql SQL script exists under Deployment\Database\Tables
    Given I am in Git user
    When I am in the DEV git
    Then I can see the STORE_TOTALS_DELTA.sql file for STORE_TOTALS_DELTA Table
