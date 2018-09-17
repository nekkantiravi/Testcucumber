#Author: Discovery QE

Feature: Verifying Size Facets in SLP
#-----------------------------
#DOMESTIC - B31614
#-----------------------------

  @artifact_navapp @project_Mercandizing_facets @domain_discovery @use_regression @mode_domestic @release_16D @priority_medium @B31614 @please_automate
  Scenario: SearchResultsPage - Domestic - Verify the size facet appearance and json response in DOMESTIC mode
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    Then I should be in Search Landing page
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see value "MULTISELECTBUTTON" for the key "facet type" in the JSON response at top-level groups
    And I should see value "MULTISELECTDEFAULT" for the key "facet type" in the JSON response at first-level groups


  @artifact_navapp @project_Mercandizing_facets @use_regression @domain_discovery  @mode_iship @release_16D @priority_medium @B31614 @please_automate
  Scenario: SearchResultsPage - Domestic - Verify the size facet appearance and json response in ISHIP mode
    Given I am on SearchResultsPage for "pants" in Iship mode
    Then I should be in Search Landing page
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see value "MULTISELECTBUTTON" for the key "facet type" in the JSON response at top-level groups
    And I should see value "MULTISELECTDEFAULT" for the key "facet type" in the JSON response at first-level groups


  @test @artifact_navapp @project_Mercandizing_facets @use_regression @domain_discovery @mode_domestic @release_16D @priority_medium @B32378 @please_automate
  Scenario: SearchResultsPage - Domestic - Verify the UI of SLP
    Given I am on SearchResultsPage for "pants" in Domestic mode
    Then I should be in Search Landing page
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "single" facet value from "Size" facet section
    Then I verify that product count from JSON response against UI are same
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select multiple facet value from "Size" facet section
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I deselect 1 facet value(s)
    Then I verify that selected facet gets deselected
    And I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original


  @artifact_navapp @project_Mercandizing_facets @domain_discovery @mode_iship @release_16D @priority_medium @B32378 @please_automate
  Scenario: SearchResultsPage - Iship - Verify the size facet functionality
    Given I am on SearchResultsPage for "pants" in Iship mode
    Then I should be in Search Landing page
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "single" facet value from "Size" facet section
    Then I verify that product count from JSON response against UI are same
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select multiple facet value from "Size" facet section
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I deselect 1 facet value(s)
    Then I verify that selected facet gets deselected
    And I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original


  @artifact_navapp @project_Mercandizing_facets @domain_discovery @use_regression @mode_domestic @release_16D @priority_medium @B33192 @please_automate
  Scenario: SearchResultsPage - Domestic - Verify the node level of facet size group in page against json response for in DOMESTIC mode
    Given I am on SearchResultsPage for "pants" in Domestic mode
    Then I should be in Search Landing page
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see below keys in the JSON response at top-level groups
      | Key            |
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facet type" in the JSON response at top-level groups
    And I should see below keys in the JSON response at first-level groups
      | Key            |
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTDEFAULT" for the key "facet type" in the JSON response at first-level groups
    And I should see below keys in the JSON response at second-level groups
      | Key            |
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
    And I should see value "MULTISELECTDEFAULT" for the key "facet type" in the JSON response at second-level groups
    #And I should verify the header values in UI


  @artifact_navapp @project_Mercandizing_facets @domain_discovery @mode_iship @release_16D @priority_medium @B33192 @please_automate
  Scenario: SearchResultsPage - Iship - Verify the node level of facet size group in page against json response in ISHIP mode
    Given I am on SearchResultsPage for "pants" in Iship mode
    Then I should be in Search Landing page
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see below keys in the JSON response at top-level groups
      | Key            |
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facet type" in the JSON response at top-level groups
    And I should see below keys in the JSON response at first-level groups
      | Key            |
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTDEFAULT" for the key "facet type" in the JSON response at first-level groups
    And I should see below keys in the JSON response at second-level groups
      | Key            |
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
    And I should see value "MULTISELECTDEFAULT" for the key "facet type" in the JSON response at second-level groups
  #  And I should verify the header values in UI


  @artifact_navapp @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @please_automate
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for size facet selection in all modes
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select "single" facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is not displayed
    When I select "multiple" facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    Examples:
      | shopping_mode |
      | Domestic  |
      | Iship     |
  # Notes:
  # Select single facet from size facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from size facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @please_automate @add_missing_scope @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for size facet selection in all modes
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select "single" facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I select "single" facet value from "Size" facet section
    Then I select random product from thumbnail grid
    And I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    Examples:
      | shopping_mode |
      | Domestic  |
      | Iship     |
  # Notes:
  # Select single facet from size facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify selected size facet is persisted on results page and in breadcrumb and also in URL.
  # Select another facet from size facet
  # Select any product from thumbnail grid and navigate back from pdp to results page
  # Verify two selected size facets are persisted on results page and in breadcrumb and also in URL.

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @please_automate @add_missing_scope @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that selected facets are separated in facet panel for size facet selection
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select "single" facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that the selected Size appears on top
    Examples:
      | shopping_mode |
      | Domestic  |
      | Iship     |
  # Notes:
  # Select single size facet from size facet section
  # Verify that selected size facets are displayed separately under facet panel.

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @please_automate @add_missing_scope @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence after sot by and pagination for size facet selection
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select "multiple" facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I select 'random' page number from pagination
    And I verify that previously selected facets persists in breadcrumb
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
      | Iship     |
  # Notes:
  # Select single/multiple size facet from facet section
  # Select any sort by option
  # Verify selected size facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected size facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected size, sort by and pagination options are persisted.

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @please_automate @add_missing_scope @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet deselection from size facet
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select "single" facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet value
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Size" facet section
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic  |
      | Iship     |