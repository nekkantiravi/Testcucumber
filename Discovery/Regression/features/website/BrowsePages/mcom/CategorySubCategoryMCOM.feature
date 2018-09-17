#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify the features in every category or sub category

############################################## Cat Splash Page #########################################

  # Now 100% of category pages are coming up with canvas media. Hence there is not FT data on category pages to verify. So adding @wip tag for backup.
  #Test case ID: MCOM-92029 MCOM-92030, MCOM-57276
  @use_dsv @domain_discovery @use_regression @mode_domestic @priority_medium
  Scenario: CategorySplashPage - Verify category splash page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify the display of category splash page
    # Notes:
    # VERIFY:
    # Update the verification step def to look for the expected assets from DB and verify the images, Banners, ads, links etc..
    # When the existing step def is updated to verify the ads, scenario "Verify Ads in category splash page" can be deleted.
    # All ads and images should display without any broken images
    # Description: Category linking
    # Steps:
    # 1. Navigate to macys.com
    # 2. Open any category
    # 3. Click on the top banner, main ad, all the links, featured categories, sub ads, any banner, customer top rated products.
    # Expected Results:
    # 1. It should navigate to macys website
    # 2. It should navigate to respective category
    # 3a.It should navigate to the respective page
    # 3b. Respective page should not have any broken image, and page should be aligned.

  #Test case number: MCOM-75954, MCOM-78144
  #Testlink-MCOM-75954 Vone - RT-07503
  @use_regression @domain_discovery @snbc_comp @priority_high @mode_domestic @priority_medium
  Scenario: SubSplashPage -  Verify the display of Reviews count in DOMESTIC mode
    Given I am on CategoryBrowsePage for "29891" category id in Domestic mode
    Then I verify Review counts in category page
    # Notes:
    # verify on browse page:
    # Star ratings and review count in Catgeory Sub Splash page
    # Review count should be displayed under the product thumbnail in paranthesis after the star rating.
    # Eg:  13 reviews: XXXXX (13)

  @snbc_comp @priority_high @domain_discovery @mode_domestic
  Scenario: SubSplashPage - Ensure product thumbnails displayed as existing on subsplash page when facet values are selected and deselected from facet panel after browse grid implementation in DOMESTIC mode
    Given I am on CategoryBrowsePage for "29891" category id in Domestic mode
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that sub splash contents are displayed
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    And I verify that sub splash contents are displayed

#http://jira/browse/ECOMSST-31229
  @release_14D @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario Outline: SubSplashPage - Verify that featured products header text in subsplash pages in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the given "<cat_id>" page
    Then I should see "<featured_products_header>" in featured product header

    Examples:
      | cat_id |featured_products_header |
      | 8380   |      Best Sellers       |
      | 5201   |     Our Best Gifts      |

  #################################### Gift Card Category Page ##############################################

  #Test Case ID: MCOM-79676
  @use_dsv @domain_discovery @use_regression @artifact_navapp @mode_domestic @priority_medium @xbrowser_browse
  Scenario: SubSplashPage - Verify Gift Card Category Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to gift card category splash page
    Then I verify the basic attributes of gift card category splash page
    # Notes:
    #  Test case description:
    #  1.Navigate to macys.com
    #  2.Click on Gift Cards
    #  Test case expected results:
    #  1.macys.com home page should be displayed.
    #  2a.It should navigate to Gift Cards page
    #  2b.It should display Main ads and sub ads) Card type & show by Occasion)

  @use_regression @domain_discovey @mode_domestic @priority_medium @wip
  Scenario: CategorySplashPage - Verify Gift Cards new layout in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to gift card category splash page
    Then I verify the new layout of the Gift Card page
    # Notes:
    # It should display Main ads and sub ads) Card type & show by Occasion)
    # Test case description:
    # Verify Gift Cards new layout
    # Test case steps:
    # 1.Navigate to macys.com
 # 2.Click on Gift Cards
 # Test case expected results:
 # 1.macys.com home page should be displayed.
 # 2a.It should navigate to Gift Cards page
 # 2b.It should display Main ads and sub ads) Card type & show by Occasion)


################################################## SEO Tag Cloud ####################################

  ##Clicking on some of the SEO tags are navigating to production site.##
  ##This scenario may fail when running in qa environment as the starting environment is different from the
  # navigated environment##
  ##Test Case ID: MCOM-85073, MCOM-86803MCOM-92055
  @use_dsv @domain_discovery @use_regression @backlog_photon @priority_medium
  Scenario Outline: CategorySplashPage - Verify SEO Tag cloud in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category splash page
    Then I should see the Popular Searches at the bottom
    And I verify the page for keyword which has "<option>" from popular searches
    Examples:
      | option          |
      | /shop/featured/ |
      | /shop/          |
#    | /cms/slp/2/ URL     |
    # Notes:
    # VERIFY:
    # Update the existing scenario to verify all points listed below
    # Verify the Tag clouds are displayed
    # SEO tag cloud should display on the bottom right of the page
    # Click on a keyword which has bold font
    # Products thumbnails should display in the page
    # Available facets should display
    # Page URL should contain "/buy/" phrase
    # Click through the links and make sure it navigates to appropriate page
    # Test different types of links like - /shop/featured, /shop, cms/slp/2/
    # For cms/slp/2/ - Make sure the page loaded and the link for products or categories take to appropriate pages.