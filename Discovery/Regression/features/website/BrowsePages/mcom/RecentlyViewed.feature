#Author: Discovery SNBC QE

Feature: Verify SubCategory Pages - Recently Viewed Items

  #Added @wip tag because expected category is not available in prduction site also
  @use_dsv @dsv_category_29 @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario:BrowsePage - Verify Recently View Panel- HOME - Vacuums & Steam Cleaners
   Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

   #Added @wip tag because expected category is not available in prduction site also
  @use_dsv @dsv_category_11 @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario:BrowsePage - Verify Recently View Panel- HOME -  Cameras & Photography
    Given I am on CategoryBrowsePage for "Cameras & Photography" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  #Added @wip tag because expected category is not available in prduction site also
  @use_dsv @dsv_category_11 @use_regression @domain_discovery @priority_high @snbc_comp @xbrowser_browse
  Scenario:BrowsePage - Verify Recently View Panel- HOME - Outdoor & Patio Furniture
    Given I am on CategoryBrowsePage for "Outdoor & Patio Furniture" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario:BrowsePage - Verify Recently View Panel- HOME - Rugs
    Given I am on CategoryBrowsePage for "Rugs" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario:BrowsePage - Verify Recently View Panel- HOME - New Arrivals
    Given I am on CategoryBrowsePage for "New Arrivals" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Recently View Panel - HOME - Fiesta
    Given I am on CategoryBrowsePage for "Fiesta" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Recently View Panel - HOME - Lighting & Lamps
    Given I am on CategoryBrowsePage for "Lighting & Lamps" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Recently View Panel - HOME - Cleaning & Organization
    Given I am on CategoryBrowsePage for "Cleaning & Organization" under "HOME" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Recently View Panel - WOMEN - All Petite Plus Size
    Given I am on CategoryBrowsePage for "All Petite Plus Size" under "WOMEN" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Recently View Panel - WOMEN - Pants
    Given I am on CategoryBrowsePage for "Pants" under "WOMEN" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Recently View Panel - MEN - Big & Tall
    Given I am on CategoryBrowsePage for "Big & Tall" under "MEN" in Domestic mode
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_regression @domain_discovery @priority_high @snbc_comp @discovery_daily_run
  Scenario: BrowsePage - Verify Recently View Panel - MEN - Polos
    Given I am on CategoryBrowsePage for "Polos" under "MEN" in Domestic mode
    When I construct RVI cookie with 8 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    And I should see the navigation arrow buttons
    When I click on "right" arrow key inside RVI panel
    Then I verify that next set of products are displayed
    When I click on "left" arrow key inside RVI panel
    Then I verify that previous set of products are displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page
