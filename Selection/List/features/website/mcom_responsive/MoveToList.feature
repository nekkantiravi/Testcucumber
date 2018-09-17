#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Aug.11,2017
#---------------------------------------------------

Feature: Move product to list on responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able to move a product to another list
    Given I visit the web site as a guest user
    And I create a new profile
    And I set cookie for SSC to see responsive experience
    And I add a "78600" product to my list through the service call
    And I select wishlist link in header
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "Move" button
    Then I should see move overlay on responsive list page
    When I create a list as "List1" on the move overlay
    Then I should see the list name as "List1" on responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signed in user, I should be able to move a product to default list
    Given I visit the web site as a guest user
    And I create a new profile
    And I set cookie for SSC to see responsive experience
    And I add a "1494" product to my list through the service call
    And I select wishlist link in header
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "Move" button
    Then I should see move overlay on responsive list page
    When I create a list as "List1" on the move overlay
    Then I should see the list name as "List1" on responsive list page
    And I click on the "Move" button
    Then I should see move overlay on responsive list page
    Then I move my product to default list on move overlay
    Then I should see move confirmation message and navigate to other list
    Then I should see the list name as "default name" on responsive list page