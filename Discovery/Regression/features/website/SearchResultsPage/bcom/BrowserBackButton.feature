Feature: Verify browser back button verification on Search Landing Page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '90' item count option in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I filter the result set to show "90" items per page
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
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '180' item count option in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I filter the result set to show "180" items per page
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
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '90' item count option and pagination in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I filter the result set to show "90" items per page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I verify that navigated to page 2 on search result page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '180' item count option and pagination in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I filter the result set to show "180" items per page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I verify that navigated to page 2 on search result page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  #B-84459
  @domain_discovery @use_regression
  Scenario: Clicking on Back to Results breadcrumb on 100% GBS should go to the page number that the user was previously on
    Given I visit the website as a guest user
    And I search for "100 percent bloomingdale's"
    And I click 2 pagination number
    And I select random product from thumbnail grid
    And I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I verify that navigated to page 2 on search result page
