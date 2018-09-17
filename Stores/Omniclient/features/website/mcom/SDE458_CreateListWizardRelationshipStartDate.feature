# Author: Ovidiu Rucoi
# Story: SDE-458 Create List Wizard: Affiliation Date/ Relationship Start Date Filter
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes, in order
  to create a customized list of my clients for personalized outreach.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-458
  Scenario: Relationship Start Date - View
    And I should see the "Relationship Start Date" filter criteria Header
#    Relationship Start Date
    And I should see the following options below Date filter
      | Last 14 Days |
      | Last 30 Days |
      | Last 60 Days |
      | Last 90 Days |
    When I click the chevron of Customer Profile filter criteria
    Then I should see the Customer Profile filter criteria collapsed
      | Relationship Start Date |
      | Birthday                |
    When I click the chevron of Customer Profile filter criteria
    Then I should see the Customer Profile filter criteria expanded
      | Relationship Start Date |
      | Birthday                |

  @mcom @domain_stores @omniclient @website @story_SDE-458
  Scenario: Relationship Start Date - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    When I select a filter criteria from Customer Profile Date
      | Last 14 Days |
#      | Last 30 Days |
#      | Last 60 Days |
#      | Last 90 Days |
    Then I should see the filter criteria selected from Customer Profile Date
      | Last 14 Days |
#      | Last 30 Days |
#      | Last 60 Days |
#      | Last 90 Days |
    When I remove the filter criteria from Customer Profile Date
      | Last 14 Days |
#      | Last 30 Days |
#      | Last 60 Days |
#      | Last 90 Days |
    Then I should no longer see the filter criteria selected from Customer Profile Date
      | Last 14 Days |
#      | Last 30 Days |
#      | Last 60 Days |
#      | Last 90 Days |