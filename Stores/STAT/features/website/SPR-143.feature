# Author: STAT Team
# Story: SPR-143 - GVB - UI - Create Request - Vendor Program link
# Date Created: 10/13/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-143
Feature: As a Gift Registry Advisor I want to be able to select 'Vendor Bonus Program' link from the
  hamburger/main menu so I can navigate to the Vendor Bonus Program page


  Scenario: QE to verify the Vendor Bonus Program link displays in the hamburger/main menu logged in as an Advisor
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link


  Scenario: QE to verify the Vendor Bonus Program link displays in the hamburger/main menu logged in as an Support
    Given I am in the Gift website
    When I log in as a Support
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link

  Scenario: QE to verify the Vendor Bonus Program link displays in the hamburger/main menu logged in as an Admin
    Given I am in the Gift website
    When I log in as an Admin
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link

  Scenario: QE to verify the Vendor Bonus Program link does not displays in the hamburger/main menu logged in as an Director
    Given I am in the Gift website
    When I log in as a Director
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I do not see the Vendor Bonus Program link

  Scenario: QE to verify the Vendor Bonus Program link does not displays in the hamburger/main menu logged in as an General
    Given I am in the Gift website
    When I log in as a General
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I do not see the Vendor Bonus Program link
