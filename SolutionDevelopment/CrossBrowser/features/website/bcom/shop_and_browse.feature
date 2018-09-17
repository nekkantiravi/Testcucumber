Feature: Verify Sort by, Pagination on Dynamic land page, Brand Index Page, Sub Splash Page, Browse Page, Search Result Page in all 3 modes.

  @scenario1 @domain_discovery @xbrowser @xbrowser_two @high
  Scenario Outline: Verify Sort by, Pagination on Search Result page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (low-high)   |
      | Price (high-low)   |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @scenario2 @domain_discovery @xbrowser @xbrowser_two @data_dependency
  Scenario Outline: Verify Sort by, Pagination on Dynamic Landing page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (low-high)   |
      | Price (high-low)   |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @scenario3 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Verify Sort by, Pagination on Browse page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (low-high)   |
      | Price (high-low)   |
    And I should be able to navigate using pagination functionality

    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @scenario4 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Verify user is able to navigate to brand index page in all modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to brand index page in "<mode_name>" mode
    Then I should be navigated to brand index page

    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |