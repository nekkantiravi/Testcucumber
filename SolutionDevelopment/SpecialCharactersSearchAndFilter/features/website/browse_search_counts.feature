Feature: Verify browse and search product count for corresponding categories

  @browse_search_counts
  Scenario: Verify browse and search product count for corresponding categories
    Given I connect to MSP and FCC
    And I verify browse and search product count for corresponding categories