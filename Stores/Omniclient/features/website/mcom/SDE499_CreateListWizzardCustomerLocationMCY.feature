# Author: Ovidiu Rucoi
# Story: SDE-499 Create List Wizard: Customer Location Filter
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

  @mcom @domain_stores @omniclient @website @story_SDE-499
  Scenario: Customer Location Filter - View
    And I should see the Customer Location filter criteria Header
    And I should see the following options below Customer Location filter
      | Domestic Customers Only      |
      | International Customers Only |
    When I click the chevron of Customer Profile filter criteria
    Then I should see the Customer Profile filter criteria collapsed
      | Affiliation Start Date |
      | Birthday                |
      | Customer Location       |
    When I click the chevron of Customer Profile filter criteria
    Then I should see the Customer Profile filter criteria expanded
      | Relationship Start Date |
      | Birthday                |
      | Customer Location       |

  @mcom @domain_stores @omniclient @website @story_SDE-499
  Scenario: Customer Location Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    And I should see the Customer Location filter criteria Header
    When I select a filter criteria from Customer Location
#      | Domestic Customers Only      |
      | International Customers Only |
    Then I should see the filter criteria selected from Customer Location
#      | Domestic Customers Only      |
      | International Customers Only |
    When I remove the filter criteria from Customer Location
#      | Domestic Customers Only      |
      | International Customers Only |
    And I should no longer see the filter criteria selected from Customer Location
#      | Domestic Customers Only      |
      | International Customers Only |
