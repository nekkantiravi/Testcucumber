# Author:
# Story: Test
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to Create Self To Dos and Client To Dos from various locations in the application.

  @manual @RegMcom @domain_stores @omniclient @website
  Scenario: Creating a SELF TO DO on the MY TO DOS tab as an associate
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Clients from top navigation bar
    And I check the My To DOs count
    And I navigate to ALL TO DOS page
    Then I should see the MY TO DOS page
    When I click on the Create To Do button on MY TO DOS page
    Then I should see the input boxes to create a to do
      | TITLE       |
      | DATE        |
      | CLIENT      |
      | DESCRIPTION |
    And I enter a title "REGRESSION TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    Then the To Do "REGRESSION TO DO" is saved on the Associates MY TO DOS tab
    When I navigate to Macys Homepage
    Then I will validate that the My ToDos count incremented by 1