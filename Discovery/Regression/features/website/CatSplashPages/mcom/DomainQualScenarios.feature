# Author: DISCOVERY QE
# Date Created: 06/30/2015

Feature: Verify CatSplash page functionality

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @priority_high @migrated_to_sdt
  Scenario: CategorySplashPage - Verify header is displayed in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify that logo is displayed and returns a 200 OK
    Then I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in CatSplash Page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify footer is displayed in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify the footer links in "domestic" Mode
  # Notes: Verifies that footer links are all present in CatSplash Page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Domestic - Verify basic attributes of page - WOMEN
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify the basic attributes of cat splash page
    #Notes : Verifies the top navigation,left navigation,footer and SEO tags in the Women category in domestic mode

