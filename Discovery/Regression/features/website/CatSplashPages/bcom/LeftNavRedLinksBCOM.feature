# Author: DISCOVERY QE
# Date Created: 06/10/2015


Feature: Verify CategorySplashPage - Left Nav Red Links

  # Note: Omitting some sub-categories as red links are not present for those categories

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - DESIGNER in DOMESTIC mode
    Given I am on CatSplash Page for "DESIGNERS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  # Note : Currently data is not available in qa and trunk environment hence added wip tag
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - WHAT'S NE in DOMESTIC mode
    Given I am on CatSplash Page for "EDITORIAL" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation-
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - WOME in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - ME in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - SHOE in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - KID in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  # Note : Currently data is not available in qa and trunk environment hence added wip tag
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - HANDBAG in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - JEWELRY & ACCESSORIE in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY & ACCESSORIES" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly


  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly


  # Note : Currently data is not available in qa and trunk environment hence added wip tag
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - GIFTS in DOMESTIC mode
    Given I am on CatSplash Page for "GIFTS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red Links - SALE in DOMESTIC mode
    Given I am on CatSplash Page for "SALE" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top/bottom of LNA & should navigate correctly

  #TestLink - BLCOM-65273, #TestLink - BLCOM-84159
  # Vone-RT-06496
  #Vone - RT-06475
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: Category Splash page - Verify Red links in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify left nav red link is clikable for bcom
    | EDITORIAL             |
    | WOMEN                 |
    | SHOES                 |
    | HANDBAGS              |
    | JEWELRY & ACCESSORIES |
    | BEAUTY                |
    | MEN                   |
    | KIDS                  |
    | HOME                  |
    | GIFTS                 |
    | SALE                  |
    | DESIGNER              |
    # Notes
    # Click on Special offers, sale and clearence etc.. in red and verify the appropriate page loads

