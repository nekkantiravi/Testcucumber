Feature: Verify category pages

  @preview_desktop
  Scenario: CategorySplash: Verify Left navigation Red link for all categories for Bcom
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify left nav red link is clickable for bcom
      | WOMEN    |
      | MEN      |
      | HOME     |
      #|GIFTS|
      | SHOES    |
      | HANDBAGS |
      | BEAUTY   |
      | KIDS     |
      #|JEWELRY & ACCESSORIES|
      | SALE     |

  @preview_desktop
  Scenario Outline: CategorySplash Internal lna: Verify Internal lna link for plus and furniture subcategory category
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "<category>" category page and I navigate to essentials "<subcategory>" page and verified the red lna for following categories
    Examples:
      | category |  | subcategory |
      | WOMEN    |  | plus        |
      | HOME     |  | Furniture   |


# GIFTS>> There is no featured category associated with GIFTS FOB
  @preview_desktop
  Scenario Outline: FeaturedCategories: Verify all featured categories for bcom fob
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "<Cat_name>" category page
    And I navigate to all features categories link and verified
    Examples:
      | Cat_name              |
      | WOMEN                 |
      | MEN                   |
      | HOME                  |
      #|GIFTS|
      | SHOES                 |
      | HANDBAGS              |
      | BEAUTY                |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |
      | SALE                  |


  @preview_desktop
  Scenario Outline: Internal featured categories: Verify Internal featured categories for following sub categories
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "<category>" category page and I navigate to essentials "<subcategory>" page and verified the internal featured categories for following categories
    Examples:
      | category |  | subcategory |
      | WOMEN    |  | plus        |
      | HOME     |  | Furniture   |

  @preview_desktop
  Scenario Outline: Verify Main ad is displaying for all categories in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<category>" category page
    Then I verify main ad is displayed on "<category>" category page

    Examples:
      | category              |
      | WOMEN                 |
      | MEN                   |
      | HOME                  |
      | GIFTS                 |
      | SHOES                 |
      | HANDBAGS              |
      | BEAUTY                |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |
      | SALE                  |

  @preview_desktop
  Scenario Outline: CategorySplashPage - Verify Sub Ads validation for each categories DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<category>" category page
    Then I verify that Sub Ads of "<category>" should be displayed on cat splash page
    Examples:
      |category|
      |WOMEN|
      |MEN|
      |HOME|
      |SHOES|
      |HANDBAGS|
      |BEAUTY|
      |KIDS|
      |JEWELRY & ACCESSORIES|

