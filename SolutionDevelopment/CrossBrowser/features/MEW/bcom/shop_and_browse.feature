Feature: Verify Sort by, Pagination on Dynamic land page, Brand Index Page, Sub Splash Page, Browse Page, Search Result Page in all 3 modes.

  @scenario1 @xbrowser_tablet @xbrowser_mew @domain_discovery
  Scenario Outline: Verify Sort by, Pagination on Search Result page in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search using mobile website for "cookware"
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |
    And I should be able to navigate using pagination functionality using mobile website

    Examples:
      | mode      |
      | domestic  |
      | iship     |

  @scenario2 @xbrowser_tablet @xbrowser_mew @domain_discovery
  Scenario: Verify Sort by, Pagination on Search Result page in rigistry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
     | Find Registry |
    And I search using mobile website for "cookware"
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |
    And I should be able to navigate using pagination functionality using mobile website


  @scenario3 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Verify Sort by, Pagination on Browse page in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Men               |
      | Jeans or Shoes    |
      | Straight or Boots |
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |
    And I should be able to navigate using pagination functionality using mobile website

    Examples:
      | mode      |
      | domestic  |
      | iship     |

  @scenario4 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Verify Sort by, Pagination on Browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | KITCHEN               |
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |
    And I should be able to navigate using pagination functionality using mobile website

  @scenario5 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Verify Sort by, Pagination on Dynamic Landing page in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate to dynamic landing page in <mode> mode using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |
    And I should be able to navigate using pagination functionality using mobile website

    Examples:
      | mode      |
      | domestic  |
      | iship     |

  @scenario6 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Verify Sort by, Pagination on Dynamic Landing page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate to dynamic landing page in registry mode using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |
    And I should be able to navigate using pagination functionality using mobile website

  @scenario7 @xbrowser_tablet @xbrowser_mew @domain_discovery
  Scenario Outline: Verify user is able to navigate to brand index page in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | All DESIGNERS |
    Then I should be navigated to mobile brand index page

    Examples:
      | mode      |
      | domestic  |
      | iship     |

  @scenario8 @xbrowser_tablet @xbrowser_mew @domain_discovery
  Scenario: Verify user is able to navigate to brand index page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | All BRANDS            |
    Then I should be navigated to mobile brand index page

  @scenario9 @xbrowser_mew @domain_discovery
  Scenario: Verify all categories are clickable on hamburger navigation
    Given I visit the mobile web site as a guest user
    Then I verify following categories are clickable on hamburger navigation:
      | ALL DESIGNERS         |
      | WHAT'S NEW            |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | GIFTS                 |
      | SALE                  |

  @scenario10 @xbrowser_mew @domain_discovery
  Scenario: Verify user can manipulate the filters on browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women   |
      | Dresses |
    And I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should see "Blue" in facet breadcrumb

  @scenario11 @xbrowser_mew @domain_discovery
  Scenario: Verify user can manipulate the filters on search page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "dresses"
    And I should be redirected to "search_result" page
    And I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should see "Blue" in facet breadcrumb

  @scenario12 @xbrowser_mew @domain_discovery
  Scenario: Verify order by phone on PDP
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
    | Home                  |
    | Furniture             |
    | Bedroom Furniture     |
    | All Bedroom Furniture |
  And I select a random member product using mobile website
  Then I verify order by phone on PDP using mobile website

  @scenario13 @xbrowser_mew @domain_discovery
  Scenario: Verify write a review button on PDP as a guest user
    Given I visit the mobile web site as a guest user
    When I navigate to "available and member_product" product PDP page
    And I click on "expand_reviews_section" on "product_display" page
    And I click on "write_a_review" on "product_display" page
    Then I should see following elements on "sign_in" panel:
    | verify_page    |
    | email          |
    | password       |

  @scenario14 @xbrowser_mew @domain_discovery
  Scenario: Verify write a review button on PDP as a signed in user
    Given I visit the mobile web site as a registered user without add CC
    When I navigate to "available and member_product" product PDP page
    And I click on "expand_reviews_section" on "product_display" page
    And I click on "write_a_review" on "product_display" page
    Then I should see following elements on "write_review" panel:
      | write_review_rating  |
      | write_review_text    |
      | review_submit_button |

  @scenario15 @xbrowser_mew @domain_discovery
  Scenario: Verify user can navigate to Master Page and add different color product to bag
    Given I visit the mobile web site as a guest user
    When I navigate to "master_product and with_color" product PDP page
    Then I select random color and add member product to bag from master PDP

  @scenario16 @domain_discovery @xbrowser_mew
  Scenario: Verify user can change colors, sizes, quantity and zoom product image on PDP page
    Given I visit the mobile web site as a guest user
    When I navigate to "with_size and with_color" product PDP page
    Then I should be able to change product quantity on PDP page
    And I select product related attributes from PDP using mobile website
    And I verify image can be zoomed on PDP using mobile website

  @scenario17 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Navigate to Registry PDP from Registry Browse page verify item can be added to bag
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | KITCHEN               |
    And I select a random member product using mobile website
    And I click on "add_to_bag_button" on "registry_pdp" page
    Then I should see "atb_overlay" on "registry_pdp" panel

  @scenario18 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Navigate to Registry PDP from Registry Search page verify item can be added to bag
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Find Registry |
    When I search using mobile website for "cookware"
    And I select a random member product using mobile website
    And I click on "add_to_bag_button" on "registry_pdp" page
    Then I should see "atb_overlay" on "registry_pdp" panel

  @scenario19 @xbrowser_mew @domain_discovery
  Scenario: Verify the browse page loads properly from Cat Splash page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women   |
    Then I should be redirected to "category_splash" page
    When I click on "sub_categories" on "category_splash" page
    Then I should be redirected to "category_browse" page

  @scenario20 @xbrowser_mew @domain_marketing
  Scenario: Verify pdp offer details appears with content on it
    Given I visit the mobile web site as a guest user
    When I navigate to "member_product and with_bonus_offer" product PDP page
    And I click on "offer_label" on "product_display" page
    Then I should see following elements on "pdp_offer_details" panel:
      | header              |
      | offers_short_desc   |
      | offers_long_desc    |
      | offer_details       |