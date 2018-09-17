# Author: Customer Preferences Project QE Team
# Date Created: 1/09/2017
# Version One:  MCOM Story B-87469

Feature: Verification of Brand preferences pages

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario: Verify user is able to save the brands
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page
    When I navigate to Categories page from Landing page
    And I click on 'Brands' tab option on the page
    Then I should see the "Add Brands" Brands sub heading on the page
    When I select any Brands tile on the page
    And I click on 'Save' button in add state
    Then I should see the Summary state of Brands page
    When I click on 'Edit' option in summary state of brands page
    Then I should see the Edit state of Brands page