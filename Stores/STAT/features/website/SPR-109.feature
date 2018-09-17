# Author: STAT Team
# Story: SPR-109 - Create and Trigger - Database Table (register_totals_delta) - NEW
# Date Created: 11/10/2017
# Date Signed Off:

@STAT @pending_returns @SPR-109 @manual
Feature: As an application I want to create a REGISTER_TOTALS_DELTA table and create a trigger to capture the current
  data from REGISTER_TOTALS prior to the update so I can have a historical view of this data

Scenario: QE to verify that REGISTER_TOTALS_DELTA is created
  Given I am in SQL Developer
  When I am in the PS_DEV
  Then I can see the REGISTER_TOTALS_DELTA Table


Scenario: QE to verify REGISTER_TOTALS_SEQ sequence is created for REGISTER_TOTALS_DELTA table
  Given I am in SQL Developer
  When I am in the PS_DEV
  Then I can see the  for REGISTER_TOTALS_SEQ sequence

Scenario: QE to verify that SQL script exists under Deployment\Database\Tables
  Given I am in Git user
  When I am in the DEV git
  Then I can see the REGISTER_TOTALS_DELTA.sql file for REGISTER_TOTALS_DELTA Table