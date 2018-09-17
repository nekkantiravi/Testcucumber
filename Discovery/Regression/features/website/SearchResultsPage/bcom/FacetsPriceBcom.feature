#Author: Discovery QE

Feature: Verifying Price Facets in Search Results Page

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify that facet breadcrumb for price facet selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    When I select another facet value from "Price" facet
    Then I verify that products are filtered with selected price facet value
    Examples:
     | shopping_mode   |
     | Domestic        |
     | Registry        |
  # Notes:
  # Select single facet from price facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from price facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify that facet persistence for price facet selection
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    Then I verify that products are filtered with selected price facet value
    When I select another facet value from "Price" facet
    Then I verify that products are filtered with selected price facet value
    When I select random product from thumbnail grid
    And I select browse 'back' button
    Then I verify that products are filtered with selected price facet value
    Examples:
     | Site_Mode |
     | Domestic  |
     | Registry  |
  # Notes:
  # Select single facet from price facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected price facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from price facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected price facets are persisted on results page and in breadcrumb and also in URL.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify that selected facets are separated in facet panel for price facet selection
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the selected Price appears on top
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
  # Notes:
  # Select single price facet from price facet section
  # Verify that selected price facets are displayed separately under facet panel.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify that facet persistence after sot by and pagination for price facet selection
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two price in the Price facet
    Then I verify that products are filtered with selected price facet value
    When I select "New Arrivals" in sort by drop down
    Then I verify that the product count is displayed
    Then I verify that the Sort By "New Arrivals" functionality
    Then I verify that previously selected facets persists in breadcrumb
    When I select 'random' page number from pagination
    Then I verify that products are filtered with selected price facet value
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous sort by selection persist
    And I verify that previous pagination selection persist
    Examples:
     | Site_Mode |
     | Domestic  |
     | Registry  |
  # Notes:
  # Select single/multiple price facet from facet section
  # Select any sort by option
  # Verify selected price facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected price facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected price, sort by and pagination options are persisted.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify that facet deselection from price facet
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select "single" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I deselect the Price from the overlay
    Then I verify that the product count returns to original
    When I select "single" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I deselect the Price from the overlay
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
     | Site_Mode |
     | Domestic  |
     | Registry  |