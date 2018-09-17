# Author: Ovidiu Rucoi
# Story: SDE-154 - OmniClient :: General Manager List/Event Details Drill Down
# Date Created: 05/18/2017
# Date Signed Off:

Feature: As a General Manager, I want to be able to drill into Lists I created

  @domain_stores @omniclient @story_SDE-154 @website @bcom
  Scenario:  General Manager drills down into the PROFILE list's details
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
    | PROFILE      |
    And I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new TO DO "PROFILE01" created by the Corporate Admin on BLM
    When I click on the newly created Bloomingdales To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Bloomingdales associates who received the To Do

  @domain_stores @omniclient @story_SDE-154 @website @bcom
  Scenario:  General Manager drills down into the TARGET list's details
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | TARGET GM     |
    And I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new TO DO "TITLE-BLM2" created by the General Manager on BLM
    When I click on the newly created Bloomingdales To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Bloomingdales associates who received the To Do

  @domain_stores @omniclient @story_SDE-154 @website @bcom
  Scenario:  General Manager drills down into the TRANSACTIONS list's details
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | TRANSACTIONS |
    And I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new TO DO "TRANSBLM01" created by the General Manager on BLM
    When I click on the newly created Bloomingdales To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Bloomingdales associates who received the To Do

  @domain_stores @omniclient @story_SDE-154 @website @bcom
  Scenario:  General Manager drills down into the LOYALLIST list's details
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | LOYALIST |
    And I navigate to MY TASKS page
    And I click on LISTS tab from BLM
    Then I should see the list of TO DOS on BLM
    And I should see the new TO DO "LOYALLIST01" created by the General Manager on BLM
    When I click on the newly created Bloomingdales To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Title, Description and Dates
    And I should see a list of Bloomingdales associates who received the To Do