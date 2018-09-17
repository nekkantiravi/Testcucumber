#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : vijaya bharathi
# Date Created	: Aug.11,2017
#---------------------------------------------------


Feature: List Landing Page scenario

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user with no items when user click on List on header he should be routed to list landing page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I land on List landing page
    Then I verify the basic components of landingpage for "Guest" user
    Then I verify "0" items and "6" place holders with "NoItem"

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest when user clicks on empty image space he should be land on empty list page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    And I click on List link in the header
    And I land on List landing page
    Then I verify the basic components of landingpage for "Guest" user
    Then I verify "0" items and "6" place holders with "NoItem"
    Then I click on empty list
    Then I verify user should land on empty "Guest" list page


  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user has an item lands on list landing page, the item image should be displayed on the landing page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "guest" user
    And I land on List landing page
    Then I verify the basic components of landingpage for "Guest" user
    Then I verify "1" items and "5" place holders with "Item"

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user has an item lands on list landing page, click on the image on landingpage user should redirected to respective list page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "guest" user
    And I land on List landing page
    Then I verify the basic components of landingpage for "Guest" user
    Then I verify "1" items and "5" place holders with "Item"
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I should see "1" products in the responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user has when he clicks on see all list he should be routed back to list landing page.
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "guest" user
    And I land on List landing page
    Then I verify the basic components of landingpage for "Guest" user
    Then I verify "1" items and "5" place holders with "Item"
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I should see "1" products in the responsive list page
    And I click on the "SeeALlList" button
    Then I verify user landed on landing page