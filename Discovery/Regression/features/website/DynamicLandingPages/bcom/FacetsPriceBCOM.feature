Feature: Verifying Price Facets in DLP Page

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Registry - Price Facet - Verify facet breadcrumb for single price facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    And  I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that applied facet value is displayed
    And I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    Examples:
      | mode          |
      | normal        |
      | registry      |
  # Notes:
  # Select single facet from price facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from price facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Registry - Price Facet - Verify selected facet value deselection from breadcrumb for single price facet
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    And  I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    And I verify that previously selected facets persists in breadcrumb
    When I deselect the Price from the overlay
    Then I verify that the product count returns to original
    Examples:
      | mode          |
      | normal        |
      | registry      |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Registry - Price Facet - Verify that facet persistence for price facet selection in all modes
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    And  I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode          |
      | normal        |
      | registry      |
# Notes:
# Select single facet from price facet
# Select any product from thumbnail grid and navigate back from pdp to results page
# Verify selected price facet is persisted on results page and in breadcrumb and also in URL.
# Select another facet from price facet
# Select any product from thumbnail grid and navigate back from pdp to results page
# Verify two selected price facets are persisted on results page and in breadcrumb and also in URL.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Registry - Price Facet - Verify that selected facets are separated in facet panel for price facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that selected facets are separated from inactive facets
    Examples:
      | mode          |
      | normal        |
      | registry      |
# Notes:
# Select single price facet from price facet section
# Verify that selected price facets are displayed separately under facet panel.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Registry - Price Facet - Verify that facet persistence after sot by and pagination for price facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    When I select the first two price in the Price facet
    Then I verify that products are filtered with selected price facet value
    When I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality
    And  I verify that previously selected facets persists in breadcrumb
    When I select 'random' page number from pagination
    Then I verify that products are filtered with selected price facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous sort by selection persist
    And I verify that previous pagination selection persist
    Examples:
      | mode          |
      | normal        |
      | registry      |
# Notes:
# Select single/multiple price facet from facet section
# Select any sort by option
# Verify selected price facets are persisted after sort by also.
# Select pagination next arrow.
# Verify selected price facets are persisted after pagination also.
# Select any product from thumbnail grid.
# Select browser back button
# Verify selected price, sort by and pagination options are persisted.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Registry - Price Facet - Verify clear all functionality for price facet
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode          |
      | normal        |
      | registry      |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Price Facet - Verify breadcrumb for multiple price facet values selection
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    When I select the first two price in the Price facet
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode          |
      | normal        |
      | registry      |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Price Facet - Verify multiple price facet values selection
    Given I am on DynamicLandingPage in "<mode>" mode with price facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    When I select another facet value from "Price" facet
    Then I verify that products are filtered with selected price facet value
    Examples:
      | mode          |
      | normal        |
      | registry      |