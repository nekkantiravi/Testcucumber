############################################
# Author: Discovery Regression QE Team
# Date: 20th June 2017
# Date Modified: 27th June 2017
############################################

Feature: Verify browse page dsv features in DOMESTIC, ISHIP and REGISTRY mode

  #BLCOM-80282 MCOM-92035, MCOM-92052
  @use_dsv @mcom_dsv_sev1 @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @ifs @discovery_daily_run
  Scenario: BrowsePage - Verify multi Facet functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    Then I select "Jeans" subcategory from flyout menu
    And I select 3 facet value(s) from 'any' facet sections
    Then I verify that clear all button is displayed
    When I click on clear all button
    Then I verify that clear all button is not displayed
  # Notes:
  # Select two price facets and verify the products load accordingly
  # Select two size facets and verify the products load accordingly
  # Select two color facets and verify the products load accordingly
  # Click a two brand facets and verify the products load accordingly

  #MCOM-92035
  #ECOMSST-21482
  @use_dsv @mcom_dsv_sev1 @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario: BrowsePage - Verify clear and clear all after selecting multi Facets in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Backpacks" browse page under "HANDBAGS"
    When I select the first 1 in the Price facet
    And I select 3 facet value(s) from 'any' facet sections
    Then I verify that clear all button is displayed
    When I select clear all button for 'Price' facet
    Then I should not see selected facets from 'Price' facet
    When I click on clear all button
    Then I verify that clear all button is not displayed
  # Notes:
  # Select two price facets and verify the products load accordingly
  # Select two size facets and verify the products load accordingly
  # Select two color facets and verify the products load accordingly
  # Click a two brand facets and verify the products load accordingly
  # Click "clear" in price facet section and verify all selected price facets are deselected and the products are loaded accordingly
  # Click on selected size facet and verify that the facet is deselected and the products are loaded accordingly
  # Click "clear all" on top of the facet area and verify all selected facets are deselected and the products should update in the Browse page in default view

  #MCOM-92035
  @use_dsv @mcom_dsv_sev1 @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario: BrowsePage - Verify the sort by option
    Given I am on Browse Page for "Jeans" under "MEN"
    And I select "Price: High to Low" in sort by drop down
    Then I verify product display order for "Price: High to Low" option
    # Notes:
    # "New Arrivals" "Best sellers" "Customer top rated" "Price (High to Low)" "Price (Low to High),
    # "Featured Items", "Item Name" sort by option should be available in the drop down
    # Select sorted by price (High - Low) and verify the products display accordingly

  @use_dsv @dsv_desktop_sev1 @use_regression @migrated_to_sdt @launch_call @domain_discovery @use_bat @discovery_daily_run
  Scenario: BrowsePage - Verify products are displayed with international currency value in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    And I close the welcome mat if it's visible
    When I mouse over "MEN" category from top navigation
    Then I select "Jeans" subcategory from flyout menu
    Then I should see "AUD" currency for all products in thumbnail grid
    When I select a random product
    Then I verify the currency "AUD" on product display page

    ################################## Description/CopyBlock Feature ######################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run @discovery_daily_run
  Scenario: BrowsePage - Verify Description - HOME - Luggage & Backpacks
    Given I am on Browse Page for "Luggage & Backpacks" under "HOME"
    Then I verify the Description section for "Travel Bags" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv  @dsv_desktop_sev2 @dsv_category_2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Description - HOME - Mattresses
    Given I am on Browse Page for "Mattresses" under "HOME"
    Then I verify the Description section for "Mattresses" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv  @dsv_desktop_sev2 @dsv_category_2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Description - WOMEN - Sweaters
    Given I am on Browse Page for "Sweaters" under "WOMEN"
    Then I verify the Description section for "Sweaters" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv  @dsv_desktop_sev2 @dsv_category_2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario:BrowsePage - Verify Description - WOMEN - Lingerie
    Given I am on Browse Page for "Leggings" under "WOMEN"
    Then I verify the Description section for "Leggings" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv  @dsv_desktop_sev2 @dsv_category_2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario:BrowsePage - Verify Description - KIDS - All Baby
    Given I am on Browse Page for "All Baby" under "KIDS"
    Then I verify the Description section for "Baby Clothes" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv  @dsv_desktop_sev2 @dsv_category_2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Description - JEWELRY - Necklaces
    Given I am on Browse Page for "Necklaces" under "JEWELRY"
    Then I verify the Description section for "Necklaces" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

    ###################################### Sub Ads Feature ############################################

  @use_dsv  @dsv_desktop_sev2 @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - WOMEN - Lingerie in DOMESTIC mode
    Given I am on Browse Page for "Lingerie & Shapewear" under "WOMEN"
    Then I verify that Sub Ads of "Lingerie & Shapewear" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - MEN - The North Face in DOMESTIC mode
    Given I am on Browse Page for "The North Face" under "MEN"
    Then I verify that Sub Ads of "The North Face" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - MEN - Shoes in DOMESTIC mode
    Given I am on Browse Page for "All Men's Shoes" under "MEN"
    Then I verify that Sub Ads of "All Men's Shoes" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - KIDS - All Baby in DOMESTIC mode
    Given I am on Browse Page for "All Baby" under "KIDS"
    Then I verify that Sub Ads of "All Baby" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv @dsv_desktop_sev2 @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: Sub Category Page - Verify Sub Ads - HANDBAGS - Designer Handbags in DOMESTIC mode
    Given I am on Browse Page for "Designer Handbags" under "HANDBAGS"
    Then I verify that Sub Ads of "Designer Handbags" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - HOME - Martha Stewart Collection in DOMESTIC mode
    Given I am on Browse Page for "Martha Stewart Collection" under "HOME"
    Then I verify that Sub Ads of "Martha Stewart Collection" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads


  @use_dsv @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - HOME - Cookware in DOMESTIC mode
    Given I am on Browse Page for "Kitchen" under "HOME"
    And I select "Cookware & Cookware Sets" category from left nav
    Then I verify that Sub Ads of "Cookware & Cookware Sets" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv @dsv_desktop_sev2 @dsv_category_27 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - HOME - Furniture in DOMESTIC mode
    Given I am on Browse Page for "Furniture" under "HOME"
    Then I verify that Sub Ads of "Furniture" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - HOME - Mattresses in DOMESTIC mode
    Given I am on Browse Page for "Mattresses" under "HOME"
    Then I verify that Sub Ads of "Mattresses" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv  @dsv_desktop_sev2 @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - HOME - Luggage & Backpacks in DOMESTIC mode
    Given I am on Browse Page for "Luggage & Backpacks" under "HOME"
    Then I verify that Sub Ads of "Luggage & Backpacks" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - WOMEN - Maternity in DOMESTIC mode
    Given I am on Browse Page for "Maternity" under "WOMEN"
    Then I verify that Sub Ads of "Maternity" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  @use_dsv @dsv_category_8 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: Sub Category Page - Verify Sub Ads - WOMEN - Coats in DOMESTIC mode
    Given I am on Browse Page for "Coats" under "WOMEN"
    Then I verify that Sub Ads of "Coats" should be displayed on browse page
    # Notes:
    # Do full sub ad validation - should not display any broken image or link
    # visit each link within the subad for all subads

  ###################################### Main Ads Feature ############################################


  @use_dsv @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - MEN - Guys
    Given I am on Browse Page for "Guys" under "MEN"
    Then I verify that Main Ads of "Guys" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: BrowsePage - Verify Main Ad - MEN - Shoes
    Given I am on Browse Page for "All Men's Shoes" under "MEN"
    Then I verify that Main Ads of "All Men's Shoes" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - MEN - Big & Tall
    Given I am on Browse Page for "Big & Tall" under "MEN"
    Then I verify that Main Ads of "Big & Tall" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - KIDS - Shop All Baby
    Given I am on Browse Page for "All Baby" under "KIDS"
    Then I verify that Main Ads of "All Baby" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HANDBAGS - Luggage
    Given I am on Browse Page for "Backpacks" under "HANDBAGS"
    Then I verify that Main Ads of "Backpacks" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Mattresses
    Given I am on Browse Page for "Mattresses" under "HOME"
    Then I verify that Main Ads of "Mattresses" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME -  Furniture
    Given I am on Browse Page for "Furniture" under "HOME"
    Then I verify that Main Ads of "Furniture" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Rugs
    Given I am on Browse Page for "Rugs" under "HOME"
    Then I verify that Main Ads of "Rugs" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Luggage
    Given I am on Browse Page for "Luggage & Backpacks" under "HOME"
    Then I verify that Main Ads of "Luggage & Backpacks" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Lighting & Lamps
    Given I am on Browse Page for "Lighting & Lamps" under "HOME"
    Then I verify that Main Ads of "Lighting & Lamps" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link


  @use_dsv @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Storage & Organization
    Given I am on Browse Page for "Cleaning & Organization" under "HOME"
    Then I verify that Main Ads of "Cleaning & Organization" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  # Note ; below mentioned subcategory is not available in left nav data

  @use_dsv @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Outdoor Living
    Given I am on Browse Page for "Outdoor & Patio Furniture" under "HOME"
    Then I verify that Main Ads of "Outdoor & Patio Furniture" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Kitchen
    Given I am on Browse Page for "Kitchen" under "HOME"
    Then I verify that Main Ads of "Kitchen" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Dining & Entertaining
    Given I am on Browse Page for "Dining & Entertaining" under "HOME"
    Then I verify that Main Ads of "Dining & Entertaining" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Furniture
    Given I am on Browse Page for "Furniture" under "HOME"
    Then I verify that Main Ads of "Furniture" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_23 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - HOME - Outdoor & Patio Furniture
    Given I am on Browse Page for "Outdoor & Patio Furniture" under "HOME"
    Then I verify that Main Ads of "Outdoor & Patio Furniture" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - WOMEN - Plus Sizes
    Given I am on Browse Page for "All Plus Sizes" under "WOMEN"
    Then I verify that Main Ads of "All Plus Sizes" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - WOMEN - Petite
    Given I am on Browse Page for "All Petites" under "WOMEN"
    Then I verify that Main Ads of "All Petites" should be displayed on browse page
    # Notes:
    # Do full main ad validation - should not display any broken image or link

  @use_dsv  @dsv_desktop_sev2 @dsv_category_5 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Main Ad - WOMEN - Lingerie
    Given I am on Browse Page for "Lingerie & Shapewear" under "WOMEN"
    Then I verify that Main Ads of "Lingerie & Shapewear" should be displayed on browse page


  ##################################### Recently Viewed Panel #######################################

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
  Scenario: BrowsePage - Verify Recently View Panel - HANDBAGS - Luggage
    Given I visit the web site as a guest user
    When I construct RVI cookie with 8 products and reload the page
    And I navigate to "HANDBAGS" category page
    And I navigate to "Backpacks" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
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

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Recently View Panel - WOMEN - Leggings
    Given I visit the web site as a guest user
    When I navigate to the "dresses" browse page under "WOMEN"
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "WOMEN" category page
    And I navigate to "Leggings" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Recently View Panel - MEN - Shoes
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "MEN" category page
    And I navigate to "Casual Shoes" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Recently View Panel - KIDS - Shop All Baby
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "KIDS" category page
    And I navigate to "All Baby" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Recently View Panel - HOME - Slipcovers
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HOME" category page
    And I navigate to "Slipcovers" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Recently View Panel - HOME - Outdoor & Patio Furniture
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HOME" category page
    And I navigate to "Outdoor & Patio Furniture" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

  @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high
  Scenario: BrowsePage - Verify Recently View Panel - HOME - Home Decor
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    And I navigate to "HOME" category page
    And I navigate to "Home Decor" browse page from cat splash page
    Then I verify the display of RVI in "category browse" page
    And  I should see edit option inside Recently Viewed panel
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel
    # Notes:
    # Do full validation - Recently viewed products should be displayed with arrows if applicable
    # Verify edit button is present and functionality
    # Verify clicking on product navigates to the correct product page

   @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_registry @discovery_daily_run
  Scenario: BrowsePage - Verify the display of Badge Text content in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "KITCHEN" category from top navigation
    And I select "Cookware & Cookware Sets" subcategory from flyout menu
    Then I should see "batch text" is displayed for the "KITCHEN - Cookware & Cookware Sets" brand

   @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @discovery_daily_run
  Scenario: BrowsePage - Verify the display of Badge Text content in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I mouse over "BEAUTY" category from top navigation
    And I select "All Makeup" subcategory from flyout menu
    Then I should see "batch text" is displayed for the "BEAUTY - All Makeup" brand

    ######################### LEFT NAV ################################################

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: BrowsePage - Verify Left Hand Nav - HANDBAGS & ACCESSORIES - COACH in DOMESTIC mode
    Given I am on Browse Page for "Tights, Socks, & Hosiery" under "HANDBAGS"
    And I navigate to sub categories from Left hand nav links
    Then I verify the correct subcategory page is loaded
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @dsv_category_22 @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Dining & Entertaining in DOMESTIC mode
    Given  I am on Browse Page for "Dining & Entertaining" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @dsv_category_22 @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Furniture in DOMESTIC mode
    Given  I am on Browse Page for "Furniture" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Mattresses in DOMESTIC mode
    Given  I am on Browse Page for "Mattresses" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Bed & Bath in DOMESTIC mode
    Given  I am on Browse Page for "Bed & Bath" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Rugs in DOMESTIC mode
    Given  I am on Browse Page for "Rugs" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - KitchenAid in DOMESTIC mode
    Given  I am on Browse Page for "KitchenAid" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Luggage & Backpacks in DOMESTIC mode
    Given  I am on Browse Page for "Luggage & Backpacks" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Kitchen in DOMESTIC mode
    Given  I am on Browse Page for "Kitchen" under "HOME"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Wedding Registry in DOMESTIC mode
    Given  I am on Browse Page for "Wedding Registry" under "HOME"
    Then I should see page navigated to "registry/wedding/registryhome" pattern url
    And I should be in Registry mode

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - WOMEN - Plus Sizes in DOMESTIC mode
    Given  I am on Browse Page for "All Plus Sizes" under "WOMEN"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - WOMEN - Petite in DOMESTIC mode
    Given  I am on Browse Page for "All Petites" under "WOMEN"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - WOMEN - Handbags & Accessories in DOMESTIC mode
    Given  I am on Browse Page for "Handbags & Accessories" under "WOMEN"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: Scenario: BrowsePage - Verify Left Hand Nav - MEN - Shirts in DOMESTIC mode
    Given  I am on Browse Page for "Shirts" under "MEN"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - MEN - Guys in DOMESTIC mode
    Given  I am on Browse Page for "Levi's" under "MEN"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - MEN - All Men's Shoes in DOMESTIC mode
    Given  I am on Browse Page for "All Men's Shoes" under "MEN"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - KIDS - Shop All Boys in DOMESTIC mode
    Given  I am on Browse Page for "All Boys" under "KIDS"
    And  I navigate to sub categories from Left hand nav links
    Then  I verify the correct subcategory page is loaded

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: BrowsePage - Verify Brand Search box in Left Navigation in DOMESTIC mode
    Given I am on Browse Page for "Dress Shirts" under "MEN"
    And I collect total brands under brand facet
    When I type a character "random" in brand search box
    And I type a character "random" in brand search box
    Then I should see brand facets displayed according to the text contains "ca" entered in brand search box
    And the characters "ca" should be highlighted in the displayed brands under brand facet
    When I select the random value in the "Brand" facet
    Then I verify all results match with the randomly-selected brand facet name
    And I verify selected brand facet product count matches with the filtered product count

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic @use_launch  @dsv_desktop_sev2 @discovery_daily_run @akamai_waf
  Scenario: BrowsePage - Verify product counts when hovering and selecting color facets in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    And I "expand" the "Color" facet on left nav
    When I select the first 1 in the Color facet
    Then I should verify both product counts in tooltip, SDP and Site are same for selected color facet
  # Notes:
  # product count display in the breadcrumb should matched with the product count display when mouse hover on color facet
  #  5.While hovering over color swatches, the available product count for that color should display.
  #  6.Product count should be same and selected color (image or tile)

  @use_dsv @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_medium  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: BrowsePage - Verify promotion bubble in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Makeup" browse page under "BEAUTY"
    And I select a product having badge text
    Then I verify that promo text and promo popup is displayed under product thumbnail
  # Notes:
  # Mouse hover on the promotional badge text then the promotional bubble should display with a close link
  # By clicking the close link the promotional bubble can be closed
  # Test case description:
  # Verify promotional bubble
  # Test case steps:
  # 1.Navigate to macys.com
  # 2.Click on any category e.g. Women
  # 3.Click on any browse page e.g. dresses
  # 4.Mouse hover promotion bubble below the product price.
  # 5.Click on close link or click outside promotion bubble
  # Test case expected result:
  # 1. macys.com home page should be displayed.
  # 2.Women category should be displayed.
  # 3.Dresses Browse page should be displayed
  # 4.It should open a pop-up with close link.
  # 5.It should closes the promotion bubble.

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: BrowsePage - Verify the pagination in cat browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Socks" browse page under "MEN"
    Then I verify the functionality of pagination when there are products above default product count in browse page
    When I navigate to a random page other than first and last page from pagination
    Then I verify the functionality of pagination when there are products above default product count in browse page
    When I navigate to first page from pagination
    Then I verify the functionality of pagination when there are products above default product count in browse page
    When I navigate to last page from pagination
    Then I verify the functionality of pagination when there are products above default product count in browse page
    # Notes:
    # Please update the existing step def to verify everything listed below
    # Verify the pagination display on top as well as the bottom of the page.
    # Verify that pagination should display by default as 1 2 3 4 . . . N (N = last page)
    # Click one of the pagination items then the relevant page should display
    # Current page number should be highlighted
    # When the user is in the last page "Next" pagination control should not display
    # When the user is in the first page "Previous" pagination control should not display
    # When a pagination item is selected from the bottom page should scroll up and display the selected page
    # When there are more than 100 items
    # Then 40 items should be displayed by default
    # 1st page should be higligted
    # Number of pages should be appropriate
    # When 1st Page, Prev link should not be displayed
    # When switch to last Page , Next link should not be displayed
    # When switch to middle pages like  page 2 or 3, products from those page should be displayed
    # and page 2 and 3 should be highlighted
    # When click on prev or Next link, it should be navigate dto appropriate pages
    # When there are less than 40 items then page number, prev, next link should not be displayed

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: BrowsePage - Verify the pagination in cat browse page with low product number in DOMESTIC mode
    Given I am on Browse Page for "Fitbit" under "WATCHES"
    Then I verify that "Showing All X Items" text displayed for pagination

  @use_regression @migrated_to_sdt @domain_discovery @mode_registry  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify shop/featured pattern url display in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "DINING" category from top navigation
    And I select "Dinnerware" subcategory from flyout menu
    Then I should see the Popular Searches at the bottom
    And I verify the page for keyword which has "/shop/featured/" from popular searches
  # Notes:
  # Description: "shop/featured" should display in the url for both registry  and domestic
  # Steps:
  # 1. Navigate to macys.com
  # 2. Click on any of the products under popular searches from registry/domestic.
  #
  # Expected Results:
  # 1. Macys.com home page should display
  # 2. It should navigate to the respective page and "cms" or "shop/featured" should display in url

  #Test Case ID: MCOM-92035 MCOM-92031 MCOM-66548
  @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: BrowsePage - Verify the item count option buttons in DOMESTIC mode
    Given I am on Browse Page for "Dress Shirts" under "MEN"
    And I verify that item count buttons in page
    Then I verify that "60" item count option and respective number of products on page
    When I filter the result set to show "120" products from "bottom"
    Then I verify that "120" item count option and respective number of products on page

    #Test Case ID: MCOM-92035, MCOM-92031
  @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify the top banner in DOMESTIC mode
    Given I am on Browse Page for "Polos" under "MEN"
    Then I verify that Main Ads of "Polos" should be displayed on cat splash page
    # Notes:
    # Description: Verify the top banner on cat browse page
    # Steps:
    # 1. Navigate to any Category Browse Page E.g.: 'Women' --> 'Dresses'
    #
    # Expected Results:
    # 1. Verify that top banner should be displayed on cat browse page.

  @use_regression @migrated_to_sdt @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify the New URL Rewrite_structure - Iship
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I navigate to the "Dresses" browse page under "WOMEN"
    Then I should see page navigated to "/shop/womens-clothing/" pattern url
  # Notes:
  # Verify that URL should not have more than 2 subdirectories within any category browse pages. (Ex:New URL format should display as follows: www1.macys.com/shop/juniors-apparel?id=118.
  # Verify the url font should be lower case
  # When the category contains , search description value(ex: search description value in DB and SDP -Men's Clothing), Verify that URL should display as below format:[Domain]/shop/[Search Description]?id=XXXX. (ex: www1.macys.com/shop/mens-clothing?id=1).
  # When the category search description value is "Null",  Verify that URL should display as below format:[Domain]/shop/[Category Name]?id=XXXX. (ex: www.macys.com/shop/womens?id=118
  # When search description is provided for both the parent and child categories.(ex: Women's Clothing for parent and Wedding Dresses for child), Verify that URL should display as below format:[Domain]/shop/[Parent Search Description]/[Search Description]?id=XXXX. (ex: www1.macys.com/shop/womens-clothing/wedding-dresses?id=51098 ).
  # Check the URL structure for search description is provided  for only the child category and not the ultimate parent category(ex: parent category doesn't have the search description and child category have the search description :Wedding-dresses).  Verify that URL should display as below format:[Domain]/shop/[Parent Category Name]/[Search Description]?id=XXXX . (ex: www.macys.com/shop/womens/ /wedding-dresses?id=51098).
  # Check the URL structure for search description is provided for only the ultimate parent category and not the child category(Ex: Women's clothing for parent category and not for the child),  Verify that URL should display as below format:[Domain]/shop/[Parent Search Description]/[Category Name]?id=XXXX . (ex:     www1.macys.com/shop/womens-clothing/jeans?id=3111).
  # Check the URL structure when Search Description is NOT provided for either the parent and child categories,  Verify that URL should display as below format:[Domain]/shop/[Parent Category Name]/[Category Name]?id=XXXX . (ex: www.macys.com/shop/women/ womens?id=118).
  # Check whether the old URLs are redirecting to new URL structure by appending the old URL(Append /shop/womens?id=118), Verify that user should redirect to new URL format. Format should display as follows:www1.macys.com/shop/womens-clothing?id=118
  # Description: Verify the New URL Rewrite_structure for non registry and registry URLS
  # Steps:
  # 1. Navigate to macys.com
  # 2. Click on any FOB.(For E.g. : juniors) link.
  # 3. Click on any link or banner in Cat01 page(For e.g.: jeans).
  # 4. Check  the format for  URL in the browse window.
  # 5. Check the URL structure  font.
  # 6. Check the URL structure , When the category contains , search description value(ex: search description value in DB and SDP -Men's Clothing)
  # 7. Check the URL structure, when the category search description value is "Null".
  # 8. Check the URL Structure, when search description is provided for both the parent and child categories.(ex: Women's Clothing for parent and Wedding Dresses for child)
  # 9. Check the URL structure for search description is provided  for only the child category and not the ultimate parent category(ex: parent category doesn't have the search description and child category have the search description :Wedding-dresses).
  # 10. Check the URL structure for search description is provided for only the ultimate parent category and not the child category(Ex: Women's clothing for parent category and not for the child)
  # 11. Check the URL structure when Search Description is NOT provided for either the parent and child categories
  #12.Check whether the old URLs are redirecting to new URL structure by appending the old URL(Append /shop/womens?id=118)
  # 13. Check for registry URLS
  #
  # Expected Results:
  # 1. macys.com home page should be displayed.
  # 2. Verify that User should navigate to the juniors cat01 page.
  # 3. Verify that  cat04 page is displayed.
  # 4. Verify that URL should not have more than 2 subdirectories within any category browse pages. (Ex:New URL format should display as follows: www1.macys.com/shop/juniors-apparel?id=118.
  # Old URL: http://www1.macys.com/shop/juniors/apparel/jeans?id=28754&edge=hybrid)
  # 5. Verify that URL font should be lower case.
  # 6. Verify that URL should display as below format: [Domain]/shop/[Search Description]?id=XXXX. (ex: www1.macys.com/shop/mens-clothing?id=1).
  # 7. Verify that URL should display as below format: [Domain]/shop/[Category Name]?id=XXXX. (ex: www.macys.com/shop/womens?id=118
  # 8. Verify that URL should display as below format: [Domain]/shop/[Parent Search Description]/[Search Description]?id=XXXX. (ex: www1.macys.com/shop/womens-clothing/wedding-dresses?id=51098 ).
  # 9. Verify that URL should display as below format: [Domain]/shop/[Parent Category Name]/[Search Description]?id=XXXX . (ex: www.macys.com/shop/womens/ /wedding-dresses?id=51098).
  # 10. Verify that URL should display as below format: [Domain]/shop/[Parent Search Description]/[Category Name]?id=XXXX . (ex:     www1.macys.com/shop/womens-clothing/jeans?id=3111).
  # 11. Verify that URL should display as below format: [Domain]/shop/[Parent Category Name]/[Category Name]?id=XXXX . (ex: www.macys.com/shop/women/ womens?id=118).
  # 12. Verify that user should redirect to new URL format. Format should display as follows: www1.macys.com/shop/womens-clothing?id=118
  # 13. www1.macys.com/shop/wedding-registry/mens-clothing?id=1
  # Note : Check for categories and subcategories and in International mode.

  #########################################################  Macys Logo link #########################################
  #TestLink - MCOM-96617 Vone - RT-07472
  @use_regression @domain_discovery @mode_domestic @priority_high @mcom_dsv_sev1 @dsv_desktop_sev1 @migrated_to_sdt @discovery_daily_run
  Scenario: BrowsePage  - Verify the Macys Logo link in header in DOMESTIC mode
    Given I visit the web site as a guest user
    Given I am on Browse Page for "Pants" under "MEN"
    Then I verify that Macys logo takes to Home Page
    # Click on the Macys link from browse page and make sure it navigates to Home page

