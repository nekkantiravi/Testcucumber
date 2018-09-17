Feature: Verify AutoComplete functionality in DOMESTIC, ISHIP and REGISTRY mode

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
  Scenario Outline: SearchResultsPage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Boots" in mew search and click enter
    Then I should be on the search results page
    And I refresh the page
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - User types two characters in search box and verify the products Recommendation in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I navigate to a random PDP page on browse page
    Then I should be on PDP page
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ShoppingBagPage - User types two characters in search box and verify the products Recommendation in DOMESTIC mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I navigate to a random PDP page on browse page
    And I scroll to add bag button
    And I add product to my bag from standard PDP Page using mobile site
    And I click on continue checkout button
    And I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    Examples:
      | mode     | keyword |
      | domestic | Je      |
      | iship    | Je      |

  @domain_mew_discovery @use_mew_regression
  Scenario: SecuremWalletpage - User types two characters in search box and verify the products Recommendation in DOMESTIC mode
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | WALLET |
    Then I should be navigated to wallet page
    And I type "Jeans" in mew search
    Then I should see auto complete suggestions in mew

    ############################### REGISTRY MODE ##########################################################

  @domain_mew_discovery @use_mew_regression
  Scenario: RegistryHomePage - User types two characters in search box and verify the products Recommendation in REGISTRY mode
    Given I visit the mobile web site as a guest user
    And I navigate to wedding registry page
    And I type "Jeans" in mew search
    Then I should not see auto complete suggestions in mew

