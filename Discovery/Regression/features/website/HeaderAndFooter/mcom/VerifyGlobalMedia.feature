Feature: Verify the global media pools as a part of global pool in domestic,iship and registry

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: Verify the navigation functionality for INTL_GNA in iship mode
    Given I visit the web site as a "guest" user in "iship" mode
    When I click on global media link from top nav
    Then I should be navigated to media page in "iship" mode

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: Verify the details of global media with response in iship mode
    Given I visit the web site as a "guest" user in "iship" mode
    Then I compare the details of global media in UI with response in "iship" mode

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario Outline: Verify the navigation functionality for ABOVE_NAV_POOL in domestic mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I click on global media link from top nav
    Then I should be navigated to media page in "site" mode
    Examples:
      | user_type  | mode |
      | guest      | site |
      | registered | site |

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario Outline: Verify the details of global media with response in domestic mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I compare the details of global media in UI with response in "site" mode
    Examples:
      | user_type  | mode |
      | guest      | site |
      | registered | site |

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario Outline: Verify the navigation functionality for REG_W_ABOVE_NAV_POOL in registry mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I click on global media link from top nav
    Then I should be navigated to media page in "registry" mode
    Examples:
      | user_type  | mode     |
      | guest      | registry |
      | registered | registry |

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario Outline: Verify the details of global media with response in registry mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I compare the details of global media in UI with response in "registry" mode
    Examples:
      | user_type  | mode     |
      | guest      | registry |
      | registered | registry |