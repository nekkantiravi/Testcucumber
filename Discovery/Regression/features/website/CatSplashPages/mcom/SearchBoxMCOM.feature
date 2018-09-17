#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify CategorySplashPage - Search Box functionality in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify Search Box functionality in DOMESTIC mode
    Given I am on CatSplash Page for "<fob>" on "domestic" mode
    When I enter "cap" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And  I click on search icon
    Then  I should be in Search Landing page
    Examples:
      | fob        |
      | BED & BATH |
      | SHOES      |
      | BEAUTY     |
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @migrated_to_sdt @mode_iship
  Scenario Outline: CategorySplashPage - Verify Search Box functionality in ISHIP mode
    Given I am on CatSplash Page for "<fob>" on "iship" mode
    When I enter "cap" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And  I click on search icon
    Then  I should be in Search Landing page
    Examples:
      | fob                    |
      | JEWELRY                |
      | MEN                    |
      | KIDS                   |
      | SHOES                  |
      | HANDBAGS & ACCESSORIES |
      | HOME                   |
      | WOMEN                  |
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

