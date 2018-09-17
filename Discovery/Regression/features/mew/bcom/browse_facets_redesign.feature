Feature: Verify Facet UI and Functionality

  @dsv_mew_sev1 @domain_discovery
  Scenario: Scenario: Verify Designer facet and text field functionality
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Women   |
      | Dresses |
    Then I should see the "category_browse" Page
    And I click show more to expand facet panel using mobile website
    When I select "Designer" facet on left nav using mobile website
    And I select "1.STATE" sub facet from left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should see "1.STATE" in facet breadcrumb

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify sort by functionality on category browse page in domestic mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Men       |
      | Pants     |
    Then I should see the "category_browse" Page
    Then I should see sort by functionality with below options using mobile website:
      | Featured             |
      | Newest               |
      | Best Sellers         |
      | Price: Low to High   |
      | Price: High to Low   |

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify the bops facet feature on category browse page when store is known
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    When I select Pick Up In Store facet
    When I select random facets
    When I tap apply button in the accordion
    Then I verify facet breadcrumbs