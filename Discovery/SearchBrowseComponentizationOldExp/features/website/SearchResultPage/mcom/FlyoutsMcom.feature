#Author: Discovery QE

Feature: Verify Flyouts on SLP in DOMESTIC, ISHIP and REGISTRY mode

############################### DOMESTIC MODE ##########################################################

  @use_domain_qual @domain_discovery @artifact_navapp @high @mcom @project_snb
  Scenario: SearchResultsPage - Domestic - Verify FLYOUT appears
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I hover on any category
    Then I verify that flyout menu is displayed
  # Notes: Verifies that flyout menu appears on hovering a random FOB

  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic @use_regression_3 @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Domestic mode
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

  @use_regression @priority_high @artifact_navapp @domain_discovery @use_regression_3 @project_snb
  Scenario Outline: SearchResultsPage - Iship - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Iship mode
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

  @use_regression @priority_high @artifact_navapp @domain_discovery @use_regression_3 @project_snb
  Scenario Outline: SearchResultsPage - Registry - Verify FLYOUTS menu is displayed for all FOBs
    Given I am on SearchResultsPage for "plates" in Registry mode
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