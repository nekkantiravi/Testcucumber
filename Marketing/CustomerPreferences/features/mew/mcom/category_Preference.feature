# Author: Customer Preferences Project QE Team
# Date Created: 06/29/2017
# Version One:  MCOM Story B-78908, B-76806

Feature: Verification of Categories Preferences pages

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario: Verify user is able to save the categories
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page
    When I navigate to Categories page from Landing page
    Then I should see the Add state of Categories page
    When I select 'Select All' checkbox option in the add state
    And I click on 'Save' button on the page
    Then I should see the Summary state of Categories page
    When I click on 'Edit' option in Summary state of Categories
    Then I should see the Edit state of Categories page