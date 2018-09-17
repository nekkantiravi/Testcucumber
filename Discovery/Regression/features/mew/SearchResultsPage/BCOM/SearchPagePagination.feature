Feature: As a BCOM mobile user i want to search products in easy views with limited products on each page

  ### Domestic and Iship modes ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - As a mobile user I should see pagination for more than 90 products
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I verify there are products on the page
    Then I should see pagination displayed for more than 90 products
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - As a mobile user I should see correct page number of pages in pagination for more than 90 products
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I verify there are products on the page
    Then I should see pagination displayed for more than 90 products
    And pagination should be displayed and defaulted to first page
    And I should see correct number of pages in pagination
    And current page number should be equal 1
    When I navigate to next page on search page
    Then current page number should be equal 2
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - As a mobile user I should see correct page number of pages in pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "pants" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I verify there are products on the page
    Then I should see pagination displayed for more than 90 products
    And I should see correct number of pages in pagination
    And I select 2 page on search page
    Then current page number should be equal 2
    When I navigate to prev page on browse
    Then current page number should be equal 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - As a mobile user I should check navigation from pagination dropdown
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "pants" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I verify there are products on the page
    Then I should see pagination displayed for more than 90 products
    When I navigate to prev page on search
    And I should be navigated to last page
    When I navigate to next page on search page
    Then current page number should be equal 1
    When I select 3 page on search page
    And current page number should be equal 3
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - Verify correct number of pages showing on page
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    When I type "plates" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I should see pagination displayed for more than 90 products
    And pagination should be displayed and defaulted to first page
    And I should see correct number of pages in pagination
    And current page number should be equal 1
    When I navigate to next page on search page
    Then current page number should be equal 2
    Examples:
      | option                |
      | Add Gifts to Registry |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - Verify the current page number after changing the page numbers using pagination.
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    When I type "kitchen" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I should see pagination displayed for more than 90 products
    And pagination should be displayed and defaulted to first page
    And I select 2 page on search page
    Then current page number should be equal 2
    When I navigate to prev page on browse
    Then current page number should be equal 1
    Examples:
      | option                |
      | Add Gifts to Registry |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - Page navigation using pagination
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    When I type "plates" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I should see pagination displayed for more than 90 products
    And pagination should be displayed and defaulted to first page
    When I navigate to prev page on search
    And I should be navigated to last page
    When I navigate to next page on search page
    Then current page number should be equal 1
    When I select 3 page on search page
    And current page number should be equal 3
    Examples:
      | option                |
      | Add Gifts to Registry |
