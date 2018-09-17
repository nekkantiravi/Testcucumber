# Author: Customer Preferences Project QE Team
# Date Created: 9/13/2017
# Version One:  BCOM Story B-89608

Feature: Verification of Store preference page

  @domain_Marketing @project_Preferences @bcom
  Scenario: Verify the add state of preferred store page
    Given I visit the web site as a registered user
    And I navigate to Preferences page directly from My Account page
    When I click on 'Add a Store' option of Preferred Store
    Then I should be navigated to the Preferred store page
    And I should see the ZIP code text box field
    And I should see 'Search' button

  @domain_Marketing @project_Preferences @bcom
  Scenario: Verify the summary state of preferred store page
    Given I visit the web site as a registered user
    And I navigate to Preferences page directly from My Account page
    When I click on 'Add a Store' option of Preferred Store
    Then I should be navigated to the Preferred store page
    When I enter the ZIP Code into the text field
    And I click on 'Search' button
    And I select any store from the search results of Preferred store
    Then I should be navigated to the summary state of Preferred store
    And I should see the 'Change Store' option on the summary state of Preferred store


  @domain_Marketing @project_Preferences @bcom @use_regression
  Scenario: Verify the edit state of preferred store page
    Given I visit the web site as a registered user
    And I navigate to Preferences page directly from My Account page
    When I click on 'Add a Store' option of Preferred Store
    Then I should be navigated to the Preferred store page
    When I enter the ZIP Code into the text field
    And I click on 'Search' button
    And I select any store from the search results of Preferred store
    Then I should be navigated to the summary state of Preferred store
    When I click on 'Change Store' option in the summary state
    Then I should navigate to edit state of Preferred store page
    And I should see the 'Selected' store
    And I should see the ZIP code text box field
    And I should see 'Search' button