# Author: Customer Preferences Project QE Team
# Date Created: 8/30/2017
# Version One:  MCOM Story B-71235

Feature: Verification of Store preference page

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario: Verify the preferred store in all states
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page
    When I navigate to Preferred store page from the landing page
    And I enter the ZIP Code into the field on the Preferred store page
    And  I click the 'Search' button on the page
    And I select any store from the search results on the page
    Then I should navigate to the summary state of Preferred store page
    When I click on the Edit option on the summary state of Preferred store page
    Then I should navigate the edit state of the Preferred store page
