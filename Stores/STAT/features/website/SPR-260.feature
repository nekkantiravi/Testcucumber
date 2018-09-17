# Author: STAT Team
# Story: SPR-260 -GVB - UI - Evaluate Request Duplicate Error Message
# Date Created: 12/13/17
# Date Signed Off:

@STAT @vendor_bonus @SPR-260
Feature: As a Gift Registry Advisor I want to be notified with a message when a duplicate
  Vendor Bonus request is submitted so I can manage the bonus request process appropriately with registrants


  Scenario: QE to verify error message with duplicate vendor bonus
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 2330989
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    When I select the drop down box
    Then I should see multiple vendors in the drop down list
    When I select the vendor
#    Select same vendor from SPR-171
    Then I should see the Save button is enabled
    When I click on the save button
    Then I should see error message displays and states "A Vendor Bonus Request already exists for "vendor name"

















