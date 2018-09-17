# Author: STAT Team
# Story: SPR-195 - GVB - UI - Vendor accordion select
# Date Created: 12/1/17
# Date Signed Off:

@STAT @vendor_bonus @SPR-195
Feature: As a Gift Registry Advisor I want to be able to open the 'Bonus Program' accordion on the
  'Vendor Bonus Request' page so I can select a participating vendor and initiate a Vendor Bonus Request


  Scenario: QE to verify field displays 'Please make a selection' and no vendor in the drop down (preferences not set, no purchase)
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 2370033
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    Then I should see the drop down box states 'Please make a selection'
    When I select the drop down box
    Then I should see 'Please make a selection' displays with no vendors
    Then I should see the Save button is disabled


  Scenario: QE to verify field displays 'Please make a selection' and no vendor in the drop down (preferences set, no purchase)
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 2357102
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    Then I should see the drop down box states 'Please make a selection'
    When I select the drop down box
    Then I should see 'Please make a selection' displays with no vendors
    Then I should see the Save button is disabled

  Scenario: QE to verify multiple participating vendor in the drop down (manual purchase, preferences set)
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 2370031
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    When I select the drop down box
    Then I should see multiple vendors in the drop down list
    When I select the vendor
    Then I should see the Save button is enabled

  Scenario:QE to verify multiple participating vendor in the drop down (preference set, purchase and manual puchases)
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
    Then I should see the Save button is enabled

  Scenario:QE to verify one participating vendor in the drop down (preference set, FIL sale)
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 1102141
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    When I select the drop down box
    Then I should see one vendors in the drop down list
    When I select the vendor
    Then I should see the Save button is enabled














