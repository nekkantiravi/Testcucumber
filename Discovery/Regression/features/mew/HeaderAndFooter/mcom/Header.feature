#Author: Discovery QE
#Date Created: 06/21/2017

Feature: Verify MCOM Componentization :: MW Header UI

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Header : Verify all the elements in Left Navigation Menu
    Given I visit the mobile web site as a guest user
    When I click on open Navigation
    Then I verify All elements of left navigation in Header Menu
      | MENU               |
      | SHOP               |
      | DEALS              |
      | MY ACCOUNT         |
      | MACY'S CREDIT CARD |
      | WALLET             |
      | LISTS              |
      | STORES             |
      | WEDDING REGISTRY   |
      | CUSTOMER SERVICE   |
      | MORE               |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Navigating twice on the Global Navigation Button should collapse the Global Navigation Panel
    Given I visit the mobile web site as a guest user
    When I click on open Navigation
    Then I should not see global navigation panel if I close global navigation

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify whether expected categories are displayed in the mobile top nav
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    Then I Click on Shop
    Then I should see the top level categories under the shop menu:
      | FOBs                     |
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
      | Active & Wellness        |
      | Furniture & Mattresses   |
      | Wedding Registry         |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the top level FOBs for international customers
    Given I visit the mobile web site as a guest user in iship mode
    When I open the global navigation
    Then I should see the top level categories under the shop menu:
      | FOBs                  |
      | Women                 |
      | Men                   |
      | Kids & Baby           |
      | Shoes                 |
      | For The Home          |
      | Handbags & Sunglasses |
      | Jewelry & Watches     |
      | Plus & Petite         |
      | Juniors & Guys        |
      | Active & Wellness     |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify the left navigation expands when hamburger menu is opened in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I click on open Navigation
    Then I verify below "<mode>" left navigation expands
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify user navigates to category splash page from Global navigation menu
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop       |
      | Men        |
      | Activewear |
    Then I should be redirected to "category_splash" page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Verify Macys logo and quick bag icon in Header menu in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    Then I verify Macys Logo is displayed in Header
    And I verify shopping bag icon is displayed in Header
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Verify on clicking the logo will redirect to the homepage in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I click on Macys logo in header
    Then I verify logo will redirect to the homepage
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  #Need to implement Add to bag scenario to to cehck item count in bag icon
  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high @wip
  Scenario Outline:  Verify on clicking the shopping bag will redirect to the homepage in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I click on Macys logo in header
    Then I verify logo will redirect to the homepage
    When I add the product to the bag
    #When I add a registry product to the shopping bag
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Verify  default text: “Search or enter web ID” is displayed for search bar in domestic, registry and iship mode
    Given I visit the mobile web site as a guest user in <mode> mode
    Then I verify default text in Search bar is "Search or enter web ID"
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

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Navigating twice on the Global Navigation Button on random page should collapse the Global Navigation Panel
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    When I navigate on menu item "Shop"
    When I navigate on menu item "Women"
    Then I should not see global navigation panel if I close global navigation

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify all categories are clickable on hamburger navigation
    Given I visit the mobile web site as a guest user
    Then I verify following categories are clickable on hamburger navigation:
      | Women                 |
      | Men                   |
      | Kids & Baby           |
      | Shoes                 |
      | For The Home          |
      | Beauty                |
      | Handbags & Sunglasses |
      | Jewelry & Watches     |
      | Lingerie & Shapewear  |
      | Plus & Petite         |
      | Juniors & Guys        |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Recent Searches text in recent search panel in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | Pants           |
      | red dresses      |
      | Clearance shoes |
      | Electrics       |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Recent Searches text in recent search panel in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | Pants           |
      | red dresses      |
      | Clearance shoes |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Recent Searches text in recent search panel in international mode
    Given I visit the mobile web site as a guest user in iship mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | Pants           |
      | red dresses      |
      | Clearance shoes |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel


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
      | string  |
      | Chanel  |
      | Lenox   |
      | origins |
      | clarins |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high @manual
  Scenario Outline: As a user i should not see the header is loading allways for every search on searchbox in all the modes
    Given I visit the mobile web site as a guest user
    When I type "<string>" in mew search box
    Then I Should see the Header is not loaded for every search on search box
    Examples:
      | string  |
      | Jeans   |
      | Kipling |
      | Shirts  |
      | Pants   |
      | Dresses |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Registy in IShip mode
    Given I visit the mobile web site as a guest user in iship mode
    When I Click on Registry in IShip mode
    Then I Should not see Registry Home page in IShip mode

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high @manual
  Scenario: Verify Customer service in IShip mode
    Given I visit the mobile web site as a guest user in iship mode
    When I Click on customerservice from Left Nav in IShip mode
    Then I Should not see 404 Error page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Correct state of GN on View Registry page
    Given I visit the mobile web site as a registry user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Registry Tools   |
      | View Registry    |
    Then I verify GN is expanded till "View Registry"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify X icon present on the search box while typing in all the modes
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "<string>" in mew search box
    And I should see 'X' Icon on the right corner of the search box
    Examples:
      | string   | mode     |
      | Jeans    | domestic |
      | Shirts   | iship    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify user navigates to category splash page from hamburger menu
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
    Then I should be redirected to "category_splash" page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify GN is expanded correctly for Sale media banner
    Given I visit the mobile web site as a guest user
    When I click on sales media banner in home page
    Then I verify GN is expanded till "Sale"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify GN is expanded correctly for Deals page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Deals |
    Then I verify GN is expanded till "Deals"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify navigation of Registry category in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Registry |
    Then I verify user navigates to Gift Registry customer service page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify navigation of Customer Service category in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Customer Service |
    Then I verify user navigates to customer service page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high @wip
  Scenario: Verify autocomplete suggestions are displayed in domestic mode
    Given I visit the mobile web site as a guest user
    When I enter "je" keyword in search field for mew
    Then I should see autocomplete suggestions in mew

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high @wip
  Scenario: Verify autocomplete suggestions are displayed in IShip mode
    Given I visit the mobile web site as a guest user in iship mode
    When I enter "je" keyword in search field for mew
    Then I should see autocomplete suggestions in mew

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high @wip
  Scenario: Verify autocomplete suggestions are not displayed in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I enter "je" keyword in search field for mew
    Then I should not see autocomplete suggestions in mew

    # D-64363
  @domain_mew_discovery @use_mew_regression
    Scenario: D-64363 - Auto Suggestion selection in Domestic should not taking into Registry Search Pages
    Given I visit the mobile web site as a guest user in registry mode
    And I type "Plates" in mew search and click enter
    Then I should be on the registry search results page
    And I click on Macys logo and navigate back to home page
    When I enter "je" keyword in search field for mew
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    And I should be on the search results page
    Then I should not navigate to registry search page

    # D-64727
  @domain_mew_discovery @use_mew_regression
  Scenario: D-64727 - Auto Suggestion selection in Domestic should not taking into Registry Search Pages
    Given I visit the mobile web site as a guest user in registry mode
    And I type "Plates" in mew search and click enter
    Then I should be on the registry search results page
    And I click on Menu in GN to navigate back to home page
    When I enter "je" keyword in search field for mew
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    And I should be on the search results page
    Then I should not navigate to registry search page

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify all the elements in Left Navigation Menu in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I click on open Navigation
    Then I verify All elements of left navigation in Header Menu
      | MENU             |
      | SHOP             |
      | ORDER TRACKING   |
      | STORES           |
      | REGISTRY         |
      | CUSTOMER SERVICE |
      | LEGAL POLICIES   |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify all the elements in Left Navigation Menu in registry home page
    Given I visit the mobile web site as a guest user in registry mode
    When I click on open Navigation
    Then I verify All elements of left navigation in registry Header Menu
      | MENU             |
      | WEDDING REGISTRY |
      | GIFT CATEGORIES  |
      | REGISTRY GUIDE   |
      | REGISTRY TOOLS   |
      | FIND A REGISTRY  |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify all the elements in Left Navigation Menu in registry search page page
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Plates" in mew search and click enter
    Then I should be on the registry search results page
    When I click on open Navigation
    Then I verify All elements of left navigation in Header Menu
      | MENU             |
      | WEDDING REGISTRY |
      | GIFT CATEGORIES  |
      | REGISTRY GUIDE   |
      | REGISTRY TOOLS   |
      | FIND A REGISTRY  |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify user can navigate back to registry home page from registry search page
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Plates" in mew search and click enter
    Then I should be on the registry search results page
    When I tap on Wedding Registry link in left Nav
    Then I should be on the registry mobile home page
    When I click on open Navigation
    Then I verify All elements of left navigation in registry Header Menu
      | MENU             |
      | WEDDING REGISTRY |
      | GIFT CATEGORIES  |
      | REGISTRY GUIDE   |
      | REGISTRY TOOLS   |
      | FIND A REGISTRY  |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify user can navigate back to registry home page from registry browse page
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I verify GN is expanded till "Electrics"
    Then I should be on the browse page
    When I tap on Wedding Registry link in left Nav
    Then I verify All elements of left navigation in Header Menu
      | MENU             |
      | WEDDING REGISTRY |
      | GIFT CATEGORIES  |
      | REGISTRY GUIDE   |
      | REGISTRY TOOLS   |
      | FIND A REGISTRY  |
    When I tap on Wedding Registry link in left Nav
    Then I should be on the registry mobile home page
    When I click on open Navigation
    Then I verify All elements of left navigation in registry Header Menu
      | MENU             |
      | WEDDING REGISTRY |
      | GIFT CATEGORIES  |
      | REGISTRY GUIDE   |
      | REGISTRY TOOLS   |
      | FIND A REGISTRY  |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify user can navigate back to domestic home page when tapping on macys logo from registry home page
    Given I visit the mobile web site as a guest user in registry mode
    And I click on Macys logo and navigate back to home page
    When I click on open Navigation
    Then I verify All elements of left navigation in Header Menu
      | MENU               |
      | SHOP               |
      | DEALS              |
      | MY ACCOUNT         |
      | MACY'S CREDIT CARD |
      | WALLET             |
      | LISTS              |
      | STORES             |
      | WEDDING REGISTRY   |
      | CUSTOMER SERVICE   |
      | MORE               |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify user can navigate back to domestic home page when tapping on macys logo from registry search page
    Given I visit the mobile web site as a guest user in registry mode
    And I type "plates" in mew search and click enter
    Then I should be on the registry search results page
    And I click on Macys logo and navigate back to home page
    When I click on open Navigation
    Then I verify All elements of left navigation in Header Menu
      | MENU               |
      | SHOP               |
      | DEALS              |
      | MY ACCOUNT         |
      | MACY'S CREDIT CARD |
      | WALLET             |
      | LISTS              |
      | STORES             |
      | WEDDING REGISTRY   |
      | CUSTOMER SERVICE   |
      | MORE               |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify user can navigate back to domestic home page when tapping on Menu in left Nav from registry search page
    Given I visit the mobile web site as a guest user in registry mode
    And I type "plates" in mew search and click enter
    Then I should be on the registry search results page
    And I click on Menu in GN to navigate back to home page
    Then I verify All elements of left navigation in Header Menu
      | MENU               |
      | SHOP               |
      | DEALS              |
      | MY ACCOUNT         |
      | MACY'S CREDIT CARD |
      | WALLET             |
      | LISTS              |
      | STORES             |
      | WEDDING REGISTRY   |
      | CUSTOMER SERVICE   |
      | MORE               |

  @domain_mew_discovery @use_mew_regression
  Scenario: Header : Verify user can navigate back to domestic home page when tapping on Menu in left Nav from registry browse page
    Given I visit the mobile web site as a guest user in registry mode
    And I type "lenox" in mew search and click enter
    Then I should be on the registry browse page
    And I click on Menu in GN to navigate back to home page
    Then I verify All elements of left navigation in Header Menu
      | MENU               |
      | SHOP               |
      | DEALS              |
      | MY ACCOUNT         |
      | MACY'S CREDIT CARD |
      | WALLET             |
      | LISTS              |
      | STORES             |
      | WEDDING REGISTRY   |
      | CUSTOMER SERVICE   |
      | MORE               |

  @domain_mew_discovery @use_mew_regression
  Scenario:Verify GN is expanded till selected brand page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I type "Dinnerware" in mew search and click enter
    Then I should be on the registry browse page
    And I verify GN is expanded till "Dinnerware"

    # D-65022
  @domain_mew_discovery @use_mew_regression
  Scenario: D-65022 - Verify related brands are showing in brand index page for specific FOB
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop           |
      | For The Home   |
      | See All Brands |
    Then I should be redirected to designer page using mobile website
    And I should see related brands in brand index page for For The Home FOB

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DS-95103 - Verify category redirect search from registry browse page
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should be on the registry browse page
    And I type "<keyword>" in mew search and click enter
    Then I should be on the registry browse page
    And I verify GN is expanded till "<keyword>"
    Examples:
      | keyword   |
      | Lenox     |
      | waterford |
      | vitamix   |

  @domain_mew_discovery @use_mew_regression
  Scenario: DS-95103 - Verify GN in Lenox PDP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I type "Lenox" in mew search and click enter
    Then I should be on the registry browse page
    And I navigate to a random PDP page on browse page
    Then I should be redirected to registry PDP page
    And I verify GN is expanded till "Lenox"

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Domestic|Iship - Verify GN in PDP page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "Lenox" in mew search and click enter
    Then I should be on the browse page
    And I navigate to a random PDP page on browse page
    Then I should be on PDP page
    And I verify GN is expanded till "Lenox"
    Examples:
      | mode     |
      | domestic |
      | iship    |