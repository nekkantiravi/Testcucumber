############################################
# Author: Discovery Regression QE Team
# Date: 6th June 2017
# Date Modified: None
############################################

Feature: To verify coremetric tags in category splash page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: Category splash Page - Domestic|Iship|Registry - Verify Page view tags for catsplash page
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to random category splash page
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: Category Splash Page - Domestic - Verify link click tags for media in catsplash page
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media                                                      |
      | 101      | IMAGE_MAP                                                  |
      | 101      | FLEXIBLE_POOL                                              |
      | 101      | SLIDESHOW                                                  |
      | 104      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: Category Splash Page - Registry - Verify link click tags for media in catsplash page
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media                                                                                    |
      | 101      | AD                                                                                       |
#      | 101      | IMAGE_MAP                                                                                |
#      | 101      | RECENTLY_REVIEWED                                                                        |
#      | 101      | FLEXIBLE_POOL                                                                            |
#      | 101      | SLIDESHOW                                                                                |
#      | 105      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: Category Splash Page - Iship - Verify link click tags for media in catsplash page
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media                                                      |
      | 101      | IMAGE_MAP                                                  |
      | 101      | FLEXIBLE_POOL                                              |
      | 101      | SLIDESHOW                                                  |
      | 104      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |