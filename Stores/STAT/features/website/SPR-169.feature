# Author: STAT Team
# Story: SPR-169 - GVB - UI - Vendor accordion
# Date Created: 11/6/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-169
Feature: As a Gift Registry Advisor I want to see the 'Bonus Program' accordion open under
  the Registrant accordion on the 'Vendor Bonus Request' page so I can select a participating vendor and
  initiate a Vendor Bonus Request

  Background: The associate navigates to the Vendor Bonus Request page
    Given I am on Gift
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page



  Scenario: QE to verify the Bonus Program accordion opens automatically when a valid registry is entered
    When I input a registry number 2357102
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    Then I should see the Save button is disabled

  Scenario: QE to verify the accordion closes
    When I input a registry number 2357102
    And  I click search
    Then I should see the Bonus Program accordion is open
    When I click on the accordion
    Then I should see the accordion view close












