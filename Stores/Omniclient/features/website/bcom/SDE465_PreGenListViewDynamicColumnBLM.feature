# Story: Create List: Display Pre-Generated List Views with a dynamic column
# Date Created: 12/28/2017
# Date Signed Off:

#  TODO: AUTOMATE ONCE CREATE LIST IS FINISHED AND WORKING. IT IS A MANUAL TEST CASE FOR NOW.

@manual
Feature: As a user when I view the List that is a result of a Pre-Generated list "run", in the My Tasks "list" view,
  I want to see the additional column that contains the dynamic criteria that relates to the specific topic of the list.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate

  @domain_stores @omniclient @story_SDE-465 @website @bcom
  Scenario: Pre-Generated List contains the dynamic column - between Name and Visit
    When I select the title My Top 25 Clients on the Create List dashboard
    And I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    When I select a valid date in Create List View on CREATE LIST page
    And I select the CREATE MY LIST button from Create List view
    Then LISTS section is displayed
    And the List "My Top 25 Customers (Last 12 Months)" is displayed on the LISTS tab
    When I click on the newly created Bloomingdales To Do
    Then EVENT INFORMATION page is displayed
    And the Dynamic Column "Rank" will be displayed

  @domain_stores @omniclient @story_SDE-465 @website @bcom
  Scenario: Non Pre-Generated List does not contain the dynamic column
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view
    And I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    When I enter a title "WIZARD LIST 001" in the input field in CREATE LIST page
    And I select a valid date in Create List View on CREATE LIST page
    And I select the CREATE MY LIST button from Create List view
    Then LISTS section is displayed
    And the List "WIZARD LIST 001" is displayed on the LISTS tab
    When I click on the newly created Bloomingdales To Do
    Then EVENT INFORMATION page is displayed
    And the Dynamic Column will not be displayed