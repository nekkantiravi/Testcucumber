Feature: To verify Auto Partial Match Messaging

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify displayed result message based on search term matches on the Partial Match search with One combination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a valid search term
    Then I should be on the search results page
    And I verify result message for mcom valid search term
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify displayed result message for based on Invalid search term
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a invalid search term
    Then I should be on the search results page
    And I verify result message for mcom invalid search term
    Examples:
      | mode     |
      | domestic |
      | iship    |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify displayed result message based on search term matches on the Partial Match search with Multiple combination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a multiple search term
    Then I should be on the search results page
    And I verify result message for mcom multiple search term
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify displayed result message for Invalid search term
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with a complete-invalid search term
    And I verify result message for mcom complete-invalid search term
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: SearchResultsPage - Verify displayed result message based on search term matches on the Partial Match search with One combination in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with a valid search term
    Then I should be on the registry search results page
    And I verify result message for mcom valid search term in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: SearchResultsPage - Verify displayed result message for based on Invalid search term in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with a invalid search term
    Then I should be on the registry search results page
    And I verify result message for mcom invalid search term in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: SearchResultsPage - Verify displayed result message based on search term matches on the Partial Match search with Multiple combination in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with a multiple search term
    Then I should be on the registry search results page
    And I verify result message for mcom multiple search term in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: SearchResultsPage - Verify displayed result message for Invalid search term in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with a complete-invalid search term
    Then I should be on zero search results page
    And I verify result message for mcom complete-invalid search term in registry mode