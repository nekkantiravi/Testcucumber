Feature: Verify Back To Top functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify back to top button is excluded for chanel pages in DOMESTIC mode
    Given I search for "chanel makeup" in Domestic mode
    And I navigate to bottom of page
    Then I verify that back to top button is not displayed on page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @migrated_to_sdt @use_regression @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic - Verify back to top button is displayed on Search results page
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry -  Verify back to top button is not displayed immediately on Search results page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode | keyword    |
      | Domestic      | Jeans      |
      | Registry      | Plates     |
      | Iship         | Jeans      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Search results page is navigated to top on click back to top button
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I select back to top button
    Then I verify that Search result page navigated to top of the page
    Examples:
      | shopping_mode | keyword|
      | Domestic      | Jeans  |
      | Registry      | Plates |
      | Iship         | Jeans  |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify back to top button is not displayed after navigating from bottom to top of search result page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I navigate to top of page
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode | keyword |
      | Domestic      | Jeans   |
      | Registry      | Plates  |
      | Iship         | dresses |