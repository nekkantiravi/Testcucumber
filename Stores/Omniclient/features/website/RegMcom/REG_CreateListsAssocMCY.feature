# Author: Ovidiu Rucoi
# Story: Create Lists
# Date Created: 11/08/2017
# Date Signed Off:

Feature: As an associate, I want to Create Lists and validate them

  @RegMcom @domain_stores @omniclient @website
  Scenario: Validate Create Lists page, Associate Level
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then PROFILE tab is displayed in omniclient view
    And TRANSACTIONS tab is displayed in omniclient view
    And TARGET tab is displayed in omniclient view
    When I click on PREVIEW button from CREATE TODOS tabs
    Then the CREATE LIST PREVIEW page is displayed
    When I close the CREATE LIST PREVIEW page
    And I click on CONTINUE button from CREATE TODOS tabs
    Then the CREATE LIST screen is displayed
    When I enter "ABC" in the Title checkbox
    Then I should see the ToDo TITLE remaining characters count as 50
    When I add a description "TEST My To Do list" in Create List Send to Dos view
    Then I should see the ToDo DESCRIPTION remaining characters count as 500


  @RegMcom @domain_stores @omniclient @website
  Scenario: Validate Create Lists Cancel Alert, Associate Level
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then PROFILE tab is displayed in omniclient view
#    CANCEL button Alert
    When I click on CONTINUE button from CREATE TODOS tabs
    Then the CREATE LIST screen is displayed
    When I enter "ABC" in the Title checkbox
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click the CANCEL button on CREATE LIST screen
    Then I should see the Disregard Changes message
    When I select the Cancel button on the Disregard Changes error message
    Then the CREATE LIST screen is displayed
    And current List details are retained Title "ABC" and Description "TEST My To Do list"
    When I click the CANCEL button on CREATE LIST screen
    Then I should see the Disregard Changes message
    When I select the OK button on the Disregard Changes error message
    Then PROFILE tab is displayed by default
#    X button Alert
    When I click on CONTINUE button from CREATE TODOS tabs
    Then the CREATE LIST screen is displayed
    When I enter "ABC" in the Title checkbox
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click the X button on CREATE LIST screen
    Then I should see the Disregard Changes message
    When I select the Cancel button on the Disregard Changes error message
    Then the CREATE LIST screen is displayed
    And current List details are retained Title "ABC" and Description "TEST My To Do list"
    When I click the X button on CREATE LIST screen
    Then I should see the Disregard Changes message
    When I select the OK button on the Disregard Changes error message
    Then PROFILE tab is displayed by default
    When I click the X button located on the top right corner of the To Do page
    Then I will see the associates HOMEPAGE


  @RegMcom @domain_stores @omniclient @website
  Scenario: Validate Credit Card Info in Title and Description error message, Associate Level
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then PROFILE tab is displayed in omniclient view
#    CC error in TITLE
    When I click on CONTINUE button from CREATE TODOS tabs
    Then the CREATE LIST screen is displayed
    When I enter "1234567890" in the Title checkbox
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then I should see the Credit Card information in Title field error message
    When I select the OK button on the error message
#     CC error in DESCRIPTION
    Then the CREATE LIST screen is displayed
    When I enter "ABC" in the Title checkbox
    And I add a description "1234567890" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then I should see the Credit Card information in Description field error message

  @RegMcom @domain_stores @omniclient @website
  Scenario: Create Valid List from PROFILE and check count increase, Associate Level
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate
    And I check the My Macys To DOs count
    And I navigate on Create To Dos tab
    And I create a TO DO from CREATE TO DOS page
      | PROFILE |
    Then I should see the list of TO DOS
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    When I navigate to Macys Homepage
    Then I will validate that the My Macys ToDos count incremented by 1

  @RegMcom @domain_stores @omniclient @website
  Scenario: Create Valid List from TRANSACTIONS and check count increase, Associate Level
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate
    And I check the My Macys To DOs count
    And I navigate on Create To Dos tab
    And I create a TO DO from CREATE TO DOS page
      | TRANSACTIONS |
    Then I should see the list of TO DOS
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    When I navigate to Macys Homepage
    Then I will validate that the My Macys ToDos count incremented by 1

  @RegMcom @domain_stores @omniclient @website
  Scenario: Create Valid List from TARGET and check count increase, Associate Level
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate
    And I check the My Macys To DOs count
    And I navigate on Create To Dos tab
    And I create a TO DO from CREATE TO DOS page
      | TARGET SA |
    Then I should see the list of TO DOS
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    When I navigate to Macys Homepage
    Then I will validate that the My Macys ToDos count incremented by 1

  @RegMcom @domain_stores @omniclient @website
  Scenario: Delete Created ToDos
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Admin User
    And change user into "10000051" from Admin interface
    And I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    When I delete all created ToDos
    Then I should not see any ToDos that can be deleted
