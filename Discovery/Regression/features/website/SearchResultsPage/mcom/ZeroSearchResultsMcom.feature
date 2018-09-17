# Author: Discovery QE

Feature: Verify Search Zero Results Page contents in DOMESTIC mode

  @domain_discovery @priority_high @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify For a numerical search with no results, show standard zero results page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the search results should be in zero results page
  Examples:
  | shopping_mode | keyword |
  | Domestic      | 1145   |
  | Iship         | 1145   |

  @domain_discovery @priority_high @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @discovery_daily_run
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the MCOM  zero results page with special character
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the message "We found 0 results for <keyword>" in zero results page header
  Examples:
  | shopping_mode | keyword   |
  | Domestic      | qrs&fd    |
  | Iship         | xtyz&asdf |

  @domain_discovery @priority_high @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify For a numerical search with no results, show standard zero results page
    Given I search for "1145" in Registry mode
    Then I should be in Registry zero result page

  @domain_discovery @priority_high @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic|Iship|Registry - Verify the MCOM  zero results page with special character
    Given I search for "xtyz&asdf" in Registry mode
    Then I verify the display Message in Registry zero results page
#We found 0 results for your search. You're currently searching within Registry. Return to macys.com to search all products.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify recommendation Panel, ShopBy & category icons are displayed on zero search result page in all mode
    Given I am on ZeroSearchResultPage for "siapsdoa" in <Site_Mode> mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that shopBy text is displayed on ZSR page
    And I verify that category icons are displayed on ZSR page
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |