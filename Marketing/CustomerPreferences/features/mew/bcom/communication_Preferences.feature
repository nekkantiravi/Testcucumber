# Author: Customer Preferences Project QE Team
# Date Created: 9/13/2017
# Version One:  BCOM Story B-87013

Feature: Verification of Communication Preferences

  @domain_Marketing @project_mew_Preferences @bcom @use_regression
  Scenario: Verify changing the email notification preference
    Given I visit the mobile web site as a registered user without add CC
    When I navigate to My Preferences page
    And I select Enabled text for Email preference on the page
    Then I should get navigated to a page having "Communication Preferences" as page title
    When I click on 'Edit' option of Email Preferences
    Then I should see the Email section in edit state on the page
    When I select 'Maybe later No thanks' radio option in the edit state of Email section
    And I click on 'Save' button in the edit state of Email Section
    Then I should see "Disabled" status for the Email Preference on the page

  @domain_Marketing @project_mew_Preferences @bcom @use_regression
  Scenario: Verify the text notification preference
    Given I visit the mobile web site as a registered user without add CC
    When I navigate to My Preferences page
    And I select Enabled text for Text preference on the page
    Then I should get navigated to a page having "Communication Preferences" as page title
    When I click on 'Edit' option of Text Preference
    Then I should see the Text section in edit state on the page
    When I click on 'Cancel' button in the edit state of Text Section
    Then I should see "Disabled" status for the Text Preference on the page