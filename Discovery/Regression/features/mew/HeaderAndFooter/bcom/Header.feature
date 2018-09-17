Feature: Verify BCOM Componentization :: MEW Header UI

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Header : Verify all the elements in Left Navigation Menu
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    Then I should see global navigation panel
    And I verify all elements of left navigation in Header Menu
      | MENU                  |
      | DESIGNERS             |
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
      | EDITORIAL             |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Navigating twice on the Global Navigation Button should collapse the Global Navigation Panel
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    Then I should see global navigation panel
    And I should not see global navigation panel if I click on global navigation

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the top level FOBs for international customers
    Given I visit the mobile web site as a guest user in iship mode
    When I open the global navigation
    Then I should see global navigation panel
    And I verify all elements of left navigation in Header Menu
      | MENU                  |
      | DESIGNERS             |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |
      | EDITORIAL             |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify the left navigation expands when hamburger menu is opened in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I open the global navigation
    Then I verify left navigation expands in "<mode>" mode
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Verify bloomingdales logo and quick bag icon in Header menu in domestic and registry mode
    Given I visit the mobile web site as a guest user in <mode> mode
    Then I verify bloomies Logo is displayed in Header
    And I should see shopping bag icon is displayed in Header
    And I should see the wishlist wrapper in Header
    Examples:
      | mode     |
      | domestic |
      | registry |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify bloomingdales logo and quick bag icon in Header menu in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify bloomies Logo is displayed in Header
    And I should see shopping bag icon is displayed in Header
    And I should not see the wishlist wrapper in Header

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Verify on clicking the logo will redirect to the homepage in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I click on bloomies logo in header
    Then I verify logo will be redirect to the homepage
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Verify  default text: “Search or enter web ID” is displayed for search bar in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    Then I verify default text in Search bar is "Search"
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify user lands on search results page from Search box
    Given I visit the mobile web site as a guest user
    When I sign in to my existing profile using mobile website
    When I search using mobile website for "men's watches"
    Then I should be in Search Landing page
    And I should see the global navigation button displayed

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Navigating twice on the Global Navigation Button on random page should collapse the Global Navigation Panel
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    And I navigate on menu item "Women"
    Then I should see the global navigation button displayed
    And I should not see global navigation panel if I click on global navigation

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify all categories are clickable on hamburger navigation
    Given I visit the mobile web site as a guest user
    Then I verify following categories are clickable on hamburger navigation:
      | DESIGNERS             |
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

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify Recent Searches text in recent search panel in domestic mode,iship and registry modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | Pants           |
      | Clearance shoes |
      | Electrics       |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario:As a signed in user I want to verify GN is expanded till My Account
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | My Account |
    And I open the global navigation
    Then I verify GN is expanded till "My Account"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:Verify GN is expanded till selected brand page
    Given I visit the mobile web site as a guest user
    And I type "<string>" in mew search and click enter
    Then I should be on the browse page
    And I verify GN is expanded till "<string>"
    Examples:
      | string   |
      | Chanel   |
      | Gucci    |
      | burberry |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Registy in IShip mode
    Given I visit the mobile web site as a guest user in iship mode
    When I open the global navigation
    And I navigate on menu item "The Registry"
    Then I Should not see Registry Home page in IShip mode

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify the display of Global navigation menu on My account, credit card pages as registered user
    Given I visit the mobile web site as a registered user
    When I open the global navigation
    And I navigate on menu item "<page>"
    Then I should see the global navigation button displayed
    And I verify bloomies Logo is displayed in Header
    And I should see shopping bag icon is displayed in Header
    And I should see the wishlist wrapper in Header
    Examples:
      | page           |
      | MY ACCOUNT     |
      | MY CREDIT CARD |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the display of Global navigation menu on bwallet page as registered user
    Given I visit the mobile web site as a registered user
    When I open the global navigation
    And I navigate on menu item "MY bWALLET"
    Then I should see the global navigation button displayed
    And I verify bloomies Logo is displayed in Header
    And I should see shopping bag icon is displayed in Header

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the display of header on registry home page
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    And I navigate on menu item "The Registry"
    Then I should see the global navigation button displayed
    And I verify bloomies Logo is displayed in Header
    And I should see shopping bag icon is displayed in Header
    And I should see the wishlist wrapper in Header

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify the display of Global navigation menu on My account,registry,credit card and on bwallet pages
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    And I navigate on menu item "<page>"
    Then I should see the bloomingdales logo on unified login page header
    And I should see shopping bag icon is displayed in Header
    Examples:
      | page           |
      | MY ACCOUNT     |
      | MY CREDIT CARD |
      | MY bWALLET     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify global navigation is visible on pdp pages in domestic and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | MEN   |
      | Pants |
    Then I should be on the browse page
    And I scroll to 18th product on browse page
    When I open the global navigation
    Then I should see global navigation panel
    And I should not see global navigation panel if I click on global navigation
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM BrowsePage - Verify product persistence on browse pages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    Then I should be on the browse page
    And I scroll to 18th product on browse page
    When I open the global navigation
    Then I should see global navigation panel
    And I should not see global navigation panel if I click on global navigation

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the display of Global navigation menu on Find Registry pages
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Find Registry|
    Then I should see the global navigation button displayed
    And I verify Registry title is displayed in Header
    And I should see shopping bag icon is displayed in Header

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the display of Global navigation menu on Create Registry pages
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Create Registry|
    Then I should see the bloomingdales logo on header part of create registry login page
    And I should see shopping bag icon is displayed in Header

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the display of Global navigation menu on Manage Registry pages
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Manage Registry|
      | View Registry  |
    Then I should see the bloomingdales logo on header part of create registry login page
    And I should see shopping bag icon is displayed in Header
