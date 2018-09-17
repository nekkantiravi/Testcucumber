Feature: As a macys.com customer,I need to be able to sort my search and browse results by price

  ### Domestic mode ###

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify that sorting order of products priced at the colorway is accurate
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Charter Club" in mew search and click enter
    Then I should be on the search results page
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Minimum UPC in a colorway priced product
    When I click on filter link
    When I filter the products by price "$50-$100"
    Then I should see the products that has prices within the range "$50-$100"
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Minimum UPC in a colorway priced product

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on search results page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on UI page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |

  @domain_mew_discovery @use_mew_regression
  Scenario: Search page - Verify user is navigating back to first page when he select any sort functionality from different page other than first
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" in sort by drop down in mew
    And current page number should be equal 1

    ### Iship mode ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in Iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on search results page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in Iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on UI page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |

  @domain_mew_discovery @use_mew_regression
  Scenario: Search page - Verify user is navigating back to first page when he select any sort functionality from different page other than first in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Jeans" in mew search and click enter
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And  I select "Price: Low to High" in sort by drop down in mew
    And current page number should be equal 1

    ### Registry mode ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Plates" in mew search and click enter
    Then I should be on the registry search results page
    And  I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on wedding search page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customer's Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Plates" in mew search and click enter
    Then I should be on the registry search results page
    And  I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on UI page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |

  @domain_mew_discovery @use_mew_regression
  Scenario: Search page - Verify user is navigating back to first page when he select any sort functionality from different page other than first in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Plates" in mew search and click enter
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And  I select "Price: Low to High" in sort by drop down in mew
    And current page number should be equal 1