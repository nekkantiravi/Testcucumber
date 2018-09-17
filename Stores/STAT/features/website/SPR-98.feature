# Author: STAT Team
# Story: SPR-98 -Create and Trigger - Database Table(counted_cash_delta) - NEW
# Date Created: 10/23/2017
# Date Signed Off:

@STAT @pending_returns @SPR-98 @manual
Feature:As an application I want to create a COUNTED_CASH_DELTA table and create a trigger
  to capture the current data from COUNTED_CASH prior to the update so I can have a historical view of this data


  Scenario: QE to verify COUNTED_CASH_DELTA Table is created is added to the PENDING_ITEMS table
    Given I am in SQL Developer
    When I am in the PENDING_ITEMS Table
    Then I can see the QTY_RETURNED column




