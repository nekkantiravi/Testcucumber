Feature: Grid column & Items per page verification on Search Landing Page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '3 column' grid
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous grid view selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |
 # Notes:
 # Verify facet selection persists when we navigate back from PDP page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search @discovery_daily_run
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '120' item count
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I filter the result set to show "120" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '60' item count
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When  I filter the result set to show "60" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |