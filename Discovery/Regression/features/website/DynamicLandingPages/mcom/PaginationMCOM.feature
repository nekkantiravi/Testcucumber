Feature: Pagination verification on DynamicLanding Page

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Verify pagination in DOMESTIC mode
    Given I am on DynamicLandingPage in "Domestic" mode with pagination
    Then I verify that pagination works

  @domain_discovery @priority_medium @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Pagination using Next / Previous button in DOMESTIC & ISHIP mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with pagination
    And I click 2 pagination number
    Then I verify that navigated to page 2 on dlp page
    And I click on next pagination button
    Then I verify that navigated to page 3 on dlp page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on dlp page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Pagination using Next / Previous button in DOMESTIC & ISHIP Mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with pagination
    Then I should be in Dynamic Landing page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Verify browser back button takes to previously navigated page before navigating to PDP in DOMESTIC & ISHIP modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with pagination
    When I click 2 pagination number
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on next pagination button
    Then I verify that navigated to page 4 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    And I navigate to PDP of the first product
    And I select browse 'back' button
    Then I verify that navigated to page 4 on dlp page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
  #Note:  paginate,navigate to product and press browser back button, check url
  # paginate,choose quick view and press browser back button, check url
  # paginate , press forward backward button in between

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality with pagination with 60 items per page
    Given I am on DynamicLandingPage in "Domestic" mode with pagination
    When I filter the result set to show "60" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on dlp page

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify Sort By functionality with pagination with 120 items per page
    Given I am on DynamicLandingPage in "Domestic" mode with pagination
    When I filter the result set to show "120" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on dlp page

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify "60" item per page functionality with pagination
    Given I am on DynamicLandingPage in "Domestic" mode with pagination
    And I click 3 pagination number
    Then I verify that navigated to page 3 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I filter the result set to show "60" items per page
    Then I verify that navigated to page 1 on dlp page

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify "120" item per page functionality with pagination
    Given I am on DynamicLandingPage in "Domestic" mode with pagination
    And I click 3 pagination number
    Then I verify that navigated to page 3 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on dlp page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on dlp page