# Author: Ovidiu Rucoi
# Story: SDE-68 - OmniClient :: General Manager List/Event Visibility
# Date Created: 05/18/2017
# Date Signed Off:

@manual @SKIPPED
Feature: As a General Manager, I want to see all Lists and Events created for the Selling Associates who I oversee.

  @domain_stores @omniclient @story_SDE-68 @website @mcom
  Scenario: General Manager sees the to dos created by himself
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | PROFILE      |
    And I navigate to ALL TO DOS page
    And I click on MY TO DOS tab
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the new TO DO "PROFILE01" created by the General Manager
    When I navigate to Macys Homepage
    Then I should see the TO DOs on the dashboard

  @domain_stores @omniclient @story_SDE-68 @website @mcom
  Scenario: General Manager sees the to dos created by a Corporate Store Executive
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate store executive
    And I create a TO DO from CREATE TO DOS page
      | PROFILE      |
    And I log out from the mcom aplication
    When I sign into Omniclient application as general manager
    And I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the new TO DO "PROFILE01" created by the Corporate Store Executive
    When I navigate to Macys Homepage
    Then I should see the TO DOs on the dashboard

  @manual @domain_stores @omniclient @story_SDE-68 @website @mcom
  Scenario: General Manager sees the to events created by a Corporate Admin
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate admin
    And I create an event from CREATE EVENT page
    And I click on the Switch User button
    And I enter "FIFTY" credentials
    And I select the "FIFTY NINE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "general manager"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the new EVENT created by the Corporate Admin
    When I navigate to Macys Homepage
    Then I should see the Events on the dashboard



#    Scenario: Delete Created ToDos
#      Given I launch the macy's omniclient page
#      When I sign into Omniclient application as Admin User
#      And change user into "10000059" from Admin interface
#      And I navigate to ALL TO DOS page
#      And I click on MY MACYS TO DOS tab
#      Then I should see the list of TO DOS
#      When I delete all created ToDos
#      Then I should not see any ToDos that can be deleted