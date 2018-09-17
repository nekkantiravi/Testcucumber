Feature: Verify Search Box functionality on SLP in DOMESTIC, ISHIP and REGISTRY mode

  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery
  Scenario: SearchResultsPage - Domestic - Verify the keyword iship in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search for "iship"
    Then I verify that "zero search result" page is displayed