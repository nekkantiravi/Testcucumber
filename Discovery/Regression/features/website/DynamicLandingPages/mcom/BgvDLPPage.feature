Feature: Grid column & Items per page verification on DynamicLandingPage

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify facet selection persistence
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous grid view selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
 # Notes:
 # Verify facet selection persists when we navigate back from PDP page

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify facet selection persistence with '120' item count
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I filter the result set to show "120" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify facet selection persistence with '60' item count
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I filter the result set to show "60" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry|Iship - Verify H1 header text for results message
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I should see results message with H1 element
    Examples:
      | shopping_mode |
      | Domestic      |
#      | Iship         |