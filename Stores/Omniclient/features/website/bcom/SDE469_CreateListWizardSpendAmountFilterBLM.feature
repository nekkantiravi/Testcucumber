# Story: SDE-469 Create List Wizard: Spend Amount Filter
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
    When I select "Spend with All" from the Spend filter section

  @bcom @domain_stores @omniclient @website @story_SDE-469
  Scenario: Spend Amount Filter - View / all options are included by default until a specific one is selected
    Then I should see the 'Spend Amount' filter criteria Header
    And I should see the following options below 'Spend Amount' filter
      | $0 (Did Not Purchase)    |
      | $250 or more             |
      | $500 or more             |
      | $1000 or more            |
      | $2500 or more            |
      | Custom Date Range        |
    When I click the chevron of Transactions filter criteria
    Then I should see the Transactions criteria collapsed
    When I click the chevron of Transactions filter criteria
    Then I should see the Transactions filter criteria expanded

  @bcom @domain_stores @omniclient @website @story_SDE-469
  Scenario: Spend Amount Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    And I select a filter criteria from Transaction Spend Amount
#      | $0 (Did Not Purchase)    |
      | $250 or more             |
#      | $500 or more             |
#      | $1000 or more            |
#      | $2500 or more            |
#      | Custom Date Range        |
    Then I should see the filter criteria selected from Transaction Spend Amount
#      | $0 (Did Not Purchase)    |
      | $250 or more             |
#      | $500 or more             |
#      | $1000 or more            |
#      | $2500 or more            |
#      | Custom Date Range        |
    When I remove the filter criteria from Transaction Spend Amount
#      | $0 (Did Not Purchase)    |
      | $250 or more             |
#      | $500 or more             |
#      | $1000 or more            |
#      | $2500 or more            |
#      | Custom Date Range        |
    Then I should no longer see the filter criteria selected from Transaction Spend Amount
#      | $0 (Did Not Purchase)    |
      | $250 or more             |
#      | $500 or more             |
#      | $1000 or more            |
#      | $2500 or more            |
#      | Custom Date Range        |