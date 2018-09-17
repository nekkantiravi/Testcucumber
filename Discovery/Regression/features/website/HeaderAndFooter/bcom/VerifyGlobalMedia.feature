Feature: Verify the global media pools as a part of global pool in domestic,iship and registry

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario Outline: Verify the navigation functionality for ABOVE_NAV_POOL in domestic mode

    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I click on global media link from top nav
    Then I should be navigated to media page in "site" mode

    Examples:
      | user_type  |  mode |
      | guest      | site |
      | registered | site |

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario Outline: Verify the details of global media with response in domestic mode

    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I compare the details of global media in UI with XAPI response in "site" mode

    Examples:
      | user_type  |  mode |
      | guest      |  site |
      | registered |  site |

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario Outline: Verify the navigation functionality for REG_W_ABOVE_NAV_POOL in registry mode

    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I click on global media link from top nav
    Then I should be navigated to media page in "registry" mode

    Examples:
      | user_type  |  mode     |
      | guest      | registry |
      | registered | registry |

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario Outline: Verify the details of global media with response in registry mode

    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I compare the details of global media in UI with XAPI response in "registry" mode

    Examples:
      | user_type  |  mode     |
      | guest      |  registry |
      | registered |  registry |