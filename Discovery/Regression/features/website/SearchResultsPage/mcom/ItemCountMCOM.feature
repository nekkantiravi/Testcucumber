Feature: Verify Item Count functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

#  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
#  Scenario Outline: SearchResultsPage - Verify item count selection for 3 column in DOMESTIC mode from top
#    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
#    And I verify that item count buttons in page
#    Then I verify that "60" item count option and respective number of products on page
#    When I filter the result set to show "120" products from "bottom"
#    Then I verify that "120" item count option and respective number of products on page
#    Examples:
#      | shopping_mode | keyword     |
#      | Domestic      | jeans       |
#      | Registry      | cookware    |
#      | Iship         | jeans       |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Verify item count selection for 3 column in DOMESTIC mode from bottom
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that item count buttons in page
    When I filter the result set to show "60" products from "bottom"
    Then I verify that "60" item count option and respective number of products on page
    When I filter the result set to show "120" products from "bottom"
    Then I verify that "120" item count option and respective number of products on page
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | jeans       |
      | Registry      | cookware    |
      | Iship         | jeans       |