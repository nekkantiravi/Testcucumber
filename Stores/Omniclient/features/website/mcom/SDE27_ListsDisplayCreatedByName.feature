# Author: Ovidiu Rucoi
# Story: SDE-27 - OmniClient :: Display 'Created By' Name
# Date Created: 05/18/2017
# Date Signed Off:

@manual @SKIPPED
Feature: As an associate, I want to see the name of the person who created the Lists so that I can understand
  who is assigning me/my direct reports work.

  @domain_stores @omniclient @story_SDE-27 @website @mcom
  Scenario: Associate sees the name of the Selling Manager who created the List
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I create a TO DO from CREATE TO DOS page
      | PROFILE      |
    And I click on the Switch User button
    And I enter "FIFTY" credentials
    And I select the "FIFTY ONE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the Created By name:
      | FIFTY SEVEN |

  @domain_stores @omniclient @story_SDE-27 @website @mcom
  Scenario: Associate sees the name of the general manager who created the List
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | TARGET GM |
    And I click on the Switch User button
    And I enter "FIFTY" credentials
    And I select the "FIFTY ONE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the Created By name:
      | FIFTY NINE |

  @domain_stores @omniclient @story_SDE-27 @website @mcom
  Scenario: Associate sees the name of the corporate store executive who created the List
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate store executive
    And I create a TO DO from CREATE TO DOS page
      | TARGET CORP STORE EXEC   |
    And I click on the Switch User button
    And I enter "FIFTY" credentials
    And I select the "FIFTY ONE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the Created By name:
      | ONE HUNDRED-SEVENTEEN |

  @manual @domain_stores @omniclient @story_SDE-27 @website @mcom
  Scenario: Associate sees the name of the corporate admin who created the List/Event
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate admin
    And I create an event from CREATE EVENT page
    And I click on the Switch User button
    And I enter "FIFTY" credentials
    And I select the "FIFTY ONE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    #Verify the created TO DO
    And I should see the Created By name:
      | Selling Effectiveness|
    #Verify the created EVENT
    And I should see the Created By name:
      | Selling Effectiveness |