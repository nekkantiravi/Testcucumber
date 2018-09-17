# Author: Claudiu Chirila
# Story: SDE-470 Create List Wizard: Visits in Last 12 Months Filter
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

  @mcom @domain_stores @omniclient @website @story_SDE-470
  Scenario: Visits in the Last 12 Months - View / all options are included by default until a specific one is selected
    And I should see the Visits in the last 12 Months filter criteria Header
    And I should see the following options below Visits in the Last 12 Months filter
      | 0 Visits   |
      | 1 Visits   |
      | 2-3 Visits |
      | 4+ Visits  |
    When I click the chevron of Transactions filter criteria
    Then I should see the Transactions filter criteria collapsed
      | Visits in the last 12 Months |
    When I click the chevron of Transactions filter criteria
    Then I should see the Transactions filter criteria expanded
      | Visits in the last 12 Months |

  @mcom @domain_stores @omniclient @website @story_SDE-470
  Scenario: Visits in the Last 12 Months - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    When I select a filter criteria from Visits in the Last 12 Months
      | 1 Visits |
    Then I should see the filter criteria selected from Visits in the Last 12 Months
      | 1 Visits |
    When I remove the filter criteria from Visits in the Last 12 Months
      | 1 Visits |
    Then I should no longer see the filter criteria selected from Visits in the Last 12 Months
      | 1 Visits |
