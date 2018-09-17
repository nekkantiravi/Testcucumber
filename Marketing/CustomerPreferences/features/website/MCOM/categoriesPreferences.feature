# Author: Customer Preferences Project QE Team
# Date Created: 06/29/2017
# Version One:  MCOM Story B-78908, B-76806

Feature: Verification of Categories Preferences pages

  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify user is able to save the categories in Add State
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    Then I should see the Add state of Categories
    And I should see the "Shopping Preferences" page title
    And I should see the 'Save' button in disabled state
    And I should see the "Categories" tab heading
    And I should see the sub heading "Add Categories"
    And I should see the caption text "Choose the categories you love to shop"
    And I should see the dropdown field
    And I should see the 'Select All' checkbox option
    When I select 'Select All' checkbox option
    And I click on 'Save' button
    Then I should see the Summary state of Categories

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify user is able to view the categories in summary state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    Then I should see the Add state of Categories
    When I select 'Select All' checkbox option
    And I click on 'Save' button
    Then I should see the Summary state of Categories
    And I should see the "Shopping Preferences" page title
    And I should see the text "What do you usually shop for? Tell us your favorite categories" in summary state
    And I should see the 'Edit' option
    And I should see the caption text "These are your favorite categories:" in summary state
    And I should see the categories count sub heading
    And I should see the categories in Summary state


  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify user is able to view the categories in Edit state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    Then I should see the Add state of Categories
    When I select 'Select All' checkbox option
    And I click on 'Save' button
    Then I should see the Summary state of Categories
    When I click on 'Edit' option in Summary state
    Then I should see the Edit state of Categories
    And I should see the "Shopping Preferences" page title
    And I should see the text "What do you usually shop for? Tell us your favorite categories" in edit state
    And I should see the "Categories" tab heading
    And I should see the sub heading "Edit Categories" in edit state
    And I should see the caption text "Add or remove categories to update your preferences" in edit state
    And I should see the dropdown field
    And I should see the 'Deselect All' checkbox option
    And I should see 'Save' and 'Cancel' buttons
    When I deselect a category in Edit state
    And I click on 'Save' button
    Then I should see the Summary state of Categories