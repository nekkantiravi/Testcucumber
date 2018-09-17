#Author: Discovery QE


Feature: Verifying Facets in Category browse page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @xbrowser_browse
  Scenario: CategoryBrowsePage - Domestic - Verify user can expand and collapse the same facet multiple times on browse pages in DOMESTIC mode
    Given I am on CategoryBrowsePage for "dresses" under "Women" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I click on any "expanded" facet
    Then I verify that the selected facet is "collapsed"
    When I click on the same "collapsed" facet
    Then I verify that the selected facet is "expanded"
    When I click on the same "expanded" facet
    Then I verify that the selected facet is "collapsed"

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Registry - Verify that the first value in price facet should be displayed as "Under $50" in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Price" facet is listed on left nav
    When I verify that the first value in price facet should be displayed as "Under $50"
    Examples:
    | shopping_mode |Category_Name    |FOB     |
    | Domestic      |Coats & Jackets  |Men     |
    | Registry      |Blenders         |Kitchen |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Registry - Verify that the first value in price facet should not be displayed as "$0 - $50" in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Price" facet is listed on left nav
    When I verify that the first value in price facet should not be displayed as "$0 - $50"
    Examples:
    | shopping_mode |Category_Name    |FOB     |
    | Domestic      |Coats & Jackets  |Men     |
    | Registry      |Blenders         |Kitchen |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - CategoryBrowsePage - To verify breadcrumbs are not displayed in DOMESTIC mode
    Given I am on CategoryBrowsePage for "dresses" under "Women" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    And I select random product from thumbnail grid
    And I verify that the selected breadcrumbs not displayed in pdp

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - To verify facet selection and closing in DOMESTIC mode
    Given I am on CategoryBrowsePage for "dresses" under "Women" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that selected facet gets deselected

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Navigate to browse page to verify navigation title as "filter by" in DOMESTIC mode
    Given I am on CategoryBrowsePage for "dresses" under "Women" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I verify that facet section displaying with 'filter by' header text
    #And I verify that "filter by" text is not clickable

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Clear displays under the facet header, after a value of that facet is selected in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Rings" under "JEWELRY" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I select 'single' facet value from 'any' facet section
    Then I verify that clear all button is displayed

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify sign in functionality after selecting facets in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Rings" under "JEWELRY" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    When I select Sign In link from header and sign in from the current page
    Then I verify that applied facet value is displayed

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Ability to handle custom price range option with validation in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Rings" under "JEWELRY" in Domestic mode
    And I clear existing class variable data to avoid data issues
    And I verify that "Price" facet is listed on left nav
    When I select minimum price as "20" and maximum price as "100" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    And I select "single" facet value from "Price" facet section
    And I verify that products are filtered with selected price facet value

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Select Clear all facet values selections at once in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Rings" under "JEWELRY" in Domestic mode
    And I clear existing class variable data to avoid data issues
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    Then I verify that clear all button is displayed
    When I click on clear all button
    Then I verify that the product count returns to original
    And I verify that clear all button is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that clear all button is displayed
    When I click on clear all button
    Then I verify that no facet is displayed in breadcrumb
    And I verify that the product count returns to original
    And I verify that clear all button is not displayed

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: Verify that the page display a proper error message for price range error in domestic mode
    Given I visit the web site as a guest user
    And I clear existing class variable data to avoid data issues
    When I navigate to "MEN" category page
    And I navigate to "Activewear" subsplash page from cat splash page
    And I select minimum price as "<min_value>" and maximum price as "<max_value>" range
    And I select 'GO' button from price facet
    Then I verify invalid price range facet selection is not applied
    Examples:
      | min_value | max_value |
      |           | 2         |
      |           |           |
      | 100       |  50       |
      | 100       |           |
      |           | 100       |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Registry|iSHIP - To verify facet selection and closing in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that facets are listed on left nav
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that selected facet gets deselected
    Examples:
    | shopping_mode |Category_Name    |FOB     |
    | Domestic      |Coats & Jackets  |Men     |
    | Registry      |Blenders         |Kitchen |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify Select facets and ensure facet and its product count get updated for each query, in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Rings" under "JEWELRY" in Domestic mode
    And I clear existing class variable data to avoid data issues
    And I verify that facets with product counts are displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    And I verify that the product count returns to original


  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify multiselect functionality on in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Rings" under "JEWELRY" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I verify that each facet types have multifacet functionality
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value