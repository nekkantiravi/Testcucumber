Feature: Verify Tap nav functionality on browse pages in DOMESTIC, ISHIP and RESGITRY mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @use_regression @add_missing_scope @please_automate
  Scenario: BrowsePage - Verify Seasonal Action wrapper in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    And I click on "Seasonal Action wrapper" link
    Then I verify Seasonal Action wrapper is displayed
    And I click on "Seasonal Action wrapper" link
    Then I verify that Seasonal Action wrapper is closed


  @domain_discovery @priority_medium @artifact_navapp @mode_iship @use_regression @add_missing_scope @please_automate
  Scenario: CatSplashPage - Verify Seasonal Action wrapper in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to category browse page
    And I click on "Seasonal Action wrapper" link
    Then I verify Seasonal Action wrapper is displayed
    And I click on "Seasonal Action wrapper" link
    Then I verify that Seasonal Action wrapper is closed


  #TestLink -BLCOM-56887
  #Vone - RT-06474
  @domain_discovery @priority_medium @artifact_navapp @mode_registry @use_regression @add_missing_scope @please_automate
  Scenario: BrowsePage - Verify Seasonal Action wrapper in REGISTRY Home Page
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to category browse page
    Then I verify Seasonal Action wrapper is not displayed
    When I click on "back to bloomingdales" link
    When I click on "Seasonal Action wrapper" link
    Then I verify Seasonal Action wrapper is displayed

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @use_regression @add_missing_scope @please_automate
  Scenario: CategorySplashPage - Verify TOPNAV elements are displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to random category splash page
    Then I verify below topnav elements are displayed and return a 200 OK
      | STORES & EVENTS   |
      | COUNTRY FLAG      |
      | CURRENCY          |
      | MY ACCOUNT        |
      | WISH LIST         |
      | BROWN BAG:(0)     |
      | SHOPPING BAG ICON |

  @domain_discovery @priority_medium @artifact_navapp @mode_registry @use_regression @add_missing_scope @please_automate
  Scenario: BrowsePage - Verify TOPNAV elements are displayed in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to category browse page
    Then I verify below topnav elements are displayed and return a 200 OK
      | STORES & EVENTS   |
      | MY ACCOUNT        |
      | BROWN BAG:(0)     |
      | SHOPPING BAG ICON |

  @domain_discovery @priority_medium @artifact_navapp @mode_iship @use_regression @add_missing_scope @please_automate
  Scenario: BrandIndexPage - Verify TOPNAV elements are displayed in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to brand index page in "iship" mode
    Then I verify below topnav elements are displayed and return a 200 OK
      | COUNTRY FLAG      |
      | CURRENCY          |
      | BROWN BAG:(0)     |
      | SHOPPING BAG ICON |
    And I verify below topnav elements are not displayed
      | STORES & EVENTS |
      | MY ACCOUNT      |
      | WISH LIST       |

