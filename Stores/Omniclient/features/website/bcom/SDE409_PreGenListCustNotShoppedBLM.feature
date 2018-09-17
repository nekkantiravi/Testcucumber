# Story: Create List - Pre-Generated List - Customers who have NOT shopped in a Store
# Date Created: 12/28/2017
# Date Signed Off:

Feature: As a Selling Associate, I want the ability to select/view a ‘Pre-Generated List’ from the new
  Create List Dashboard, so that I can quickly and efficiently create my list for outreach.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate on Create List tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-409 @website @bcom
  Scenario: Verify List Title and Subtitles
    And I should see the title Cust Who Have Not Shopped on the Create List dashboard
    And I should see the subtitle "Last 90 Days" for Cust Who Have Not Shopped
    When I expand the subtitle Last 90 Days for Cust Who Have Not Shopped
    Then I should see the following values in the Cust Who Have Not Shopped dropdown on the Create List dashboard
      | Last 30 Days   |
      | Last 60 Days   |
      | Last 90 Days   |
      | Last 6 Months  |
      | Last 12 Months |

  @domain_stores @omniclient @story_SDE-409 @website @bcom
  Scenario: Verify Pre-generated list is displayed when selected
    When I select the title Cust Who Have Not Shopped on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    And the customer count will display on the Cust Who Have Not Shopped Create List dashboard
    And the list should be sorted by LAST VISIT DATE
    And the following columns should be displayed on the Create List dashboard
      | Last Visit |
      | Visits     |
      | $/All      |
      | $/Me       |
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
