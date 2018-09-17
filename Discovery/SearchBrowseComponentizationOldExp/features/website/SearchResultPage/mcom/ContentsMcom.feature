Feature: Contents verification on Search Landing Page

  @use_regression @release_15C @artifact_navapp @domain_discovery @priority_medium @use_regression_1 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Edit button is displayed on RVI Panel on search results page
    Given I am on SearchResultsPage for "Dresses" in Domestic mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I search for "jeans"
    And I navigate to bottom of page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario: SearchResultsPage - Domestic - Verify header is displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that logo is displayed and returns a "200" OK
    Then I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in SRP

  @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario: SearchResultsPage - Domestic - Verify footer is displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that the footer elements are displayed
  # Notes: Verifies that footer links are all present in SRP

  @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario: SearchResultsPage - Domestic - Verify FOBs in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    And I verify that fobs are displayed and return a 200 OK


  @use_bat @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Left Nav is displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that facets are listed on left nav
    Then I verify that search result page Facets displayed match with production

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_1 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Content in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in DOMESTIC mode
    Then I verify that Search Results contents are displayed
  #Notes:
  #Verify you see results that match the keyword
  #Verify all images and links all return 200 OK on http party
  #Verify leftnav is displayed, sort by and next pages are displayed


  @release_14I @artifact_navapp @domain_discovery @mode_domestic @priority_medium @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Search URL should not contain x and y coordinates while search is done using SEARCH button in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in DOMESTIC mode
    And I verify that the URL should not contain x and y coordinates


