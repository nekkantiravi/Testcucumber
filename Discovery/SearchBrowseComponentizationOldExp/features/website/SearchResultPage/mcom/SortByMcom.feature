Feature: SortBy verification on Search Landing Page

@domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with 'sort by' option
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "4" Column Grid icon
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select "New Arrivals" in sort by drop down
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
    # Verify facet selection persists when we navigate back from PDP page

  @use_regression @saturnrules @use_preview @mode_Domestic @artifact_navapp @domain_discovery @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality: Price: High to Low
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality

  @use_regression @saturnrules @use_preview @mode_Domestic @artifact_navapp @domain_discovery  @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality: Price: Low to High
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I select "Price: Low to High" in sort by drop down
    Then I verify that the Sort By "Price: Low to High" functionality

  @use_regression @saturnrules @use_preview @mode_Domestic @artifact_navapp @domain_discovery @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the Sort By displayed with all options
   # Notes:
   # Verify all sort by options are displayed

  @test @use_regression @artifact_navapp @domain_discovery @priority_high @mode_domestic @use_regression_3 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality: Customer Top Rated
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    When I select "Customers' Top Rated" in sort by drop down
    Then I verify that the Sort By "Customers' Top Rated" functionality
   # Notes:
   # Verify that products are displayed with non broken images and links, price and quick peek links

  @test @use_regression @artifact_navapp @domain_discovery @priority_high @mode_domestic @use_regression_3 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality: Best Sellers
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    When I select "Best Sellers" in sort by drop down
    Then I verify that the Sort By "Best Sellers" functionality

  @test @use_regression @artifact_navapp @domain_discovery @priority_high @mode_domestic @use_regression_3 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality: New Arrivals
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    When I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality

  @test @use_regression @artifact_navapp @domain_discovery @priority_high @mode_domestic @use_regression_3 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality: Featured Items
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that the product count is displayed
    When I select "Featured Items" in sort by drop down
    Then I verify that the Sort By "Our Top Picks" functionality