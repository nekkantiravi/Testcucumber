# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification TOPNAV FLYOUTS functionality in DOMESTIC, ISHIP and REGISTRY modes

  ################################################ Flyout Menu #########################################################

  @use_regression @domain_discovery @priority_high @mode_registry @mode_domestic @mode_iship
  Scenario Outline: CatSplashPage - Verify TOPNAV - FLYOUTS in Search Results Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to search results page in "<mode_name>" mode
    And I mouse over "Women" category from top navigation
    Then I verify that flyout menu is displayed
  Examples:
    | mode_name |
    | domestic  |
    | registry  |
    | iship     |

  @use_regression @domain_discovery @priority_high @mode_registry @mode_domestic @mode_iship
  Scenario Outline: PDP Page - Verify TOPNAV - FLYOUTS on PDP Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to search results page in "<mode_name>" mode
    Then I select a random product
    And I mouse over "Women" category from top navigation
    Then I verify that flyout menu is displayed
  Examples:
    | mode_name |
    | domestic  |
    | registry  |
    | iship     |

  @use_regression @domain_discovery @priority_high @mode_registry @mode_domestic @mode_iship
  Scenario Outline: ShoppingBagPage - Verify TOPNAV - FLYOUTS on Shopping bag page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to search results page in "<mode_name>" mode
    Then I select a random member product
    And I add items to bag after selecting upc of the product
    Then I should see Shopping Bag Page
    And I mouse over "Women" category from top navigation
    Then I verify that flyout menu is displayed
  Examples:
    | mode_name |
    | domestic  |
    | registry  |
    | iship     |

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario Outline: HomePage - Verify TOPNAV - FLYOUTS should be displayed for all FOB in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
  Examples:
    |fob                     |
    | HOME                   |
    | WOMEN                  |
    | MEN                    |
    | BED & BATH             |
    | JUNIORS                |
    | KIDS                   |
    | BEAUTY                 |
    | JEWELRY                |
    | WATCHES                 |
    | SHOES                  |
    | HANDBAGS               |
    | BRANDS                 |

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario Outline: HomePage - Verify TOPNAV - FLYOUTS should be displayed for all FOB in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
  Examples:
    |fob                     |
    | HOME                   |
    | WOMEN                  |
    | MEN                    |
    | BED & BATH             |
    | JUNIORS                |
    | KIDS                   |
    | JEWELRY                |
    | WATCHES                |
    | SHOES                  |
    | HANDBAGS & ACCESSORIES |
    | BRANDS                 |


  @use_regression @domain_discovery @priority_high @mode_registry @mode_domestic @mode_iship
  Scenario Outline: BrowsePage - Verify TOPNAV - FLYOUTS should be displayed in Category Browse Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to search results page in "<mode_name>" mode
    When I navigate to random browse page
    And I mouse over "BED & BATH" category from top navigation
    Then I verify that flyout menu is displayed
  Examples:
    | mode_name |
    | domestic  |
    | registry  |
    | iship     |

  @use_regression @domain_discovery @priority_high @mode_registry @mode_domestic @mode_iship
  Scenario Outline: HomePage - Verify TOPNAV - FLYOUTS should be displayed in Home Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    And I mouse over "BED & BATH" category from top navigation
    Then I verify that flyout menu is displayed
  Examples:
    | mode_name |
    | domestic  |
    | registry  |
    | iship     |

    ######## Below scenarios are moved from Common folder as these are MCOM only scenarios
     # @release_13J
  @use_regression @priority_medium @domain_discovery @mode_iship @discovery_daily_run
  Scenario: LegacyPage - Verify TOPNAV - FLYOUTS on Stores legacy page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I change country to "India"
    And I close the welcome mat if it's visible
    When I navigate to new stores page
    Then I should see Ajax call to navapp for "flyout" in "SITE" mode
    When I mouse hover on any category
    Then I should see flyout menu

  @use_regression @priority_high @domain_discovery @mode_domestic @discovery_daily_run
  Scenario: My offer page  - Verify TOPNAV - FLYOUTS and new header footer elements verification on My Offer page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I click on "deals & promotions" link from subNav
    Then I should be navigated to "deals & promotions" page
    And I should see Ajax call to navapp for "topnav" in "SITE" mode
    And I verify dynamic top navigation in "domestic" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Domestic"

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: New Legacy Page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in domestic mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to new stores page
    Then I should see Ajax call to navapp for "topnav" in "domestic" mode
    And I verify dynamic top navigation in "domestic" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Domestic"

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: Order Confirmation page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in on Order Confirmation Page in DOMESTIC mode
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see Ajax call to navapp for "topnav" in "SITE" mode
    Then I verify dynamic top navigation in "domestic" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Domestic"

  @use_regression @priority_high @domain_discovery @mode_registry
  Scenario: SubSplashPage  - Verify TOPNAV - FLYOUTS and new header footer elements verification in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to the "Anolon" browse page under "KITCHEN"
    Then I verify dynamic top navigation in "registry" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Registry"

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: StoresPage - Verify TOPNAV - FLYOUTS on new stores page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to new stores page
    Then I should see Ajax call to navapp for "flyout" in "SITE" mode
    When I mouse hover on any category
    Then I should see flyout menu
