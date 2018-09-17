Feature: Verify Thumbnail Grid functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario:SearchResultsPage - Domestic - Verify QV appears on products
    Given I am on SearchResultsPage for "shoes" in Domestic mode
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
  # Notes: Verifies that QuickView appears on product

  @use_bat @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario:SearchResultsPage - Domestic - Verify products are displayed with pricing
    Given I am on SearchResultsPage for "shoes" in Domestic mode
    And I verify that products are displayed with price
  # Notes: Verifies that prices are displayed for the products displayed.

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the product with color swatches has the thumbnail with all the desired attributes
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the product with multi tier has the thumbnail with all the desired attributes
    Given I am on SearchResultsPage for "slim leg" in <Site_Mode> mode
    When I select a product having multi tier pricing
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the product with badge has the thumbnail with all the desired attributes
    Given I am on SearchResultsPage for "slim leg" in <Site_Mode> mode
    When I select a product having badge text
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |