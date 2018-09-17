# Author: STAT Team
# Story: SPR-99-Create and Trigger - Database Table (coupon_info_delta) - NEW
# Date Created: 10/23/2017
# Date Signed Off:

@STAT @pending_returns @SPR-99 @manual
Feature:As an application I want to create a COUPON_INFO_DELTA table and create a trigger
  to capture the current data from COUPON_INFO prior to the update so I can have a historical view of this data


  Scenario: QE to verify COUPON_INFO_DELTA Table is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Sequences
    Then I can see the COUPON_INFO_DELTA Table

  Scenario: QE to verify COUPON_INFO_AFTER_UPD Trigger is created
    Given I am in SQL Developer
    When I am in the PT_DEV
    And I select Triggers
    Then I can see the COUPON_INFO_AFTER_UPD




