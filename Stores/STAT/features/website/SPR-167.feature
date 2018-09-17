# Author: STAT Team
# Story: SPR-167 - GVB - UI - Registrant Info on reguest page
# Date Created: 10/25/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-167
Feature: As a Gift Registry Advisor I want to see the registrant's information on the 'Vendor Bonus Request' page in a
  new section under the registry field so I can verbally validate the shipping information before I submit a
  Vendor Bonus Request

  Background: The associate navigates to the Vendor Bonus Request page
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page


  Scenario: QE to verify when logged in as an Advisor that we are able to see the registrants information on the Vendor
    Bonus Request page
    When I input a registry number 2357102
    And  I click search
    Then I should see an accordion view opened of the registrants information

  Scenario: QE to verify the accordion closes
    When I input a registry number 2357102
    And  I click search
    Then I should see an accordion view opened of the registrants information
    When I click on the accordion
    Then I should see the accordion view close

  Scenario: QE to verify associate information text boxes are not editable
    When I input a registry number 2357102
    And  I click search
    Then I should see an accordion view opened of the registrants information
    And I should see the fields are not editable










