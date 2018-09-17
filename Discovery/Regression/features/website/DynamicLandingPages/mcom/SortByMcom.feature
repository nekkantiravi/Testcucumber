Feature: SortBy verification on DynamicLanding Page

  @domain_discovery @priority_medium @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify facet selection persistence with 'sort by' option
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select "New Arrivals" in sort by drop down
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous sort by selection persist
    Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    # Notes:
    # Verify facet selection persists when we navigate back from PDP page

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality: Price: High to Low
    Given I am on DynamicLandingPage in "Domestic" mode
    And I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality: Price: Low to High
    Given I am on DynamicLandingPage in "Domestic" mode
    And I select "Price: Low to High" in sort by drop down
    Then I verify that the Sort By "Price: Low to High" functionality

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that the Sort By displayed with all options
   # Notes:
   # Verify all sort by options are displayed

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality: Customer Top Rated
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that the product count is displayed
    When I select "Customers' Top Rated" in sort by drop down
    Then I verify that the Sort By "Customers' Top Rated" functionality
   # Notes:
   # Verify that products are displayed with non broken images and links, price and quick peek links

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality: Best Sellers
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that the product count is displayed
    When I select "Best Sellers" in sort by drop down
    Then I verify that the Sort By "Best Sellers" functionality

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: priority_medium - Domestic - Verify Sort By functionality: New Arrivals
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that the product count is displayed
    When I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality: Featured Items
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that the product count is displayed
    When I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality
    When I select "Featured Items" in sort by drop down
    Then I verify that the Sort By "Featured Items" functionality