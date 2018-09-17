Feature: Verify Thumbnail Grid functionality in Category Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario:CategoryBrowsePage - Domestic - Verify QV appears on products
    Given I am on CategoryBrowsePage for "25122" category id in Domestic mode
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
  # Notes: Verifies that QuickView appears on product

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario:CategoryBrowsePage - Domestic - Verify products are displayed with pricing
    Given I am on CategoryBrowsePage for "25122" category id in Domestic mode
    And I verify that products are displayed with price
  # Notes: Verifies that prices are displayed for the products displayed.

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the product with color swatches has the thumbnail with all the desired attributes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 25122       |
      | Registry      | 31760       |
      | Iship         | 25122       |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the product with multi tier has the thumbnail with all the desired attributes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select a product having multi tier pricing
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 25122       |
      | Registry      | 31760       |
      | Iship         | 25122       |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the product with badge has the thumbnail with all the desired attributes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select a product having badge text
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 25122       |
      | Registry      | 31760       |
      | Iship         | 25122       |
