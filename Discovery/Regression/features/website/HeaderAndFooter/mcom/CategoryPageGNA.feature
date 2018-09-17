# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify Category Page GNA

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - HOME in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - BED & BATH  in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "BED & BATH" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - WOMEN in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - MEN in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - Juniors in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "JUNIORS" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - KIDS in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "KIDS" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - BEAUTY in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "BEAUTY" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - SHOES in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "SHOES" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - HANDBAGS in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HANDBAGS" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - JEWELRY in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "JEWELRY" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Category Page - Verify GNA consistency - WATCHES in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WATCHES" category page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Kitchen in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Kitchen" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Dining & Entertaining in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Dining & Entertaining" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Furniture in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Furniture" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Outdoor & Patio Furniture in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Outdoor & Patio Furniture" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Mattresses in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Mattresses" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Outdoor Furniture in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Furniture" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Rugs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Rugs" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Window Treatments in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Window Treatments" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Luggage in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "luggage & backpacks" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Lighting & Lamps in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Lighting & Lamps" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Cleaning & Organization in DOMESTIC mode ing
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Cleaning & Organization" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HOME - Vacuums & Steam Cleaners in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Vacuums & Steam Cleaners" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - WOMEN - Plus Sizes in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to "All Plus Sizes" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - WOMEN - Maternity in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to "Maternity" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - WOMEN - All Petites in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to "All Petites" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - MEN - Big & Tall in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Big & Tall" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - MEN - Guys in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Guys" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - MEN - Shorts in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Shorts" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub Category Page - Verify GNA consistency - KIDS - Shop All Baby in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "KIDS" category page
    And I navigate to "All Baby" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub Category Page - Verify GNA consistency - HANDBAGS - Luggage in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HANDBAGS" category page
    And I navigate to "Backpacks" browse page from cat splash page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: Category Page - Verify GNA consistency - DINING & ENTERTAINING - in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "DINING" category page from the registry
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: Category Page - Verify GNA consistency - KITCHEN - in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "KITCHEN" category page from the registry
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario:Category Page - Verify GNA consistency - BED & BATH - in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "BED & BATH" category page from the registry
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: Category Page - Verify GNA consistency - HOME DECOR - in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "HOME DECOR" category page from the registry
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: Category Page - Verify GNA consistency - LUGGAGE - in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "LUGGAGE" category page from the registry
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: Category Page - Verify GNA consistency - CLEANING & ORGANIZING - in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "CLEANING & ORGANIZING" category page from the registry
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # Category Page
