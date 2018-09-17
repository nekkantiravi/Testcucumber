# Author: Stat Team
# Story: SPR-269 - GVB - UI - Bonus Request Table - View functionality
# Date Created: 12/13/2017
# Date Signed Off:

@STAT @pending_returns @SPR-287
Feature: As a Gift Registry Advisor I want to be able to select 'View' on a row in the Bonus Request Table so I can see
  a larger, easier to read view of the bonus request information (Submit Date, Request (number), Registry (number),
  Registrant names, Occasion Date, Vendor, Pattern (if applicable), Item (if applicable), Program,
  Associate (number and name), Store (number), and Status of the request)


  Scenario: Clicking on the "View" linked text opens Vendor Bonus Request details
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    Then I should see "Vendor Bonus Program" as title
    And I should see "Bonus Request" Table
    And I should see "View" linked text in the "Bonus Request" Table
    When I click the "View" linked text in the "Bonus Request" Table
    Then I should see Vendor Bonus Request details
    And I should see Vendor Bonus Request details information is not editable
    When I click X in Vendor Bonus Request details
    Then I should not see Vendor Bonus Request details

