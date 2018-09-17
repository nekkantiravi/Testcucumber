Feature: Verification of banner images on Catsplash pages

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify all categories navigation to cat splash pages from global navigation in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify the page navigation to cat splash pages for "<category>"
    Examples:
      | category              |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |
      | GIFTS                 |
      | EDITORIAL             |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify all categories navigation to cat splash pages from global navigation in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify the page navigation to cat splash pages for "<category>"
    Examples:
      | category              |
      | EDITORIAL             |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - Verify that the subcategories navigate to available pages in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following "<category>" from global navigation
    And I verify all media banners are displaying
    And I verify all displayed links navigating to respective page on "<category>" cat splash page
    Examples:
      | category  |
      | EDITORIAL |
      | Gifts     |

  @domain_mew_discovery @use_mew_regression
  Scenario: CatSplashPage - Verify that the subcategories navigate to available pages in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify following "EDITORIAL" from global navigation
    And I verify all media banners are displaying
    And I verify all displayed links navigating to respective page on "EDITORIAL" cat splash page
