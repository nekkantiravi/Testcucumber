# Author: Discovery QE Team

Feature: Verify Thumbnail Grid in Category Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify QV appears on products in DOMESTIC mode
    Given I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
  # Notes: Verifies that QuickView appears on product

  @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify color swatches appear in DOMESTIC mode
    Given I am on CategoryBrowsePage for "1005370" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
  # Notes: Verifies that color swatch selection is working

  @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify pricing is displayed on products in DOMESTIC mode
    Given I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    And I verify that products are displayed with price
  # Notes: Verifies that prices are displayed for the products displayed.

  @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify FOBs in DOMESTIC mode
    Given I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    And I verify that fobs are displayed and return a 200 OK
    # Notes: Verifies that FOBs are displayed in SRP and returns 200 OK on clicking it in Domestic mode

  @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Iship - Verify FOBs in Iship mode
    Given I am on CategoryBrowsePage for "1000238" category id in Iship mode
    And I clear existing class variables to avoid data issues
    And I verify that fobs are displayed and return a 200 OK
    # Notes: Verifies that FOBs are displayed in SRP and returns 200 OK on clicking it in ISHIP mode

  @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify products are displayed in DOMESTIC mode
    Given I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    Then I verify that all products are displayed
  # Notes: Verifies that products are displayed