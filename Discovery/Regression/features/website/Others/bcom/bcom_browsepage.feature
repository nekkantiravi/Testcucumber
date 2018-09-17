Feature: Verify Sort By on Category Browse Pages

  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery
  Scenario: BrowsePage - Verify Sort by options function in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I "should" see the following options in the Sort By list
      |New Arrivals        |
      |Best Sellers        |
      |Customer Top Rated  |
      |Price (high-low)    |
      |Price (low-high)    |
      |Our Top Picks       |
    And I verify display order of sort by drop down
    Then I verify each sort by option on page
      |Best Sellers        |
      |Customer Top Rated  |
      |Price (high-low)    |
      |Price (low-high)    |
      |Our Top Picks       |
      |New Arrivals        |