#Author: Discovery QE

Feature: Verify Flyouts on SLP in DOMESTIC, ISHIP and REGISTRY mode

############################### DOMESTIC MODE ##########################################################

  @use_regression @domain_discovery @high @mcom @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify FLYOUT appears
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I hover on any category
    Then I verify that flyout menu is displayed
  # Notes: Verifies that flyout menu appears on hovering a random FOB

  @use_regression @priority_high @domain_discovery @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | DESIGNERS             |
      | EDITORIAL            |
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

  @use_regression @priority_high @domain_discovery @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Iship - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Iship mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | DESIGNERS             |
      | EDITORIAL             |
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

  @use_regression @priority_high @domain_discovery @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Registry - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Registry mode
    When I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | GETTING STARTED       |
      | DESIGNERS             |
      | DINING & ENTERTAINING |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | HOME CARE & TECH      |