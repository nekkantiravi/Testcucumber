#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : German Ortega
# Date Created	: Sept.13,2017
#---------------------------------------------------

Feature: Delete and item from your list

  @responsive_list @domain_selection @project_mcom
  Scenario: The Guest user should be able to delete an item from the list
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "guest" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I should see "1" products in the responsive list page
    And I click on the "Delete" button
    And I should see "0" products in the responsive list page


  @responsive_list @domain_selection @project_mcom
  Scenario: The signedin user should be able to delete an item from the list
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I should see "1" products in the responsive list page
    And I click on the "Delete" button
    And I should see "0" products in the responsive list page





