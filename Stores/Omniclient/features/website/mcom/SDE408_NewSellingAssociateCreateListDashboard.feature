# Author: Traci Morris
# Story: Create List
# Date Created: 12/12/2017
# Date Signed Off:

Feature: As a Selling Associate, I want the ability to Create Lists from scratch or select from a pre-generated list,
  so that I can effectively outreach to my clients.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on Create To Dos from navigation bar
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-408 @website @mcom
  Scenario: Verify UI of the Create List Dashboard for a Selling Associate
    And I should see Create Lists title on left side panel
    And I should see TO DO LISTS header
    And I should see MY LISTS header
    And I should see Create Custom List button on left side panel
    And I should see Create To Dos button on right hand side panel

  @domain_stores @omniclient @story_SDE-408 @website @mcom
  Scenario: Verify the static lookup panel and static communication center do not display on Create List Dashboard.
    And I should not see the Search Options
    And I should not see the Communication Center