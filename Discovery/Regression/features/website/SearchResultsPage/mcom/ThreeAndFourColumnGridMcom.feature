Feature: Verify Thumbnail Grid functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario:SearchResultsPage - Domestic - Verify QV appears on products
    Given I am on SearchResultsPage for "shoes" in Domestic mode
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
  # Notes: Verifies that QuickView appears on product

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario:SearchResultsPage - Domestic - Verify products are displayed with pricing
    Given I am on SearchResultsPage for "shoes" in Domestic mode
    And I verify that products are displayed with price
  # Notes: Verifies that prices are displayed for the products displayed.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the product with color swatches has the thumbnail with all the desired attributes
    Given I am on SearchResultsPage for "<keyword>" in <Site_Mode> mode
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | Site_Mode | keyword     |
      | Domestic  | red bedroom |
      | Registry  | red bedroom |
      | Iship     | slip leg    |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the product with multi tier has the thumbnail with all the desired attributes
    Given I am on SearchResultsPage for "<keyword>" in <Site_Mode> mode
    When I select a product having multi tier pricing
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | Site_Mode | keyword     |
      | Domestic  | red bedroom |
      | Registry  | red bedroom |
      | Iship     | slip leg    |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the product with badge has the thumbnail with all the desired attributes
    Given I am on SearchResultsPage for "<keyword>" in <Site_Mode> mode
    When I select a product having badge text
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | Site_Mode | keyword |
      | Domestic  | spoons  |
      | Registry  | spoons  |
      | Iship     | spoons  |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the PROMO text and promo popup on thumbnail
    Given I am on SearchResultsPage for "spoons" in <Site_Mode> mode
    When I select a product having badge text
    Then I verify that promo text and promo popup is displayed under product thumbnail
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |