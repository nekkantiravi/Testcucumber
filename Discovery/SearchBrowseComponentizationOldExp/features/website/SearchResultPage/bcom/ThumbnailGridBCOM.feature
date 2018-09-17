# Author: Discovery QE Team

Feature: Verify Thumbnail Grid in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: SearchResultsPage - Domestic - Verify QV appears on products in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
  # Notes: Verifies that QuickView appears on product

  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: SearchResultsPage - Domestic - Verify color swatches appear in DOMESTIC mode
    Given I am on SearchResultsPage for "women's dresses" in DOMESTIC mode
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
  # Notes: Verifies that color swatch selection is working

  @use_bat @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: SearchResultsPage - Domestic - Verify pricing is displayed on products in DOMESTIC mode
    Given I am on SearchResultsPage for "red hat" in DOMESTIC mode
    And I verify that products are displayed with price
  # Notes: Verifies that prices are displayed for the products displayed.


  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom @test
  Scenario: SearchResultsPage - Domestic - Verify FOBs in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    And I verify that fobs are displayed and return a 200 OK
    # Notes: Verifies that FOBs are displayed in SRP and returns 200 OK on clicking it in Domestic mode

  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: SearchResultsPage - Domestic - Verify products are displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that all of the products are displayed
  # Notes: Verifies that products are displayed

  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom @test
  Scenario: SearchResultsPage - Iship - Verify FOBs in Iship mode
    Given I am on SearchResultsPage for "shoes" in Iship mode
    And I verify that fobs are displayed and return a 200 OK
    # Notes: Verifies that FOBs are displayed in SRP and returns 200 OK on clicking it in ISHIP mode