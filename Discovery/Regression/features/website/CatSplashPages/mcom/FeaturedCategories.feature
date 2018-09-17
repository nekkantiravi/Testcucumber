# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify CatSplash Pages - Featured Categories

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - BED & BATH in DOMESTIC mode
    Given I am on CatSplash Page for "BED & BATH" on "domestic" mode
    Then I verify that Featured Categories of "BED & BATH" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - WOMEN
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify that Featured Categories of "WOMEN" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify that Featured Categories of "MEN" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify that Featured Categories of "KIDS" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Featured Categories - BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify that Featured Categories of "BEAUTY" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify that Featured Categories of "SHOES" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken


  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HANDBAGS in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then  I verify that Featured Categories of "HANDBAGS" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - JEWELRY
    Given I am on CatSplash Page for "JEWELRY" on "domestic" mode
    Then I verify that Featured Categories of "JEWELRY" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - WATCHES in DOMESTIC mode
    Given I am on CatSplash Page for "WATCHES" on "domestic" mode
    Then I verify that Featured Categories of "WATCHES" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Kitchen in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Kitchen" browse page from cat splash page
    Then I verify that Featured Categories of "Kitchen" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Dining & Entertaining in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Dining & Entertaining" browse page from cat splash page
    Then I verify that Featured Categories of "Dining & Entertaining" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Furniture in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Furniture" browse page from cat splash page
    Then I verify that Featured Categories of "Furniture" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Apartment Living in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Apartment Living" browse page from cat splash page
    Then I verify that Featured Categories of "Apartment Living" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Mattresses in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Mattresses" browse page from cat splash page
    Then I verify that Featured Categories of "Mattresses" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Outdoor Furniture in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Furniture" browse page from cat splash page
    Then I verify that Featured Categories of "Furniture" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Rugs in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Rugs" browse page from cat splash page
    Then I verify that Featured Categories of "Rugs" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Window Treatments in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Window Treatments" browse page from cat splash page
    Then I verify that Featured Categories of "Window Treatments" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Luggage in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Luggage & Backpacks" browse page from cat splash page
    Then I verify that Featured Categories of "Luggage & Backpacks" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Lighting & Lamps in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Lighting & Lamps" browse page from cat splash page
    Then I verify that Featured Categories of "Lighting & Lamps" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Ralph Lauren in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Lauren Ralph Lauren" browse page from cat splash page
    Then I verify that Featured Categories of "Lauren Ralph Lauren" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - HOME - Holiday Lane in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    And I navigate to "Holiday Lane" browse page from cat splash page
    Then I verify that Featured Categories of "Holiday Lane" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - WOMEN - Swimwear in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    And I navigate to "Swimwear" browse page from cat splash page
    Then I verify that Featured Categories of "Swimwear" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - WOMEN - Maternity in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    And I navigate to "Maternity" browse page from cat splash page
    Then I verify that Featured Categories of "Maternity" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - WOMEN - Activewear in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    And I navigate to "Activewear" browse page from cat splash page
    Then I verify that Featured Categories of "Activewear" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - MEN - Big & Tall in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    And I navigate to "Big & Tall" browse page from cat splash page
    Then I verify that Featured Categories of "Big & Tall" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - MEN - Guys in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    And I navigate to "Guys" browse page from cat splash page
    Then I verify that Featured Categories of "Guys" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - MEN - Shoes in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    And I navigate to "All Men's Shoes" browse page from cat splash page
    Then I verify that Featured Categories of "All Men's Shoes" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - KIDS - Shop All Baby in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    And I navigate to "All Baby" browse page from cat splash page
    Then I verify that Featured Categories of "All Baby" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - JEWELRY - Watches in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY" on "domestic" mode
    And I navigate to "Watches" browse page from cat splash page
    Then I verify that Featured Categories of "Watches" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - DINING  in REGISTRY mode
    Given I am on CatSplash Page for "DINING" on "registry" mode
    Then I verify that Featured Categories of "DINING" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  # Data Not Available as DML's are overridden with production data.
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high @wip
  Scenario: CategorySplashPage - Verify Featured Categories - KITCHEN in REGISTRY mode
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    Then I verify that Featured Categories of "KITCHEN" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken


  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Featured Categories - HOME DECOR in REGISTRY mode
    Given I am on CatSplash Page for "HOME DECOR" on "registry" mode
    Then I verify that Featured Categories of "HOME DECOR" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - LUGGAGE in REGISTRY mode
    Given I am on CatSplash Page for "LUGGAGE" on "registry" mode
    Then I verify that Featured Categories of "LUGGAGE" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Featured Categories - CLEANING & ORGANIZING in REGISTRY mode
    Given I am on CatSplash Page for "CLEANING & ORGANIZING" on "registry" mode
    Then I verify that Featured Categories of "CLEANING & ORGANIZING" should be displayed on cat splash page
  # Notes:
  # Do full validation -
  # Verify links navigate correctly
  # Verify images are not broken

    #Testlink-MCOM-65793 Vone - RT-07333
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_low @wip
  Scenario: CategorySplashPage - Verification for no 404 Errors and no 2nd call requests in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I should not see 404 error code in homepage
#    And I should not see second call request
  # For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

    #Notes: #Verify no 404 Error status codes should be displayed in http watch
    #Verify that no second call request should be sent
    #Eg:  a). when u click women on the FOB , no 404 should be seen in the status codes,
    #  b)http://www1.macys.com/shop/womens?id=118& edge=hybrid request should be sent .
    #  c)We should make sure  the following URL request should not be sent  http://www1.macys.com/shop/   ( this is the Second call )

  #Testlink-MCOM-65793 Vone - RT-07333
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_low @wip
  Scenario: CategorySplashPage - Verification for no 404 Errors and no 2nd call requests in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I navigate to random category splash page
    Then I should not see 404 error code in homepage
#    And I should not see second call request
# For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

    #Notes: #Verify no 404 Error status codes should be displayed in http watch
    #Verify that no second call request should be sent
    #Eg:  a). when u click women on the FOB , no 404 should be seen in the status codes,
    #  b)http://www1.macys.com/shop/womens?id=118& edge=hybrid request should be sent .
    #  c)We should make sure  the following URL request should not be sent  http://www1.macys.com/shop/   ( this is the Second call )
