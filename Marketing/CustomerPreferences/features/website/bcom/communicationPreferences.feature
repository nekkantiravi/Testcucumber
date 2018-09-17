# Author: Customer Preferences Project QE Team
# Date Created: 9/13/2017
# Version One:  BCOM Story B-87013

Feature: Verification of Communication Preferences

  @domain_Marketing @project_Preferences @bcom
  Scenario: Verify the display of email notification preferences pages
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see "My Preferences" page title
    When I select Enabled text for Email preference
    Then I should get navigated to a page having "Communication Preferences" page title
    And I should see "Email" section
    And I should see an Email address
    And I should see "Enabled" status for the Email Preference
    When I click on 'Edit' option for Email
    Then I should see the Email section in edit state
    And I should see the two radial buttons
      | Yes, send me emails    |
      | Maybe later. No thanks |
    And I should see the 'Save' and 'Cancel' button in Email section

  @domain_Marketing @project_Preferences @bcom @use_regression @Marketing_CBT
  Scenario: Verify changing the email notification preference
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    And I select Enabled text for Email preference
    Then I should get navigated to a page having "Communication Preferences" page title
    When I click on 'Edit' option for Email
    Then I should see the Email section in edit state
    When I select 'Maybe later No thanks' radio option in edit state
    And I click on 'Save' button in edit state of Email Preference
    Then I should see "Disabled" status for the Email Preference

  @domain_Marketing @project_Preferences @bcom
  Scenario: Verify the display of text notification preferences pages
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see "My Preferences" page title
    When I select Enabled text for Text preference
    Then I should get navigated to a page having "Communication Preferences" page title
    And I should see "Text" section in edit state
    When I click on 'Edit' option for Text Preference
    Then I should see the Text section in edit state
    And I should see the two radial buttons in edit state of text preference
      | Yes, text me    |
      | I'd prefer not to receive texts at this time |
    And I should see the 'Save' and 'Cancel' button in Text section

  @domain_Marketing @project_Preferences @bcom @use_regression
  Scenario: Verify changing the text notification preference
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    And I select Enabled text for Text preference
    Then I should get navigated to a page having "Communication Preferences" page title
    When I click on 'Edit' option for Text Preference
    Then I should see the Text section in edit state
    When I select 'I would prefer not' radio option in text edit state
    And I click on 'Save' button in edit state of Text Preference
    Then I should see "Disabled" status for the Text Preference