# Author: Customer Preferences Project QE Team
# Date Created: 5/19/2017
# Version One:  MCOM Story B-74847

Feature: Verification of notification preference page

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario: Verify the email notification preferences
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page
    When I navigate to Notifications preferences sub page from landing page
    And I click on 'EDIT' link next to email icon on the page
    And I select 'Sure, but I only want the highlights' radio option in edit state
    And I click on 'Save' button in edit state of Email
    Then I should see the "Enabled" text in the summary state of Email

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario:  Verify the text notification preferences
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page
    When I navigate to Notifications preferences sub page from landing page
    And I click on edit link next to text preference on the page
    Then I should see the Text Preference section in Edit state
