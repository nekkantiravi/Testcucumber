#Author: Discovery QE

Feature: Verify Flyouts on SLP in DOMESTIC, ISHIP and REGISTRY mode

############################### DOMESTIC MODE ##########################################################

  @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @v1
  Scenario: SearchResultsPage - Domestic - Verify FLYOUT appears
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I hover on any category
    Then I verify that flyout menu is displayed
  # Notes: Verifies that flyout menu appears on hovering a random FOB

  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic @use_regression_3 @please_automate
  Scenario Outline: SearchResultsPage - Domestic - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | DESIGNERS             |
      | WHAT'S NEW            |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | GIFTS                 |
      | THE REGISTRY          |
      | SALE                  |

  ############################### ISHIP MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in ISHIP mode as well

  @use_regression @priority_high @artifact_navapp @domain_discovery @use_regression_3 @please_automate
  Scenario Outline: SearchResultsPage - Iship - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Iship mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | DESIGNERS             |
      | WHAT'S NEW            |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  ############################### REGISTRY MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in registry mode as well

  @use_regression @priority_high @artifact_navapp @domain_discovery @use_regression_3 @please_automate
  Scenario Outline: SearchResultsPage - Registry - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Registry mode
    When I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | GETTING STARTED       |
      | BRANDS                |
      | DINING & ENTERTAINING |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | HOME CARE & TECH      |