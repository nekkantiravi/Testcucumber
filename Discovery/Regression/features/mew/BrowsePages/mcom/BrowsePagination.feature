Feature: As a mobile user i want to browse products in easy views with limited products on each page

  ### Domestic and Iship Modes ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - As a mobile user I should see not more than 48 products per page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should see all products displayed in browse page
    Then I should see not more than 48 products in the browse page
    And I should see pagination is displayed
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - As a mobile user I should see no pagination with less than 48 products
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop              |
      | Jewelry & Watches |
      | Watches           |
      | Smart Watches     |
      | Fitbit            |
    Then I should see all products displayed in browse page
    And Pagination should not be displayed
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - As a mobile user when I tap on next button next page should be displayed with appropriate products
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then pagination should be displayed and defaulted to first page
    When I navigate to next page on search page
    Then I should see all products displayed in browse page
    And current page number should be equal 2
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - As a mobile user when I tap on prev button previous page should be displayed with appropriate products
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    When I select 3 page on browse page
    And I navigate to prev page on search
    Then I should see all products displayed in browse page
    And current page number should be decreased by 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - As a mobile user I should see correct number of pages in pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should see pagination is displayed
    And I should see correct number of pages in pagination
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - As a mobile user I should be able to navigate different pages in pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should see pagination is displayed
    When I select 3 page on browse page
    Then selected page should be displayed in pagination
    Examples:
      | mode     |
      | domestic |
      | iship    |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify Next and Prev buttons on pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should see pagination is displayed
    And I should see next button on pagination
    And I should not see prev button on pagination
    When I navigate to last page
    Then I should not see next button on pagination
    And I should see prev button on pagination
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify page is navigating back to first when user clears selected facets from clear all in facet accordion model
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should be on the browse page
    And I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    And I click on Apply button
    Then I should see only products in browse page with selected multiple facet values
    And I navigate to a random page other than first page
    When I click on filter link
    And I click the clear all button
    And I click on Apply button
    Then I should be on the browse page
    Then current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify page is navigating back to first when user clears selected facet from breadcrumbs
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should be on the browse page
    And I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    And I click on Apply button
    Then I should see only products in browse page with selected multiple facet values
    And I navigate to a random page other than first page
    When I remove the facet from the breadcrumb
    Then current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

    ### Registry Mode ###

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - As a mobile user I should see not more than 48 products per page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should see all products displayed in registry browse page
    Then I should see not more than 48 products in the browse page
    And I should see pagination is displayed

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - As a mobile user I should see no pagination with less than 48 products in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Blenders        |
    Then I should see all products displayed in registry browse page
    And Pagination should not be displayed


  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - As a mobile user when I tap on next button next page should be displayed with appropriate products in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then pagination should be displayed and defaulted to first page
    When I navigate to next page on search page
    Then I should see all products displayed in registry browse page
    And current page number should be equal 2

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - As a mobile user when I tap on prev button previous page should be displayed with appropriate products in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    When I select 3 page on browse page
    And I navigate to prev page on search
    Then I should see all products displayed in registry browse page
    And current page number should be decreased by 1

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - As a mobile user I should see correct number of pages in pagination in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should see pagination is displayed
    And I should see correct number of pages in pagination on registry browse page

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - As a mobile user I should be able to navigate different pages in pagination in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should see pagination is displayed
    When I select 3 page on browse page
    Then selected page should be displayed in pagination


  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify Next and Prev buttons on pagination  in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should see pagination is displayed
    And I should see next button on pagination
    And I should not see prev button on pagination
    When I navigate to last page
    Then I should not see next button on pagination
    And I should see prev button on pagination

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify page is navigating back to first when user clears selected facets from clear all in facet accordion model in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should be on the registry browse page
    And I click on filter link
    And I select facet name "Electrics Type"
    And I select multiple facets
    And I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    And I navigate to a random page other than first page
    When I click on filter link
    And I click the clear all button
    And I click on Apply button
    Then I should be on the registry browse page
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify page is navigating back to first when user clears selected facet from breadcrumbs in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should be on the registry browse page
    And I click on filter link
    And I select facet name "Electrics Type"
    And I select multiple facets
    And I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    And I navigate to a random page other than first page
    When I remove the facet from the breadcrumb
    Then current page number should be equal 1