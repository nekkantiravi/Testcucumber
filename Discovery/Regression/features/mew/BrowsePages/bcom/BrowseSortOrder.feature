Feature: Browse page sorting in domestic

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I randomly navigate to any browse page by clicking on child categories of below "MEN"
    Then I should see the "category_browse" Page
    And I should see "featured" option selected as default on browse page
    And I should see products sorted by "Featured" on the category browse page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I randomly navigate to any browse page by clicking on child categories of below "MEN"
    Then I should see the "category_browse" Page
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on browse page
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    And I select "<sort_by>" sort by drop down on category browse page
    Then I should see products sorted by "<sort_by>" on the category browse page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |
      | Featured           |
      | Best Sellers       |
      | Newest             |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify user is navigating back to first page when he select any sort functionality from different browse page other than first in dometic
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | BEAUTY |
      | Makeup |
    Then I should see the "category_browse" Page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down on category browse page
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in browse domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    And I select "<sort_by>" sort by drop down on category browse page
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |

  ### iship
  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by options visibility on browse page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I randomly navigate to any browse page by clicking on child categories of below "WOMEN"
    Then I should see the "category_browse" Page
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: Low to High" sort by drop down on category browse page
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify user is navigating back to first page when he select any sort functionality from different browse page other than first in iship
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down on category browse page
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in browse iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    And I select "<sort_by>" sort by drop down on category browse page
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |

  ##registry mode
  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by options visibility on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    Then I should see the "category_browse" Page
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |
    Examples:
      | option                |
      | Add Gifts to Registry |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    Then I should see the "category_browse" Page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: Low to High" sort by drop down on category browse page
    And current page number should be equal 1
    Examples:
      | option                |
      | Add Gifts to Registry |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in browse registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    Then I should see the "category_browse" Page
    And I select "<sort_by>" sort by drop down on category browse page
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |
