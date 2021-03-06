#Author: Discovery QE
#Date Created: 28/11/2016

Feature: Verifying Department Facets in DLP Page

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Department Facet - Verify that facet breadcrumb for department facet selection in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with department facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single facet from department facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from department facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Department Facet - Verify that facet persistence for department facet selection in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with department facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that products are filtered as per selected facet value
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single facet from department facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected department facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from department facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected department facets are persisted on results page and in breadcrumb and also in URL.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Department Facet - Verify that selected facets are separated in facet panel for department facet selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with department facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that "Department" facets are separated from inactive facets
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single department facet from department facet section
  # Verify that selected department facets are displayed separately under facet panel.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Department Facet - Verify that facet persistence after sot by and pagination for department facet selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with department facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random option in sort by drop down
    Then I verify that products are updated with selected sort option
    And I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I click on next pagination button
    #Then I should see products are updated with selected page products
    And I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    When I select browse 'back' button
    #Then I should be navigated back to results page
    And I verify that products are filtered as per selected facet value
    And I verify that previous sort by selection persist
    And I verify that selected page number persist
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single/multiple department facet from facet section
  # Select any sort by option
  # Verify selected department facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected department facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected department, sort by and pagination options are persisted.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Department Facet - Verify that facet deselection from department facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with department facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I deselect the selected facet from the overlay
    Then I verify that the product count returns to original
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |