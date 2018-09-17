#############################################################
# Author         : C2 QE Team
# Date           :
# Reviewer       :
# Date Signed Off:
# Mingle Path    : http://mingle/projects/discovery/cards/19446
#############################################################

Feature: WDS :: MCOM :: Render Contextual Template for CatSplash Page

################################## Device = Desktop Navigation Type = Browse ###################################

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium @wip
  Scenario Outline: CategorySplashPage - Verify row102 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                        |
    | 102      | CATEGORY_ICON, CATEGORY_ICON |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium @wip
  Scenario Outline: CategorySplashPage - Verify row103 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                                       |
    | 103      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium @wip
  Scenario Outline: CategorySplashPage - Verify row104 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 104      | CATEGORY_ICON |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium @wip
  Scenario Outline: CategorySplashPage - Verify row105 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 105      | CATEGORY_ICON |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium @wip
  Scenario Outline: CategorySplashPage - Verify row106 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 106      | CATEGORY_ICON |


  ################################################# Slide show scenarios #########################################

  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify max and min slides in slideshow and all should display as astra data on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "MULTI_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should see respective media as per astra data
    And I should see slideshow with minimum of one and maximum of six slides

  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify tapping on arrows will take user forward or backward in slideshow on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "MULTI_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    When I select "forward arrow" in slideshow
    Then I should see "next" slide is displayed in slideshow
    When I select "previous arrow" in slideshow
    Then I should see "previous" slide is displayed in slideshow

  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @mode_domestic
  Scenario: CategorySplashPage - Verify single dot is not displaying in slideshow when only single slide is present on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "SINGLE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should not see dot is displaying in slideshow



  ######################################## Advance Product Pool scenarios ####################################


  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify existing types of Advanced Product Pool and sort by options on on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product pool and sort by options
  Examples:
    | page_type       | row_type | media                  |
    | Category Splash | 101      | PRODUCT_PANEL_POOL     |
    | Category Splash | 101      | PRODUCT_PANEL_CATEGORY |
    | Category Splash | 101      | PRODUCT_PANEL_NA       |


  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - In product pool all available product count should be multiple of 3 on on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product's are multiple of 3 or maximum 24
  Examples:
    | page_type       | row_type | media                  |
    | Category Splash | 101      | PRODUCT_PANEL_POOL     |
    | Category Splash | 101      | PRODUCT_PANEL_CATEGORY |
    | Category Splash | 101      | PRODUCT_PANEL_NA       |

  ################################## Device = Desktop Navigation Type = Browse ###################################

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify row102 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                        |
    | 102      | CATEGORY_ICON, CATEGORY_ICON |

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify row103 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                                       |
    | 103      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify row104 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 104      | CATEGORY_ICON |

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify row105 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 105      | CATEGORY_ICON |

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify row106 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 106      | CATEGORY_ICON |

  ############################################ Slide show scenarios #############################################3

  @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify max and min slides in slideshow and all should display as astra data in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "MULTI_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "SLIDESHOW" in "101" on cat splash page

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify tapping on arrows will take user forward or backward in slideshow in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "MULTI_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    When I select "forward arrow" in slideshow
    Then I should see "next" slide is displayed in slideshow
    When I select "previous arrow" in slideshow
    Then I should see "previous" slide is displayed in slideshow

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify single dot is not displaying in slideshow when only single slide is present in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "SINGLE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should not see dot is displaying in slideshow


  ########################################## Advanced Product Pool scenarios ########################################

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - Verify existing types of Advanced Product Pool and sort by options on ISHIP mode
    Given I visit the web site as a guest user
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product pool and sort by options
  Examples:
    | page_type       | row_type | media                  |
    | Category Splash | 101      | PRODUCT_PANEL_POOL     |
    | Category Splash | 101      | PRODUCT_PANEL_CATEGORY |
    | Category Splash | 101      | PRODUCT_PANEL_NA       |

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario Outline: CategorySplashPage - In product pool all available product count should be multiple of 3 on ISHIP mode
    Given I visit the web site as a guest user
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product's are multiple of 3 or maximum 24
  Examples:
    | page_type       | row_type | media                        |
    | Category Splash | 101      | PRODUCT_PANEL_CATEGORY_FACET |
    | Category Splash | 101      | PRODUCT_PANEL_POOL           |
    | Category Splash | 101      | PRODUCT_PANEL_CATEGORY       |
    | Category Splash | 101      | PRODUCT_PANEL_NA             |

