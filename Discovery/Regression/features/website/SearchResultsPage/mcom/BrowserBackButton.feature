Feature: Verify browser back button verification on Search Landing Page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '60' item count option in 3 column grid view
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I filter the result set to show "60" items per page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '120' item count option in 3 column grid view
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I filter the result set to show "120" items per page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '60' item count option and pagination in 3 column grid view
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I filter the result set to show "60" items per page
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '120' item count option and pagination in 3 column grid view
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I filter the result set to show "120" items per page
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @priority_medium @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Iship - Ensure product thumbnails displayed as existing on Search Landing page when navigated through browser back button after browse grid implementation
    Given I am on SearchResultsPage for "jeans" in ISHIP mode
    Then I should be in Search Landing page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I should be in Search Landing page
    And I verify that landed on SearchResultsPage on same product grid point