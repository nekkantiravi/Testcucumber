#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Search Box functionality on CatSplash in DOMESTIC, ISHIP and REGISTRY mode


  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify Search Box functionality for all FOBs in DOMESTIC mode
    Given I am on CatSplash Page for "<FOB>" on "domestic" mode
    When I enter "caps" keyword in global search field
    Then I "should see" autocomplete suggestions list
    When I click on search icon
    Then I verify that landed on "search_result" Page
  Examples:
    | FOB                   |
    | DESIGNERS             |
    | EDITORIAL             |
    | MEN                   |
    | KIDS                  |
    | JEWELRY & ACCESSORIES |
    | SHOES                 |
    | HANDBAGS              |
    | SALE                  |
    | HOME                  |
    | WOMEN                 |
    | BEAUTY                |
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

   @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_high
    Scenario Outline: CategorySplashPage - Verify Search Box functionality for all FOBs in ISHIP mode
     Given I am on CatSplash Page for "<FOB>" on "iship" mode
     When I enter "caps" keyword in global search field
     Then I "should see" autocomplete suggestions list
     When I click on search icon
     Then I verify that landed on "search_result" Page
  Examples:
    | FOB                   |
    | SALE                  |
    | HOME                  |
    | WOMEN                 |
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify Search Box functionality for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<FOB>" on "registry" mode
    When I enter "caps" keyword in global search field
    Then I "should not see" autocomplete suggestions list
    When I click on search icon
    Then I verify that landed on "search_result" Page
  Examples:
    | FOB                   |
    | KITCHEN               |
    | BED & BATH            |
    | DINING & ENTERTAINING |
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text
    #Autocomplete suggestions are not displayed in Registry mode. That is a expected behaviour (ECOMSST-48207)
