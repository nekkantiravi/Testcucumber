Feature: Verify BCOM Pages being served from DAL

  #This scenarios is N/A for registry mode
  @dsv_dal
  Scenario Outline: Navigate to an FOB from flyouts with dca cookie as DAL in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I mouse over "<string>" category from top navigation
    And I select "<category>" subcategory from flyout menu
    Then I should be navigated to "<category>" category page
    And I verify that page is served from "DAL" server
    Then I verify the basic attributes of cat splash page

    Examples:
      | string | category  |
      | women  | Plus      |
      | home   | Furniture |

  @dsv_dal
  Scenario Outline: Navigate to an FOB from flyouts with dca cookie as DAL in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I mouse over "<fob>" category from top navigation
    And I select "<category>" subcategory from flyout menu
    Then I should be navigated to "<category>" category page
    And I verify that page is served from "DAL" server
    Then I verify the basic attributes of cat splash page

    Examples:
      | fob   | category  |
      | women | Plus      |
      | home  | Furniture |

  @dsv_dal
  Scenario: Verify SEO tag cloud displayed on browse page with dca cookie as DAL in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to browse page in domestic mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" server
    And I verify that SEO tag clouds are displayed
    And I verify that page is served from "DAL" server

 # for below scenario - registry automcomplete suggestions are not displaying manually as well
  @dsv_dal
  Scenario: Verify autocomplete suggestions are displayed with dca cookie as DAL in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I type "wo" in search box
    Then I should see autocomplete suggestions
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify autocomplete suggestions are displayed with dca cookie as DAL in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I type "wo" in search box
    Then I should see autocomplete suggestions
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search-Sort by High/low product order functionality is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search for "Dresses"
    Then I should be in Search Landing page
    When I select different sort options from dropdown in domestic mode and Verify Search Page is served from "DAL":
      | Price (high-low)   |
      | Price (low-high)   |

  @dsv_dal
  Scenario: Verify Search-Sort by High/low product order functionality is served from Dallas for Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search for "Dresses"
    Then I should be in Search Landing page
    When I select different sort options from dropdown in iship mode and Verify Search Page is served from "DAL":
      | Price (high-low)   |
      | Price (low-high)   |

  @dsv_dal
  Scenario: Verify Search-Sort by High/low product order functionality is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode registry
    Then I should be in Search Landing page
    When I select different sort options from dropdown in registry mode and Verify Search Page is served from "DAL":
      | Price (high-low)   |
      | Price (low-high)   |

  @dsv_dal
  Scenario: Verify Browse-Sort by High/low product order functionality is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I mouse over "women" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    Then I verify that page is served from "DAL" server
    When I select different sort options from dropdown in domestic mode and Verify Browse Page is served from "DAL":
      | Price (high-low)   |
      | Price (low-high)   |

  @dsv_dal
  Scenario: Verify Browse-Sort by High/low product order functionality is served from Dallas for Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    When I mouse over "women" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    Then I verify that page is served from "DAL" server
    When I select different sort options from dropdown in iship mode and Verify Browse Page is served from "DAL":
      | Price (high-low)   |
      | Price (low-high)   |

  @dsv_dal
  Scenario: Verify Browse-Sort by High/low product order functionality is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    When I mouse over "DINING & ENTERTAINING" category from top navigation
    And I select "Fine China" subcategory from flyout menu
    Then I verify that page is served from "DAL" server
    When I select different sort options from dropdown in registry mode and Verify Browse Page is served from "DAL":
      | Price (high-low)   |
      | Price (low-high)   |

  @dsv_dal
  Scenario: Verify browse page from flyouts is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    And I click on sub category link in flyout from "WOMEN"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify browse page from flyouts is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    And I click on sub category link in flyout from "KITCHEN"
    Then I verify the basic attributes of Browse page or Cat Sub Splash page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify browse page from FOB is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    And I click on sub category link in flyout from "WOMEN"
    Then I verify the basic attributes of iship browse or cat splash page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify order review page with a member product as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user
    Then I verify that page is served from "DAL" server
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signedIn" user
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario:Verify Browse-Sort by options functionality is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I navigate to category browse page
    Then I verify that page is served from "DAL" server
    Then I "should" see the following options in the Sort By list
      |New Arrivals        |
      |Best Sellers        |
      |Customer Top Rated  |
      |Price (high-low)    |
      |Price (low-high)    |
      |Our Top Picks       |
    And I verify display order of sort by drop down
    Then I verify each sort by option on DAL Server
      |Best Sellers        |
      |Customer Top Rated  |
      |Our Top Picks       |
      |New Arrivals        |

  @dsv_dal
  Scenario: Verify Search-Sort by options functionality is served from Dallas for Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I search a random product for mode iship
    Then I should be in Search Landing page
    Then I verify that page is served from "DAL" server
    Then I "should" see the following options in the Sort By list
      |New Arrivals        |
      |Best Sellers        |
      |Customer Top Rated  |
      |Price (high-low)    |
      |Price (low-high)    |
      |Our Top Picks       |
    And I verify display order of sort by drop down
    Then I verify each sort by option on DAL Server
      |Best Sellers        |
      |Customer Top Rated  |
      |Our Top Picks       |
      |New Arrivals        |

  @dsv_dal
  Scenario: Verify Browse-Sort by options functionality is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    When I navigate to category browse page
    Then I verify that page is served from "DAL" server
    Then I "should" see the following options in the Sort By list
      |New Arrivals        |
      |Best Sellers        |
      |Customer Top Rated  |
      |Price (high-low)    |
      |Price (low-high)    |
      |Our Top Picks       |
    And I verify display order of sort by drop down
    Then I verify each sort by option on DAL Server
      |Best Sellers        |
      |Customer Top Rated  |
      |Our Top Picks       |
      |New Arrivals        |