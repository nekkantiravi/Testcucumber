# Story: Create List - Pre-Generated List - Top Tier Loyalty Customers
# Date Created: 01/09/2018
# Date Signed Off:

Feature: As a Selling Associate, I want the ability to select/view a ‘Pre-Generated List’ from the new
  Create List Dashboard, so that I can quickly and efficiently create my list for outreach.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-418 @website @mcom
  Scenario: Verify List Title and is displayed when selected
    And I should see the title My Platinum Customers on the Create List dashboard
    When I select the title My Platinum Customers on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    And the customer count will display on the My Platinum Customers Create List dashboard
    And the list should be sorted by LAST VISIT
    And the following columns should be displayed on the Create List dashboard
      | Last Visit |
      | Visits     |
      | $/All      |
      | $/Me       |
    And the list will not exceed 100 Top Tier Customers
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed