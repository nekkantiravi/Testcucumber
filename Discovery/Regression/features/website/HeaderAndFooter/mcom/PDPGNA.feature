# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify GNA in PDP Page for every Category

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: PDP Page - Verify GNA consistency - HOME in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - BED & BATH in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "BED & BATH" category page
    And I navigate to category browse page from "BED & BATH"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:PDP Page - Verify GNA consistency - WOMEN in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to category browse page from "WOMEN"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - MEN in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to category browse page from "MEN"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - Juniors in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "JUNIORS" category page
    And I navigate to category browse page from "Juniors"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:PDP Page - Verify GNA consistency - KIDS in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "KIDS" category page
    And I navigate to category browse page from "KIDS"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - BEAUTY in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "BEAUTY" category page
    And I navigate to category browse page from "BEAUTY"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - SHOES in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "SHOES" category page
    And I navigate to category browse page from "SHOES"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - HANDBAGS in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HANDBAGS" category page
    And I navigate to category browse page from "HANDBAGS & ACCESSORIES"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - JEWELRY in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "JEWELRY" category page
    And I navigate to category browse page from "JEWELRY"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - WATCHESin DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WATCHES" category page
    And I navigate to category browse page from "WATCHES"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Kitchen in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Kitchen" browse page from cat splash page
    And I navigate to category browse page from "Kitchen"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Dining & Entertaining in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Dining & Entertaining" browse page from cat splash page
    And I navigate to category browse page from "Dining & Entertaining"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Furniture in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Furniture" browse page from cat splash page
    And I navigate to category browse page from "Furniture"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Outdoor & Patio Furniture in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Outdoor & Patio Furniture" browse page from cat splash page
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Mattresses in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Mattresses" browse page from cat splash page
    And I navigate to category browse page from "Mattresses"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  #Obsolete verification."Outdoor Furniture" link navigated in to a browse page
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Outdoor Furniture in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Furniture" browse page from cat splash page
    And I navigate to "Outdoor Dining Sets" browse page from cat splash page
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Rugs in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Rugs" browse page from cat splash page
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Lenox in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Lenox" browse page from cat splash page
    And I navigate to category browse page from "Lenox"
    And I click on any Product Thumbnail in search results page
    Then I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Luggage in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Luggage & Backpacks" browse page from cat splash page
    And I navigate to category browse page from "Luggage & Backpacks"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Lighting & Lamps in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Lighting & Lamps" browse page from cat splash page
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Cleaning & Organization in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Cleaning & Organization" browse page from cat splash page
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  #  @dsv_category_24 @use_regression
  # Commented out as Data for this section is not available.
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - HOME - Window Treatments in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Window Treatments" browse page from cat splash page
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub PDP Page - Verify GNA consistency - WOMEN - Plus Sizes in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to "All Plus Sizes" browse page from cat splash page
    And I navigate to category browse page from "Plus Sizes"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub PDP Page - Verify GNA consistency - WOMEN - Petite in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to "All Petites" browse page from cat splash page
    And I navigate to category browse page from "Petite"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - WOMEN - Lingerie in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "WOMEN" category page
    And I navigate to "Leggings" browse page from cat splash page
    And I navigate to category browse page from "Leggings"
    And I click on any Product Thumbnail in search results page
    Then I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  # Added wip tag because expected category is not available in production site
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - MEN - Big & Tall in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Big & Tall" browse page from cat splash page
    And I navigate to category browse page from "Big & Tall"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - MEN - Hoodies & Sweatshirts in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Hoodies & Sweatshirts" browse page from cat splash page
    And I navigate to category browse page from "Hoodies & Sweatshirts"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub PDP Page - Verify GNA consistency - MEN - Shorts in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Shorts" browse page from cat splash page
    And I navigate to category browse page from "Shorts"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario:Sub PDP Page - Verify GNA consistency - KIDS - Shop All Girls in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "KIDS" category page
    And I navigate to "All Girls" browse page from cat splash page
    And I navigate to category browse page from "All Girls"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Sub PDP Page - Verify GNA consistency - JEWELRY - Watches in DOMESITC mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to "JEWELRY" category page
    And I navigate to "Watches" browse page from cat splash page
    And I navigate to category browse page from "Watches"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - DINING & ENTERTAINING in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "DINING" category page from the registry
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic @xbrowser_hfr
  Scenario: PDP Page - Verify GNA consistency - KITCHEN in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "KITCHEN" category page from the registry
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - BED & BATH in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "BED & BATH" category page from the registry
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - HOME DECOR in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "HOME DECOR" category page from the registry
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - LUGGAGE in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "LUGGAGE" category page from the registry
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: PDP Page - Verify GNA consistency - CLEANING & ORGANIZING in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the GNA count on Home Page
    When I navigate to registry home page
    And I visit the "CLEANING & ORGANIZING" category page from the registry
    And I navigate to category browse page from "HOME"
    And I click on any Product Thumbnail in search results page
    And I verify same GNA count on current page
    # Notes:
    # Do full validation -
    # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
    # PDP Page