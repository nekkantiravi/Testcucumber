#Author: Discovery QE

Feature: Verifying New Size Facets in SLP

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I am on SearchResultsPage for "dress" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Size" facet section
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Iship - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I am on SearchResultsPage for "jeans" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Size" facet section
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select 'customer service' link from header
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Iship         |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I am on SearchResultsPage for "shoes" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "Size" facet section
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    And I navigate to random category splash page
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |



  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the size facet functionality
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
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
      | shopping_mode | keyword   |
      | Domestic      | red jeans |
      | Iship         | red jeans |
      | Registry      | dress     |


  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the commas in multi facets in search page
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |


  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the node level of facet size group in page against Json response
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
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

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify facet de-selection with check box under Size facet section
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first size in the Size facet
    Then I verify that the product count is updated
    When I deselect the Size from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Iship         | Dress       |
