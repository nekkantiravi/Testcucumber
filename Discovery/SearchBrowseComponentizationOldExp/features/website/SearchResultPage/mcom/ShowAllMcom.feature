#Author: Discovery QE

Feature: Verifying ShowALL in Search Results Page


  use_regression  @artifact_navapp @domain_discovery @priority_low @use_dsv @use_regression_1 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Keyword search for less than 4 products in DOMESTIC mode
    Given I am on SearchResultsPage for "jumbo cool touch" in DOMESTIC mode
    Then I verify that the product count is "less" than "4"
    And I verify that pagination is not displayed

  @use_regression @artifact_navapp @domain_discovery @priority_medium @use_regression_1 @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify Select n Items per page in search result page in DOMESTIC mode
    Given I am on SearchResultsPage for "coats" in DOMESTIC mode
    And I filter the result set to show "<number_of_items>" items
    Then I verify that "<number_of_items>" product thumbnails in search results page
    Examples:
      | number_of_items |
      | 60              |
      | 120             |

  @use_regression @artifact_navapp @domain_discovery @priority_medium @use_regression_1 @mode_domestic @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify Search for a keyword with results contains sort by and show by drop down in DOMESTIC mode
    Given I am on SearchResultsPage for "coats" in DOMESTIC mode
    When I select "<SortOrder>" in sort by drop down
    And I filter the result set to show "<Show>" items
    Then I verify that "<show>" product thumbnails in search results page
    And I verify that products are updated with selected sort option
    Examples:
      | Show | SortOrder           |
      | 60   | Best Sellers        |
      | 60   | Price (low to high) |
      | 60   | Price (high to low) |
      | 60   | Customer Top rated  |
      | 120  | New Arrivals        |
      | 120  | Featured Items      |