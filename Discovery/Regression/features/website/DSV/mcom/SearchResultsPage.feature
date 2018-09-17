############################################
# Author: Discovery Regression QE Team
# Date: 20th June 2017
# Date Modified: None
############################################

Feature: Verify search results page dsv features in DOMESTIC, ISHIP and REGISTRY mode

  @use_dsv @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @priority_high @mode_domestic @use_bat @xbrowser_search @discovery_daily_run @akamai_waf
  Scenario: SearchResultsPage - Search for a keyword in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type "sh" in search box
    Then I should see autocomplete suggestions
    When I select random autocomplete suggestion
    Then I should be in Search Landing or redirected Browse Page page
    And I should see facets listed on left nav

  # @13g
  @use_dsv @dsv_desktop_sev1 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic @ifs @xbrowser_search
  Scenario: SearchResultsPage - Verify pagination, Items per page, pricing, ratings & reviews in DOMESTIC mode
    Given I am on SearchResultsPage for "skirts" in domestic mode
    Then I should be in Search Landing page
    And I verify that pagination is displayed
    And I verify that "60" item count option and respective number of products on page
    And I should see basic attributes of Search Results page

  #MCOM-92121
  @use_dsv @dsv_desktop_sev1 @use_regression @migrated_to_sdt @domain_discovery @mode_iship @ifs @xbrowser_search
  Scenario: SearchResultsPage - Verify zero results search in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "fghdds"
    Then I verify that the search results should be in zero results page

  @use_regression @migrated_to_sdt @domain_discovery @priority_low @use_dsv  @dsv_desktop_sev2 @xbrowser_search
  Scenario: SearchResultsPage - Verify keyword search for less than 4 products results keyword in DOMESTIC mode
    Given I am on SearchResultsPage for "bread maker" in Domestic mode
    Then I verify that the product count is "less" than "4"
    And I verify that pagination is not displayed

  @domain_discovery @priority_high @use_dsv @mode_domestic @use_regression @migrated_to_sdt  @dsv_desktop_sev2
  Scenario: SearchResultsPage - Verify JSESSIONID cookie in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    Then I verify that "JSESSIONID" cookie is not displayed
  # Notes:
  # Test case description
  # View JSESSIONID cookie - HP
  # Test case steps (MCOM)
  # 1. Navigate to macys.com
  # 2. enter "javascript:document.cookie" in address bar and click enter and
  # Search for JSESSIONID cookie in the resulted page
  # Test case expected result (MCOM)
  # 1. Macys.com home page should display
  # 2. JSESSIONID should not be present.
  # Test case steps (BCOM)
  # 1. Navigate bloomingdales.com
  # 2. enter "javascript:document.cookie" in address bar and click enter and
  # Search for JSESSIONID cookie in the resulted page
  # Test case expected result (BCOM)
  # 1. Bloomingdales.com home page should display
  # 2. JSESSIONID should not be present.

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @use_regression @migrated_to_sdt @feature_search_results_page  @dsv_desktop_sev2 @discovery_daily_run @akamai_waf
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the size facet
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    When I select "single" facet value from "Size" facet section
    And I select 'customer service' link from header
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
#      | Registry      |

  @dsv_desktop_sev1 @domain_discovery @mode_iship @use_regression @migrated_to_sdt
  Scenario: SearchResultsPage - Verify search in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    When I type "wo" in search box
    Then I should see autocomplete suggestions
    When I search for "dresses"
    Then I should be in Search Landing page
    And I verify product count
