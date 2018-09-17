Feature: Verify MCOM Pages served from DALLAS

  @dsv_dal
  Scenario Outline: Navigate to an FOB from flyouts with dca cookie as DAL in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I mouse over "<string>" category from top navigation
    And I select "<category>" subcategory from flyout menu
    And I verify the basic attributes of cat splash page
    And I verify that page is served from "DAL" server
    Examples:
      | string | category        |
      | women  | All Plus Sizes  |
      | men    | All Men's Shoes |

  @dsv_dal
  Scenario Outline: Navigate to an FOB from flyouts with dca cookie as DAL in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    When I mouse over "<string>" category from top navigation
    And I select "<category>" subcategory from flyout menu
    And I verify the basic attributes of cat splash page
    And I verify that page is served from "DAL" server

    Examples:
      | string | category        |
      | women  | All Plus Sizes  |
      | men    | All Men's Shoes |

   #This scenarios is N/A for iship
  @dsv_dal
  Scenario: Verify SEO tag cloud displayed on browse page with dca cookie as DAL in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I navigate to browse page in domestic mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" server
    And I verify that SEO tag clouds are displayed
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify SEO tag cloud displayed on browse page with dca cookie as DAL in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    When I navigate to browse page in registry mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" server
    And I verify that SEO tag clouds are displayed
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search-Sort by functionality is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I search a random product for mode domestic
    Then I should be in Search Landing page
    When I select different sort options from dropdown in domestic mode and Verify Search Page is served from "DAL":
      | Price: Low to High   |
      | Featured Items       |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @dsv_dal
  Scenario: Verify Search-Sort by functionality is served from Dallas for Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I search a random product for mode iship
    Then I should be in Search Landing page
    When I select different sort options from dropdown in iship mode and Verify Search Page is served from "DAL":
      | Price: Low to High   |
      | Featured Items       |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @dsv_dal
  Scenario: Verify Search-Sort by functionality is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I search a random product for mode registry
    Then I should be in Search Landing page
    When I select different sort options from dropdown in registry mode and Verify Search Page is served from "DAL":
      | Price: Low to High   |
      | Featured Items       |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @dsv_dal
  Scenario: Verify Browse-Sort by functionality is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to browse page in domestic mode with DCA cookie as DAL
    When I select different sort options from dropdown in domestic mode and Verify Browse Page is served from "DAL":
      | Price: Low to High   |
      | Featured Items       |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @dsv_dal
  Scenario: Verify Browse-Sort by functionality is served from Dallas for Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to browse page in iship mode with DCA cookie as DAL
    When I select different sort options from dropdown in iship mode and Verify Browse Page is served from "DAL":
      | Price: Low to High   |
      | Featured Items       |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @dsv_dal
  Scenario: Verify Browse-Sort by functionality is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to browse page in registry mode with DCA cookie as DAL
    When I select different sort options from dropdown in registry mode and Verify Browse Page is served from "DAL":
      | Price: Low to High   |
      | Featured Items       |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @dsv_dal
  Scenario: Verify order review page with a member product as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I select a random member product
    Then I verify that page is served from "DAL" server
    Then I should be redirected to PDP page
    When I add product to my bag from PDP Page
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "DAL" server