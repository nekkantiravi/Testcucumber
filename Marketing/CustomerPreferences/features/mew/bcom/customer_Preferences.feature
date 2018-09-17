# Author: Customer Preferences Project QE Team
# Date Created: Oct 20, 2017
# Version One:  BCOM Story B-69744

Feature: Customer Preferences BAT

  @domain_Marketing @project_mew_Preferences @bcom @use_regression
  Scenario: Validate the display on preferences landing page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate to My Preferences page
    Then I should see "My Preferences" page title on the page
    And I should see "Communication Preferences" card on the page
    And I should see the "Preferred Store" card on the page

