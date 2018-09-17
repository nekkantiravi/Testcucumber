#Author: Dennis Richard
#Date Created: 6/26/14
#Mingle: http://mingle/projects/fast_track/cards/4051

Feature: Tabletop Glassware Refinement Filters

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify table top url redirected to category id
    Given I visit the web site as a guest user
    When I navigate to the bookmarked url:
      | BCOM | /shop/tabletop-builder/set-your-table |
    Then I should be redirected to category id "1000231"
