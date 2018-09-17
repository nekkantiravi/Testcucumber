# Author: Discovery Regression QE Team
# Created Date: 10/10/2017

Feature: Verify Sub Splash Page functionality

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - Verify header is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" browse page under "BEAUTY"
    Then I verify that logo is displayed and returns a 200 OK
    And  I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in Sub Splash Page

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - Verify footer is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to the "CHANEL" browse page under "BEAUTY"
    Then I verify the footer links in "Domestic" Mode
  # Notes: Verifies that footer links are all present in Sub Splash Page

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - Verify COACH HANDBAGS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify the basic attributes of COACH Brand browse Page
   # Notes: Verifies the basic attributes in the Coach Handbag page



