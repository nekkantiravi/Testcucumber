# Author: Ovidiu Rucoi
# Story: SDE-303
# Date Created:
# Date Signed Off:

Feature: For EVERY keyword that is searched and brings a matching result back, the "match" should be returned in the
  Result display, as a link that allows the user to click, and be taken to the location of the match.

   Scenario: Create Client for test
     Given I launch the macy's omniclient page
     When I sign into Omniclient application as associate
     And I add a new Macys Client: "FRSTNM" "LSTNM" "ADDRESSLINE1" "TESTCITY" "12345" "TESTHINT" "2345678901" "AL"

  @domain_stores @omniclient @story_SDE-303 @website @mcom
  Scenario: Validate that all search keywords from the Client's info return a highlighted link
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Book radio button
#    FIRST NAME search
    And I type the name of a customer "FRSTNM" in the search box
    And I click on the omniclient search button
    Then I should see the "FRSTNM" link in the My Book results list
#    LAST NAME search
    When I type the name of a customer "LSTNM" in the search box
    And I click on the omniclient search button
    Then I should see the "LSTNM" link in the My Book results list
#    ADDRESS LINE 1 search
    When I type the name of a customer "ADDRESSLINE1" in the search box
    And I click on the omniclient search button
    Then I should see the "ADDRESSLINE1" link in the My Book results list
#    ADDRESS LINE 2 search
    When I type the name of a customer "ADDRESSLINE2" in the search box
    And I click on the omniclient search button
    Then I should see the "ADDRESSLINE2" link in the My Book results list
#    CITY search
    When I type the name of a customer "TESTCITY" in the search box
    And I click on the omniclient search button
    Then I should see the "TESTCITY" link in the My Book results list
#    ZIP search
    When I type the name of a customer "12345" in the search box
    And I click on the omniclient search button
    Then I should see the "12345" link in the My Book results list
#   PHONE search
    When I type the name of a customer "2345678901" in the search box
    And I click on the omniclient search button
    Then I should see the "(234) 567-8901" link in the My Book results list
#   STATE search
    When I type the name of a customer "AL" in the search box
    And I click on the omniclient search button
    Then I should see the "AL" link in the My Book results list
#   HINT search
    When I type the name of a customer "TESTHINT" in the search box
    And I click on the omniclient search button
    Then I should see the "TESTHINT" link in the My Book results list
#   NOTE search
    When I type the name of a customer "TESTNOTE" in the search box
    And I click on the omniclient search button
    Then I should see the "TESTNOTE" link in the My Book results list


