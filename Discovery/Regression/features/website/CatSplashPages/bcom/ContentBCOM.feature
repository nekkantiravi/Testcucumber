#Author: Discovery QE
#Date Created: 10/5/2015

Feature: Verify each FOB CatSplash Content

  #Validate Main Ad, Shop by Category, Happening Now
#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify Content Rows - DESIGNERS in DOMESTIC mode
    Given I am on CatSplash Page for "DESIGNERS" on "domestic" mode
    Then I verify all media links and popups are not resulting error page

    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

  #Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content - HANDBAGS in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

  #Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - JEWELRY & ACCESSORIES in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY & ACCESSORIES" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

  #Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - THE REGISTRY in DOMESTIC mode
    Given I am on CatSplash Page for "THE REGISTRY" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - SALE in DOMESTIC mode
    Given I am on CatSplash Page for "SALE" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

#Testlink-BLCOM-65290
  #Vone-RT-06298
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage  - Verify media content  - EDITORIAL in DOMESTIC mode
    Given I am on CatSplash Page for "EDITORIAL" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party

  #TBD - need the same scenarios for ISHIP & REGISTRY mode