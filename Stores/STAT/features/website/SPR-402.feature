# Author: Stat Team
# Story: SPR-402 - GVB - UI - Format Submit Date
# Date Created: 12/05/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-402
Feature: As a Gift Registry Advisor I want to see the Submit Date for a Vendor Bonus request
  in a format consistent to the GFT application (MM-DD-YYYY) so I can know when a request was submitted

  Scenario: QE to verify Submit date is in MM-DD-YYYY format
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    Then I should see "Vendor Bonus Program" as title
    And I should see "Bonus Request" Table
    And I should see "Bonus Request" Table Header is "SUBMIT DATE,REQUEST,REGISTRY,REGISTRANTS,OCCASION DATE,VENDOR,ASSOCIATE,STORE,STATUS, "
    And I should see the submit date data is in MM-DD-YYYY format


