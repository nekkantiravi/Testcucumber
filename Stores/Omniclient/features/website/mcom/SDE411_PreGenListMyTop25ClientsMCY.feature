# Story: Create List - Pre-Generated List - Top 25 Clients over last 12 Months by Spend with Me
# Date Created: 12/28/2017
# Date Signed Off:

Feature: As a Selling Associate, I want the ability to select/view a ‘Pre-Generated List’ from the new
  Create List Dashboard, so that I can quickly and efficiently create my list for outreach.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-411 @website @mcom
  Scenario: Verify List Title and Subtitles
    And I should see the title My Top 25 Clients on the Create List dashboard
    And I should see the subtitle "Last 12 Months" for My Top 25 Clients
    When I expand the subtitle Last 12 Months by Spend me for My Top 25 Clients
    Then I should see the following values in the My Top 25 Clients dropdown on the Create List dashboard
      | Last 12 Months |

  @domain_stores @omniclient @story_SDE-411 @website @mcom
  Scenario: Verify Pre-generated list is displayed when selected
    When I select the title My Top 25 Clients on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    And the customer count will display on the Top Clients Create List dashboard
    And the list should be sorted by RANK
    And the following columns should be displayed on the Create List dashboard
      | Rank   |
      | VISITS |
      | $ ALL  |
      | $ ME   |
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
