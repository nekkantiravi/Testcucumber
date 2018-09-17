# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify CategorySplashPage - Recently Viewed Panel

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: CategorySplashPage - Verify Recently View Panel - HOME in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 10 products and reload the page
    And I navigate to "HOME" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I click right navigation button in rvi panel
    Then I should move towards next set of products
    When I click left navigation button in rvi panel
    Then I should move towards previous set of products
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - BED & BATH in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "BED & BATH" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - WOMEN in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "WOMEN" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high @xbrowser_catsplash
  Scenario: CategorySplashPage - Verify Recently View Panel - MEN in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "MEN" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - Juniors in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "JUNIORS" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - KIDS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "KIDS" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - BEAUTY in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "BEAUTY" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - SHOES in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "SHOES" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - HANDBAGS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HANDBAGS" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - JEWELRY in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "JEWELRY" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - WATCHES in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "WATCHES" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page


  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - DINING & ENTERTAINING -  in REGISTRY mode
    Given I visit the web site as a guest user
    When I visit the registry home page
    When I construct RVI cookie with 10 products and reload the page
    And I navigate to "DINING" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I click on "right" arrow key inside Recently Viewed panel
    Then I should move towards next set of products
    When I click on "left" arrow key inside Recently Viewed panel
    Then I should move towards previous set of products
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high @xbrowser_catsplash
  Scenario: CategorySplashPage - Verify Recently View Panel - KITCHEN -  in REGISTRY mode
    Given I visit the web site as a guest user
    When I visit the registry home page
    And I view 1 random products
    And I click on logo in "registry" mode
    And I visit the registry home page
    And I navigate to "KITCHEN" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - HOME DECOR -  in REGISTRY mode
    Given I visit the web site as a guest user
    When I visit the registry home page
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HOME DECOR" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - BED & BATH- -  in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "BED & BATH" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - LUGGAGE -  in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "LUGGAGE" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_registry @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - CLEANING & ORGANIZING -  in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "CLEANING & ORGANIZING" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  #Test Case ID: MCOM-80943, MCOM-86985, MCOM-80417, MCOM-92126
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage -  Verify removing items from the RVI panel from Cat Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 3 products and reload the page
    And I navigate to random category splash page
    Then I should see edit option inside Recently Viewed panel
    And I remove all Recently viewed items
    # Notes
    # After navigating to the products Verify recently viewed items are displayed in RVI panel in PDP, FOB and Browse pages
    # Delete all RVI by click on edit -> remove. remove all items one by one. Finally click done.
    # Recently view items panel is in collapsed mode
    # "recently viewed items (0)" should be displayed

  #Test Case ID: MCOM-80415
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage -  Verify total number of products and number of display products per row in RV Items panel in Cat Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 10 products and reload the page
    And I navigate to random category splash page
    When I click on "right" arrow key inside Recently Viewed panel
    Then I should move towards next set of products
    When I click on "left" arrow key inside Recently Viewed panel
    Then I should move towards previous set of products
    # Notes
    # After navigating to the products Verify recently viewed items are displayed in RVI panel
    # Verify the left and right arrow keys functioning correctly (7+ products should be in RVI panel)

  #Test Case ID: MCOM-80941
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario:CategorySplashPage - Verify RVI panel on Brand Shop Sub Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 3 products and reload the page
    When I navigate to the "MICHAEL Michael Kors" browse page under "HANDBAGS"
    Then I should see edit option inside Recently Viewed panel
    When I remove all Recently viewed items
    Then I should not see recently viewed items section
    # Notes
    # Recently view items panel is in collapsed mode
    # "recently viewed items (0)" should be displayed
    # Recently view items panel arrows should be inactive


  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage - Verify Edit button is displayed on RVI Panel on Brand Index Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to the "See All Brands" browse page under "WOMEN"
    Then I should see edit option inside Recently Viewed panel
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: CategorySplashPage -  Verify Edit button is displayed on RVI Panel on Category SubSplash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to the "Activewear" sub splash page under "MEN"
    Then I should see edit option inside Recently Viewed panel
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high @xbrowser_catsplash
  Scenario: CategorySplashPage - Verify Recently View Panel - HOME in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HOME" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - BED & BATH in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "BED & BATH" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - WOMEN in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "WOMEN" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - Juniors in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "JUNIORS" category page
    Then I verify the display of RVI in "category Splash" page
    And I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - KIDS in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "KIDS" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - SHOES in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "SHOES" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - HANDBAGS in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HANDBAGS & ACCESSORIES" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - JEWELRY in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "JEWELRY" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  # Test Case ID: RT-07332
  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - WATCHES in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "WATCHES" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page

  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage -  Verify Edit button is displayed on RVI Panel on splash page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to random category splash page
    Then I should see edit option inside Recently Viewed panel
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items


  @domain_discovery @feature_catsplash @use_regression @migrated_to_sdt @mode_iship @priority_high
  Scenario: CategorySplashPage - Verify Recently View Panel - MEN in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 10 products and reload the page
    And I navigate to "MEN" category page
    Then I verify the display of RVI in "category Splash" page
    And  I should see edit option inside Recently Viewed panel
    When I click right navigation button in rvi panel
    Then I should move towards next set of products
    When I click left navigation button in rvi panel
    Then I should move towards previous set of products
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
  # Notes:
  # Do full validation - Recently viewed products should be displayed with arrows if applicable
  # Verify edit button is present and functionality
  # Verify clicking on product navigates to the correct product page