#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Oct.26,2017
#---------------------------------------------------

Feature: Manage settings on responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should verify settings overlay appears on responsive list page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should be able to change my list name through settings overlay
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    And I land on List landing page
    And I click on default list
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page
    And I check the box to make my list "searchable"
    And I click on "save" on manage list overlay
    And I verify the list name as "default" on list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should be able to change my list name through settings overlay
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    And I land on List landing page
    And I click on default list
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page
    And I check the box to make my list "have price alerts"
    And I click on "save" on manage list overlay
    And I verify that my list is subscribed for price alerts
