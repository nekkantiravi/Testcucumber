############################################
# Author: Discovery SNBC QE Team
# Date: 19th Sep 2017
############################################

Feature: To verify coremetric tags in category splash page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: CategorySplashPage - Verify page view coremetric tags fired when we select FOB menu in DOMESTIC mode
    Given I am on CatSplash Page for "<fob>" on "domestic" mode
    When I modify the url to get in to snbc experiment
    Then I should be on category splash page
    Examples:
      | fob        |
      | HOME       |
      | BED & BATH |
      | WOMEN      |
      | MEN        |
      | JUNIORS    |
      | KIDS       |
      | ACTIVE     |
      | BEAUTY     |
      | SHOES      |
      | HANDBAGS   |
      | JEWELRY    |
      | WATCHES    |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: CategorySplashPage - Verify page view coremetric tags fired when we select FOB menu in Registry mode
    Given I am on CatSplash Page for "<fob>" on "registry" mode
    When I modify the url to get in to snbc experiment
    Then I should be on category splash page
    Examples:
      | fob                   |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: CategorySplashPage - Verify page view coremetric tags fired when we select FOB menu in ISHIP mode
    Given I am on CatSplash Page for "<fob>" on "iship" mode
    When I modify the url to get in to snbc experiment
    Then I should be on category splash page
    Examples:
      | fob                    |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | ACTIVE                 |
      | SHOES                  |
      | HANDBAGS & ACCESSORIES |
      | JEWELRY                |
      | WATCHES                |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: Category Splash Page - Domestic - Verify link click tags for media in catsplash page
    Given I am on CategorySplashPage for "<category_id>" category id in Domestic mode
    Then I should see "<media>" in "<row_type>" on browse page
    And I verify "<media>" is clickable and navigating to respective media page
    Examples:
      | category_id | row_type | media                                                      |
      | 22672       | 101      | IMAGE_MAP                                                  |
      | 29391       | 101      | FLEXIBLE_POOL                                              |
      | 29391       | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: Category Splash Page - Registry - Verify link click tags for media in catsplash page
    Given I am on CategorySplashPage for "<category_id>" category id in Registry mode
    Then I should see "<media>" in "<row_type>" on browse page
    And I verify "<media>" is clickable and navigating to respective media page
    Examples:
      | category_id | row_type | media                                                      |
      | 7498        | 101      | IMAGE_MAP                                                  |
      | 7498        | 101      | FLEXIBLE_POOL                                              |
      | 7498        | 101      | SLIDESHOW                                                  |
      | 7498        | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: Category Splash Page - Iship - Verify link click tags for media in catsplash page
    Given I am on CategorySplashPage for "<category_id>" category id in Iship mode
    Then I should see "<media>" in "<row_type>" on browse page
    And I verify "<media>" is clickable and navigating to respective media page
    Examples:
      | category_id | row_type | media                                                      |
      | 7497        | 101      | IMAGE_MAP                                                  |
      | 7497        | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |