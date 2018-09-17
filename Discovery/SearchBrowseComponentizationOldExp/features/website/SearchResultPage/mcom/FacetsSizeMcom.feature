#Author: Discovery QE

Feature: Verifying New Size Facets in SLP

  @artifact_navapp @size_facet_redesign @domain_discovery @mode_domestic @release_15K @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select 1 facet value(s) from 'any' facet sections
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @artifact_navapp @size_facet_redesign @domain_discovery @mode_domestic @release_15K @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select 4 facet value(s) from 'any' facet sections
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  @artifact_navapp @project_Mercandizing_facets @use_regression @domain_discovery @mode_domestic @release_16D @priority_medium @B35019 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the size facet header in DOMESTIC mode
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    #And I should verify the header with multiple rows
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  @artifact_navapp @project_Mercandizing_facets @domain_discovery @mode_domestic @release_16D @priority_medium @B32380 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the size facet functionality in DOMESTIC mode
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    When I select already selected facet sub header
    Then I verify that facet sub header gets "Collapsed"
    And I verify that the facet values are not displayed
    When I select "single" facet value from "Size" facet section
    Then I verify that product count from JSON response against UI are same
    #And I should see the selected facets gets highlighted
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I deselect 1 facet value(s)
    Then I verify that selected facet gets deselected
    When I select multiple facet value from "Size" facet section
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I deselect 1 facet value(s)
    Then I verify that selected facet gets deselected
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |


  @artifact_navapp @project_Mercandizing_facets @domain_discovery @mode_domestic @release_16D @priority_medium @B33889 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the commas in multi facets in search page
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    # comma is not displayed on UI
    #And I verify that comma's is displayed in the facet value
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |


  @artifact_navapp @project_Mercandizing_facets @domain_discovery @use_regression @mode_domestic @release_16D @priority_medium @B31170 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the node level of facet size group in page against Json response in DOMESTIC mode
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see below keys in the JSON response at top-level groups
      | name           |
      | display Name   |
      | facet Type     |
      | robot Readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at top-level groups
    And I should see below keys in the JSON response at first-level groups
      | name           |
      | display Name   |
      | facet Type     |
      | robot Readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at first-level groups
    And I should see below keys in the JSON response at second-level groups
      | name           |
      | display Name   |
      | facet Type     |
      | robot Readable |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at second-level groups
    #And I should verify the header values in UI
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |
