Feature: SortBy verification on Search Landing Page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with 'sort by' option
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "New Arrivals" in sort by drop down
    And I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet values
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous sort by selection persist
    Examples:
    | shopping_mode | keyword |
    | Domestic      | jeans   |
    | Registry      | plates  |
    | Iship         | jeans   |
    # Notes:
    # Verify facet selection and sort by persists when we navigate back from PDP page

  @use_regression @domain_discovery @priority_high @mode_doemstic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the Sort By displayed with all options
    # Notes:
    # Verify all sort by options are displayed

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Sort By Price (low-high) functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I select "Price (low-high)" in sort by drop down
    Then I verify that the Sort By "Price (low-high)" functionality
 # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links and that
    # first product price is more than second product and last product

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Sort By Price (high-low) functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I select "Price (high-low)" in sort by drop down
    Then I verify that the Sort By "Price (high-low)" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links and that
    # first product price is more than second product and last product

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Sort By Customer Top Rated functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    And I select "Customer Top Rated" in sort by drop down
    Then I verify that the Sort By "Customer Top Rated" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Sort By Best Sellers functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    When I select "Best Sellers" in sort by drop down
    Then I verify that the Sort By "Best Sellers" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify Sort By New Arrivals functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    When I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @domain_discovery @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic - Verify sort by functionality against response
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I select "<sort_by>" in sort by drop down
    Then I verify that product count from UI is same as given by response
    Then I verify that product ids from UI is same as given by response
    Examples:
      | sort_by             |
      | New Arrivals        |
      | Best Sellers        |
      | Price (high-low)    |
      | Price (low-high)    |
      | Customer Top Rated  |