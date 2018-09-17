#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Nov.13,2017
#---------------------------------------------------

Feature: Add to List from QuickView with Responsive List Page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, I should be able to add to list from quick view
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I navigate to the "activewear" browse page under "women"
    When I select a random product in a quickview dialog
    And I add the item to wishlist from QV
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I should see "1" products in the responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should be able to add to list from quick view
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I navigate to the "activewear" browse page under "women"
    When I select a random product in a quickview dialog
    And I add the item to wishlist from QV
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I should see "1" products in the responsive list page