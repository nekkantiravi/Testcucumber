Feature: Cat Splash Pages

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - Verify that the subcategories navigate to available pages in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify the navigation to "<category_name>" cat splash page from homepage
    And I verify media banners are displaying
    And I verify all links navigating to respective page on "<category_name>" cat splash page
    Examples:
      | category_name          |
      | Women                  |
      | Men                    |
      | Kids & Baby            |
      | Shoes                  |
      | For The Home           |
      | Beauty                 |
      | Handbags & Sunglasses  |
      | Jewelry & Watches      |
      | Lingerie & Shapewear   |
      | Plus & Petite          |
      | Juniors & Guys         |
      | Furniture & Mattresses |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - Verify available subcategories are displaying for all Cat splash icons in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I verify child categories of below parent "<category_name>" from backend service call
    Examples:
      | category_name          |
      | Women                  |
      | Men                    |
      | Kids & Baby            |
      | Shoes                  |
      | For The Home           |
      | Beauty                 |
      | Handbags & Sunglasses  |
      | Jewelry & Watches      |
      | Juniors & Guys         |
      | Furniture & Mattresses |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - Verify Cat splash pages appears in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify the navigation to "<category_name>" cat splash page from homepage
    And I verify media banners are displaying
    And I verify all links navigating to respective page on "<category_name>" cat splash page
    Examples:
      | category_name         |
      | Women                 |
      | Men                   |
      | Kids & Baby           |
      | Shoes                 |
      | For The Home          |
      | Handbags & Sunglasses |
      | Jewelry & Watches     |
      | Plus & Petite         |
      | Juniors & Guys        |

  @domain_mew_discovery @discovery_mew_missing_scope
  Scenario Outline: CatSplashPage - Verify available subcategories are displaying for all Cat splash icons in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I verify child categories of below parent "<category_name>" from backend service call
    Examples:
      | category_name         |
      | Women                 |
      | Men                   |
      | Kids & Baby           |
      | Shoes                 |
      | For The Home          |
      | Handbags & Sunglasses |
      | Jewelry & Watches     |
      | Plus & Petite         |
      | Juniors & Guys        |

  @domain_mew_discovery @discovery_mew_missing_scope
  Scenario Outline: CatSplashPage - Verify Cat splash pages appears in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the navigation to "<category_name>" cat splash page from registry homepage
    And I verify media banners are displaying
    And I verify all links navigating to respective page on "<category_name>" cat splash page
    Examples:
      | category_name         |
      | Dining & Entertaining |
      | Kitchen               |
      | Bed & Bath            |
      | Luggage & Backpacks   |

  @domain_mew_discovery @discovery_mew_missing_scope
  Scenario Outline: CatSplashPage - Verify available subcategories are displaying for all Cat splash icons in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I verify child categories of below parent "<category_name>" from backend service call in registry mode
    Examples:
      | category_name         |
      | Dining & Entertaining |
      | Kitchen               |
      | Bed & Bath            |
      | Luggage & Backpacks   |