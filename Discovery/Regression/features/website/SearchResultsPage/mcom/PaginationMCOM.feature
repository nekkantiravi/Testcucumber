Feature: Pagination verification on Search Landing Page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Without Pagination in DOMESTIC mode
    Given I am on SearchResultsPage for "bread maker" in Domestic mode
    Then I should be in Search Landing page
    And I verify that pagination is not displayed

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Pagination using Next / Previous button in REGISTRY Mode
    Given I am on SearchResultsPage for "dining" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Verify pagination in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that pagination works
    Examples:
      | shopping_mode  |
      | Domestic       |
      | Registry       |
      | Iship          |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Verify previous pagination button in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @discovery_daily_run
  Scenario Outline: SearchResultsPage - Verify Next pagination button in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode |keyword|
      | Domestic      |Jeans  |
      | Registry      |Plates |
      | Iship         |Jeans  |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Verify browser back button takes to previously navigated page before navigating to PDP in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 4 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    And I navigate to PDP of the first product
    And I select browse 'back' button
    Then I verify that navigated to page 4 on search result page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  #Note:  paginate,navigate to product and press browser back button, check url
  # paginate,choose quick view and press browser back button, check url
  # paginate , press forward backward button in between

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality with pagination with 60 items per page
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I filter the result set to show "60" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on search result page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality with pagination with 120 items per page
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I filter the result set to show "120" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on search result page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify "60" item per page functionality with pagination
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I filter the result set to show "60" items per page
    Then I verify that navigated to page 1 on search result page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify "120" item per page functionality with pagination
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page