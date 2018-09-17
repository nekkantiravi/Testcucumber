#Author: Discovery QE

Feature: Verifying Customer Ratings Facets in SLP

############################### ALL MODES ##########################################################
  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the ratings in product thumbnail matches the first facet selection
    Given I am on SearchResultsPage for "spoons" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the ratings in product thumbnail matches the facet selection
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 2 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify deselecting of ratings from breadcrumb
    Given I am on SearchResultsPage for "spoons" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    When I remove the selected facet from the breadcrumb
    Then I verify that all of the products are displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify ratings choice is retained when paginated
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I navigate to the last page
    Then I verify that the products are displayed with the selected rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting a rating from overlay displays all the products
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 2 rating in the "Customer Ratings" facet
    And I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count for customer top rated facet
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 2 rating in the "Customer Ratings" facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the rating one by one from breadcrumbs displays products accordingly
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 rating in the "Customer Ratings" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
  #Note : Verify that the product assortment is getting changed after the deselection of each rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 rating in the "Customer Ratings" facet
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    And I verify that the product count is updated
    Then I verify that all of the products are displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I verify that product count for the selected CUSTRATINGS in the overlay and results match
  #Note : The product count can be off by 2
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the quick peek is showing the selected rating
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I verify that the product rating in quick peek
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the PDP is showing the selected rating
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I select random product from thumbnail grid
    Then I verify that the product rating in PDP
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet de-selection with check box under Customer Ratings facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Customer Ratings facet
    Then I verify that the product count is updated
    When I deselect the Customer Ratings from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |