#Author: Discovery QE

Feature: Verifying Color Facets in Search Results Page

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for color swatch selection  in all modes
    Given I am on SearchResultsPage for "victorinox swiss" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for multiple color swatch selection  in all modes
    Given I am on SearchResultsPage for "victorinox swiss" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product thumbnail images for color swatch selection  in all modes
    Given I am on SearchResultsPage for "victorinox swiss" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    When I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for color swatch selection in all modes
    Given I am on SearchResultsPage for "victorinox swiss" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    When I select a product having color swatches
    And I navigate to PDP of that product
    Then I verify that the selected color is highlighted in the color swatches on pdp
    When I select browse 'back' button
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for multiple color swatch selection in all modes
    Given I am on SearchResultsPage for "victorinox swiss" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected colors
    When I select a product having color swatches
    And I navigate to PDP of that product
    Then I verify that the selected color is highlighted in the color swatches on pdp
    When I select browse 'back' button
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that selected facets are separated in facet panel for color swatch selection
    Given I am on SearchResultsPage for "victorinox swiss" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    And I verify that the selected Color appears on top
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Color Facet - Verify that facet persistence after sort by and pagination for color swatch selection
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 2 color in the "Color" facet
    Then I verify that the product thumbnails are displayed with the selected color
    When I select random option in sort by drop down
    Then I verify that the product count is displayed
    And I verify that products are updated with selected sort option
    When I select 'random' page number from pagination
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous pagination selection persist
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet deselection from color facet
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Color" facet section
    Then I verify that the product thumbnails are displayed with the selected color
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "single" facet value from "Color" facet section
    Then I verify that the product thumbnails are displayed with the selected color
    And I verify that previously selected facets persists in breadcrumb
    When I deselect the selected facet from the overlay
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Color" facet section
    Then I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |