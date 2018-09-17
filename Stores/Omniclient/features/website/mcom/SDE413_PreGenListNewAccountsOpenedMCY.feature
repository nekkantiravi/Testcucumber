# Story: Create List - Pre-Generated List - New Accounts opened with Me in the Last 30 Days
# Date Created: 12/28/2017
# Date Signed Off:

Feature: As a Selling Associate, I want the ability to select/view a ‘Pre-Generated List’ from the new
  Create List Dashboard, so that I can quickly and efficiently create my list for outreach.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-413 @website @mcom
  Scenario: Verify List Title and Subtitles
    And I should see the title Opened a New Credit Account with Me on the Create List dashboard
    And I should see the subtitle "Last 90 Days" for Opened a New Credit Account with Me
    When I expand the subtitle Last 90 Days for Opened a New Credit Account with Me
    Then I should see the following values in the New Accounts dropdown on the Create List dashboard
      | Last 30 Days |
      | Last 60 Days |
      | Last 90 Days |

  @domain_stores @omniclient @story_SDE-413 @website @mcom
  Scenario: Verify Pre-generated list is displayed when selected
    When I select the title Opened a New Credit Account with Me on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    And the customer count will display on the Opened New Acc Create List dashboard
    And the list should be sorted by AGE OF ACCOUNT
    And the following columns should be displayed on the Create List dashboard
      | Account Open Date |
      | Visits            |
      | $/All             |
      | $/Me              |
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
