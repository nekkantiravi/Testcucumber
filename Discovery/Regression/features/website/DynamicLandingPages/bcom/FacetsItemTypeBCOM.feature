Feature: Verifying Item Type Facets in DLP Page

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Item Type Facet - Verify breadcrumb for item type facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Item Type" facet section
    And I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    And I verify that the product count is updated
    Examples:
      | mode          |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single facet from item type facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from item type facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Item Type Facet - Verify that facet persistence for item type single facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Item Type" facet section
    Then I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I select another facet value from "Item Type" facet
    Then I verify that products are filtered as per selected facet values
    Examples:
      | mode          |
      | normal        |
#      | registry      |
#      | iship         |
  # Notes:
  # Select single facet from item type facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected item type facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from item type facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected item type facets are persisted on results page and in breadcrumb and also in URL.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Item Type Facet - Verify that facet persistence for multiple item type facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Item Type" facet section
    Then I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
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
      | iship         |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Item Type Facet - Verify that selected facets are separated in facet panel for single item type facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Item Type" facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that "Item Type" facets are separated from inactive facets
    Examples:
      | mode          |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single item type facet from item type facet section
  # Verify that selected item type facets are displayed separately under facet panel.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Item Type Facet - Verify that selected facets are separated in facet panel for multiple item type facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Item Type" facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that selected facets are separated from inactive facets
    Examples:
      | mode          |
      | normal        |
      | registry      |
      | iship         |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Item Type Facet - Verify that facet persistence after sot by and pagination for item type facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Item Type" facet section
    Then I verify that products are filtered as per selected facet values
    When I select "New Arrivals" in sort by drop down
    And I verify that the Sort By "New Arrivals" functionality
    Then I verify that previously selected facets persists in breadcrumb
    When I select 'random' page number from pagination
    Then I verify that previously selected facets persists in breadcrumb
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
      | iship         |
  # Notes:
  # Select single/multiple item type facet from facet section
  # Select any sort by option
  # Verify selected item type facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected item type facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected item type, sort by and pagination options are persisted.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Item Type Facet - Verify clear all button for item type facet
    Given I am on DynamicLandingPage in "<mode>" mode with item type
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Item Type" facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that applied facet value is displayed
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode          |
      | normal        |
      | registry      |
      | iship         |
