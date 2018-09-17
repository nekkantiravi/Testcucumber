# Author: Stat Team
# Story: SPR-269 - GVB - UI - Bonus Request Table
# Date Created: 12/05/2017
# Date Signed Off:

@STAT @pending_returns @SPR-269
Feature: As a Gift Registry Advisor I want to the status and relevant information for Vendor Bonus requests in a table
  on the Vendor Bonus Program page so I can see an overview of requests

  Scenario: Verify table header columns are correct
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    Then I should see "Vendor Bonus Program" as title
    And I should see "Bonus Request" Table
    And I should see "Bonus Request" Table Header is "SUBMIT DATE,REQUEST,REGISTRY,REGISTRANTS,OCCASION DATE,VENDOR,ASSOCIATE,STORE,STATUS, "
    And I should see "Bonus Request" Table information displayed is uppercase


   Scenario Outline: Verify that table data is ordered by <Order_By>
     Given I am on Gift
     And I log in as an Advisor
     When I click the hamburger/main menu
     And I click the Vendor Bonus Program link
     Then I should see "Vendor Bonus Program" as title
     When I click "<Order_By>" in the "Bonus Request" Table Header
     Then I should see "Bonus Request" Table data is ordered "ascending" for "<Order_By>"
     When I click "<Order_By>" in the "Bonus Request" Table Header
     Then I should see "Bonus Request" Table data is ordered "descending" for "<Order_By>"

     Examples:
     | Order_By |
     | SUBMIT DATE |
     | REQUEST |
     | REGISTRY |
     | REGISTRANTS |
     | OCCASION DATE |
     | VENDOR |
     | ASSOCIATE |
     | STORE     |
     | STATUS    |
