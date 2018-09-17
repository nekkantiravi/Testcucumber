# Author: Claudiu Chirila
# Story: SDE-467 Create List Wizard: Loyalty Filter Filter
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes,
  in order to create a customized list of my clients for personalized outreach.


  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-466
  Scenario: Loyalty Filter - View
    And I should see the "Star Rewards Join/Upgrade Date" filter criteria Join Upgrade Header
    And I should see the following options below Join Upgrade filter
      | Join Date    |
      | Upgrade Date |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Join Upgrade section filter criteria collapsed
      | Join Date    |
      | Upgrade Date |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Join Upgrade filter section filter criteria expanded
      | Join Date    |
      | Upgrade Date |
    When user selects the following option bellow Join Upgrade filter
      | Join Date |
    Then the following options are displayed bellow the first secondary filter Join Upgrade
      | Last Month     |
      | Last 3 Months  |
      | Last 6 Months  |
      | Last 12 Months |
    When I remove the filter from the following primary filter option Join Upgrade
      | Join Date    |
    And user selects the following option bellow Join Upgrade filter
      | Upgrade Date |
    Then the following options are displayed bellow the second secondary filter Join Upgrade
      | Last Month     |
      | Last 3 Months  |
      | Last 6 Months  |
      | Last 12 Months |

  @mcom @domain_stores @omniclient @website @story_SDE-466
  Scenario: Loyalty Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    When user selects the following option bellow Join Upgrade filter
      | Upgrade Date |
    And user selects a secondary filter criteria from second filter option Join Upgrade
      | Last Month |
    Then I should see the secondary filter from the second filter option Join Upgrade
      | Last Month |
    When I remove the secondary filter from the second filter option Join Upgrade
      | Last Month |
    Then I should no longer see the secondary filter from second filter selected Join Upgrade
      | Last Month |

    When I remove the filter from the following primary filter option Join Upgrade
      | Upgrade Date    |
    Then user selects the following option bellow Join Upgrade filter
      | Join Date |
    And user selects a secondary filter criteria from first filter option Join Upgrade
      | Last Month |
    And I should see the secondary filter from the first filter option Join Upgrade
      | Last Month |
    When I remove the secondary filter from the first filter option Join Upgrade
      | Last Month |
    Then I should no longer see the secondary filter from first filter selected Join Upgrade
      | Last Month |
