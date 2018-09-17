#=========================
# Author: Discovery SNBC QE
# Date Created: 21/06/2017
# Version One: B-83493
#=========================
Feature: Verify Quick View Overlay functionality in DynamicLanding Page contents in DOMESTIC and ISHIP mode

  # *********************** ADD TO BAG  AS Guest User for Domestic|Iship|Registry Mode *******************************************
  @domain_discovery @priority_medium @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify ADD TO BAG button on quick view overlay as guest user
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    Then I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
   Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify price on quick viw overlay for member/standard product
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I verify price on quick view overlay is same as on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  # Navigate to any browse page where we have member/standard products in each mode
  # Get price content section data from thumbnail grid and select quick view button for that member/standard product
  # Verify same price values are displayed on QV overlay or not

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify price on quick viw overlay for master product
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I select "quick view" button for "master" product on page
    Then I verify that quick peek is displayed
    Then I verify price on quick view overlay is same as on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Navigate to any browse page where we have master products in each mode
  # Get price content section data from thumbnail grid and select quick view button for that master product
  # Verify same price values are displayed on QV overlay or not