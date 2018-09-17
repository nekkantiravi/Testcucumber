#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Flyouts on Browse Pages in DOMESTIC, ISHIP and REGISTRY mode


  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high @akamai_waf
  Scenario Outline: CategorySplashPage - Verify FLYOUTS menu is displayed for all FOBs in DOMESTIC mode
    Given I am on CatSplash Page for "<FOB>" on "domestic" mode
    And I mouse over on the "<FOB>" fob
    Then I should see flyout menu
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
    | GIFTS                  |
    | BEAUTY                |

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify FLYOUTS menu is displayed for all FOBs in ISHIP mode
    Given I am on CatSplash Page for "<FOB>" on "iship" mode
    And I mouse over on the "<FOB>" fob
    Then I should see flyout menu
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

  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify FLYOUTS menu is displayed for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<FOB>" on "registry" mode
    And I mouse over on the "<FOB>" fob
    Then I should see flyout menu
  Examples:
    | FOB                   |
    | DINING & ENTERTAINING |
    | KITCHEN               |
    | BED & BATH            |
    | HOME DECOR            |
    | LUGGAGE               |
    | HOME CARE & TECH      |



