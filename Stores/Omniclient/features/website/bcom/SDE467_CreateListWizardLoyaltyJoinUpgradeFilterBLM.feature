# Author: Claudiu Chirila
# Story: SDE-467 Create List Wizard: Loyalty Filter Filter
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes,
  in order to create a customized list of my clients for personalized outreach.


  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate on Create List tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @bcom @domain_stores @omniclient @website @story_SDE-466
  Scenario: Loyalty Filter - View
    And I should see the 'Loyallist Join / Upgrade  Date' filter criteria Header
    And I should see the following options below Loyalty filter
      | Join Date    |
      | Upgrade Date |
    When user selects the following option bellow Loyalty filter
      | Join Date |
    Then the following options are displayed bellow secondary filter Loyalty section
      | Last Month     |
      | Last 3 Months  |
      | Last 6 Months  |
      | Last 12 Months |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Loyalty section filter criteria collapsed
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Loyalty section filter criteria expanded
    When user selects the following option bellow Loyalty filter
      | Upgrade Date |
    Then the following options are displayed bellow secondary filter Loyalty section
      | Last Month     |
      | Last 3 Months  |
      | Last 6 Months  |
      | Last 12 Months |

  @bcom @domain_stores @omniclient @website @story_SDE-466
  Scenario: Loyalty Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    When user selects the following option bellow Loyalty filter
      | Upgrade Date |
    And user selects a secondary filter criteria "Last Month" from Loyalty filter
    Then I should see the secondary filter criteria "Last Month" selected from Loyalty filter
    When I remove the secondary filter criteria from Loyalty filter
    Then I should no longer see the secondary filter criteria selected from Loyalty filter

