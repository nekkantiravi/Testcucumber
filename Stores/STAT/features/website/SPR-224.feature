# Author: STAT Team
# Story: SPR-224 -GVB - UI - Participating vendors - Error message
# Date Created: 12/14/17
# Date Signed Off:

@STAT @vendor_bonus @SPR-224
Feature: As a Gift Registry Advisor I want to be notified if there are no participating vendors
  for a registry for a bonus program so I can notify the registrant and take appropriate action


  Scenario: QE to verify error when a registry has no participating vendors
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
    Then I should see the error message stating "There are no qualifying vendors for a bonus request"


















