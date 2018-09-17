###############################################
# Story:          18269: WDS:: Render Contextual template for Cat Splash
# Author:         Ruchita Jain
# Modified by:    Akhilesh Kushwaha
# Date Created:   23th April, 2014
# Last Modified:  1st May, 2014
# Reviewer:
#############################################################

Feature: WDS :: MCOM :: Render Contextual Template for CatSplash Page

  @domain_discovery @priority_high @use_regression @mode_domestic @mode_registry @mode_iship
  Scenario Outline: CategorySplashPage - Domestic|Iship|Registry - Verify media links and popup links are not resulting any error page
    Given I visit the web site as a guest user in "<mode>" mode
    And I navigate to "<catsplash>" category page
    Then I verify all media links and popups are not resulting error page
    Examples:
      | mode     | catsplash              |
      | domestic | HANDBAGS               |
      | domestic | BED & BATH             |
      | registry | BED & BATH             |
      | domestic | BEAUTY                 |
      | iship    | HANDBAGS & ACCESSORIES |
  # Navigate to any cat splash page in each mode
  # Identify all links from media content section on page.
  # Then verify all media links are resulting '200' response code with 'Mechanize' agent request.

  @domain_discovery @priority_high @use_regression @mode_domestic @mode_registry @mode_iship @discovery_daily_run
  Scenario Outline: CategorySplashPage - Domestic|Iship - Verify media links and popup links are not resulting any error page on BED & BATH and JUNIORS
    Given I visit the web site as a guest user in "<mode>" mode
    And I navigate to "<catsplash>" category page
    Then I verify all media links and popups are not resulting error page
    Examples:
      | mode     | catsplash  |
      | domestic | BED & BATH |
      | iship    | JUNIORS    |
  # Navigate to any cat splash page in each mode
  # Identify all links from media content section on page.
  # Then verify all media links are resulting '200' response code with 'Mechanize' agent request.


################################## Device = Desktop Navigation Type = Browse ###################################
  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic
  Scenario Outline: CategorySplashPage -  Verify row101 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media                  |
      | 101      | SLIDESHOW              |
      | 101      | PRODUCT_PANEL_CATEGORY |
      | 101      | FLEXIBLE_POOL          |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic @discovery_daily_run
  Scenario Outline: CategorySplashPage - Verify row101 are contextualized on desktop for 'AD, COPY BLOCK, IMAGE MAP, HORIZONTAL RULE, TEXT' media in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media                  |
      | 101      | AD                     |
      | 101      | COPY_BLOCK             |
      | 101      | IMAGE_MAP              |
      | 101      | HORIZONTAL_RULE        |
      | 101      | TEXT                   |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic
  Scenario Outline: CategorySplashPage -  Verify row104 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 104      | CATEGORY_ICON |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic
  Scenario Outline: CategorySplashPage -  Verify row105 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 105      | CATEGORY_ICON |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic
  Scenario Outline: CategorySplashPage -  Verify row106 are contextualized on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 106      | CATEGORY_ICON |

#  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic
#  Scenario Outline: CategorySplashPage -  Verify row2 are contextualized on desktop in DOMESTIC mode
#    Given I visit the web site as a guest user
#    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
#      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
#      | DESKTOP     | US          | SITE          | BROWSE          |
#    Then I should see "<media>" in "<row_type>" on cat splash page
#    Examples:
#      | row_type | media |
#      | 2        | AD    |
#
#  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic
#  Scenario Outline: CategorySplashPage -  Verify row4 are contextualized on desktop in DOMESTIC mode
#    Given I visit the web site as a guest user
#    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
#      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
#      | DESKTOP     | US          | SITE          | BROWSE          |
#    Then I should see "<media>" in "<row_type>" on cat splash page
#    Examples:
#      | row_type | media |
#      | 4        | AD    |


################################## Device = Desktop Navigation Type = Browse ###################################
  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry @discovery_daily_run
  Scenario Outline: CategorySplashPage -  Verify row101 are contextualized on desktop for media 'AD, TEXT' in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media             |
      | 101      | AD                |
      | 101      | TEXT              |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry
  Scenario Outline: CategorySplashPage -  Verify row101 are contextualized on desktop in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media             |
      | 101      | SLIDESHOW         |
      | 101      | COPY_BLOCK        |
      | 101      | IMAGE_MAP         |
      | 101      | HORIZONTAL_RULE   |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry
  Scenario Outline: CategorySplashPage -  Verify row104 are contextualized on desktop in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 104      | CATEGORY_ICON |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry
  Scenario Outline: CategorySplashPage -  Verify row106 are contextualized on desktop in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 106      | CATEGORY_ICON |

#  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry
#  Scenario Outline: CategorySplashPage -  Verify row2 are contextualized on desktop in REGISTRY mode
#    Given I visit the web site as a guest user
#    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
#      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
#      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
#    Then I should see "<media>" in "<row_type>" on cat splash page
#    Examples:
#      | row_type | media |
#      | 2        | AD    |
#
#  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_registry
#  Scenario Outline: CategorySplashPage -  Verify row4 are contextualized on desktop in REGISTRY mode
#    Given I visit the web site as a guest user
#    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
#      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
#      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
#    Then I should see "<media>" in "<row_type>" on cat splash page
#    Examples:
#      | row_type | media |
#      | 4        | AD    |

################################## Device = Desktop Navigation Type = Browse ###################################

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship @discovery_daily_run
  Scenario Outline: CategorySplashPage -  Verify row101 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media             |
      | 101      | AD                |
      | 101      | COPY_BLOCK        |
      | 101      | IMAGE_MAP         |
      | 101      | HORIZONTAL_RULE   |
      | 101      | TEXT              |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship
  Scenario Outline: CategorySplashPage -  Verify row101 are contextualized on desktop for 'SLIDESHOW' media in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media             |
      | 101      | SLIDESHOW         |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship
  Scenario Outline: CategorySplashPage -  Verify row104 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 104      | CATEGORY_ICON |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship
  Scenario Outline: CategorySplashPage -  Verify row105 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 105      | CATEGORY_ICON |

  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship
  Scenario Outline: CategorySplashPage -  Verify row106 are contextualized on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 106      | CATEGORY_ICON |

#  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship
#  Scenario Outline: CategorySplashPage -  Verify row2 are contextualized on desktop in ISHIP mode
#    Given I visit the web site as a guest user
#    When I navigate to international context page
#    And I change country to "a random country"
#    And I close the welcome mat if it's visible
#    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
#      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
#      | DESKTOP     | INTL        | SITE          | BROWSE          |
#    Then I should see "<media>" in "<row_type>" on cat splash page
#    Examples:
#      | row_type | media |
#      | 2        | AD    |
#
#  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_iship
#  Scenario Outline: CategorySplashPage -  Verify row4 are contextualized on desktop in ISHIP mode
#    Given I visit the web site as a guest user
#    When I navigate to international context page
#    And I change country to "a random country"
#    And I close the welcome mat if it's visible
#    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
#      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
#      | DESKTOP     | INTL        | SITE          | BROWSE          |
#    Then I should see "<media>" in "<row_type>" on cat splash page
#    Examples:
#      | row_type | media |
#      | 4        | AD    |


