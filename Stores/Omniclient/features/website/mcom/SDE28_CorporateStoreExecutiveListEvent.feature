# Author: Claudiu Chirila
# Story: SDE-28 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

@manual @SKIPPED
Feature: As a Corporate Store Executive (no book), I want to see the same Lists and Events as the General Manager so that I can monitor outreach status and avoid creating duplicate Lists.

  @domain_stores @omniclient @story_SDE-28 @website @mcom
  Scenario: Corporate Admin is able to view TODOs that he created
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate admin
    And I create a TO DO from CREATE TO DOS page
      | PROFILE      |
    Then I should see the list of TO DOS
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "PROFILE01" created by the Corporate Admin
    When I click to expand the chevron
    Then List description will be displayed


  @domain_stores @omniclient @story_SDE-28 @website @mcom
  Scenario: Corporate Store Executive is able to view Lists created by a General Manager
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | TARGET GM |
    Then I log out from the mcom aplication
    When I sign into Omniclient application as corporate store executive
    And I navigate to ALL TO DOS page
#    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS for corp store exec
      #this method will include validations for: Created By' Associate, Title, Number of Uncalled Customers, and 'Due By' date
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "TARGET TODO2 GM" created by the General Manager
    When I click to expand the chevron
    Then List description will be displayed

  @manual @domain_stores @omniclient @story_SDE-28 @website @mcom
  Scenario: Corporate Store Executive is able to view Events created by a Corporate Admin
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate admin
    And I create an event from CREATE EVENT page
    And I click on the Switch User button
    And I enter "ONE HUNDRED" credentials
    And I select the "ONE HUNDRED-EIGHTEEN" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "corp store exec"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the list information
  #this method will include validations for: Created By' Associate, Title, Number of Uncalled Customers, and 'Due By' date
    And I should see the new EVENT created by the Corporate Admin
    When I click to expand the chevron
    Then Event description will be displayed

