# Author: Ovidiu Rucoi
# Story: SDE-154 - OmniClient :: General Manager List/Event Details Drill Down
# Date Created: 05/18/2017
# Date Signed Off:

@manual @SKIPPED
Feature: As a General Manager, I want to be able to drill into Lists I created

  @domain_stores @omniclient @story_SDE-154 @website @mcom
  Scenario: General Manager drills down into the PROFILE list's details
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | PROFILE      |
    Then I should see the list of TO DOS
    And I should see the new TO DO "PROFILE01" created by the General Manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Macys associates who received the To Do

  @domain_stores @omniclient @story_SDE-154 @website @mcom
  Scenario: General Manager drills down into the TARGET list's details
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | TARGET GM       |
    Then I should see the list of TO DOS
    And I should see the new TO DO "TARGET TODO2 GM" created by the General Manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Macys associates who received the To Do

  @domain_stores @omniclient @story_SDE-154 @website @mcom
  Scenario: General Manager drills down into the TRANSACTIONS list's details
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | TRANSACTIONS |
    Then I should see the list of TO DOS
    And I should see the new TO DO "TRANS01" created by the General Manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Macys associates who received the To Do

  Scenario: Delete Created ToDos
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Admin User
    And change user into "10000059" from Admin interface
    And I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    When I delete all created ToDos
    Then I should not see any ToDos that can be deleted