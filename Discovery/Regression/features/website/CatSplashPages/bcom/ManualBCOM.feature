#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Manual Scenarios in DOMESTIC, ISHIP and REGISTRY mode

  # Test Case ID: RT-06294
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @mode_iship @use_manual @priority_medium
  Scenario Outline: CategorySplashPage - Verify the Special/Featured Shops in left navigation navigate to appropriate pages and displayed properly
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    When I select random child categories in Special or Featured shops from left navigation
    Then I verify content is displayed properly
  Examples:
    | page_type | mode          |
    | Browse    | normal        |
    | Browse    | international |