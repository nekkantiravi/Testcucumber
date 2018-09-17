#############################################################
# Author:      C2 QE Team
# Date:        5th May, 2014
# Reviewer:
# Date Signed Off:
# Mingle Path: http://mingle/projects/discovery/cards/19446
#############################################################

Feature: WDS :: MCOM :: Render Contextual Template for Sub Splash page manual scenarios

################################## Device = Desktop Navigation Type = Browse ###################################

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag. We have to certify this scenario manually if we have data from production.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_regression @use_regression_2 @feature_c2 @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row101 are contextualized on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media            |
    | 101      | AD               |
    | 101      | SLIDESHOW        |
    | 101      | THUMBNAIL_GRID   |
    | 101      | VIDEO            |
    | 101      | IMAGE_MAP        |
    | 101      | WIDGET           |
    | 101      | PRODUCT_PANEL_NA |
    | 101      | MEDIA_ADS        |
    | 101      | HORIZONTAL_RULE  |
    | 101      | JSP              |
    | 101      | TEXT             |

  ########################################### Slide show scenarios ############################################

  #Note : Sub-splash pages are not available in registry mode. Hence added wip tag. We have to certify this scenario manually if we have data from production.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_regression @use_regression_2 @feature_c2 @mode_registry @wip @deprecated
  Scenario: CategorySubSplashPage - Verify max and min slides in slideshow and all should display as astra data in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page for "SLIDESHOW" media in "101" with "multiple" slides
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should see respective media as per astra data
    And I should see slideshow with minimum of one and maximum of six slides

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag. We have to certify this scenario manually if we have data from production.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_regression @use_regression_2 @feature_c2 @mode_registry @wip @deprecated
  Scenario: CategorySubSplashPage - Verify tapping on arrows will take user forward or backward in slideshow in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page for "SLIDESHOW" media in "101" with "multiple" slides
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    When I select "forward arrow" in slideshow
    Then I should see "next" slide is displayed in slideshow
    When I select "previous arrow" in slideshow
    Then I should see "previous" slide is displayed in slideshow

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag. We have to certify this scenario manually if we have data from production.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_regression @use_regression_2 @feature_c2 @mode_registry @wip @deprecated
  Scenario: CategorySubSplashPage - Verify single dot is not displaying in slideshow when only single slide is present in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page for "SLIDESHOW" media in "101" with "single" slides
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should not see dot is displaying in slideshow

  ########################################### Advanced Product Pool scenarios ####################################

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag. We have to certify this scenario manually if we have data from production.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_regression @use_regression_2 @feature_c2 @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify existing types of Advanced Product Pool and sort by options in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product pool and sort by options
  Examples:
    | page_type  | row_type | media                        |
    | Sub Splash | 101      | PRODUCT_PANEL_CATEGORY_FACET |
    | Sub Splash | 101      | PRODUCT_PANEL_POOL           |
    | Sub Splash | 101      | PRODUCT_PANEL_CATEGORY       |
    | Sub Splash | 101      | PRODUCT_PANEL_NA             |

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag. We have to certify this scenario manually if we have data from production.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_regression @use_regression_2 @feature_c2 @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - In product pool all available product count should be multiple of 3 in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product's are multiple of 3 or maximum 24
  Examples:
    | page_type  | row_type | media                        |
    | Sub Splash | 101      | PRODUCT_PANEL_CATEGORY_FACET |
    | Sub Splash | 101      | PRODUCT_PANEL_POOL           |
    | Sub Splash | 101      | PRODUCT_PANEL_CATEGORY       |
    | Sub Splash | 101      | PRODUCT_PANEL_NA             |

################################## Device = Tablet, Navigation Type = Browse, Registry & International ###################################

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row101 are contextualized on tablet in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                  |
    | 101      | AD                     |
    | 101      | SLIDESHOW              |
    | 101      | COPY_BLOCK             |
    | 101      | THUMBNAIL_GRID         |
    | 101      | VIDEO                  |
    | 101      | IMAGE_MAP              |
    | 101      | WIDGET                 |
    | 101      | PRODUCT_PANEL_CATEGORY |
    | 101      | MEDIA_ADS              |
    | 101      | HORIZONTAL_RULE        |
    | 101      | JSP                    |
    | 101      | TEXT                   |

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row102 are contextualized on tablet in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                        |
    | 102      | CATEGORY_ICON, CATEGORY_ICON |

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row103 are contextualized on tablet in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                                       |
    | 103      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row104 are contextualized on tablet in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                                                      |
    | 104      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row105 are contextualized on tablet in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                                                                     |
    | 105      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify row106 are contextualized on tablet in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
  Examples:
    | row_type | media                                                                                    |
    | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  ############################################ Slide show scenarios #############################################

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage - Verify max and min slides in slideshow and all should display as astra data in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page for "SLIDESHOW" media in "101" with "multiple" slides
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should see respective media as per astra data
    And I should see slideshow with minimum of one and maximum of six slides

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage - Verify tapping on arrows will take user forward or backward in slideshow in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page for "SLIDESHOW" media in "101" with "multiple" slides
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    When I select "forward arrow" in slideshow
    Then I should see "next" slide is displayed in slideshow
    When I select "previous arrow" in slideshow
    Then I should see "previous" slide is displayed in slideshow

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage - Verify single dot is not displaying in slideshow when only single slide is present in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page for "SLIDESHOW" media in "101" with "single" slides
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "SLIDESHOW" on the page in "101" row
    And I should not see dot is displaying in slideshow

  ######################################## Advance Product Pool scenarios ####################################

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify existing types of Advanced Product Pool and sort by options in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product pool and sort by options
  Examples:
    | page_type  | row_type | media                  |
    | Sub Splash | 101      | PRODUCT_PANEL_POOL     |
    | Sub Splash | 101      | PRODUCT_PANEL_CATEGORY |
    | Sub Splash | 101      | PRODUCT_PANEL_NA       |
    | Sub Splash | 101      | PRODUCT_PANEL_BAZAAR   |

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15A @use_manual @mode_iship @mode_registry @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage - In product pool all available product count should be multiple of 3 in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see product's are multiple of 3 or maximum 24
  Examples:
    | page_type  | row_type | media                  |
    | Sub Splash | 101      | PRODUCT_PANEL_POOL     |
    | Sub Splash | 101      | PRODUCT_PANEL_CATEGORY |
    | Sub Splash | 101      | PRODUCT_PANEL_NA       |
    | Sub Splash | 101      | PRODUCT_PANEL_BAZAAR   |