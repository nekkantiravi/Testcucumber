# Author: Ovidiu Rucoi
# Story: SDE-27 - OmniClient :: Display 'Created By' Name
# Date Created: 05/18/2017
# Date Signed Off:

Feature: As an associate, I want to see the name of the person who created the Lists so that I can understand
  who is assigning me/my direct reports work.

  @domain_stores @omniclient @story_SDE-27 @website @bcom
  Scenario: Associate sees the name of the Selling Manager who created the List
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as Selling Manager
    And I create a TO DO from CREATE LIST page
      | PROFILE      |
    And I click on the Switch User button
    And I enter "EIGHTY THR" credentials
    And I select the "EIGHTY THREE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate" from BLM
    When I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the Created By name on BLM:
      | EIGHTY NINE |

  @domain_stores @omniclient @story_SDE-27 @website @bcom
  Scenario: Associate sees the name of the general manager who created the List
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | TARGET GM      |
    And I click on the Switch User button
    And I enter "EIGHTY THR" credentials
    And I select the "EIGHTY THREE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate" from BLM
    When I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the Created By name on BLM:
      | NINETY ONE |

  @domain_stores @omniclient @story_SDE-27 @website @bcom
  Scenario: Associate sees the name of the corporate store executive who created the List
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as Corporate Store Executive
    And I create a TO DO from CREATE LIST page
    | TARGET CORP STORE EXEC |
    And I click on the Switch User button
    And I enter "EIGHTY THR" credentials
    And I select the "EIGHTY THREE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate" from BLM
    When I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the Created By name:
      | ONE HUNDRED-FIFTEEN |

#  @manual @domain_stores @omniclient @story_SDE-27 @website @bcom
#  Scenario: Associate sees the name of the corporate admin who created the List/Event
#    Given I launch the bloomingdales's omniclient page
#    When I sign into Omniclient BLM application as corporate admin
#    And I create a TO DO from CREATE LIST page
#      | PROFILE      |
#    And I create an event from CREATE EVENT page from BLM
#    And I click on the Switch User button
#    And I enter "EIGHTY" credentials
#    And I select the "EIGHTY THREE" credentials from the dropdown
#    And click the Switch button
#    Then I should be switched into the selected "associate" from BLM
#    When I navigate to MY TASKS page
#    And I click on LISTS tab from BLM
#    Then I should see the list of TO DOS on BLM
#    #Verify the created TO DO
#    And I should see the Created By name:
#      | Selling Effectiveness |
#    #Verify the created EVENT
#    And I should see the Created By name:
#      | Selling Effectiveness |