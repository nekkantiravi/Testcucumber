Feature: BCOM - Verify AutoComplete functionality in DOMESTIC, ISHIP and REGISTRY mode

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: HomePage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: HomePage - User types single character in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | J       |
      | iship    | J       |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: HomePage - User types multiple valid products words in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword    |
      | domestic | blue jeans |
      | iship    | blue jeans |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    Then I should be on the search results page
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User types single character in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    Then I should be on the search results page
    And I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | j       |
      | iship    | j       |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User types multiple valid products words in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    Then I should be on the search results page
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword    |
      | domestic | blue jeans |
      | iship    | blue jeans |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I randomly navigate to any browse page by clicking on child categories of below "SHOES"
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - User types single character in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I randomly navigate to any browse page by clicking on child categories of below "SHOES"
    And I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | J       |
      | iship    | J       |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - User types multiple valid products words in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I randomly navigate to any browse page by clicking on child categories of below "SHOES"
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword    |
      | domestic | blue jeans |
      | iship    | blue jeans |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I randomly navigate to any browse page by clicking on child categories of below "MEN"
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - User types single character in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I randomly navigate to any browse page by clicking on child categories of below "MEN"
    And I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | J       |
      | iship    | J       |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - User types multiple valid products words in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I randomly navigate to any browse page by clicking on child categories of below "MEN"
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword           |
      | domestic | levi's blue jeans |
      | iship    | levi's blue jeans |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    And I navigate to a random PDP page on search results page
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - User types single character in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    And I navigate to a random PDP page on search results page
    When I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | J       |
      | iship    | J       |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - User types multiple valid products in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    And I navigate to a random PDP page on search results page
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword    |
      | domestic | blue jeans |
      | iship    | blue jeans |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify auto suggestions on below pages in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following "<category>" from global navigation
    When I type "je" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | category        |
      | PROMOTIONS      |
      | MY bWALLET      |
      | MY LOYALLIST    |
      | THE REGISTRY    |
      | WISH LIST       |
      | RECENT ACTIVITY |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify auto suggestions on below pages in domestic mode with single character
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following "<category>" from global navigation
    When I type "j" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | category        |
      | PROMOTIONS      |
      | MY bWALLET      |
      | MY LOYALLIST    |
      | THE REGISTRY    |
      | WISH LIST       |
      | RECENT ACTIVITY |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify auto suggestions on below pages in domestic mode with valid product word
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following "<category>" from global navigation
    When I type "jeans" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | category        |
      | PROMOTIONS      |
      | MY bWALLET      |
      | MY LOYALLIST    |
      | THE REGISTRY    |
      | WISH LIST       |
      | RECENT ACTIVITY |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify auto suggestions on below pages in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify following "<category>" from global navigation
    When I type "je" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | category        |
      | RECENT ACTIVITY |
      | ORDER TRACKING  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify auto suggestions on below pages in iship mode with single character
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify following "<category>" from global navigation
    When I type "p" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      | category        |
      | RECENT ACTIVITY |
      | ORDER TRACKING  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify auto suggestions on below pages in iship mode with valid product word
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify following "<category>" from global navigation
    When I type "plates" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | category        |
      | RECENT ACTIVITY |
      | ORDER TRACKING  |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "ALL DESIGNERS" page
    And I type "pl" in mew search
    Then I should see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in domestic mode with single character
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I type "p" in mew search
    Then I should not see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in domestic mode with valid word
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I type "plates" in mew search
    Then I should see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I type "pl" in mew search
    Then I should see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in iship mode with single character
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I type "p" in mew search
    Then I should not see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in iship mode with valid word
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I type "plates" in mew search
    Then I should see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I type "pl" in mew search
    Then I should see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in registry mode with single character
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I type "p" in mew search
    Then I should not see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM - DLP Page auto suggestions in registry mode with valid word
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I type "plates" in mew search
    Then I should see auto complete suggestions in mew

  ##Verify auto sugestions on responsive pages
  @domain_mew_discovery @use_mew_regression
  Scenario: Verify below categories and their response code for responsive pages
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Find Registry |
    Then I type "pl" in mew search
    Then I should not see auto complete suggestions in mew

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify below categories and their response code for responsive pages
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Create Registry |
    Then I type "pl" in mew search
    Then I should not see auto complete suggestions in mew
