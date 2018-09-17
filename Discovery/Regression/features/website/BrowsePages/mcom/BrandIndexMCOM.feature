#Author: Discovery SNBC QE
#Date Created: 04/09/2017


Feature: Verify Brand Index Page functionality in Browse page

 # @13f
  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp
  Scenario: BrowsePage -  Verify Navigation to a brand index page Click on a FOB and then click on a brand in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to brand index page in "domestic" mode
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    When I click on any brand FOB link in left navigation
    And I select any brand in brand index page
    Then I verified selected Brand returned as keyword
    Then I verify Facets,Pagination, Show Items and Sort by display on the page

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario: BrowsePage - Verify Brand Facet for Browse Page in Domestic Mode
    Given I am on CategoryBrowsePage for "65147" category id in Domestic mode
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of Featured Brands is expanded
    And I verify that default state of All Brands is collapsed
    When I select "collapse" button on left of "Featured Brands"
    Then I verify that "Featured Brands" got "collapsed"
    When I select "expand" button on left of "Featured Brands"
    Then I verify that "Featured Brands" got "expanded"
    When I select "expand" button on left of "All Brands"
    Then I verify that "All Brands" got "expanded"
    And I verify that brands are duplicated within Featured Brands
    And I verify that sequencing of all brand values under All Brands is alpha numeric
    # Notes:
    # things to verify:
    # should see "Featured brands" and "All brands"
    # should see "Featured brands" by default expanded
    # should see "All brands" by default collapsed
    # expanding "All brands" should see Scrollbar appear on the right side to enable me scroll down to view all the brands

  #These might be replaced by Unified Navigation project
  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario: BrowsePage -  Verify brand names are displayed in alphabetical order in Shop all brands page in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "See All Brands" browse page under "HOME"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    Then I verify the display of GNA in Shop all brands page
    And I verify the display of GFA in Shop all brands page
    And I verify all the brand names is in alphabetical order

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario: BrowsePage - Verify existing brand URL navigated to category has "http://www.macys.com/shop/..." in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "See All Brands" browse page under "WOMEN"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    When I click on any brand FOB link in left navigation
    When I select a brand in designer index page assigned to "category"
    Then I verify navigated URL is in "/shop/" format

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario: BrowsePage - Verify existing brand URL assigned to DLP has "http://www.macys.com/shop/featured/..." for normal item in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to the "See All Brands" browse page under "WOMEN"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    When I click on any brand FOB link in left navigation
    When I select a brand in designer index page assigned to "DLP"
    Then I verify navigated URL is in "/shop/featured/" format

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario: BrowsePage - Verify the ability to see brand specific search results page in "/shop/featured or /shop" format in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to the "See All Brands" browse page under "WOMEN"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    When I click on any brand FOB link in left navigation
    Then I select any brand in brand index page
    Then I should be in Search Landing or redirected Browse Page page
    And I verified selected Brand returned as keyword
    And I verify navigated URL is in "/shop/featured or /shop" format
    And I Verify the URL contain "cm_sp" tag to support coremetrics

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario: BrowsePage - Verify URL under /shop/featured/ or /shop/ and URL structure in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "See All Brands" browse page under "WOMEN"
    And I modify the url to get in to snbc experiment
    And I click on any brand FOB link in left navigation
    When I select a brand in designer index page assigned to "DLP"
    Then I verified selected Brand returned as keyword
    Then I verify navigated URL is in "/{Brand-name}-{FOB-name-from-Brand-Index}?{any-required-parameters}" format
