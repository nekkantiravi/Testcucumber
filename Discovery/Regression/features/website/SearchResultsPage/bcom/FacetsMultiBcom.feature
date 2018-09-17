Feature: Verifying Facets in SLP


  @use_regression @domain_discovery @use_preview @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify LEFT NAV LINKS are displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    And I verify that search result page Facets displayed match with production
    Examples:
      |keyword   |shopping_mode|
      |jeans     |Domestic     |
      |dinnerware|REGISTRY     |
      |jeans     |ISHIP        |

  @use_regression @unifiednavigation_4157 @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify facet selection and closing in in DOMESTIC page
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    And I verify that previously selected facets persists in breadcrumb

  @use_regression @unifiednavigation_4157 @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify breadcrumbs are not displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    And I select random product from thumbnail grid
    And I verify that the selected breadcrumbs not displayed in pdp

  @use_regression  @unifiednavigation_11495 @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Clear displays under the facet header, after a value of that facet is selected in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value

  @domain_discovery @use_regression @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verifying search using the keyword that checks pagination in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    Then I verify that facets are listed on left nav
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    When I select "Best Sellers" in sort by drop down
    And I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value

  @use_regression @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify user can expand and collapse the same facet multiple times on SLP in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I click on any "expanded" facet
    Then I verify that the selected facet is "collapsed"
    When I click on the same "collapsed" facet
    Then I verify that the selected facet is "expanded"
    When I click on the same "expanded" facet
    Then I verify that the selected facet is "collapsed"

  @unifiednavigation_15589 @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the first value in price facet should be displayed as "Under $50" in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Price" facet is listed on left nav
    When I verify that the first value in price facet should be displayed as "Under $50"
    Examples:
      |keyword   |shopping_mode |
      |jeans     |Domestic      |
      |dinnerware| REGISTRY     |
     #|jeans     |Iship         |

  @unifiednavigation_15589 @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the first value in price facet should not be displayed as "$0 - $50" in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Price" facet is listed on left nav
    When I verify that the first value in price facet should not be displayed as "$0 - $50"
    Examples:
      |keyword   |shopping_mode |
      |jeans     | Domestic     |
      |dinnerware| REGISTRY     |
     #|jeans     | Iship        |

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify sign in functionality after selecting facets in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    When I select Sign In link from header and sign in from the current page
    Then I verify that products are filtered as per selected facet value

  @use_regression @unifiednavigation_11495 @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Clear displays under the facet header, after a value of that facet is selected in DOMESTIC mode
    Given I am on SearchResultsPage for "rings" in Domestic mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that clear all button is displayed

  @domain_discovery @use_regression @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verifying search using the keyword that contains special character in DOMESTIC mode
    Given I am on SearchResultsPage for "calvin & kelin" in Domestic mode
    And I clear existing class variable data to avoid data issues
    Then I verify that facets are listed on left nav
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    When I select "New Arrivals" in sort by drop down
    And I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value

  @use_regression @unifiednavigation_12761 @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify Ability to handle custom price range option with validation in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    And I should see "Price" facet listed on left nav
    When I select minimum price as "20" and maximum price as "100" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    And I select "single" facet value from "Price" facet section
    And I verify that products are filtered with selected price facet value

