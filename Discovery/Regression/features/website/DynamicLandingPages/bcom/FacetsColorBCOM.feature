#Author: Discovery QE
#Date Created: 28/11/2016

Feature: Verifying Color Facets in Search Results Page

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Color Facet - Verify that facet breadcrumb for color swatch selection in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I select the first color in the Color facet
    When I select the first two color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single facet from color facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from color facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Color Facet - Verify that product thumbnail images for color swatch selection  in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that products are filtered as per selected facet value
    Then I verify that the selected color in the color swatch is highlighted
    And I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single facet from color facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Verify color swatches selected below product thumbnail as per selected facet value.
  # Verify colorzed product thumbnail is displayed as per selected facet values.
  # Verify jumbo swatches are displayed on product thumbnail if colorized image not available for any product.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Color Facet - Verify that facet persistence for color swatch selection in all modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that products are filtered as per selected facet value
    When I select the first color in the Color facet
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
  # Select single facet from color facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected color facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from color facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected color facets are persisted on results page and in breadcrumb and also in URL.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Color Facet - Verify that selected facets are separated in facet panel for color swatch selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that "Color" facets are separated from inactive facets
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single color facet from color facet section
  # Verify that selected color facets are displayed separately under facet panel.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Color Facet - Verify that facet persistence after sot by and pagination for color swatch selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that applied facet value is displayed
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
    #And I verify that selected page number persist
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
  # Notes:
  # Select single/multiple color facet from facet section
  # Select any sort by option
  # Verify selected color facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected color facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected color, sort by and pagination options are persisted.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Color Facet - Verify that facet deselection from color facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I deselect the selected facet from the overlay
    Then I verify that the product count returns to original
    When I select the first color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select the first two color in the Color facet
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |