Feature: BATs for Discovery

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search
  Scenario: SearchResultsPage - Verify random facet selection filtering works on search results page
    Given I visit the web site as a guest user
    And I search for "dresses"
    When I select a random facet item from each of the facets
    Then I should see a filtered list of displayed products

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search
  Scenario: SearchResultsPage - Verify that user should be able to search Items and Sort them
    Given I visit the web site as a guest user
    When I search for "DRESS"
    And I select "Price (high-low)" in sort by drop down
    Then I verify the search results are sorted by "Price (high-low)" across multiple pages

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse
  Scenario: BrowsePage - Verify one random facet selection from each facet section
    Given I visit the web site as a guest user
    And I navigate to the "Jeans" browse page under "MEN"
    When I select a random facet item from each of the facets and check the breadcrumbs
    Then I should see a filtered list of displayed products

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search
  Scenario: SearchResultsPage - A user can search for products that match two keywords.
    Given I visit the web site as a guest user
    When I search for "long dress"
    Then All matching products should be returned

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search
  Scenario: SearchResultsPage - A user can search for a product using a product ID.
    Given I visit the web site as a guest user
    When  I search for "17979"
    Then I should be taken directly to that product

  @use_bat @domain_discovery @use_regression @migrated_to_sdt
  Scenario: REAPPS - Verify Release Engineering Site Details API
    Given I visit the web site as a guest user
    Then I verify RE API - EnvironmentDetails

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse
  Scenario: BrowsePage - Verify QP appears on products in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I select "Quick Peek" for a random product

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse
  Scenario: BrowsePage - Verify Left Nav is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I should see facets listed on left nav

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse
  Scenario: BrowsePage - Verify BOPS Pick Up In-Store facet is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Electrics" browse page under "HOME"
    Then I "should" see BOPS store facet tab
    When I select BOPS store tab view for thumbnail grid
    Then I should see products are updated from all products tab to BOPS production tab
#    Then I should see "Pick Up In Store" listed in facets

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search
  Scenario: SearchResultsPage - Verify pricing is displayed on products in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "red hat"
    Then I should see price for the products

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse
  Scenario: BrowsePage - Verify COACH HANDBAGS in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify the basic attributes of COACH Brand browse Page

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_browse
  Scenario: CategorySplashPage - Verify Left Nav section for WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify Left Nav section and links return 200 OK

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @feature_search
  Scenario: SearchResultPage -  To verify facet selection and closing in in DOMESTIC page
    Given I visit the web site as a guest user
    When I search for "jeans"
    Then I should be in Search Landing page
    And  I select a random facet item from each of the facets and check the breadcrumbs
    Then I should see a filtered list of displayed products
    When I close Breadcrumb of any selected facet
    Then I should see Breadcrumbs are removed from pagination section

  @use_bat @domain_discovery @use_regression @migrated_to_sdt @mode_domestic
  Scenario: CatSplashPage - Verify SEO Tag cloud in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I should see the Popular Searches at the bottom

  @domain_discovery @use_regression @migrated_to_sdt @mode_registry @use_bat
  Scenario: CategorySplashPage - Verify FLYOUTS menu is displayed for MEN FOB in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    And I hover over on the below "MEN" fob's
    Then I verify that flyout menu is displayed

  @domain_discovery @use_regression @migrated_to_sdt @mode_registry @use_bat
  Scenario: CategorySplashPage - Verify FLYOUTS menu is displayed for SHOES FOB in ISHIP mode
    Given I am on CatSplash Page for "SHOES" on "iship" mode
    And I hover over on the below "SHOES" fob's
    Then I verify that flyout menu is displayed

  @domain_discovery @use_regression @migrated_to_sdt @mode_registry @use_bat
  Scenario: CategorySplashPage - Verify FLYOUTS menu is displayed for DINING FOB in REGISTRY mode
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    And I hover over on the below "KITCHEN" fob's
    Then I verify that flyout menu is displayed

