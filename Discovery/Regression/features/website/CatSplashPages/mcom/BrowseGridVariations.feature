# Author: BGV QE Team
# Date Created :
# Date Modified:

Feature: C2 :: P2 :: MCOM :: Browse Grid Variations automation scenarios for CatSplash Page

##############################################################################################################
# Story B-13911: C2 P2:: Browse :: CAP:: Back to top [Cat Splash Pages]
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-13911
##############################################################################################################

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @priority_medium @mode_registry @mode_iship @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify user should not see back to top button immediately when navigated to page in DOMESTIC & REGISTRY & ISHIP Mode
    Given I am on CatSplash Page for "<category>" on "<mode>" mode
    Then I verify that back to top button is not displayed on page
    Examples:
      | category | mode     |
      | HOME     | domestic |
      | LUGGAGE  | registry |
      | MEN      | iship    |

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @priority_medium @mode_registry @mode_iship @mode_domestic
  Scenario Outline: CategorySplashPage - Verify back to top sits above footer when user scrolls to bottom of the page in DOMESTIC & REGISTRY & ISHIP Mode
    Given I am on CatSplash Page for "<category>" on "<mode>" mode
    When I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      | category   | mode     |
      | SHOES      | domestic |
      | HOME DECOR | registry |
      | KIDS       | iship    |

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @priority_medium @mode_registry @mode_iship @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify page is navigated to top when user clicks on back to top button in DOMESTIC & REGISTRY & ISHIP Mode
    Given I am on CatSplash Page for "<category>" on "<mode>" mode
    When I navigate to bottom of page
    And I select back to top button
    Then I verify that Cat Splash page navigated to top of the page
    Examples:
      | category | mode     |
      | BEAUTY   | domestic |
      | LUGGAGE  | registry |
      | SHOES    | iship    |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry @mode_iship @mode_domestic @migrated_to_sdt
  Scenario Outline: CategorySplashPage - Verify while scrolling, view of back to top button should be at bottom of page and button fades away when scroll bar has reached top of scroll bar track in DOMESTIC & REGISTRY & ISHIP Mode
    Given I am on CatSplash Page for "<category>" on "<mode>" mode
    When I scroll "down" with "1000" pixel size on the page
    Then I verify that back to top button is displayed on page
    When I scroll "up" with "-1100" pixel size on the page
    Then I verify that back to top button is not displayed on page
    Examples:
      | category              | mode     |
      | JUNIORS               | domestic |
      | CLEANING & ORGANIZING | registry |
      | MEN                   | iship    |

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @priority_medium @mode_registry @mode_iship @mode_domestic
  Scenario Outline: CategorySplashPage - Verify back to top button appears when bottom edge of sub nav passes viewable screen in DOMESTIC & REGISTRY & ISHIP Mode
    Given I am on CatSplash Page for "<category>" on "<mode>" mode
    When I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      | category | mode     |
      | HANDBAGS | domestic |
      | LUGGAGE  | registry |
      | SHOES    | iship    |