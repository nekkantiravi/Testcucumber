#Author: Discovery QE

Feature: Verifying Department Facets in Search Results Page

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for department facet selection in all modes
    Given I am on SearchResultsPage for "china" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    When I select another facet value from "Department" facet
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Notes:
  # Select single facet from department facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from department facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for department facet selection in all modes
    Given I am on SearchResultsPage for "china" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that products are filtered as per selected facet value
    When I select another facet value from "Department" facet
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Notes:
  # Select single facet from department facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected department facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from department facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected department facets are persisted on results page and in breadcrumb and also in URL.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that selected facets are separated in facet panel for department facet selection
    Given I am on SearchResultsPage for "china" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the selected Department appears on top
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Notes:
  # Select single department facet from department facet section
  # Verify that selected department facets are displayed separately under facet panel.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence after sot by and pagination for department facet selection
    Given I am on SearchResultsPage for "china" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I verify that the product count is displayed
    When I select the first facet in the Department facet
    Then I verify that products are filtered as per selected facet value
    When I select "New Arrivals" in sort by drop down
    Then I verify that the product count is displayed
    And I verify that the Sort By "New Arrivals" functionality
    When I select another facet value from "Department" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
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
      | Domestic      |
      | Registry      |
      | Iship         |
  # Notes:
  # Select single/multiple department facet from facet section
  # Select any sort by option
  # Verify selected department facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected department facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected department, sort by and pagination options are persisted.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet deselection from department facet
    Given I am on SearchResultsPage for "china" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I verify that the product count is displayed
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I deselect the selected facet from the overlay
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |