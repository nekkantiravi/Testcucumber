# Author: Customer Preferences Project QE Team
# Date Created: 07/14/2017
# Version One:  MCOM Story B-78527, B-78526 & B-84861

Feature: Verification of Sizes Preferences pages

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario: Verify user is able to save the Sizes
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page
    When I navigate to Categories page from Landing page
    And I click on 'Sizes' tab option on the page
    Then I should see the Sizes sub heading "Add Sizes" on the page
    When I click on first accordion
    And I select any Size tile in the expanded accordion
    And I click on 'Save' button in add state of Sizes page
    Then I should see the Summary state of Sizes page
    When I click on 'Edit' option in summary state of sizes
    Then I should see the Edit state of Sizes page