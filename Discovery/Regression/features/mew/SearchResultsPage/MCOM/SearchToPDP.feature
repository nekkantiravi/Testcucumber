Feature: Component tests for search

  @domain_mew_discovery @use_mew_regression
  Scenario: SearchResultsPage - Verify navigation to a PDP page from search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Boots" in mew search and click enter
    And I navigate to a random PDP page on search results page
    And I should be on PDP page