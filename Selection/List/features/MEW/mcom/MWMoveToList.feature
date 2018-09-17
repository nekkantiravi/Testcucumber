#---------------------------------------------------
# Brand         : MCOM MEW Responsive
# Author        : Ejay Landicho
# Date Created	: Nov.27,2017
#---------------------------------------------------

Feature: MEW Responsive List page for moving products to different lists for registered users

  @responsive_list_mew @domain_selection @project_mcom @t
  Scenario: As a signed in user, I should be able to move a product to another list in MEW responsive list page
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "22805" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I land on MEW List landing page
    And I verify the basic components on the page for "SignedIn" user
    And I should see "1" products in the responsive list page
    And I click on the "Move" button
    Then I should verify the move confirmation overlay
    When I "create" a list as "List1" on list overlay
    And I verify the list name as "List1" on list page
    And I should see "1" products in the responsive list page

  @responsive_mew_list @domain_selection @project_mcom
  Scenario: As as sigined in user , I should be able to move a product to a default like in MEW resposive lit page
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "22805" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I should see "1" products in the responsive list page
    And I click on the "Move" button
    Then I should verify the move confirmation overlay
    When I "create" a list as "List2" on list overlay
    And I verify the list name as "List2" on list page
    And I should see "1" products in the responsive list page
    And I click on the "Move" button
    When I "select" a list as "default" on list overlay
    And I verify the list name as "default" on list page
