Feature: Browse Page Pagination scenarios

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Navigate to random browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    And I should see correct number of pages in pagination
    And current page number should be equal 1
    When I navigate to next page on browse page
    Then current page number should be equal 2
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Navigate to diffrent pages on browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    Then pagination should be displayed and defaulted to first page
    And I should see correct number of pages in pagination
    And I select 2 page on browse page
    Then current page number should be equal 2
    When I navigate to prev page on browse
    Then current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify selected page on browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    Then pagination should be displayed and defaulted to first page
    When I select 2 page on browse page
    Then selected page should be displayed in pagination
    And I navigate to prev page on browse
    And current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify diffrent page navigation on browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    When I navigate to prev page on browse
    And I should be navigated to last page
    When I navigate to next page on browse page
    Then current page number should be equal 1
    When I select 3 page on browse page
    And current page number should be equal 3
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify pagination functionality when facet is selected
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    When I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    Then current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify pagination functionality when facet is selected and cleared
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    When I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    When I tap on clear all button
    Then current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify pagination functionality when facet is selected in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    When I tap on show more for all filters
    And I select "Sales & Offers" facet from panel
    And I select random "Sales & Offers" facet from the selected category
    And I click apply for the facet selected
    Then current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify pagination functionality when facet is selected and cleared in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    When I tap on show more for all filters
    And I select "Sales & Offers" facet from panel
    And I select random "Sales & Offers" facet from the selected category
    And I click apply for the facet selected
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I tap on show more for all filters
    And I select "Sales & Offers" facet from panel
    When I tap on clear all button
    Then current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: Navigate to random browse page on registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    And I should see pagination displayed for more than 90 products
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    And I should see correct number of pages in pagination
    And current page number should be equal 1
    When I navigate to next page on browse page
    Then current page number should be equal 2

  @domain_mew_discovery @use_mew_regression
  Scenario: Navigate to diffrent pages on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    And I should see pagination displayed for more than 90 products
    Then I should see the "category_browse" Page
    Then pagination should be displayed and defaulted to first page
    And I should see correct number of pages in pagination
    And I select 2 page on browse page
    Then current page number should be equal 2
    When I navigate to prev page on browse
    Then current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify selected page on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    And I should see pagination displayed for more than 90 products
    Then I should see the "category_browse" Page
    Then pagination should be displayed and defaulted to first page
    When I select 2 page on browse page
    Then selected page should be displayed in pagination
    And I navigate to prev page on browse
    And current page number should be equal 1

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify diffrent page navigation on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    And I should see pagination displayed for more than 90 products
    Then I should see the "category_browse" Page
    And pagination should be displayed and defaulted to first page
    When I navigate to prev page on browse
    And I should be navigated to last page
    When I navigate to next page on browse page
    Then current page number should be equal 1
    When I select 3 page on browse page
    And current page number should be equal 3
