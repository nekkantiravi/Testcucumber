# Author: STAT Team
# Story: SPR-100-Create and trigger - Database Table (customer_info_delta) - NEW
# Date Created: 10/25/2017
# Date Signed Off:

@STAT @pending_returns @SPR-100 @manual
Feature:As an application I want to create a CUSTOMER_INFO_DELTA table and create a trigger to capture
  the current data from CUSTOMER_INFO prior to the update so I can have a historical view of this data


  Scenario: QE to verify CUSTOMER_INFO_DELTA Table is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    Then I can see the CUSTOMER_INFO_DELTA Table




