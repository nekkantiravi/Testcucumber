#Author: Discovery QE

Feature: Verify Partial match messaging on Search Results Page - DOMESTIC, ISHIP and REGISTRY modes

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry  - Verify partial match message in domestic search landing page when there is only one combination to the search term entered
    Given I am on SearchResultsPage for "asdasdfg <keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <keyword>" in search landing page header
    And I verify that search message "There were 0 matches" is displayed
    Examples:
      | shopping_mode | keyword |
      | Domestic      | nike    |
      | Iship         | nike    |
      | Registry      | plates  |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search @discovery_daily_run
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify partial match message on search landing page when there are multiple combinations to the search term entered
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    And I verify that search message "There were 0 matches for " is displayed
    When I select any search term link
    And I select browse 'back' button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    When I select 'single' facet value from 'any' facet section
    And I click on clear all button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    When I select random product from thumbnail grid
    And I select browse 'back' button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    Examples:
      | shopping_mode | keyword                     | partial_match_text                   |
      | Domestic      | red gucci belt              | Red Gucci , Red Belt                 |
      | Iship         | yellow glassware silverware | Yellow Glassware , Yellow Silverware |
      | Registry      | yellow glassware silverware | Yellow Glassware , Yellow Silverware |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify search message is not displayed with quotations
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <keyword>" in search landing page header
    And I verify that quotations are not displayed for search keyword in search message
    Examples:
      | shopping_mode | keyword |
      | Domestic      | jeans   |
      | Iship         | jeans   |
      | Registry      | cups    |

  @domain_discovery @priority_medium @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify search message in zero search results page
    Given I am on ZeroSearchResultPage for "xytz" in <shopping_mode> mode
    And I verify that the message "We found 0 results for xytz" in zero results page header
    And I verify that quotations are not displayed for search keyword in search message
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Registry - Verify search message in Registry Zero Search Results Page
    Given I am on ZeroSearchResultPage for "xytz" in Registry mode
    And I verify the display Message in Registry zero results page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify search message
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <keyword>" in search landing page header
    Examples:
      | shopping_mode | keyword |
      | Domestic      | jeans   |
      | Iship         | jeans   |
      | Registry      | cups    |