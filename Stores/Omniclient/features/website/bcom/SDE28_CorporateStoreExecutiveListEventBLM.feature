# Author: Claudiu Chirila
# Story: SDE-28 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As a Corporate Store Executive (no book), I want to see the same Lists and Events as the General Manager so that I can monitor outreach status and avoid creating duplicate Lists.


  @domain_stores @omniclient @story_SDE-28 @website @mcom

  Scenario: Corporate Admin is able to view LISTS that he created
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as corporate admin
    And I create a TO DO from CREATE LIST page
      | PROFILE |
    Then I should see the list of TO DOS on BLM
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "PROFILE01" created by the Corporate Admin on BLM
    When I click to expand the chevron
    Then List description will be displayed


  @domain_stores @omniclient @story_SDE-28 @website @mcom
  Scenario: Corporate Store Executive is able to view Lists created by a General Manager
    Given I launch the bloomingdales's omniclient page
    And I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | TARGET GM |
    Then I log out from the application
    When I sign into Omniclient BLM application as Corporate Store Executive
    And I navigate to MY TASKS page
    Then I should see the list of TO DOS
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "TITLE-BLM2" created by the General Manager on BLM
    When I click to expand the chevron
    Then List description will be displayed


    #  @manual @domain_stores @omniclient @story_SDE-28 @website @mcom
#  Scenario: Corporate Store Executive is able to view Events created by a Corporate Admin
#    Given I launch the bloomingdales's omniclient page
#    When I sign into Omniclient BLM application as corporate admin
#    And I create an event from CREATE EVENT page from BLM
#    And I click on the Switch User button
#    And I enter "ONE HUNDRED" credentials
#    And I select the "ONE HUNDRED-FIFTEEN" credentials from the dropdown
#    And click the Switch button
#    Then I should be switched into the selected "corp store exec" from BLM
#    When I navigate to MY TASKS page
#    And I click on LISTS tab from BLM
#    Then I should see the list of TO DOS on BLM
#      #this method will include validations for: Created By' Associate, Title, Number of Uncalled Customers, and 'Due By' date
#    And I should see the list information
#      | Created by       |
#      | List Title       |
#      | Uncalled clients |
#      | Due by           |
#    And I should see the new EVENT created by the Corporate Admin on BLM
#    When I click to expand the chevron
#    Then Event description will be displayed