#Author: Discovery QE

Feature: Verifying ShowALL in Search Results Page

  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify Keyword search for less than 4 products
    Given I am on SearchResultsPage for "jumbo cool touch" in DOMESTIC mode
    Then I verify that the product count is "less" than "4"
    And I verify that pagination is not displayed

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic - Verify Select n Items per page in search result page
    Given I am on SearchResultsPage for "coats" in DOMESTIC mode
    When I filter the result set to show "<number_of_items>" items per page
    Then I verify that "<number_of_items>" product thumbnails in search results page
    Examples:
      | number_of_items |
      | 60              |
      | 120             |

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic - Verify Search for a keyword with results contains sort by and show by drop down
    Given I am on SearchResultsPage for "coats" in DOMESTIC mode
    When I select "<SortOrder>" in sort by drop down
    When I filter the result set to show "<Show>" items per page
    Then I verify that "<Show>" product thumbnails in search results page
    And I verify that products are updated with selected "<SortOrder>" sort option
    Examples:
      | Show | SortOrder           |
      | 60   | Best Sellers        |
      | 60   | Price: Low to High  |
      | 60   | Price: High to Low  |
      | 60   | Customers' Top Rated|
      | 120  | New Arrivals        |
      | 120  | Featured Items      |