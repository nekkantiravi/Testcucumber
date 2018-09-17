# Author: DISCOVERY QE
# Date Created: 06/10/2015


Feature: Verify CategorySplashPages - Left Navigation

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in DOMESTIC mode
    Given I am on CatSplash Page for "<FOB>" on "domestic" mode
    Then I verify Left Nav section for "<FOB>"
    Examples:
      | FOB                    |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | SHOES                  |
      | HANDBAGS               |
      | JEWELRY                |
      | WATCHES                |

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in ISHIP mode
    Given I am on CatSplash Page for "<FOB>" on "iship" mode
    Then I verify Left Nav section for "<FOB>"
    Examples:
      | FOB                    |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | SHOES                  |
      | HANDBAGS & ACCESSORIES |
      | JEWELRY                |
      | WATCHES                |

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS are displayed for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<FOB>" on "registry" mode
    Then I verify Left Nav section for "<FOB>"
    Examples:
      | FOB                   |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS against production in DOMESTIC mode
    Given I am on CatSplash Page for "<FOB>" on "domestic" mode
    Then I verify Left Nav section match with production
    Examples:
      | FOB   |
      | HOME  |
      | WOMEN |
      | MEN   |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS against production in REGISTRY mode
    Given I am on CatSplash Page for "<FOB>" on "registry" mode
    Then I verify Left Nav section match with production
    Examples:
      | FOB        |
      | KITCHEN    |
      | BED & BATH |


  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: CategorySplashPage - Verify LEFT NAV LINKS against production in ISHIP mode
    Given I am on CatSplash Page for "<FOB>" on "iship" mode
    Then I verify Left Nav section match with production
    Examples:
      | FOB   |
      | HOME  |
      | WOMEN |
      | MEN   |