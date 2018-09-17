#---------------------------------------------------
# Brand         : MCOM MEW Responsive
# Author        : Ejay Landicho
# Date Created	: Nov.27,2017
#---------------------------------------------------

Feature: MEW Responsive List page for managing list settings for registered users

  @responsive_list_mew @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able to see settings overlay in responsive MEW list page
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "22805" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I should see "1" products in the responsive list page
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page

  @responsive_list_mew @domain_selection @project_mcom
  Scenario: As a signed in user, I should be to set my default list in settings overlay in responsive MEW list page
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "86800" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page
    And I check the box to make my list "searchable"
    And I click on "save" on manage list overlay
    And I verify the list name as "default" on list page

  @responsive_list_mew @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able set up price alerts in settings overlay in responsive MEW list page
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "86800" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page
    And I check the box to make my list "have price alerts"
    And I click on "save" on manage list overlay
    And I verify that my list is subscribed for price alerts

  @responsive_list_mew @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able to change my list name in settings overlay in responsive MEW list page
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "86800" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I click on the "settings" button
    And I verify lists manage settings on responsive list page
    When I rename the list as "List1" on settings overlay
    And I click on "save" on manage list overlay
    And I verify the list name as "List1" on list page