# Author: STAT Team
# Story: SPR-158 - GVB - UI -Create Request - Vendor Bonus Program Page
# Date Created: 10/16/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-158
Feature: As a Gift Registry Advisor I want to navigate to the Vendor Bonus Program page with a 'Create Request'
  link when I select Vendor Bonus Program from the main menu so I can initiate a bonus request


  Scenario: QE to verify selecting the Vendor Bonus Program link navigates to the Vendor Bonus page
  and sees Create Request link
    Given I am in the Gift website
    When I log in as an Advisor
    Then I see the hamburger/main menu
    When I click the hamburger/main menu
    Then I see the Vendor Bonus Program link
    When I click the Vendor Bonus Program link
    Then I am on the Vendor Bonus Program page
    And I see the hamburger/main menu
    And I see the Search icon
    And I see the Application Title
    And I see the Welcome Info
    And I see Create Icon
    And I see the title Vendor Bonus Program
    Then I see the Create Request Link




