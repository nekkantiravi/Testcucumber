# Author: Ovidiu Rucoi
# Story: SDE-464 Create List Wizard: Birthday Filter
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes, in order
  to create a customized list of my clients for personalized outreach.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate on Create List tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @bcom @domain_stores @omniclient @website @story_SDE-464
  Scenario: Birthday Filter - View
    And I should see the "Birthday" filter criteria section Header
    And I should see the following options below Birthday filter
      | Next 15 Days |
      | Next 30 Days |
    When I click the chevron of Customer Profile filter criteria
    Then I should see the Customer Profile filter criteria collapsed
      | Affiliation Date |
      | Birthday         |
    When I click the chevron of Customer Profile filter criteria
    Then I should see the Customer Profile filter criteria expanded
      | Affiliation Date |
      | Birthday         |

  @bcom @domain_stores @omniclient @website @story_SDE-464
  Scenario: Birthday Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    When I select a filter criteria from Customer Profile Birthday
#      | Next 15 Days |
      | Next 30 Days |
    Then I should see the filter criteria selected from Customer Profile Birthday
#      | Next 15 Days |
      | Next 30 Days |
    When I remove the filter criteria from Customer Profile Birthday
#      | Next 15 Days |
      | Next 30 Days |
    Then I should no longer see the filter criteria selected from Customer Profile Birthday
#      | Next 15 Days |
      | Next 30 Days |
