# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify SEO tags on CatSplash Page - DOMESTIC, ISHIP and REGISTRY modes

  # @SEO_4431
  @domain_discovery @feature_catsplash @use_regression @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: CategorySplashPage - Domestic - Verify canonical tag
    Given I am on CatSplash Page for "<category>" on "domestic" mode
    Then I verify the "canonical" tag in HTML view source code
    Examples:
      | category |
      | WOMEN    |
      | MEN      |

  @domain_discovery @feature_catsplash @use_regression @mode_registry @priority_high @migrated_to_sdt @discovery_daily_run
  Scenario Outline: CategorySplashPage - Registry - Verify canonical tag
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify the "canonical" tag in HTML view source code
    Examples:
      | category |
      | DINING   |
      | KITCHEN  |
