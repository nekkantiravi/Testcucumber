Feature: m616128: Component tests for browse page

  @dsv_mew_sev1 @domain_discovery
  Scenario: User applies multiple facet values within a single facet category
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Pants          |
    And I click filter of search and browse page
    When I select multiple facet within a single facet category
    Then I should see the "category_browse" Page
    Then I should see the applied facets on browse page

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify sort by functionality on category browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | shoes          |
      | dress shoes    |
    Then I should see the "category_browse" Page
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |