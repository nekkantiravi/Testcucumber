# Author: Traci Morris
# Story: SDE-336 - OmniClient : Macy's Loyalty: Loyalty Tier Icons on the My Client Screen
# Date Created: 10/16/2017
# Date Signed Off:

Feature: As a MyClient User, I want to know the current loyalty tier level status of my customer who is a member of the
  Macy's Star Rewards Loyalty Program, so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-336 @website @mcom
  Scenario Outline: Verify the Loyalty Tier Icons are displayed on the My Client screen

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Clients from top navigation bar
    Then I should see the My Clients screen
    And I should see the Star Rewards Level title on My Clients screen
    And I should see the Star Rewards Tier Icons "<icon>" on the My Clients screen

    Examples:
      | icon     |
      | gold     |
      | silver   |
      | platinum |


  @domain_stores @omniclient @story_SDE-336 @website @mcom
  Scenario: Verify Star Rewards column is sortable

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Clients from top navigation bar
    Then I should see the My Clients screen
    When I select the Star Rewards column My Clients
    Then I should see the column sorted by Platinum, Gold, Silver and Non-Loyalty
