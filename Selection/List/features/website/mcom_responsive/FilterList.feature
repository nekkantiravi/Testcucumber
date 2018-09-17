#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Dec.13,2017
#---------------------------------------------------

Feature: Filtering products on responsive list page

  @responsive_list_mew @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able to filter products on responsive list page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    And I add a "78600" product to my list through the service call
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "registered" user
    And I should see "2" products in the responsive list page
    And I filter the list as "In Stock"
    Then I should see the list filtered as "In Stock"

  @responsive_list_mew @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able to filter out products on responsive list page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I add a "22805" product to my list through the service call
    And I add a "1494" product to my list through the service call
    And I add a "78600" product to my list through the service call
    And I add a "136769" product to my list through the service call
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "registered" user
    And I should see "4" products in the responsive list page
    And I filter the list as "In Stock"
    Then I should see the list filtered as "In Stock"
    And I should see "3" products in the responsive list page