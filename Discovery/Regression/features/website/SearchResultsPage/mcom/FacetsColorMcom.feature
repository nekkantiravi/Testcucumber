#Author: Discovery QE
#Date Created: 12/16/2016

Feature: Verifying Color Facets in Search Results Page


  ############################### ALL MODES ##########################################################

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that color swatch is highlighted based on the selected color
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the selected color
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that color swatches on product are highlighted when 2 colors are selected
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected colors in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected colors
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected colors
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  #Notes: may need to check with fcc response

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @discovery_daily_run
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify unselecting of color from overlay
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify color swatch is highlighted when paginated
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I navigate to the last page
    Then I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet de-selection with check box under Color facet section
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I deselect the Color from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Shirts      |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all for color facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first two color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count for color facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the color one by one from breadcrumbs displays products accordingly
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 color in the "Color" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first color facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second color facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last color facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    #Note : Verify that the product assortment is getting changed after the deselection of each color
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original for color facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 facets in the "Color" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match for color facet
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that product count for the selected Color in the overlay and results match
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the quick peek is highlighting the selected color
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I select the quick peek of that product
    Then I verify that the selected color is highlighted in the color swatches on quick view
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the PDP is highlighting the selected color
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I navigate to PDP of that product
    Then I verify that the selected color is highlighted in the color swatches on pdp
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the color swatches is highlighting the selected color after selecting 3 column grid for color facet
    Given I am on SearchResultsPage for "traveler" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
#    And I select "3" Column Grid icon
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the color swatches is highlighting the selected color after selecting 120 item count option for color facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I filter the result set to show "120" items per page
    And I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @use_manual
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify unselecting of color from breadcrumb
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected colors in the color swatch is highlighted
    When I remove the selected facet from the breadcrumb
    Then I verify that no products have color swatches highlighted
    # By default most of the products are displayed with color swatches highlighted
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

