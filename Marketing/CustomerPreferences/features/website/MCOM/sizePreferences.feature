# Author: Customer Preferences Project QE Team
# Date Created: 07/14/2017
# Version One:  MCOM Story B-78527, B-78526 & B-84861

Feature: Verification of Sizes Preferences pages

  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify user is able to save the Sizes in Add State
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    And I should see "Sizes" tab heading
    When I click on 'Sizes' tab option
    Then I should see the Sizes sub heading "Add Sizes"
    And I should see the "Shopping Preferences" page title
    And I should see the 'Save' button in disabled state
    And I should see the Sizes caption text "Tell us your preferred sizes."
    And I should see the Sizes dropdown field
    When I click on accordion
    Then I should see that the accordion as expanded
    When I select any Size tile
    And I click on 'Save' button
    Then I should see the Summary state of Sizes

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify user is able to view the Sizes in summary state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    When I click on 'Sizes' tab option
    Then I should see the Sizes sub heading "Add Sizes"
    When I click on accordion
    And I select any Size tile
    And I click on 'Save' button
    Then I should see the Summary state of Sizes
    And I should see the "Shopping Preferences" page title
    And I should see the sizes in Summary state
    When I click on 'Edit' option
    Then I should see the Edit state of Sizes


  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify user is able to view the Sizes in Edit state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    Then I should see the Add state of Categories
    When I click on 'Sizes' tab option
    Then I should see the Sizes sub heading "Add Sizes"
    When I click on accordion
    And I select any Size tile
    And I click on 'Save' button
    Then I should see the Summary state of Sizes
    When I click on 'Edit' option
    Then I should see the Edit state of Sizes
    And I should see the 'Save' button in disabled state
    And I should see the 'Cancel' button
    And I should see the Edit Sizes sub heading "Edit Sizes"
    And I should see the dropdown field in edit sizes page
    When I select any Size tile
    And I click on 'Save' button
    Then I should see the Add state of Sizes

  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify the sizes display for all categories on size preferences page
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    Then I should see the Add state of Categories
    When I click on 'Sizes' tab option
    Then I should see the sizes categories in drop down
      |Women|
      |Men  |
      |Kids |
    When I select "Kids" category in the dropdown
    Then I should see "Baby" sizes categories loaded on selection
    When I select "Men" category in the dropdown
    Then I should see "Garment Size" sizes categories loaded on selection
    When I select "Women" category in the dropdown
    Then I should see "Maternity" sizes categories loaded on selection
