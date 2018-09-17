############################################
# Author: Discovery Regression QE Team
# Date: 27th June 2017
# Date Modified: None
############################################

Feature: Verify Category Splash page dsv features in DOMESTIC, ISHIP and REGISTRY mode

  ##################################### Left Nav Feature ############################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to sub categories from Left hand nav links
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - BED & BATH in DOMESTIC mode
    Given I am on CatSplash Page for "BED & BATH" on "domestic" mode
    And I navigate to sub categories from Left hand nav links
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - Juniors in DOMESTIC mode
    Given I am on CatSplash Page for "JUNIORS" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - KIDS  in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Left Hand Nav - SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - HANDBAGS in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - JEWELRY in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_22 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - WATCHES in DOMESTIC mode
    Given I am on CatSplash Page for "WATCHES" on "domestic" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - HOME DECOR in REGISTRY mode
    Given I am on CatSplash Page for "HOME DECOR" on "registry" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - BED & BATH in REGISTRY mode
    Given I am on CatSplash Page for "BED & BATH" on "registry" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - LUGGAGE  in REGISTRY mode
    Given I am on CatSplash Page for "LUGGAGE" on "registry" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - CLEANING & ORGANIZING  in REGISTRY mode
    Given I am on CatSplash Page for "CLEANING & ORGANIZING" on "registry" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Left Hand Nav - DINING & ENTERTAINING  in REGISTRY mode
    Given I am on CatSplash Page for "DINING" on "registry" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Left Hand Nav - KITCHEN  in REGISTRY mode
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    And I navigate to "random" browse page from cat splash page
    Then I verify the correct subcategory page is loaded

    ####################################### Red Link in LeftNav #######################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Red Links - HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - BED & BATH in DOMESTIC mode
    Given I am on CatSplash Page for "BED & BATH" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - Juniors in DOMESTIC mode
    Given I am on CatSplash Page for "JUNIORS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - HANDBAGS in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - JEWELRY in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_dsv  @dsv_desktop_sev2 @dsv_category_26 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Red Links - WATCHES in DOMESTIC mode
    Given I am on CatSplash Page for "WATCHES" on "domestic" mode
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  ###################################### Sub Ads Feature ############################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Sub Ads - BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify that Sub Ads of "BEAUTY" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  #Testlink-MCOM-57276 Vone - RT-07323
  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify that Sub Ads of "HOME" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - BED & BATH in DOMESTIC mode
    Given I am on CatSplash Page for "BED & BATH" on "domestic" mode
    Then I verify that Sub Ads of "BED & BATH" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify that Sub Ads of "WOMEN" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify that Sub Ads of "MEN" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify that Sub Ads of "KIDS" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - Juniors in DOMESTIC mode
    Given I am on CatSplash Page for "JUNIORS" on "domestic" mode
    Then I verify that Sub Ads of "JUNIORS" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify that Sub Ads of "SHOES" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - HANDBAGS & ACCESSORIES in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify that Sub Ads of "HANDBAGS" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - JEWELRY in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY" on "domestic" mode
    Then I verify that Sub Ads of "JEWELRY" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Sub Ads - WATCHES in DOMESTIC mode
    Given I am on CatSplash Page for "WATCHES" on "domestic" mode
    Then I verify that Sub Ads of "WATCHES" should be displayed on cat splash page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  ###################################### Featured Category Links ############################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_18 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify that Featured Categories of "HOME" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @use_dsv  @dsv_desktop_sev2 @dsv_category_33 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - BED & BATH in REGISTRY mode
    Given I am on CatSplash Page for "BED & BATH" on "registry" mode
    Then I verify that Featured Categories of "BED & BATH" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  ###################################### Main Ads Feature ############################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: Category Page - Verify Main Ad - DINING - Registry Mode
    Given I am on CatSplash Page for "DINING" on "registry" mode
    Then I verify that Main Ads of "DINING" should be displayed on cat splash page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: Category Page - Verify Main Ad - KITCHEN - Registry Mode
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    Then I verify that Main Ads of "KITCHEN" should be displayed on cat splash page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: Category Page - Verify Main Ad - BED & BATH - Registry Mode
    Given I am on CatSplash Page for "BED & BATH" on "registry" mode
    Then I verify that Main Ads of "BED & BATH" should be displayed on cat splash page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: Category Page - Verify Main Ad - HOME DECOR - Registry Mode
    Given I am on CatSplash Page for "HOME DECOR" on "registry" mode
    Then I verify that Main Ads of "HOME DECOR" should be displayed on cat splash page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: Category Page - Verify Main Ad - Luggage - Registry Mode
    Given I am on CatSplash Page for "LUGGAGE" on "registry" mode
    Then I verify that Main Ads of "LUGGAGE" should be displayed on cat splash page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_32 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: Category Page - Verify Main Ad - CLEANING & ORGANIZING - Registry Mode
    Given I am on CatSplash Page for "CLEANING & ORGANIZING" on "registry" mode
    Then I verify that Main Ads of "CLEANING & ORGANIZING" should be displayed on cat splash page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

   ##################################### Flyouts Feature #############################################

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @dsv_desktop_sev1
  Scenario Outline: CategorySplashPage - Verify FLYOUTS menu is displayed for all FOBs in DOMESTIC mode
    Given I am on CatSplash Page for "<fob>" on "domestic" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                  |
      | ACTIVE               |
      | BRANDS               |
      | HOME                 |
      | BED & BATH           |
      | WOMEN                |
      | MEN                  |
      | JUNIORS              |
      | KIDS                 |
      | BEAUTY               |
      | SHOES                |
      | HANDBAGS             |
      | JEWELRY              |
      | WATCHES              |

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario Outline: CategorySplashPage - Verify FLYOUTS menu is displayed for all FOBs in ISHIP mode
    Given I am on CatSplash Page for "<fob>" on "iship" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                     |
      | HOME                    |
      | BED & BATH              |
      | WOMEN                   |
      | MEN                     |
      | JUNIORS                 |
      | KIDS                    |
      | SHOES                   |
      | HANDBAGS & ACCESSORIES  |
      | JEWELRY                 |
      | WATCHES                 |

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario Outline: CategorySplashPage - Verify FLYOUTS menu is displayed for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<fob>" on "registry" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |

    #################################### Popular Searches #############################################

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic
  Scenario: CategorySplashPage - Verify Popular Related Searches section for MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic @discovery_daily_run
  Scenario: CategorySplashPage - Verify Popular Related Searches section for KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic
  Scenario: CategorySplashPage - Verify Popular Related Searches section for BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic
  Scenario: CategorySplashPage - Verify Popular Related Searches section for SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic
  Scenario: CategorySplashPage - Verify Popular Related Searches section for HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic
  Scenario: CategorySplashPage - Verify Popular Related Searches section for WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK

    ################################### Description in Copy Block #####################################

  @use_dsv @dsv_category_16 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - HOME in domestic mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify the Description section for "HOME" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_17 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - BED & BATH in domestic mode
    Given I am on CatSplash Page for "BED & BATH" on "domestic" mode
    Then I verify the Description section for "BED & BATH" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_1 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - WOMEN in domestic mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify the Description section for "WOMEN" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_16 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - MEN in domestic mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify the Description section for "MEN" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_17 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - Juniors in domestic mode
    Given I am on CatSplash Page for "JUNIORS" on "domestic" mode
    Then I verify the Description section for "Juniors" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_1 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - KIDS in domestic mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify the Description section for "KIDS" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_16 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: CategorySplashPage - Verify Description - BEAUTY in domestic mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify the Description section for "BEAUTY" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_17 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - SHOES in domestic mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify the Description section for "SHOES" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_1 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - HANDBAGS in domestic mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify the Description section for "HANDBAGS & ACCESSORIES" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_16 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - JEWELRY in domestic mode
    Given I am on CatSplash Page for "JEWELRY" on "domestic" mode
    Then I verify the Description section for "JEWELRY" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_16 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Description - WATCHES in domestic mode
    Given I am on CatSplash Page for "WATCHES" on "domestic" mode
    Then I verify the Description section for "WATCHES" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  ############################# SEO TAG CLOUD ############################################

  ##Clicking on some of the SEO tags are navigating to production site.##
  ##This scenario may fail when running in qa environment as the starting environment is different from the
  # navigated environment##
  ##Test Case ID: MCOM-85073, MCOM-86803MCOM-92055
   @dsv_desktop_sev2 @preview_desktop @dsv_dryrun @use_regression @migrated_to_sdt @domain_discovery @mode_domestic
  Scenario: CategorySplashPage - Verify SEO Tag cloud in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category splash page
    Then I should see the Popular Searches at the bottom
    And I verify the page for keyword which has "/shop/featured/" from popular searches

  ####################################### Linking to REGISTRY CATEGORIES #####################

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_registry @discovery_daily_run
  Scenario: CategorySplashPage - Verify the linking in the KITCHEN cat page (for DSV)
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    Then I verify the title for "KITCHEN" category page
    And I verify the basic attributes of cat splash page

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_registry
  Scenario: CategorySplashPage - Verify the linking in the HOME DECOR cat page (for DSV)
    Given I am on CatSplash Page for "HOME DECOR" on "registry" mode
    Then I verify the title for "HOME DECOR" category page
    And I verify the basic attributes of cat splash page

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_registry
  Scenario: CategorySplashPage - Verify the linking in the DINING cat page (for DSV)
    Given I am on CatSplash Page for "DINING" on "registry" mode
    Then I verify the title for "DINING" category page
    And I verify the basic attributes of cat splash page

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_registry
  Scenario: CategorySplashPage - Verify the "canonical" tag exist on Registry mode - Dining
    Given I am on CatSplash Page for "DINING" on "registry" mode
    Then I verify the "canonical" tag in HTML view source code
    When I navigate to sub categories from Left hand nav links
    Then I verify the correct subcategory page is loaded

  @use_dsv @domain_discovery @s4a_stable @use_regression @migrated_to_sdt @mode_domestic @priority_medium
  Scenario: CategorySplashPage - Verify MBOX in women category in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify MBOX in the page source
    # Notes:
    # "MBOX" should display in the page source
    # Description: Verify MBOX in women category
    # Steps:
    # 1.Navigate to macys.com
    # 2.Click on WomenDiscovery/Regression/features/website/DSV/mcom/BrowsePage.feature:153
    # 3.Right click > view source > search MBOX
    #
    # Expected Results:
    # 1. macys.com home page should be displayed.
    # 2.Women category should be displayed.
    # 3.MBOX should be there in view source.

  ###################################### Customer's Top Rated ###############################

  @dsv_category_1 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Shop Customer's Top Rated - Home in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @use_regression @migrated_to_sdt @domain_discovery @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify the "canonical" tag exist on Iship mode
    Given I am on CatSplash Page for "WOMEN" on "iship" mode
    Then I verify the "canonical" tag in HTML view source code

  @use_regression @migrated_to_sdt @domain_discovery @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: CategorySplashPage - Verify the canonical tag exist on Normal mode - MEN
    Given I visit the web site as a guest user
    When I navigate to "MEN" category page
    Then I verify the "canonical" tag in HTML view source code

  @use_regression @migrated_to_sdt @domain_discovery @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify the canonical tag exist on Normal mode - KIDS
    Given I visit the web site as a guest user
    When I navigate to "KIDS" category page
    Then I verify the "canonical" tag in HTML view source code

  @use_regression @migrated_to_sdt @domain_discovery @mode_domestic  @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Server names for Sign in and Cat pages
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I verify the server name "navapp or discovery_page" display in page source
    When I navigate to signin page of "site" mode
    Then I verify the server name "ShopApp" display in page source