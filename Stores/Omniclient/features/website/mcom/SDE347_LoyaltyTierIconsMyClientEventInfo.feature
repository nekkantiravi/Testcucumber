# Author: Traci Morris
# Story: SDE-347 - OmniClient : Macy's Loyalty: Loyalty Tier Icons on the My Client Event Info Screen
# Date Created: 10/16/2017
# Date Signed Off:

@manual @SKIPPED
Feature: As a MyClient User, I want to know the current Loyalty tier level status of my customer who is a member of the
  Macy's Star Rewards Loyalty Program, so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-347 @website @mcom
  Scenario: Verify the Loyalty Tier Icons are displayed on the My Client Event Info screen

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I create a TO DO from CREATE TO DOS page
      | PROFILE |
    And I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    And I should see the Star Rewards Level title on Event Information screen
    When I select the Star Rewards column from Event Information page
    Then I should see the Star Rewards Tier Icons on the Event Information screen

      | gold        |
      | silver      |
      | platinum    |
      | non-loyalty |


  @domain_stores @omniclient @story_SDE-347 @website @mcom
  Scenario: Verify Star Rewards column is sortable
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    When I select the Star Rewards column from Event Information page
    Then I should see the Loyalty icons sorted "descending"
    When I select the Star Rewards column from Event Information page
    Then I should see the Loyalty icons sorted "ascending"