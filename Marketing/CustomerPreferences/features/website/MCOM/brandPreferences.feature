# Author: Customer Preferences Project QE Team
# Date Created: 1/09/2017
# Version One:  MCOM Story B-87469

Feature: Verification of Brand preferences pages

  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify user is able to save the brands in Add State
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    And I should see "Brands" tab heading in the page
    When I click on 'Brands' tab option
    Then I should see the "Add Brands" Brands sub heading
    And I should see the "Shopping Preferences" page title
    And I should see the 'Save' button below the brands in disabled state
    And I should see the "What do you usually shop for? Tell us your favorite categories, brands & sizes" text on brands page
    And I should see the "Choose the brands you usually shop for:" Brands caption text
    And I should see the Brands dropdown field
    When I select any Brands tile
    And I click on 'Save' button
    Then I should see the Summary state of Brands

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify user is able to view the brands in summary state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    When I click on 'Brands' tab option
    Then I should see the "Add Brands" Brands sub heading
    When I select any Brands tile
    And I click on 'Save' button
    Then I should see the Summary state of Brands
    And I should see the "Shopping Preferences" page title
    And I should see the text "What do you usually shop for? Tell us your favorite categories, brands & sizes" in Edit State
    And I should see the brands in Summary state
    When I click on 'Edit' option in summary state of brands
    Then I should see the Edit state of Brands

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify user is able to view the Brands in Edit state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    When I click on 'Brands' tab option
    And I select any Brands tile
    And I click on 'Save' button
    Then I should see the Summary state of Brands
    When I click on 'Edit' option in summary state of brands
    Then I should see the Edit state of Brands
    And I should see the "Shopping Preferences" page title
    And I should see the 'Save' button in disabled state
    And I should see the 'Cancel' button
    And I should see the "What do you usually shop for? Tell us your favorite categories, brands & sizes" text on brands page
    And I should see the "Edit Brands" sub heading on Edit Brands
    And I should see the "Update the brands you usually shop for" edit brands caption text
    And I should see the Brands dropdown field
    When I deselect Brands tile
    And I click on 'Save' button
    Then I should see the Add state of Brands