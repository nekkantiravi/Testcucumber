Feature: Verify Item Count functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Verify item count selection for 4 column in DOMESTIC,ISHIP and REGISTRY mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that item count buttons in page
    When I select "4" Column Grid icon
    Then I verify that "40" item count option and respective number of products on page
    When I filter the result set to show "100" items
    Then I verify that "100" item count option and respective number of products on page
    When I filter the result set to show "All" items
    Then I verify that "All" item count option and respective number of products on page
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | jeans       |
      | Registry      | cookware    |
      | Iship         | jeans       |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Verify item count selection for 3 column in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that item count buttons in page
    When I select "3" Column Grid icon
    Then I verify that "60" item count option and respective number of products on page
    When I filter the result set to show "120" items
    Then I verify that "120" item count option and respective number of products on page
    When I filter the result set to show "All" items
    Then I verify that "All" item count option and respective number of products on page
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | jeans       |
      | Registry      | cookware    |
      | Iship         | jeans       |