# Author: Customer Preferences Project QE Team
# Date Created: 4/10/2017
# Version One:  MCOM Story B-74847

Feature: Customer Preference BAT

  @domain_Marketing @project_mew_Preferences @mcom @use_regression
  Scenario: Verify the Preferences card in My Account page
    Given I visit the mobile web site as a registered user without add CC
    When I click on View Preference option in my account page
    Then I should navigate to Preferences landing mobile page