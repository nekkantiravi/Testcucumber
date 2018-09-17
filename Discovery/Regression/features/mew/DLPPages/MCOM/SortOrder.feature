Feature: As a macys.com customer,I need to be able to sort my DLP page by price

  ### Domestic mode ###

  @domain_mew_discovery @use_mew_regression
  Scenario: DLP - Verify that sorting order of products priced at the colorway is accurate
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "7 For All Mankind" dynamic landing page in domestic using mobile website
    Then I should be on DLP page
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Minimum UPC in a colorway priced product
    When I click on filter link
    When I filter the products by price "$100-$250"
    Then I should see the products that has prices within the range "$100-$250"
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Minimum UPC in a colorway priced product

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify sort by functionality on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "7 For All Mankind" dynamic landing page in domestic using mobile website
    Then I should be on DLP page
    When I click on filter link
    And I select "<sort value>" sort order on facet accordion model
    Then I should see products sorted by "<sort value>" on DLP page
    Examples:
      | sort value           |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify sort by functionality on UI page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "7 For All Mankind" dynamic landing page in domestic using mobile website
    Then I should be on DLP page
    When I click on filter link
    And I select "<sort value>" sort order on facet accordion model
    Then I should see products sorted by "<sort value>" on UI page
    Examples:
      | sort value         |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario: DLP - Verify user is navigating back to first page when he select any sort functionality from different page other than first
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "7 For All Mankind" dynamic landing page in domestic using mobile website
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    When I click on filter link
    And I select "Price: Low to High" sort order on facet accordion model
    And current page number should be equal 1

    ### Iship mode ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify sort by functionality on DLP page in Iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "7 For All Mankind" dynamic landing page in iship using mobile website
    Then I should be on DLP page
    When I click on filter link
    And I select "<sort value>" sort order on facet accordion model
    Then I should see products sorted by "<sort value>" on DLP page
    Examples:
      | sort value           |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify sort by functionality on UI page in Iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "7 For All Mankind" dynamic landing page in iship using mobile website
    Then I should be on DLP page
    When I click on filter link
    And I select "<sort value>" sort order on facet accordion model
    Then I should see products sorted by "<sort value>" on UI page
    Examples:
      | sort value         |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario: DLP - Verify user is navigating back to first page when he select any sort functionality from different page other than first in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "7 For All Mankind" dynamic landing page in iship using mobile website
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    When I click on filter link
    And I select "Price: Low to High" sort order on facet accordion model
    And current page number should be equal 1
