# Story: Create List - Pre-Generated List - Quick list titles should be auto filled into Create To Dos Page
# Date Created: 01/24/2018
# Date Signed Off:

Feature: As a Create List user I want to see a graceful error message that will indicate that there are no results
  in my list.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-535 @website @mcom
  Scenario: Verify No Results Found Message from the Quick List
#    Will need to research to see what quick list will produce error
    When I select the title Opened a New Credit Account with Me on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    When I select a filter criteria from New Accounts Opened w/Me
      | Last 30 Days |
#      | Last 60 Days |
#      | Last 90 Days |
    Then the Quick List no results message is displayed
#  Sorry, there are no results that match this list.


  @domain_stores @omniclient @story_SDE-535 @website @mcom
  Scenario: Verify No Results Found Message from the Quick List
#    Will need to research to see what wizard list will produce error
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view
    When I select a filter criteria from Customer Profile Birthday
      | Next 15 Days |
#         | Next 30 Days |
    Then the Wizard Screen no results message is displayed
#    Sorry, there are no results that match your selections.