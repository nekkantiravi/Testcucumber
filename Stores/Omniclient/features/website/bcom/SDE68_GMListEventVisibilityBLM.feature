# Author: Ovidiu Rucoi
# Story: SDE-68 - OmniClient :: General Manager List/Event Visibility
# Date Created: 05/18/2017
# Date Signed Off:

Feature: As a General Manager, I want to see all Lists and Events created for the Selling Associates who I oversee.

  @domain_stores @omniclient @story_SDE-68 @website @bcom
  Scenario: General Manager sees the to dos created by himself
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | PROFILE      |
    And I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new TO DO "PROFILE01" created by the General Manager on BLM
    When I navigate to Bloomingdales Homepage
    Then I should see the TO DO "PROFILE01" on the BLM dashboard

  @manual @domain_stores @omniclient @story_SDE-68 @website @bcom
  Scenario: General Manager sees the to events created by a Corporate Admin
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as corporate admin
    And I create an event from CREATE EVENT page
    And I click on the Switch User button
    And I enter "NINETY" credentials
    And I select the "NINETY ONE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "general manager" from BLM
    When I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new EVENT created by the Corporate Admin on BLM
    When I navigate to Bloomingdales Homepage
    Then I should see the Events on the dashboard



  @domain_stores @omniclient @story_SDE-68 @website @bcom
  Scenario: General Manager sees the to dos created by a Corporate Store Executive
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as Corporate Store Executive
    And I create a TO DO from CREATE LIST page
      | PROFILE      |
    And I log out from the application
    When I sign into Omniclient BLM application as General Manager
    When I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new TO DO "PROFILE01" created by the Corporate Store Executive on BLM
    When I navigate to Bloomingdales Homepage
    Then I should see the TO DO "PROFILE01" on the BLM dashboard