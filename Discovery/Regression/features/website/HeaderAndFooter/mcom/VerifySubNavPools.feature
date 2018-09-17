Feature: Verify the subNav pools as a part of global pool in domestic and iship

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario Outline: Verify the navigation functionality for gift guide in domestic
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I click on "gift guide" link from subNav
    And I should be navigated to "gift guide" page
    Examples:
      | user_type  | mode |
      | guest      | site |
      | registered | site |

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario Outline: Verify the seasonal promotion and gift guide are displayed in resgitry mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I should not see seasonal promotion and gift guide in registry
    Examples:
      | user_type  | mode     |
      | guest      | registry |
      | registered | registry |

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario Outline: Verify the gift guide is clickable in random browse page in domestic
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to random browse page
    Then I click on "gift guide" link from subNav
    And I should be navigated to "gift guide" page
    Examples:
      | user_type  | mode |
      | guest      | site |
      | registered | site |