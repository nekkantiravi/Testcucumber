# Author: Discovery Regression QE Team
# Created Date: 10/10/2017


Feature: Verify COACH Pages in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page @discovery_daily_run
  Scenario: SubSplashPage - Verify COACH Quick View functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select the quick peek of random member product
    Then I verify that quick peek is displayed
    # Notes:
    # Just verify that a popup is displayed with non empty contents when hovered on random product on page

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page @discovery_daily_run
  Scenario Outline: SubSplashPage - Verify Contents functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I navigate to COACH "<leftnav>"
    Then I verify the basic attributes of COACH Brand browse Page
    Then I verify that the Sort By displayed with all options
    Examples:
      | leftnav              |
      | Coach Handbags       |
      | Coach Accessories    |
      | Coach Shoes          |
      | Coach Watches        |
      | Coach Sunwear        |
      | Coach Fragrance      |
      | Coach Gifts          |
      | Coach Special Offers |
    # Notes:
    # Do full validation - should not display any broken image or link
    # Verify shop by is displayed
    # Verify Sort By, Show and next pages appear

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - Verify COACH Left nav functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify the facets on Coach left nav
    And I verify the subcategories on Coach left nav
    # Notes:
    # Verify filter by appears in red
    # Verify only Handbag Style is expanded, rest of the facets are expanded

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Pick Up In-Store facet functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I "should" see BOPS store facet tab
    When I select BOPS store tab view for thumbnail grid
    Then I should see products are updated from all products tab to BOPS production tab

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify product count is updated for Special Offers facet selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    And   I verify that "Special Offers" facet is listed on left nav
    Then  I select the random value in the "Special Offers" facet
    And   I verify that the product count is updated
    When  I remove the selected facet from the breadcrumb
    Then  I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify product count is updated for Handbag Style facet selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    Then I verify that "Handbag Style" facet is listed on left nav
    And  I select the random value in the "Handbag Style" facet
    Then I verify that the product count is updated
    And  I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify  product count is updated for Handbag Material facet selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    Then I verify that "Handbag Material" facet is listed on left nav
    And  I select the random value in the "Handbag Material" facet
    Then I verify that the product count is updated
    And  I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify  product count is updated for Accessories Type facet selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    Then I verify that "Accessories Type" facet is listed on left nav
    And  I select the random value in the "Accessories Type" facet
    Then I verify that the product count is updated
    And  I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify the selected color in the color swatch is highlighted in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    And I select the first color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify clear all button functionality for Color facets selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select the first two color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify product count is updated for Wallet Style facet selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    Then I verify that "Wallet Style" facet is listed on left nav
    And  I select the random value in the "Wallet Style" facet
    Then I verify that the product count is updated
    And  I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify  product count is updated for Price facet selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Price facet functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then  I verify that the product count is displayed
    When I select the first two price in the Price facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify that the products are displayed with the selected rating when Customer Ratings facet is selected in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And  I verify that the product count is displayed
    Then I select 1 rating in the "Customer Ratings" facet
    And  I verify that the products are displayed with the selected rating
    When I remove the selected facet from the breadcrumb
    Then I verify that all of the products are displayed

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Customer Ratings facet functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And  I verify that the product count is displayed
    When I select 2 rating in the "Customer Ratings" facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page @wip
  Scenario: SubSplashPage - Verify COACH Color Swatches functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify that color swatches on Coach SubSplash Page are displayed
    # Notes:
    # Just verify that at-least more than 5 products have color swatches

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: CategorySubSplashPage - Verify COACH Sort By functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify that the Sort By displayed with all options
    # Notes:
    # Just verify sort by options are displayed

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Sort By Price: High to Low functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links and that
    # first product price is less than second product and last product

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page @discovery_daily_run
  Scenario: SubSplashPage - COACH - Verify Sort By Price: Low to High functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select "Price: Low to High" in sort by drop down
    Then I verify that the Sort By "Price: Low to High" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links and that
    # first product price is more than second product and last product

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Sort By Customers Top Rated functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select "Customers' Top Rated" in sort by drop down
    Then I verify that the Sort By "Customers' Top Rated" functionality
    # Notes:
    # Verify that the first product has star count greater than the second and last product
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Sort By Best Sellers functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select "Best Sellers" in sort by drop down
    Then I verify that the Sort By "Best Sellers" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Sort By New Arrivals functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality
    # Notes:
    # Verify that the word NEW appears next to each product
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Sort By Featured Items functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality
    And I select "Featured Items" in sort by drop down
    Then I verify that the Sort By "Featured Items" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario Outline: SubSplashPage - COACH - Verify  item count selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    And I filter the result set to show "<show_items>" products from "bottom"
    Then I verify that "<show_items>" item count option and respective number of products on page
    Examples:
      | show_items |
      | 60         |
      | 120        |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links after applying the facet
    # Verify that the number of products displayed matches with the count displayed on left nav
    # Verify only STYLE, WALLET TYPE and SIZE facets are expanded, verify other facets are collapsed

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Pagination functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify that pagination works
    # Notes:
    # Verify page and next pages exist with icon at the end
    # clicking on a random next page lands on page with products
    # all next page links return 200 OK with httpparty

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH - Verify Product thumbnail functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify the Coach Product thumbnail
    # Notes:
    # Verify that image, color swatch, title text, price, outline is displayed
    # Choose a random product

  @use_regression  @domain_discovery @priority_high @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - COACH  - Verify Browse Page Items text functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" browse page under "HANDBAGS"
    Then I verify that item text on Coach Browse Page are displayed
    # Notes:
    # Verify that xxx items in COACH Handbags & Wallets is displayed


  #TBD - add scenarios for iship mode
  #TBD - add scenarios for registry mode