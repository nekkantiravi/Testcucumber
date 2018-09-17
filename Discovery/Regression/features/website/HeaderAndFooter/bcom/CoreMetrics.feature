# Author: DISCOVERY QE
# Date Created: 11/17/2017

Feature: Verification of BCOM Header CoreMetrics URL in DOMESTIC, ISHIP and REGISTRY modes

  ############################################## Top Nav Coremetrics URL Verification in Domestic mode ###################

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL on Category Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I mouse over "MEN" category from top navigation
    When I navigate to "MEN" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL on Category Browse Page 2nd page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I navigate to last page from pagination
    And I mouse over "WOMEN" category from top navigation
    When I navigate to "WOMEN" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: PDP page - Verify TOPNAV CoreMetrics URL on PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    And I mouse over "BEAUTY" category from top navigation
    When I navigate to "BEAUTY" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: My Account page - Verify TOPNAV CoreMetrics URL on  at My Account in DOMESTIC mode
    Given I visit the web site as a guest user
    When I create a new profile
    And I should see "my account" page is rendered
    And I mouse over "HOME" category from top navigation
    When I navigate to "HOME" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: My Wallet Page - Verify TOPNAV CoreMetrics URL on  at My wallet in DOMESTIC mode
    Given I visit the web site as a guest user
    When I create a new profile
    And I should see "my account" page is rendered
    And I navigate to My Wallet page from My Account page
    And I mouse over "SHOES" category from top navigation
    When I navigate to "SHOES" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Promotions Page - Verify TOPNAV CoreMetrics URL on offers in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "SALES & PROMOTIONS" browse page under "SALE"
    And I mouse over "JEWELRY & ACCESSORIES" category from top navigation
    When I navigate to "JEWELRY & ACCESSORIES" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url


  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Wish List Page - Verify TOPNAV CoreMetrics URL on  at My wish list in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the my wish list page
    And I mouse over "HANDBAGS" category from top navigation
    When I navigate to "HANDBAGS" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Legacy Page - Verify TOPNAV CoreMetrics URL on Legacy Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Apply & Learn More page
    And I mouse over "KIDS" category from top navigation
    When I navigate to "KIDS" category page
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  ######################################### Top Nav Coremetrics URL Verification in Iship mode ########################

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL on Category in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I mouse over "MEN" category from top navigation
    When I navigate to "MEN" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Topnav coremetrics URL verification at browse with pagination in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I navigate to last page from pagination
    And I mouse over "WOMEN" category from top navigation
    When I navigate to "WOMEN" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: PDP page - Topnav coremetrics URL verification on PDP page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to a random product
    And I mouse over "SHOES" category from top navigation
    When I navigate to "SHOES" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url


  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: CategorySplashPage - Verify TOPNAV CoreMetrics URL on CatSplash Page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "KIDS" category page
    And I mouse over "JEWELRY & ACCESSORIES" category from top navigation
    When I navigate to "JEWELRY & ACCESSORIES" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: LegacyPage - Verify TOPNAV CoreMetrics URL on Legacy Page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to ways to shop page
    And I mouse over "SALE" category from top navigation
    When I navigate to "SALE" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  ############################################## Top Nav Coremetrics URL Verification in Registry mode #################

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL on Category Browse Page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I navigate to the "Picture Frames" browse page under "HOME DECOR"
    And I mouse over "KITCHEN" category from top navigation
    When I navigate to "KITCHEN" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL on Category Browse Page 2nd Page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I navigate to the "Picture Frames" browse page under "HOME DECOR"
    And I navigate to last page from pagination
    And I mouse over "LUGGAGE" category from top navigation
    When I navigate to "LUGGAGE" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: PDP Page - Verify TOPNAV CoreMetrics URL on PDP page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I navigate to a random product
    And I mouse over "HOME CARE & TECH" category from top navigation
    When I navigate to "HOME CARE & TECH" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url


  #please see ECOMSST-45385
  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: Legacy Page - Verify TOPNAV CoreMetrics URL on Legacy Page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    And I mouse over "DINING & ENTERTAINING" category from top navigation
    When I navigate to "DINING & ENTERTAINING" category page
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url


    ############################################## Top Nav Coremetrics URL for SubCategory Links Verification in Domestic mode ###################

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL for subcategory links on Category Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I mouse over "MEN" category from top navigation
    And I select "Dress Shirts" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL for subcategory links on Category Browse Page 2nd page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I navigate to last page from pagination
    And I mouse over "MEN" category from top navigation
    And I select "Dress Shirts" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: PDP page - Verify TOPNAV CoreMetrics URL for subcategory links on PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    And I mouse over "BEAUTY" category from top navigation
    And I select "All Makeup" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: My Account page - Verify TOPNAV CoreMetrics URL for subcategory links on  at My Account in DOMESTIC mode
    Given I visit the web site as a guest user
    When I create a new profile
    And I should see "my account" page is rendered
    And I mouse over "HOME" category from top navigation
    And I select "Sheets" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: My Wallet Page - Verify TOPNAV CoreMetrics URL for subcategory links on  at My wallet in DOMESTIC mode
    Given I visit the web site as a guest user
    When I create a new profile
    And I should see "my account" page is rendered
    And I navigate to My Wallet page from My Account page
    And I mouse over "WOMEN" category from top navigation
    And I select "Coats" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Promotions Page - Verify TOPNAV CoreMetrics URL for subcategory links on offers in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "SALES & PROMOTIONS" browse page under "SALE"
    And I mouse over "JEWELRY & ACCESSORIES" category from top navigation
    And I select "Earrings" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url


  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Wish List Page - Verify TOPNAV CoreMetrics URL for subcategory links on  at My wish list in DOMESTIC mode
    Given I visit the web site as a registered user
    When I navigate to the my wish list page
    And I mouse over "WOMEN" category from top navigation
    And I select "Skirts" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Legacy Page - Verify TOPNAV CoreMetrics URL for subcategory links on Legacy Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Apply & Learn More page
    And I mouse over "SHOES" category from top navigation
    And I select "Flats" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION-_-TOP_NAV" pattern url

  ######################################### Top Nav Coremetrics URL for SubCategory Links Verification in Iship mode ########################

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL for subcategory links on Category in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I mouse over "MEN" category from top navigation
    And I select "Dress Shirts" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Topnav coremetrics URL for subcategory links verification at browse with pagination in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "All Handbags" browse page under "HANDBAGS"
    And I navigate to last page from pagination
    And I mouse over "MEN" category from top navigation
    And I select "Dress Shirts" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: PDP page - Topnav coremetrics URL for subcategory links verification on PDP page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to a random product
    And I mouse over "MEN" category from top navigation
    And I select "Dress Shirts" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url


  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: CategorySplashPage - Verify TOPNAV CoreMetrics URL for subcategory links on CatSplash Page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "KIDS" category page
    And I mouse over "JEWELRY & ACCESSORIES" category from top navigation
    And I select "Earrings" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: LegacyPage - Verify TOPNAV CoreMetrics URL for subcategory links on Legacy Page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to ways to shop page
    And I mouse over "SHOES" category from top navigation
    And I select "Flats" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_INTL-_-TOP_NAV" pattern url

  ############################################## Top Nav Coremetrics for SubCategory Links URL Verification in Registry mode #################

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL for subcategory links on Category Browse Page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I navigate to the "Picture Frames" browse page under "HOME DECOR"
    And I mouse over "KITCHEN" category from top navigation
    And I select "Baking Pans" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify TOPNAV CoreMetrics URL for subcategory links on Category Browse Page 2nd Page in REGISTRY mode
  Scenario: Header - Verify TOPNAV CoreMetrics URL on Category Browse Page 2nd Page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I navigate to the "Picture Frames" browse page under "HOME DECOR"
    And I navigate to last page from pagination
    And I mouse over "KITCHEN" category from top navigation
    And I select "Baking Pans" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url

  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: PDP Page - Verify TOPNAV CoreMetrics URL for subcategory links on PDP page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I navigate to a random product
    And I mouse over "HOME CARE & TECH" category from top navigation
    And I select "Irons & Steamers" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url


  #please see ECOMSST-45385
  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: Legacy Page - Verify TOPNAV CoreMetrics URL for subcategory links on Legacy Page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    And I mouse over "DINING & ENTERTAINING" category from top navigation
    And I select "Fine China" subcategory from flyout menu
    Then I should see page navigated to "cm_sp=NAVIGATION_REG-_-TOP_NAV" pattern url