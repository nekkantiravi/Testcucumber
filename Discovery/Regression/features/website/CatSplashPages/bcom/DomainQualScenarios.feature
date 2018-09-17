# Author: DISCOVERY QE
# Date Created: 06/30/2015

Feature: Verify CatSplash page functionality

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage -  Verify header is displayed in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify that logo is displayed and returns a 200 OK
    And I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in Sub Splash Page

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage -  Verify footer is displayed in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify the footer links in "Domestic" Mode
  # Notes: Verifies that footer links are all present in Sub Splash Page

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage -  WOMEN - should be displayed with basic attributes in DOMESTIC Mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify the basic attributes of cat splash page
    #Notes : Verifies the top navigation,left navigation,footer and SEO tags in the Women category in domestic mode

