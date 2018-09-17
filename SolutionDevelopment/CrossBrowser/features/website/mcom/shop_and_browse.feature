Feature: Verify Sort by, Pagination on Dynamic land page, Brand Index Page, Sub Splash Page, Browse Page, Search Result Page in all 3 modes.

  @scenario1 @domain_discovery @xbrowser @xbrowser_two @high
  Scenario Outline: Verify Sort by, Pagination on Search Result page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top rated   |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @scenario2 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Verify Sort by, Pagination on Search Result page in registry mode
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | registry  |

  @scenario3 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Verify Sort by, Pagination on Dynamic Landing page in all modes
    Given I am on DynamicLandingPage in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | domestic  |
      | registry  |

  @scenario4 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Verify Sort by, Pagination on Browse page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @scenario5 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Verify user is able to navigate to brand index page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to brand index page in "<mode_name>" mode
    Then I should be navigated to brand index page

    Examples:
      | mode_name |
      | domestic  |
      | iship     |