#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : Trinath
# Date Created	: Nov 27,2017
#---------------------------------------------------


Feature: List Landing Page scenario -signed in user

  @responsive_list @domain_selection @project_mcom
  Scenario: As a Signed in user with no items when user click on List on header he should be routed to list landing page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    #Then I verify "0" items and "6" place holders with "NoItem"

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signed in user when user clicks on empty image space he should be land on empty list page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    And I click on Create list "ListA"
    And I click on the "SeeALlList" button
    Then I click on empty list
    Then I verify user should land on empty "SignedIn" list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signedin user has an item lands on list landing page, the item image should be displayed on the landing page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    Then I verify "1" items and "5" place holders with "Item"
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I should see "1" products in the responsive list page
    And I click on the "SeeALlList" button
    Then I verify user landed on landing page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signedin user has an item lands on list landing page, create-list and create a non default list
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    Then I verify "1" items and "5" place holders with "Item"
    And I click on Create list "ListA"
    And I click on the "SeeALlList" button
    Then I verify "2" List placeholder on Landing page



  @responsive_list @domain_selection @project_mcom @test
  Scenario: As a Signed in user I should me able to create a new list and make it as searchable
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    And I click on searchable Create list "ListA"
    Then Make List as searchable and verify it on settings popup


  @responsive_list @domain_selection @project_mcom
  Scenario: As a Signed in user I should me able to add 6 products and I need to have zero place holders
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I add a "22805" product to my list through the service call
    And I add a "1494" product to my list through the service call
    And I add a "4603212" product to my list through the service call
    And I add a "78600" product to my list through the service call
    And I add a "4812536" product to my list through the service call
    And I add a "22804" product to my list through the service call
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    Then I verify "6" items and "0" place holders with "Item"


  @responsive_list @domain_selection @project_mcom
  Scenario: As a signed in user i should be able to create 20 lists
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I land on List landing page
    Then I verify the basic components of landingpage for "Signedin" user
    Then I should be able to create new "list" and count "21" using service

