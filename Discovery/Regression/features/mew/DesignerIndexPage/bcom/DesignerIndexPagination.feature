Feature: As a mobile user i want to search products on DLP page in easy views with limited products on each page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should see not more than 90 products in DLP view per page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I should see pagination displayed for more than 90 products
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user when I tap on next button next page should be displayed with appropriate products on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed and defaulted to first page
    When I navigate to next page on dlp page
    Then I should see all products displayed in DLP page
    And current page number should be equal 2
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user when I tap on prev button previous page should be displayed with appropriate products on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed and defaulted to first page
    When I navigate to next page on dlp page
    Then current page number should be equal 2
    And I navigate to prev page on dlp
    Then I should see all products displayed in DLP page
    And current page number should be equal 1
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should see correct number of pages in pagination on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    And I should see correct number of pages in pagination
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should be able to navigate different pages in pagination on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    When I select "2" page on page
    Then selected page should be displayed in pagination
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should see not more than 90 products in DLP view per page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I should see pagination displayed for more than 90 products
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user when I tap on next button next page should be displayed with appropriate products on DLP page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed and defaulted to first page
    When I navigate to next page on dlp page
    Then I should see all products displayed in DLP page
    And current page number should be equal 2
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user when I tap on prev button previous page should be displayed with appropriate products on DLP page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    When I select 2 page on DLP page
    And I navigate to prev page on dlp
    Then I should see all products displayed in DLP page
    And current page number should be equal 1
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should see correct number of pages in pagination on DLP page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    And I should see correct number of pages in pagination
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should be able to navigate different pages in pagination on DLP page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    When I select 2 page on DLP page
    Then selected page should be displayed in pagination
    Examples:
      | designer          |
      | Calvin Klein      |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: As a mobile user I should be able to navigate different brand pages and pagination should be displayed on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see pagination displayed for more than 90 products
    Examples:
      | designer       |
      | Amalia         |
      | Acqua di Parma |

  @domain_mew_discovery @use_mew_regression
  Scenario: As a mobile user I should be able to navigate to different pages on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    Then I should see pagination displayed for more than 90 products
    When I select 2 page on DLP page
    Then selected page should be displayed in pagination

  @domain_mew_discovery @use_mew_regression
  Scenario: As a mobile user I should be able to navigate to different pages from pagination on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    When I select 2 page on DLP page
    Then selected page should be displayed in pagination
    And I navigate to prev page on dlp
    Then I should see all products displayed in DLP page
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: As a mobile user I should be able to navigate to different pages in pagination on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    Then I should see all products displayed in DLP page
    And current page number should be equal 1
    When I navigate to next page on DLP page
    Then I should see all products displayed in DLP page
    And current page number should be equal 2

  @domain_mew_discovery @use_mew_regression
  Scenario: As a mobile user I should be able to navigate different pages in pagination on DLP page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    When I navigate to "All Brands" page
    And I select a "Acqua di Parma" from the list
    Then I should be on DLP page
    Then pagination should be displayed
    And I should see correct number of pages in pagination
