Feature: To verify Auto Partial Match Messaging

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM - SearchResultsPage - Verify displayed result message based on search term matches on the Partial Match search with One combination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a valid search term
    Then I should be on the search results page
    And I verify result message for bcom valid search term
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM - SearchResultsPage - Verify displayed result message for based on Invalid search term
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a invalid search term
    Then I should be on the search results page
    And I verify result message for bcom invalid search term
    Examples:
      | mode     |
      | domestic |
      | iship    |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM - SearchResultsPage - Verify displayed result message based on search term matches on the Partial Match search with Multiple combination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a multiple search term
    Then I should be on the search results page
    And I verify result message for bcom multiple search term
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM - SearchResultsPage - Verify displayed result message for Invalid search term
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a complete-invalid search term
    And I verify result message for bcom complete-invalid search term
    Examples:
      | mode     |
      | domestic |
      | iship    |