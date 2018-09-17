#Author: Discovery QE
#Date Created: 28/11/2016

Feature: Verifying Sale & Offers Facets in DLP Page

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Sale & Offers Facet - Verify that facet breadcrumb for sales and offers facet selection in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with Sales & Offers facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single facet from sales and offers facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from sales and offers facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Sale & Offers Facet - Verify that facet persistence for sales and offers facet selection in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with sales & offers facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
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
# Select single facet from sales and offers facet
# Select any product from thumbnail grid and navigate back from pdp to results page
# Verify selected sales and offers facet is persisted on results page and in breadcrumb and also in URL.
# Select another facet from sales and offers facet
# Select any product from thumbnail grid and navigate back from pdp to results page
# Verify two selected sales and offers facets are persisted on results page and in breadcrumb and also in URL.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Sale & Offers Facet - Verify that selected facets are separated in facet panel for sales and offers facet selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with sales & offers facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that selected facets are separated from inactive facets
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
# Notes:
# Select single sales and offers facet from sales and offers facet section
# Verify that selected sales and offers facets are displayed separately under facet panel.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Sale & Offers Facet - Verify that facet persistence after sot by and pagination for sales and offers facet selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with sales & offers facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random option in sort by drop down
    Then I verify that products are updated with selected sort option
    And I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I click on next pagination button
    And I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that products are filtered as per selected facet value
    And I verify that previous sort by selection persist
    And I verify that selected page number persist
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
# Notes:
# Select single/multiple sales and offers facet from facet section
# Select any sort by option
# Verify selected sales and offers facets are persisted after sort by also.
# Select pagination next arrow.
# Verify selected sales and offers facets are persisted after pagination also.
# Select any product from thumbnail grid.
# Select browser back button
# Verify selected sales and offers, sort by and pagination options are persisted.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Sale & Offers Facet - Verify that facet deselection from sales and offers facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with sales & offers facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I deselect the selected facet from the overlay
    Then I verify that the product count returns to original
    When I select "single" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Sales & Offers" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |