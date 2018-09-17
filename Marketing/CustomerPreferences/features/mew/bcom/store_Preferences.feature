# Author: Customer Preferences Project QE Team
# Date Created: 9/13/2017
# Version One:  BCOM Story B-89608

Feature: Verification of Store preference page

  @domain_Marketing @project_mew_Preferences @bcom @use_regression
  Scenario: Verify the edit state of preferred store page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate to My Preferences page
    And I click on 'Add a Store' option in the Preferred Store section
    Then I should be navigated to the mobile view of Preferred Store page
    When I enter the ZIP Code into the text field on the page
    And I click on 'Search' button on the page
    And I select any store from the displayed search results
    Then I should be navigated to the summary page of Preferred store
    When I click on 'Change Store' option in the summary page
    Then I should navigate to the edit state of Preferred store page
