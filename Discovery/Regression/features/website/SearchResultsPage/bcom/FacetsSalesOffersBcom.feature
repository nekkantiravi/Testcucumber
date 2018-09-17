#Author: Discovery QE

Feature: Verifying Sales&Offers Facet in SLP

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for sales and offers facet selection in all modes
    Given I am on SearchResultsPage for "home sale" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that applied facet value is displayed
    When I select another facet value from "Sales & Offers" facet
    Then I verify that products are filtered as per selected facet values
    And I verify that applied facet value is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |
  # Notes:
  # Select single facet from sales and offers facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from sales and offers facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for sales and offers facet selection in all modes
    Given I am on SearchResultsPage for "home sale" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    And I verify that the product count is displayed
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    When I select another facet value from "Sales & Offers" facet
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |
  # Notes:
  # Select single facet from sales and offers facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected sales and offers facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from sales and offers facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected sales and offers facets are persisted on results page and in breadcrumb and also in URL.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that selected facets are separated in facet panel for sales and offers facet selection
    Given I am on SearchResultsPage for "home sale" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that the selected Sales & Offers appears on top
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |
  # Notes:
  # Select single sales and offers facet from sales and offers facet section
  # Verify that selected sales and offers facets are displayed separately under facet panel.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence after sort by and pagination for sales and offers facet selection
    Given I am on SearchResultsPage for "home sale" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet values
    When I select "New Arrivals" in sort by drop down
    And I verify that applied facet value is displayed
    And I verify that the Sort By "New Arrivals" functionality
    When I select 'random' page number from pagination
    Then I verify that products are filtered as per selected facet values
    And I verify that applied facet value is displayed
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I verify that products are filtered as per selected facet values
    And I verify that previous sort by selection persist
    And I verify that previous pagination selection persist
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |
  # Notes:
  # Select single/multiple sales and offers facet from facet section
  # Select any sort by option
  # Verify selected sales and offers facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected sales and offers facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected sales and offers, sort by and pagination options are persisted.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet deselection from sales and offers facet
    Given I am on SearchResultsPage for "home sale" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I deselect the selected facet from the overlay
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |