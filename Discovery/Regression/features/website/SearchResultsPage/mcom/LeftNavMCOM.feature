Feature: Verify browser back button verification on Search Landing Page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Verify LEFT NAV LINKS are displayed in All modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that search result page Facets displayed match with production
    Examples:
      | shopping_mode |keyword    |
      | Domestic      |jeans      |
      | Registry      |dinning    |
      | Iship         |jeans      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Brand facet recognize scripts and do not submit a search in such cases
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that "Brand" facet is listed on left nav
    And I type a character "<script>alert('hi there')</script>" in brand search box
    Then I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | shopping_mode | keyword   |
      | Domestic      | dresses   |
      | Iship         | dresses   |
      | Registry      | cookware  |