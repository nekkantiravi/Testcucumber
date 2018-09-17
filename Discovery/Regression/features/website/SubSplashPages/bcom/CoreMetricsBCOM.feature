#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify SubSplash Page CoreMetrics in DOMESTIC, ISHIP and REGISTRY mode

  #TBD - add scenarios
  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_coremetrics @use_regression @mode_registry @mode_domestic @mode_iship @da_top40 @wip @deprecated
  Scenario Outline: Multiple pages - Verify core metrics element tags when user clicks on Back to Top in DOMESTIC, REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' the page until reach bottom of footer panel
    And I select 'back to top' button
    And I save the page and category id data in variables for "<page_type>" page on <mode>
      | Variable     | Description (parameter to use the variable) |
      | {page_id}    | Page id displayed in page (pi)              |
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name               | Expected Value |
      | 15             | Element ID (eid)             | back_to_top    |
      | 15             | Element Category (ecat)      | DESKTOP        |
      | 15             | Attribute 2 (Explore) (e_a2) | {page_id}      |
  Examples:
    | page_type         | mode          |
    | Sub Splash        | normal        |
    | Sub Splash        | registry      |
    | Sub Splash        | international |
  # we have jira ticket ECOMSST-46654 for below scenarios
#    | Chanel Sub Splash | normal        |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_coremetrics @use_regression @mode_registry @mode_domestic @mode_iship @da_top40 @wip @deprecated
  Scenario Outline: Multiple pages - Verify coremetrics when color swatches are selected DOMESTIC, REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    Then I find a "random" product having "color" swatches
    When I "select" any color in color swatches section
    And I save the page and category id data in variables for "<page_type>" page on <mode>
      | Variable  | Description (parameter to use the variable) |
      | {page_id} | Page id displayed in page (pi)              |
      | {attr29}  | attribute 29 displayed in page (e_a29)      |
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                 | Expected Value |
      | 15             | Element ID (eid)               | Swatch-click   |
      | 15             | Element Category (ecat)        | Browse Grid    |
      | 15             | Attribute 2 (Explore) (e_a2)   | {page_id}      |
      | 15             | Attribute 29 (Explore) (e_a29) | {attr_29}      |
  Examples:
    | page_type             | mode          |
    | Sub Splash            | normal        |
    | Sub Splash            | registry      |
    | Sub Splash            | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_coremetrics @mode_registry @domestic_mode @da_top40 @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify element tag for banner machine on desktop in DOMESTIC & REGISTRY Mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | <re_code>   | <mode>        | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type
    And I save data in variables for banner machine on "Sub Splash" page with "<mode>" mode
      | Variable     | Description (parameter to use the variable) |
      | {page_id}    | Page id displayed in page (pi)              |
      | {element_id} | Element id displayed in page (eid)          |
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name               | Expected Value |
      | 15             | Element ID (eid)             | {element_id}   |
      | 15             | Attribute 2 (Explore) (e_a2) | {page_id}      |
  Examples:
    | re_code | mode             |
    | US      | SITE             |
    | US      | WEDDING_REGISTRY |

  @artifact_navapp @domain_discovery @priority_medium  @release_15E @use_coremetrics @iship_mode @da_top40 @mode_iship @wip @deprecated
  Scenario: CategorySubSplashPage - Verify element tag for banner machine on desktop in ISHIP Mode
    Given I visit the web site as a guest user
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type
    And I save data in variables for banner machine on "Sub Splash" page with "ISHIP" mode
      | Variable     | Description (parameter to use the variable) |
      | {page_id}    | Page id displayed in page (pi)              |
      | {element_id} | Element id displayed in page (eid)          |
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name               | Expected Value |
      | 15             | Element ID (eid)             | {element_id}   |
      | 15             | Attribute 2 (Explore) (e_a2) | {page_id}      |

  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_coremetrics @da_top40 @use_regression @mode_registry @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify coremetrics when color swatches more button selected on REGISTRY, ISHIP & DOMESTIC
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    Then I verify the color swatches and more button
    When I select 'More' button under color swatch section
    And I navigate to previous page from existing page
    And I save the page and category id data in variables for "<page_type>" page on <mode>
      | Variable     | Description (parameter to use the variable) |
      | {page_id}    | Page id displayed in page (pi)              |
      | {product_id} | product_id displayed in page (e_a29)        |
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                 | Expected Value |
      | 15             | Element Category (ecat)        | Browse_Grid    |
      | 15             | Element ID (eid)               | Swatch-more    |
      | 15             | Attribute 29 (Explore) (e_a29) | {product_id}   |
      | 15             | Attribute 2 (Explore) (e_a2)   | {page_id}      |
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | registry      |
    | Sub Splash | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_coremetrics @da_top40 @use_regression @mode_registry @mode_domestic @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify core metrics element tags when user clicks on Back to Top in sub splash in SITE, REGISTRY and ISHIP mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I scroll 'down' the page until reach bottom of footer panel
    And I select 'back to top' button
    And I save the page and category id data in variables for "<page_type>" page on <mode>
      | Variable  | Description (parameter to use the variable) |
      | {page_id} | Page id displayed in page (pi)              |
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name               | Expected Value |
      | 15             | Element ID (eid)             | back_to_top    |
      | 15             | Element Category (ecat)      | DESKTOP        |
      | 15             | Attribute 2 (Explore) (e_a2) | {page_id}      |
  Examples:
    | page_type  | mode          |
    | Sub Splash | normal        |
    | Sub Splash | registry      |
    | Sub Splash | international |

  @artifact_navapp @release_16K @project_regression @domain_discovery @mode_domestic @priority_high @use_coremetrics @wip
  Scenario Outline: CategorySubSplashPage - Verify page view coremetric tags fired when we navigate to any sub splash page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select '<fob>' category from FOB panel
    And I select '<category>' from left nav panel
    Then I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name   | Expected Value          |
      | 1              | Category ID (cg) | <category_id>           |
      | 1              | Page ID (pi)     | sub_splash_<breadcrumb> |
  Examples:
    | fob    | category | category_id |
    | BEAUTY | CHANEL   | 1000926     |

