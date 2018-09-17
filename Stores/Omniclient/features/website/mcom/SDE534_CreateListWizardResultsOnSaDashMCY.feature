# Story: SDE-534 Create List: Wizard Results list on the Associate Dashboard
# Date Created:
# Date Signed Off:

Feature: As a user of Create List, when I begin to create a list in the Create List Wizard, I want the customer
  list on the screen to be made up of four columns: $/Query, Visits, Spend w/all, Spend w/Me.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-534
  Scenario: Spend in GMM/Division/Dept/Class/ UPC/In A not B -  $/Query will display
#    TODO: NEED TO UPDATE - Spend in GMM/Division/Dept/Class/UPC/In A not B area is still being worked on
#    Kevin created a workaround - Once complete will update test case
#    When I select a filter criteria from Transaction Spend Area
#      | Spend by GMM |
    When I select a filter criteria from Customer Location
      | Domestic Customers Only      |
    Then the following columns should be displayed on the Create List dashboard
      | $/Query |
      | Visits  |
      | $/All   |
      | $/Me    |

  @mcom @domain_stores @omniclient @website @story_SDE-534
  Scenario: Any other list -  $/Query will NOT display
    When I select a filter criteria from Customer Profile Birthday
      | Next 30 Days |
    Then the following columns should be displayed on the Create List dashboard
      | Visits  |
      | $/All   |
      | $/Me    |
    And I should not see the Query column on the Create List dashboard
