#Author: Discovery QE

Feature: Verify Flyouts on Category Browse Page in DOMESTIC, ISHIP and REGISTRY mode

############################### DOMESTIC MODE ##########################################################

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run @xbrowser_browse
  Scenario: CategoryBrowsePage - Domestic - Verify FLYOUT appears
    Given I am on CategoryBrowsePage for "Flats" under "Shoes" in Domestic mode
    When I hover on any category
    Then I verify that flyout menu is displayed
  # Notes: Verifies that flyout menu appears on hovering a random FOB

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on CategoryBrowsePage for "Flats" under "Shoes" in Domestic mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob        |
      | HOME       |
      | BED & BATH |
      | WOMEN      |
      | MEN        |
      | JUNIORS    |
      | KIDS       |
      | BEAUTY     |
      | SHOES      |
      | HANDBAGS   |
      | JEWELRY    |
      | WATCHES    |
      | BRANDS     |

  ############################### ISHIP MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in ISHIP mode as well

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Iship - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on CategoryBrowsePage for "Flats" under "Shoes" in Iship mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                    |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | SHOES                  |
      | HANDBAGS & ACCESSORIES |
      | JEWELRY                |
      | WATCHES                |
      | BRANDS                 |

  ############################### REGISTRY MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in registry mode as well

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: SearchResultsPage - Registry - Verify FLYOUTS menu is displayed for all FOBs
     Given I am on CategoryBrowsePage for "Cookware & Cookware Sets" under "Kitchen" in Registry mode
    When I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | WEDDING REGISTRY      |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |