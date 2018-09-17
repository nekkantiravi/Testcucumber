# Story: SDE-468 Create List Wizard: Transaction Timeframe Filter
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
    When I select "Spend with Me" from the Spend filter section

  @mcom @domain_stores @omniclient @website @story_SDE-468
  Scenario: Transaction Timeframe Filter - View / all options are included by default until a specific one is selected
    Then I should see the 'Timeframe' filter criteria Header
    And I should see the following options below 'Timeframe' filter
      | Last 30 Days             |
      | Last 60 Days             |
      | Last 3 Months            |
      | Last 6 Months            |
      | Custom Date Range        |
    When I click the chevron of Transactions filter criteria
    Then I should see the Transactions criteria collapsed
    When I click the chevron of Transactions filter criteria
    Then I should see the Transactions filter criteria expanded

  @mcom @domain_stores @omniclient @website @story_SDE-468
  Scenario: Transaction Timeframe Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    And I select a filter criteria from Transaction Timeframe
      | Last 30 Days             |
#      | Last 60 Days             |
#      | Last 3 Months            |
#      | Last 6 Months            |
#      | Add Range                |
    Then I should see the filter criteria selected from Transaction Timeframe
      | Last 30 Days             |
#      | Last 60 Days             |
#      | Last 3 Months            |
#      | Last 6 Months            |
#      | Add Range                |
    When I remove the filter criteria from Transaction Timeframe
      | Last 30 Days             |
#      | Last 60 Days             |
#      | Last 3 Months            |
#      | Last 6 Months            |
#      | Add Range                |
    Then I should no longer see the filter criteria selected from Transaction Timeframe
      | Last 30 Days             |
#      | Last 60 Days             |
#      | Last 3 Months            |
#      | Last 6 Months            |
#      | Add Range                |