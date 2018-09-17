#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify each FOB Category Left Nav section

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for DESIGNERS in DOMESTIC mode
    Given I am on CatSplash Page for "DESIGNERS" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for HANDBAGS in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for JEWELRY & ACCESSORIES in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY & ACCESSORIES" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for THE REGISTRY in DOMESTIC mode
    Given I am on CatSplash Page for "THE REGISTRY" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for SALE in DOMESTIC mode
    Given I am on CatSplash Page for "SALE" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Left Nav section for EDITORIAL in DOMESTIC mode
    Given I am on CatSplash Page for "EDITORIAL" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  #Testlink-BLCOM-73856
  #Vone-RT-06299
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify the New URL Rewrite_structure for non registry URLs in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "WOMEN" category page
    And I navigate to "Jackets" from Left Nav links
    Then I verify the URL structure of the sub category page
    # Notes
    # Navigate to Women -> Denim
    # Verify URL adheres to the pattern as http://www.bloomingdales.com/shop/womens-apparel/denim?id=5545

  #Testlink-BLCOM-84121
  # Vone-RT-06300
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify the updated banner in Gift Cards splash page in DOMESTIC mode
    Given I am on CatSplash Page for "GIFTS" on "domestic" mode
    When I navigate to "GIFTS" category page
    Then I verify the updated banner in Gift Cards splash page
  Examples:
    |mode     |
    |DOMESTIC |
    |ISHIP    |
  #TBD - need the same scenarios for ISHIP & REGISTRY mode
  #TBD - add more scenarios for scope such as BOPS facets etc.

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in DOMESTIC mode
    Given I am on CatSplash Page for "<FOB>" on "domestic" mode
    #Then I verify Left Nav section
    Then I verify Left Nav section and links return 200 OK
  Examples:
    | FOB                   |
    | EDITORIAL             |
    | MEN                   |
    | KIDS                  |
    | JEWELRY & ACCESSORIES |
    | SHOES                 |
    | HANDBAGS              |
    | SALE                  |
    | HOME                  |
    | WOMEN                 |
    | GIFTS                 |
    | BEAUTY                |

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in ISHIP mode
    Given I am on CatSplash Page for "<FOB>" on "iship" mode
    Then I verify Left Nav section and links return 200 OK
  Examples:
    | FOB                   |
    | EDITORIAL             |
    | WOMEN                 |
    | SHOES                 |
    | HANDBAGS              |
    | JEWELRY & ACCESSORIES |
    | MEN                   |
    | KIDS                  |
    | HOME                  |

  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<FOB>" on "registry" mode
    Then I verify Left Nav section and links return 200 OK
  Examples:
    | FOB                   |
    | DINING & ENTERTAINING |
    | KITCHEN               |
    | BED & BATH            |
    | HOME DECOR            |
    | LUGGAGE               |
    | HOME CARE & TECH      |


  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in DOMESTIC mode
    Given I am on CatSplash Page for "<FOB>" on "domestic" mode
    Then I verify Left Nav section match with production
  Examples:
    | FOB                   |
    | MEN                   |
    | HOME                  |
    | WOMEN                 |

  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<FOB>" on "registry" mode
    Then I verify Left Nav section match with production
  Examples:
    | FOB                   |
    | KITCHEN               |
    | BED & BATH            |

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in ISHIP mode
    Given I am on CatSplash Page for "<FOB>" on "iship" mode
    Then I verify Left Nav section match with production
  Examples:
    | FOB                   |
    | WOMEN                 |
    | MEN                   |
    | HOME                  |

