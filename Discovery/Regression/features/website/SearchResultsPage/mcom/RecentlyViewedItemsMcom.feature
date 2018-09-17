#=========================
# Author: Discovery SNBC QE
# Date Created: 28/06/2017
# Version One: B-83485
#=========================
Feature: Verify Recently View Items Panel on Search Results Page contents in ALL mode

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Edit button is displayed on RVI Panel on Search Results Page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    And I navigate to bottom of page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items
  Examples:
    | shopping_mode | keyword    |
    | Domestic      | Dresses    |
    | Registry      | hangers    |
    | Iship         | handbags   |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search @discovery_daily_run
  Scenario Outline:PDP Page - Domestic|Iship|Registry - Verify Edit button is displayed on RVI Panel on PDP Page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I construct RVI cookie with 8 products and reload the page
    Then I verify the display of RVI in "search result" page
    And  I should see edit option inside Recently Viewed panel
    When I click right navigation button in rvi panel
    Then I should move towards next set of products
    When I click left navigation button in rvi panel
    Then I should move towards previous set of products
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    And I verify the display of RVI in "product display" page
    Examples:
      | shopping_mode | keyword    |
      | Domestic      | Dresses    |
      | Registry      | hangers    |
      | Iship         | handbags   |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: All pages - Domestic|Iship|Registry - Verify Recently viewed items should display in all pages
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to a product having "orderable and available" attributes
    And I navigate to a product having "customer_top_rated and orderable" attributes
    And I navigate to search results page
    And I navigate to bottom of page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I navigate to bottom of page
    Then I verify that edit option inside Recently Viewed panel is displayed
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |


  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the RVI functionality for max products, recent items and products move after removing items
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I construct RVI cookie with 30 products and reload the page
    And I navigate to bottom of page
    Then I verify that edit option inside Recently Viewed panel is displayed
    And I verify maximum of 31 products displayed in RVI panel
    And I verify 6 Recent products displayed at one time in RVI panel
    And I should see the navigation arrow buttons
    When I click on "right" arrow key inside RVI panel
    Then I verify that next set of products are displayed
    When I click on "left" arrow key inside RVI panel
    Then I verify that previous set of products are displayed
    When I click "edit" button in RVI panel
    Then I should see highlighted Remove button on each product and Edit button displayed as Done in RVI panel
    When I click on "Remove" button on any product
    Then I verify product is removed temporally
    When I click outside of RVI panel
    Then I verify removed product displayed again in RVI Panel
    When I click "edit" button in RVI panel
    And I click on "Remove" button on any product
    And I click "done" button in RVI panel
    Then I should see product is removed from RVI panel
    Examples:
      | shopping_mode | keyword  |
      | Domestic      | Dresses  |
      | Registry      | knife    |
      | Iship         | handbags |
