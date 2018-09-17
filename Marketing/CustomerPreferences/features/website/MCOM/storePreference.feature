# Author: Customer Preferences Project QE Team
# Date Created: 8/30/2017
# Version One:  MCOM Story B-71235

Feature: Verification of Store preference page

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify the add state of preferred store page
    Given I visit the web site as a registered user
    And I navigate to Preferences page directly from My Account page
    When I click on the Preferred store card
    Then I should get navigated to Preferred store page
    And I should see the text "Enter your ZIP Code below to choose your preferred Macy's location." below the heading
    And I should see text box field of ZIP Code
    And I should see the Search icon option

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify the summary state of preferred store page
    Given I visit the web site as a registered user
    And I navigate to Preferences page directly from My Account page
    When I click on the Preferred store card
    Then I should get navigated to Preferred store page
    When I select any store from the search results
    Then I should navigate to the summary state of Preferred store
    And I should see the Edit option on the page


  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify the edit state of preferred store page
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    When I click on the Preferred store card
    Then I should get navigated to Preferred store page
    And I enter the ZIP Code into the field
    And  I click the 'Search' button
    When I select any store from the search results
    Then I should navigate to the summary state of Preferred store
    When I click on the Edit option on the page
    Then I should navigate to edit state of Preferred store
    And I should see the Cancel button on the page
    And I should see the selected store button in disabled state



