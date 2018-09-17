Feature: BATs for Discovery

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search @discovery_daily_run
  Scenario: SearchResultsPage - Verify random facet selection filtering works on search results page
    Given I visit the web site as a guest user
    And I search for "dresses"
    When I select a random facet item from each of the facets
    Then I should see a filtered list of displayed products

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search  @discovery_daily_run
  Scenario: SearchResultsPage - Verify that user should be able to search Items and Sort them
    Given I visit the web site as a guest user
    When I search for "DRESS"
    And  I select "Price: High to Low" in sort by drop down
    Then I verify the search results are sorted by "Price: High to Low" across multiple pages

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse @discovery_daily_run
  Scenario: BrowsePage - Verify one random facet selection from each facet section
    Given I visit the web site as a guest user
    And I navigate to the "Jeans" browse page under "MEN"
    When I select a random facet item from each of the facets and check the breadcrumbs
    Then I should see a filtered list of displayed products

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search @discovery_daily_run
  Scenario:SearchResultsPage - A user can search for products that match two keywords.
    Given I visit the web site as a guest user
    When I search for "long dress"
    Then All matching products should be returned

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search @discovery_daily_run
  Scenario: SearchResultsPage - A user can search for a product using a product ID.
    Given I visit the web site as a guest user
    When  I search for "86800"
    Then I should be taken directly to that product

  @use_bat @domain_discovery @use_regression @migrated_to_sdt
  Scenario: REAPPS - Verify Release Engineering Site Details API
    Given I visit the web site as a guest user
    Then I verify RE API - EnvironmentDetails

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse @discovery_daily_run
  Scenario: BrowsePage - Verify QV appears on products in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I select "Quick View" for a random product

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse @discovery_daily_run
  Scenario: BrowsePage - Verify Left Nav is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I should see facets listed on left nav

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse @discovery_daily_run
  Scenario: BrowsePage - Verify BOPS Pick Up In-Store facet is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Kitchen" browse page under "HOME"
    And I navigate to "Electrics" from Left Nav links
    Then I "should" see BOPS store facet tab
    When I select BOPS store tab view for thumbnail grid
    Then I should see products are updated from all products tab to BOPS production tab

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search @discovery_daily_run
  Scenario: SearchResultsPage - Verify pricing is displayed on products in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "shoes"
    Then I should see price for the products

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse @discovery_daily_run
  Scenario: BrowsePage - Verify browse page(HANDBAGS -> COACH) basic attributes in DOMESTIC Mode
    Given I visit the web site as a guest user
    When  I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify the basic attributes of COACH Brand browse Page

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search @discovery_daily_run
  Scenario: SearchResultsPage - Verify Left Nav is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "shoes"
    Then I should see facets listed on left nav

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario: CategorySplashPage - Verify SEO Tag cloud in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I should see the Popular Searches at the bottom

  @domain_discovery @use_regression @migrated_to_sdt @mode_registry @use_bat @discovery_daily_run
  Scenario: CategorySplashPage - Verify FLYOUTS menu is displayed for MEN FOB in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    And I hover over on the below "MEN" fob's
    Then I verify that flyout menu is displayed

  @domain_discovery @use_regression @migrated_to_sdt @mode_registry @use_bat @discovery_daily_run
  Scenario: CategorySplashPage - Verify FLYOUTS menu is displayed for SHOES FOB in ISHIP mode
    Given I am on CatSplash Page for "SHOES" on "iship" mode
    And I hover over on the below "SHOES" fob's
    Then I verify that flyout menu is displayed

  @domain_discovery @use_regression @migrated_to_sdt @mode_registry @use_bat @discovery_daily_run
  Scenario: CategorySplashPage - Verify FLYOUTS menu is displayed for DINING FOB in REGISTRY mode
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    And I hover over on the below "KITCHEN" fob's
    Then I verify that flyout menu is displayed

