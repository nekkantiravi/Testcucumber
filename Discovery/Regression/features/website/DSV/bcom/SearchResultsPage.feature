############################################
# Author: Discovery Regression QE Team
# Date: 15th June 2017
# Date Modified: None
############################################

Feature: Verify search results page dsv features in DOMESTIC, ISHIP and REGISTRY mode

  @use_dsv @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_medium @use_bat @akamai_waf
  Scenario: SearchResultsPage - Domestic - Search for a keyword using autocomplete in DOMESTIC mode
    Given I visit the web site as a guest user
    When I enter "wa" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed
    When I select random autocomplete suggestion
    Then I should be in Search Landing page
    And I verify that facets are listed on left nav
    When I select 4 facet value(s) from 'any' facet sections
    Then I verify that clear all button is displayed
    When I click on clear all button
    Then I verify that all of the products are displayed
# Notes:
# autocomplete should  display 10 items and typed characters are displayed in bold in autocomplete items
# search result page should display. Verify the title of the page should be of the format <keyword used for search>| Bloomingdales

  #BLCOM-80263 BLCOM-80235 @mode_registry
  @use_dsv @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_medium @ifs
  Scenario: SearchResultsPage - Verify the search results page for the keyword -Plates in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "plates"
    Then I should be in Search Landing page
    And I should see page navigated to "/shop/registry/wedding/search" pattern url
#  Notes:
#  3.Search result page should display with" /shop/wedding-registry/<Search keyword> in url

  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_iship
  Scenario: SearchResultsPage - Verify search in iship mode
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    When I type "wo" in search box
    Then I should see autocomplete suggestions
    When I search for "dresses"
    Then I should be in Search Landing page
    And I verify product count
    And I verify the display of GNA and GFA

  @use_regression @migrated_to_sdt @artifact_navapp @domain_discovery @priority_low @dsv_desktop_sev2
  Scenario: SearchResultsPage - UN - Keyword search for less than 4 products in DOMESTIC mode
    Given I am on SearchResultsPage for "bread maker" in Domestic mode
    Then I verify that the product count is "less" than "4"
    And I verify that pagination is not displayed
    And I verify that the Sort By is not displayed

  @use_regression @migrated_to_sdt @artifact_navapp @domain_discovery @dsv_desktop_sev2 @akamai_waf
  Scenario: SearchResultsPage - Verify facet, pagination, sortby option and UI on search in DOMESTIC mode
    Given I am on SearchResultsPage for "dresses" in Domestic mode
    Then I verify that pagination is displayed
    And I verify that the Sort By displayed with all options
    And I should see facets listed on left nav
    And I verify product count

  @B-45480 @use_regression @migrated_to_sdt @artifact_navapp @domain_discovery @dsv_desktop_sev2
  Scenario Outline: SearchResultsPage - Verify searching for top brands redirects to zero search results page in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Canada"
    And I close the welcome mat if it's visible
    When I search for "<brand_name>"
    Then I should be landed on Zero Results Page
    Examples:
      | brand_name   |
      | burberry     |
      | ugg          |
      | ralph lauren |
      | clinique     |

  @use_dsv_severity1 @domain_discovery @use_regression @mode_domestic @priority_medium @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Search for a keyword
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I enter "WO" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed
    When I select "woks" from autocomplete suggestions
    Then I should be in Search Landing page
    And I modify the url to get in to snbc experiment
    And I verify that facets are listed on left nav
# Notes:
# autocomplete should  display 10 items and typed characters are displayed in bold in autocomplete items
# search result page should display. Verify the title of the page should be of the format <keyword used for search>| Bloomingdales

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @use_regression @migrated_to_sdt @feature_search_results_page @dsv_sev2_dryrun @akamai_waf
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the size facet
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "single" facet value from "Size" facet section
    And I select 'customer service' link from header
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
