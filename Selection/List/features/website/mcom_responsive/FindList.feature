#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Jan.10,2018
#---------------------------------------------------

Feature: Find a list on responsive landing list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should verify Find a List on responsive list landing page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    And I land on List landing page
    And I click on default list
    And I click on the "settings" button
    And I check the box to make my list "searchable"
    And I click on "save" on manage list overlay
    And I goto Home page
    When I sign out from my current profile
    When I create a new profile
    And I land on List landing page
    And I click on Find a List
    When I enter a first and last name to Find a List
    When I click on view list from Find a List results
    Then I should see my friend's list in the responsive list page