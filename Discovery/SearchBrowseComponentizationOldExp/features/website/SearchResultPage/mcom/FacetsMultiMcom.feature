#Author: Discovery QE


Feature: Verifying Facets in SearchResultsPage

  @use_regression @artifact_navapp @domain_discovery @priority_medium @release_13G @use_regression_2 @mode_domestic
  Scenario: SearchResultsPage - Domestic - Verify user can expand and collapse the same facet multiple times on SLP in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I verify that facets are listed on left nav
    And I click on any "expanded" facet
    Then I verify that the selected facet is "collapsed"
    When I click on the same "collapsed" facet
    Then I verify that the selected facet is "expanded"
    When I click on the same "expanded" facet
    Then I verify that the selected facet is "collapsed"

  @unifiednavigation_15589 @use_regression @artifact_navapp @domain_discovery @priority_low @mode_domestic @use_regression_2 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the first value in price facet should be displayed as "Under $50" in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that "Price" facet is listed on left nav
    When I verify that the first value in price facet should be displayed as "Under $50"
    Examples:
      |keyword   |shopping_mode|
      |jeans     |Domestic     |
      |dinnerware| REGISTRY     |
   #|jeans     |Iship |

  @unifiednavigation_15589 @use_regression @artifact_navapp @domain_discovery @priority_low @mode_domestic @use_regression_2 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the first value in price facet should not be displayed as "$0 - $50" in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that "Price" facet is listed on left nav
    When I verify that the first value in price facet should not be displayed as "$0 - $50"
    Examples:
      |keyword   |shopping_mode|
      |jeans     |Domestic     |
      |dinnerware| REGISTRY   |
   #|jeans     |Iship|

  @use_regression @unifiednavigation_4157 @artifact_navapp @domain_discovery @priority_high @analytics @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - To verify breadcrumbs are not displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    And I select random product from thumbnail grid
    And I verify that the selected breadcrumbs not displayed in pdp

  @use_regression @unifiednavigation_4157 @artifact_navapp @domain_discovery @priority_high @use_regression_2 @mode_domestic @use_domain_qual @mcom @project_snb
  Scenario: SearchResultsPage - Domestic - To verify facet selection and closing in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that selected facet gets deselected

  @use_regression @unifiednavigation_12226 @artifact_navapp @domain_discovery @priority_low @bat_refactored @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Navigate to search result page to verify navigation title as "filter by" in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I verify that facets are listed on left nav
    And I verify that facet section displaying with 'filter by' header text
    #And I verify that "filter by" text is not clickable

  @use_regression  @unifiednavigation_11495 @artifact_navapp @domain_discovery @priority_medium @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Clear displays under the facet header, after a value of that facet is selected in DOMESTIC mode
    Given I am on SearchResultsPage for "rings" in Domestic mode
    When I verify that facets are listed on left nav
    And I select 'single' facet value from 'any' facet section
    Then I verify that clear all button is displayed

  @use_regression @artifact_navapp @domain_discovery @mode_domestic @priority_medium
  Scenario: SearchResultsPage - Domestic - Verify sign in functionality after selecting facets in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I verify that facets are listed on left nav
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    When I select Sign In link from header and sign in from the current page
    Then I verify that applied facet value is displayed

  @use_regression @unifiednavigation_12761 @artifact_navapp @domain_discovery @priority_medium @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Ability to handle custom price range option with validation in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I verify that "Price" facet is listed on left nav
    When I select minimum price as "20" and maximum price as "100" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    And I select "single" facet value from "Price" facet section
    And I verify that products are filtered with selected price facet value

  @use_regression @unifiednavigation_11496 @artifact_navapp @domain_discovery @priority_high @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Select Clear all facet values selections at once in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    Then I verify that clear all button is displayed
    When I click on clear all button
    And I verify that products are filtered as per selected facet value
    And I verify that clear all button is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that clear all button is displayed
    When I click on clear all button
    Then I verify that selected facet gets deselected
    And I verify that products are filtered as per selected facet value
    And I verify that clear all button is not displayed

  @release_14c @priority_medium @artifact_shopapp @domain_discovery @project_snb
  Scenario Outline: Verify that the page display a proper error message for price range error in domestic mode
    Given I visit the web site as a guest user
    When I navigate to Sub Splash page in SITE mode
    And I select minimum price as "<min_value>" and maximum price as "<max_value>" range
    And I select 'GO' button from price facet
    Then I verify that the error message is shown
    Examples:
      | min_value | max_value |
      |           | 2         |
      |           |           |
      | 100      | 50       |
      | 100       |           |
      |           | 100       |

  @use_regression @unifiednavigation_4157 @artifact_navapp @domain_discovery @priority_high @use_regression_2 @mode_domestic @use_domain_qual @mcom @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Registry|iSHIP - To verify facet selection and closing in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that facets are listed on left nav
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that selected facet gets deselected
    Examples:
      |keyword   |shopping_mode|
      |jeans     |Domestic     |
      |dinnerware| REGISTRY   |
      |jeans      | ISHIP       |

  @use_regression @unifiednavigation_12763 @artifact_navapp @domain_discovery @priority_medium @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Select facets and ensure facet and its product count get updated for each query, in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I verify that facets with product counts are displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    Then I verify that products are filtered as per selected facet value


  @unifiednavigation_11869 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @mode_domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify multiselect functionality on in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I verify that facets are listed on left nav
    And I verify that each facet types have multifacet functionality
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value