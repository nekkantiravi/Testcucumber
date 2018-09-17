# Author: BGV QE Team
# Date Created :
# Date Modified :
# Date Signed Off: TBD

Feature:C2 :: P2 :: BCOM :: Browse Grid Variations automation scenarios for Sub splash Page

##############################################################################################################
# Story B-11986: C2 P2 :: Browse :: BCOM Back to Top Browse
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-11986
##############################################################################################################
  ##############################################################################################################
  # Story B-17202 : C2 P2 :: Browse :: BCOM Breadcrumb/Nav Adjustments
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-17202
  ##############################################################################################################

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify while scrolling, view of back to top button should be at bottom of page and button fades away when scroll bar has reached top of scroll bar track in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' with '1000' pixel size on the page
    Then I 'should' see back to top button on page
    When I scroll 'up' the page until reach top of header panel
    Then I should see back to top button fades away
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify page is navigated to top when user clicks on back to top button in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' the page until reach bottom of footer panel
    And I select 'back to top' button
    Then I should see page is navigated to top of the page
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify back to top sits above footer when user scrolls to bottom of the page in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' the page until reach bottom of footer panel
    Then I should see back to top button appears above footer
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify user should not see back to top button immediately when navigated to page in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see back to top button immediately after navigating to page
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @priority_medium @release_15F @disable_to @mode_domestic @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify secondary breadcrumb text is not displaying anymore under current page title in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I 'should not' see secondary breadcrumb text under current page title
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @priority_medium @release_15F @disable_env @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify pixel spacing between current page title and first link of new breadcrumb navigation in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should 13 pixel spacing between current page title and first link of new breadcrumb row
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |


  ##############################################################################################################
  # Story B-14161 : C2 P2 :: Browse :: BCOM Nav - Breadcrumb
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-14161
  ##############################################################################################################

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @priority_medium @release_15F @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify breadcrumb is displayed by skipping level-2 category in sub splash pages for n'th level category navigation in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random '<page_type>' page with "<n-level>" category in "<mode>"
    Then I should see breadcrumb upto "<n-level>" of category name except level-2 category name
    And I should see breadcrumb displayed with '11' pixel font size
    And I should see breadcrumb displayed with '#666' font color
  Examples:
    | page_type  | n-level | mode |
    | Sub Splash | 3       | SITE |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @priority_medium @release_15F @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify breadcrumb links are clickable except current page(where user currently on) in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random '<page_type>' page with "<n-level>" category in "<mode>"
    Then I should see breadcrumb upto "<n-level>" of category name except level-2 category name
    And I should see clickable action is not performed on 'current page' name in breadcrumb
    When I select 'a random' category from breadcrumb except 'current page' category
    Then I should be navigated to respective category page
  Examples:
    | page_type  | n-level | mode |
    | Sub Splash | 3       | SITE |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @priority_medium @release_15F @mode_domestic @wip @deprecated @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify breadcrumb on chanel category pages in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should not see breadcrumb in 'chanel category' page
  Examples:
    | page_type         | mode   |
    | Chanel Sub Splash | normal |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify user should not see back to top button immediately when navigated to page in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see back to top button immediately after navigating to page
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify back to top sits above footer when user scrolls to bottom of the page in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' the page until reach bottom of footer panel
    Then I should see back to top button appears above footer
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify page is navigated to top when user clicks on back to top button in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' the page until reach bottom of footer panel
    And I select 'back to top' button
    Then I should see page is navigated to top of the page
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |

  @use_regression @use_regression_1 @feature_bgv @artifact_navapp @domain_discovery @priority_medium @bgv_2 @release_15E @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify while scrolling, view of back to top button should be at bottom of page and button fades away when scroll bar has reached top of scroll bar track in DOMESTIC & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' with '1000' pixel size on the page
    Then I 'should' see back to top button on page
    When I scroll 'up' the page until reach top of header panel
    Then I should see back to top button fades away
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | international |
