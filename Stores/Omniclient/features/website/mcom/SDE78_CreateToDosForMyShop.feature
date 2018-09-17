# Author: Claudiu Chirila
# Story: SDE-78 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

@manual @SKIPPED
Feature: As a Selling Manager in the My Shop configuration, I want to be able to Create To-Do's for any associate in the store, so that I can drive Clienteling outreach in my store.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I navigate on Create To Dos tab

  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: PROFILE , TRANSACTIONS and TARGET tabs are displayed in CREATE TO DOS page
    Then PROFILE tab is displayed in omniclient view
    And TRANSACTIONS tab is displayed in omniclient view
    And TARGET tab is displayed in omniclient view

  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: "Send To Do's to SAs that report to me" , "Exclude International Customers is checked" and "Default to max 250 tasks per SA" are checked by default
    And I navigate to TARGET tab
    Then Send To Do's to SAs that report to me is checked by default
    And Exclude International Customers is checked by default
    And Default to max 250 tasks per SA is checked by default

  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: User successfully creates a TO DO by selecting "Send To Do's to SAs within specific Divisions, MGMs, and/or Departments" and "Select All" DIVISIONS, MGM and DEPARTMENTS
    When I navigate to TARGET tab
    And I select Send To Do's to SAs within specific Divisions, MGMs, and or Departments radio button
    Then Select SAs by section is displayed
    And DIVISIONS list is displayed
    When I click on Select all from DIVISION list
    Then MGM list is displayed
    When I click on Select all from MGM list
    Then DEPARTMENT list is displayed
    When I click on Select all from DEPARTMENT list
    Then I click on Continue button from Select SAs by section
    And I click on Continue button from TARGET tab page
    And I enter "SDE78FIRST" in the Title checkbox
    And I select valid Start Date in Create List Send to Dos view
    And I select valid End Date in Create List Send to Dos view
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then Attention popup is displayed in Create List Send to Dos view
    When I click on OK button in omniclient attention popup
    Then MY MACYS TO DOS section is displayed
    When I wait to the ToDo to be successfully processed
    Then I should see the new TO DO "SDE78FIRST" created by the selling manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed

  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: User successfully creates a TO DO by selecting "Send To Do's to SAs that report to me"
    Then PROFILE tab is displayed by default
    When I navigate to TARGET tab
    And I select default Send To Do's to SAs that report to me radio button
    And I select default Exclude International Customers radio button
    And I select Default to max 250 tasks per SA radio button
    And I click on Continue button from TARGET tab page
    Then CREATE LIST SEND TO DOS section is displayed
    When I enter "SDE78SECOND" in the Title checkbox
    And I select valid Start Date in Create List Send to Dos view
    And I select valid End Date in Create List Send to Dos view
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then Attention popup is displayed in Create List Send to Dos view
    When I click on OK button in omniclient attention popup
    Then MY MACYS TO DOS section is displayed
    When I wait to the ToDo to be successfully processed
    Then I should see the new TO DO "SDE78SECOND" created by the selling manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed

  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: User successfully creates a TO DO by selecting "Send To Do's to SAs that report to me" ,"All Customers" and "Limit tasks delivered to each SA" in HOW MANY section
    Then PROFILE tab is displayed by default
    When I navigate to TARGET tab
    And I select default Send To Do's to SAs that report to me radio button
    And I select All Customers Customers radio button
    And I select Limit tasks delivered to each SA radio button
    And I enter value in Limit tasks delivered to each SA field
    And I click on Continue button from TARGET tab page
    Then CREATE LIST SEND TO DOS section is displayed
    When I enter "SDE78THIRD" in the Title checkbox
    And I select valid Start Date in Create List Send to Dos view
    And I select valid End Date in Create List Send to Dos view
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then Attention popup is displayed in Create List Send to Dos view
    When I click on OK button in omniclient attention popup
    Then MY MACYS TO DOS section is displayed
    When I wait to the ToDo to be successfully processed
    Then I should see the new TO DO "SDE78THIRD" created by the selling manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed


  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: User successfully creates a TO DO by selecting "Send To Do's to SAs within specific Divisions, MGMs, and/or Departments"
    When I navigate to TARGET tab
    And I select Send To Do's to SAs within specific Divisions, MGMs, and or Departments radio button
    Then Select SAs by section is displayed
    And DIVISIONS list is displayed
    When I click on one division from DIVISION list
    Then MGM list is displayed
    When I click on one option from MGM list
    Then DEPARTMENT list is displayed
    When I click on one option from DEPARTMENT list
    Then I click on Continue button from Select SAs by section
    And I click on Continue button from TARGET tab page
    And I enter "SDE78FOURTH" in the Title checkbox
    And I select valid Start Date in Create List Send to Dos view
    And I select valid End Date in Create List Send to Dos view
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then Attention popup is displayed in Create List Send to Dos view
    When I click on OK button in omniclient attention popup
    Then MY MACYS TO DOS section is displayed
    When I wait to the ToDo to be successfully processed
    Then I should see the new TO DO "SDE78FOURTH" created by the selling manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed


  @domain_stores @omniclient @story_SDE-78 @website @mcom
  Scenario: User successfully creates a TO DO by selecting "Send To Do's to SAs within specific Divisions, MGMs, and/or Departments", "International Customers Only" and "Limit Number of Customers in List to:"
    When I navigate to TARGET tab
    And I select Send To Do's to SAs within specific Divisions, MGMs, and or Departments radio button
    Then Select SAs by section is displayed
    And DIVISIONS list is displayed
    When I click on one division from DIVISION list
    Then MGM list is displayed
    When I click on one option from MGM list
    Then DEPARTMENT list is displayed
    When I click on one option from DEPARTMENT list
    Then I click on Continue button from Select SAs by section
    And I select International Customers Only radio button
    And I enter value in Limit Number of Customers in List field "10"
    And I select Default to max 250 tasks per SA radio button
    And I click on Continue button from TARGET tab page
    And I enter "SDE78FIFTH" in the Title checkbox
    And I select valid Start Date in Create List Send to Dos view
    And I select valid End Date in Create List Send to Dos view
    And I add a description "TEST My To Do list" in Create List Send to Dos view
    And I click on CREATE MY LIST button
    Then Attention popup is displayed in Create List Send to Dos view
    When I click on OK button in omniclient attention popup
    Then MY MACYS TO DOS section is displayed
    When I wait to the ToDo to be successfully processed
    Then I should see the new TO DO "SDE78FIFTH" created by the selling manager
    When I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed

  Scenario: Delete Created ToDos
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Admin User
    And change user into "10000057" from Admin interface
    And I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    When I delete all created ToDos
    Then I should not see any ToDos that can be deleted