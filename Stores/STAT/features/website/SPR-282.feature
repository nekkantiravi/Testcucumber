# Author: STAT Team
# Story: SPR-282 - GVB - UI - Bonus Request Table - Search
# Date Created: 12/14/2017
# Date Signed Off:

@STAT @pending_returns @SPR-282
Feature: As a Gift Registry Advisor I want to be able to Search the Bonus Request Table on the Vendor Bonus Program page
  so I can quickly and efficiently locate bonus requests by Submit Date, Request (number), Registry (number),
  Registrant name, Vendor, Pattern, Item, Program, Associate (number or name), Store (number), or Status.

  Background:
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    Then I should see "Vendor Bonus Program" as title
    And I should see "Bonus Request" Table
    And I should see Bonus Request Table search field

  Scenario Outline: Search table using "<column>" random value from avilable table data
    When I search the "Bonus Request" Table with value from "<column>"
    Then I should see the "Bonus Request" Table displays rows contain search value

    Examples:
    |column|
    |SUBMIT DATE|
    |REQUEST    |
    |REGISTRY   |
    |REGISTRANTS|
    |OCCASION DATE|
    |VENDOR       |
    |ASSOCIATE    |
    |STORE        |
    |STATUS       |
