Feature: Verify Sort by, Pagination on Dynamic land page, Brand Index Page, Sub Splash Page, Browse Page, Search Result Page in all 3 modes.

  @scenario1 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Verify Sort by, Pagination on Search Result page in all modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search using mobile website for "cookware"
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

    Examples:
      | mode      |
      | domestic  |
      | iship     |
      | registry  |

  @scenario2 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Verify Sort by, Pagination on Dynamic Landing page in all modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop                                                   |
      | Handbags & Sunglasses                                  |
      | See All Brands or All Handbag Brands or Handbag Brands |
    And I select random link to navigate to dynamic landing page using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

    Examples:
      | mode     |
      | domestic |
      | iship    |

  @scenario3 @xbrowser_tablet @domain_discovery @xbrowser_mew @wip
  Scenario: Verify Sort by, Pagination on Dynamic Landing page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories          |
      | Kitchen                  |
      | See All Brands or Brands |
    And I select random link to navigate to dynamic landing page using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

  @scenario4 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Verify Sort by, Pagination on Browse page in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

    Examples:
      | mode     |
      | domestic |
      | iship    |

  @scenario5 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Verify Sort by, Pagination on Browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories        |
      | Dining & Entertaining  |
      | Dinnerware             |
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

  @scenario6 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Verify user is able to navigate to brand index page in all modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search using mobile website for "brands"
    Then I should be navigated to mobile brand index page

    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |

  @scenario7 @domain_discovery @xbrowser_mew
  Scenario: Verify user can select different display options on browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    When I select list view display options on browse page
    Then I should see products in list view on browse page
    When I select grid view display options on browse page
    Then I should see products in grid view on browse page
    When I select gallery view display options on browse page
    Then I should see products in gallery view on browse page
#    Swipe functionality is removed from 17N (B-87111)
#    When I select swipe view display options on browse page
#    Then I should see products in swipe view on browse page

  @scenario8 @domain_discovery @xbrowser_mew
  Scenario: Verify user can change colors, sizes, quantity and zoom product image on PDP page
    Given I visit the mobile web site as a guest user
    When I navigate to "with_size and with_color" product PDP page
    Then I should be able to change product quantity on PDP page
    And I select product related attributes from PDP using mobile website
    And I verify image can be zoomed on PDP using mobile website

  @scenario9 @xbrowser_mew @domain_discovery
  Scenario: Verify user can navigate to Master Page and add different color product to bag
    Given I visit the mobile web site as a guest user
    When I navigate to "master_product and with_color" product PDP page
    Then I select random color and add member product to bag from master PDP

  @scenario10 @xbrowser_mew @domain_discovery
  Scenario: Verify that ads are present and clickable from any of the category
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
    And I verify banner images are displayed on category splash page
    And I select a random banner image on category splash page
    Then I should be redirected to "category_browse" page

  @scenario11 @xbrowser_mew @domain_discovery
  Scenario: Verify all categories are clickable on hamburger navigation
    Given I visit the mobile web site as a guest user
    Then I verify following categories are clickable on hamburger navigation:
      | Women                    |
      | Men                      |
      | Kids & Baby              |
      | Shoes                    |
      | For The Home             |
      | Beauty                   |
      | Handbags & Sunglasses    |
      | Jewelry & Watches        |
      | Gift Guides & Gift Cards |
      | Plus & Petite            |
      | Juniors & Guys           |
      | Furniture & Mattresses   |

  @scenario12 @domain_discovery @xbrowser_mew
  Scenario: Verify user can manipulate the filters on browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I select "Brand" facet on left nav using mobile website
    And I select "Adrianna Papell" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should see following elements on "category_browse" page:
      | facet_section          |
      | facet_breadcrumb_value |

  @scenario13 @xbrowser_mew @domain_discovery
  Scenario: Verify appropriate products are displayed for the category
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | Shoes        |
      | Casual Shoes |
    Then I verify appropriate products are displayed for the category
