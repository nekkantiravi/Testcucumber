############################################
# Author: Discovery Regression QE Team
# Date: 15th June 2017
# Date Modified: None
############################################

Feature: Verify browse page dsv features in DOMESTIC, ISHIP and REGISTRY mode

  # BAT Scenario for Launch Call
  #BLCOM-80216 BLCOM-80218 BLCOM-80222
  @use_dsv @dsv_desktop_sev1 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @ifs @akamai_waf
  Scenario Outline: BrowsePage -  Verify EDITORIAL FOB and category browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    When I navigate to category browse page from "<FOB>"
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I should be in Category Browse page
    Examples:
      | FOB        |
      | EDITORIAL |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB WOMEN in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB   |
      | WOMEN |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB MEN in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB |
      | MEN |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB KIDS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB  |
      | KIDS |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB BEAUTY in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB    |
      | BEAUTY |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB SHOES in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB   |
      | SHOES |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB HOME in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB  |
      | HOME |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB GIFTS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB   |
      | GIFTS |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB HANDBAGS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB      |
      | HANDBAGS |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB  JEWELRY & ACCESSORIES in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB                   |
      | JEWELRY & ACCESSORIES |

  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @dsv_desktop_sev2 @akamai_waf
  Scenario Outline: BrowsePage - Verify FOBs and two category browse page in each FOB  SALE in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<FOB>" category page
    Then I verify the basic attributes of cat splash page
    And I navigate to "random" browse page from cat splash page
    Then I verify the basic attributes of Browse page
    When I click on sub category link in flyout from "<FOB>"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    Examples:
      | FOB  |
      | SALE |
  # Notes:
  # Basic attributes to verify in cat splash page
  #  - verify page loaded without any errors or broken images
  #  - verify header and footer displayed
  #  - verify leftnav  displayed
  #  - verify SEO tag cloud and Recently viewed displayed
  #  - verify flyout menu
  # When i navigate to a browse page using left navigation links
  # When i click on left nav and if its a sub splash page,
  # Then verify the basic attributes of sub splash page as well.
  # If it navigates to a browse page directly then verify browse page
  # Verify navigating to category page or browse page through flyout menu
  # Basic attributes to verify in cat splash page
  #  - verify page loaded without any errors or broken images
  #  - verify header and footer displayed
  #  - verify leftnav  displayed
  #  - verify SEO tag cloud and Recently viewed displayed
  # Basic attributes to verify in cat Browse page
  #  - verify page loaded without any errors or broken images
  #  - verify header and footer displayed
  #  - verify leftnav  displayed with facets
  #  - verify SEO tag cloud and Recently viewed displayed
  #  - verify product thumbnail is displayed with price in $
  #  - verify pagination and sort options displayed


  #Test Case ID: BLCOM-80282 MCOM-92035, MCOM-92052,BLCOM-84249
  #Vone - RT-06512
  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic @akamai_waf
  Scenario: BrowsePage - Verify multi Facet functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Pants" browse page under "WOMEN"
    Then I verify the functionality of selecting multiple facets
  # Notes:
  # Select two price facets and verify the products load accordingly
  # Select two size facets and verify the products load accordingly
  # Select two color facets and verify the products load accordingly
  # Click a two brand facets and verify the products load accordingly

  #Test Case ID: MCOM-92035
  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @akamai_waf
  Scenario: BrowsePage - Verify clear and clear all after selecting multi Facets in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Pants" browse page under "WOMEN"
    Then I verify the functionality of deselecting multiple facets
  # Notes:
  # Select two price facets and verify the products load accordingly
  # Select two size facets and verify the products load accordingly
  # Select two color facets and verify the products load accordingly
  # Click a two brand facets and verify the products load accordingly
  # Click "clear" in price facet section and verify all selected price facets are deselected and the products are loaded accordingly
  # Click on selected size facet and verify that the facet is deselected and the products are loaded accordingly
  # Click "clear all" on top of the facet area and verify all selected facets are deselected and the products should update in the Browse page in default view

  @use_dsv @dsv_sev3 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic @use_launch @dsv_desktop_sev2 @akamai_waf
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

  @use_dsv @dsv_category_25 @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_seo @preview_desktop @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Popular Searches functionality for HOME - Bedroom Furniture in DOMESTIC mode
    Given I am on Browse Page for "Bedroom Furniture" under "HOME"
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable

  @use_dsv @dsv_category_24 @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_seo @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Popular Searches functionality for HOME - Mattresses in DOMESTIC mode
    Given I am on Browse Page for "Mattresses" under "HOME"
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable

  @use_dsv @dsv_category_6 @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_seo @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Popular Searches functionality for HOME - Luggage in DOMESTIC mode
    Given I am on Browse Page for "Luggage" under "HOME"
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable

  #BLCOM-80222 TestLink - BLCOM-84256 Vone - RT-06514
  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic
  Scenario: BrowsePage - Verify Pagination in DOMESTIC mode
    Given I am on Browse Page for "All Shoes" under "SHOES"
    Then I verify the functionality of pagination when there are products above default product count in browse page
    When I navigate to a random page other than first and last page from pagination
    Then I verify the functionality of pagination when there are products above default product count in browse page
    When I navigate to first page from pagination
    Then I verify there is no left arrow in browse page
    And I verify the functionality of pagination when there are products above default product count in browse page
    When I navigate to last page from pagination
    Then I verify there is no right arrow in browse page
    And I verify the functionality of pagination when there are products above default product count in browse page
    # Notes:
    # Please update the existing step defs or add new to verify the points listed below
    # When there are more than 100 items
    # Then 96 items should be displayed by default
    # 1st page should be highligted
    # Number of pages should be appropriate
    # When 1st Page, < link should not be displayed
    # When switch to last Page , >  should not be displayed
    # When switch to middle pages like  page 2 or 3, products from those page should be displayed
    # and page 2 and 3 should be highlighted
    # When click on > or < links , it should be navigate to appropriate pages
    # When there are less than 96 items then page number, prev, next link should not be displayed
    # when there are more than 1000 products, then i should see a ...
    # and clicking on ...should display a dropdown with inbetween pages and croll bat
    # when click on one of the pages from the dropdown , it should display products from that page
    # Verify that in The currently selected page is shown in bold black
    # Verify that in Pagination current selected page should be always part of a rolling group of at least three
    # Verify that the "Previous" arrow control and the "Next" arrow control should display in top and bottom paginations.
    # Verify that no back arrow should be there, but forward arrow should be there - while in 1st page
    # 3b. Verify the paginations display on top as well as the bottom of the page.
    # 3c. Verify that pagination should display by default as 1 2 3 4 . . . N (N = last page)
    # 6.Verify that page should scrolled to the top automatically before the results are displayed.
    # 7.verify that no back arrow should be there, but forward arrow should be there.

  #BLCOM-80222
  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @priority_high @mode_domestic
  Scenario: BrowsePage - Verify Sort by options in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    And I select "Price (low-high)" in sort by drop down
    Then I verify product display order for "Price (low-high)" option
    When I select "Price (high-low)" in sort by drop down
    Then I verify product display order for "Price (high-low)" option
    # Notes:
    # Please add new step def to verify the following
    # Sort by should have following options
    #  "Our top picks" ,  #  "New Arrivals"   #  "Best sellers"
    #  "Customer top rated" ,  #  "Price (High to Low)" ,  #  "Price (Low to High)"
    #  "Designer name (a-z)" ,  #  "Designer name (z-a)"
    # 2c. Verify the default sort by option should be set as  "Our top picks"
    # 3. Verify that it should display following order
    # "Our top picks"
    # "New Arrivals"
    # "Best sellers"
    # "Customer top rated"
    # "Price (High to Low)"
    # "Price (Low to High)"
    # "Designer name (a-z)"
    # "Designer name (z-a)"
    # 4. Verify that the product results should display according to the selection

  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify currency in category browse page and PDP Page in Iship mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia"
    Then I close the welcome mat if it's visible
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify currency in category browse page and PDP page

  #BLCOM-81513
  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @priority_medium @mode_domestic @akamai_waf
  Scenario: BrowsePage - Verify Brand Facet selection and add to bag navigation
    Given I visit the web site as a guest user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select "1" random facet buckets from "Designer" facet
    When I select a random member product
    And I add product to my bag from PDP Page
  # Notes:
  # Test case description
  # Brand  Facet
  # Test case steps
  # 1.Navigate to bloomingdales.com and sign in with valid credentials.
  # 2 Navigate to Women FOB
  # 3 Navigate to category "Denim"
  # 4 Select the required facets under "Brand"
  # 5 Click the required thumb nail
  # 6 Click "ADD TO BAG" button after selecting the size and quantity in the PDP page
  # 7 Click "Check out" button
  # 8 Click the "Continue Checkout" button
  # Test case expected result
  # 1.bloomingdales.com home page should be displayed and User should be signed in.
  # 2.All the available categories should be listed in the page
  # 3.The Category Browse page should open and display the related products in the Products List Page (PLP)
  # 4.The Category Browse page should open with all products
  # 5.The Product description page should display the elaborate details of the selected product
  # 6.A pop up window should be displayed prompting the user to check
  # 7.A confirmation screen should display the details related to saved items, your current shopping bag and the Total amount to be paid.
  # 8. It should navigate to Shipping and payment page.

  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify products are displayed with international currency value in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    And I close the welcome mat if it's visible
    When I navigate to the "Jeans" browse page under "MEN"
    Then I should see "AUD" currency for all products in thumbnail grid
    When I select a random member product
    Then I verify the currency "AUD" on product display page


