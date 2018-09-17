# Author: STAT Team
# Story: SPR-171 - GVB - UI - Successful submit message
# Date Created: 12/13/17
# Date Signed Off:

@STAT @vendor_bonus @SPR-171
Feature: As a Gift Registry Advisor I want to be notified when a Vendor Bonus Program request has been
  successfully submitted so I can communicate that information to the registrant(s)


  Scenario: QE to verify field successful message
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 2332532
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    When I select the drop down box
    Then I should see multiple vendors in the drop down list
    When I select the vendor
    Then I should see the Save button is enabled
    When I click on the save button
    Then I should see the popup display
    And I should see the popup states "Vendor Bonus Request is successfully created"
    And I should see the popup displays registy number
    And I should see the popup displays Vendor name
    And I should see the popup OK button enabled


  Scenario: QE to verify field successful message
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I see the Create Request Link
    When I click on the Create Request Link
    Then I am on the Vendor Bonus Request Page
    When I input a registry number 2332532
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see the Bonus Program accordion is open
    When I select the drop down box
    Then I should see multiple vendors in the drop down list
    When I select the vendor
    Then I should see the Save button is enabled
    When I click on the save button
    Then I should see the popup display
    And I should see the popup states "Vendor Bonus Request is successfully created"
    And I should see the popup displays registy number
    And I should see the popup displays Vendor name
    And I should see the popup OK button enabled















