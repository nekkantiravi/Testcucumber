Feature: As a mobile user i want to check sort order functionality on DLP page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on category browse page in DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "1.STATE" from the list
    Then I should be on DLP page
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<brand>" from the list
    Then I should be on DLP page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the "<brand>" DLP page
    Examples:
      | sort_by            | brand             |
      | Price: Low to High | Adidas            |
      | Price: High to Low | 1.STATE           |
      | Featured           | 7 For All Mankind |
      | Best Sellers       | Wedgwood          |
      | Newest             | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by pagination functionality on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "Adidas" from the list
    Then I should be on DLP page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by pagination functionality on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "Adidas" from the list
    Then I should be on DLP page
    When I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |

  ### iship
  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on DLP page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "1.STATE" from the list
    Then I should be on DLP page
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on DLP page in iship mode by multiple options
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<brand>" from the list
    Then I should be on DLP page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the "<brand>" DLP page
    Examples:
      | sort_by            | brand             |
      | Price: Low to High | Adidas            |
      | Price: High to Low | 1.STATE           |
      | Featured           | 7 For All Mankind |
      | Best Sellers       | Wedgwood          |
      | Newest             | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by pagination functionality on DLP page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "Adidas" from the list
    Then I should be on DLP page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality of products on UI on DLP page in iship
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "Adidas" from the list
    Then I should be on DLP page
    When I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |

  ###registry mode
  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by options visibility on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "<brand>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the "<brand>" DLP page
    Examples:
      | sort_by            | brand          |
      | Price: Low to High | Acqua di Parma |
      | Price: High to Low | Babe           |
      | Featured           | Bio Ionic      |
      | Best Sellers       | Bodum          |
      | Newest             | Chantal        |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by pagination functionality on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    When I select 2 page on DLP page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by pagination functionality on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |


