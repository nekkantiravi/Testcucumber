Feature: Verify contextual content data on UI pages

  @scenario1 @domain_discovery @xbrowser @xbrowser_one @data_dependency
  Scenario Outline: Category Splash page - Verify row101 are contextualized on desktop in DOMESTIC mode for contextual media
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media                  |
      | 101      | AD                     |
      | 101      | VIDEO                  |
      | 101      | IMAGE_MAP              |
      | 101      | PRODUCT_PANEL_CATEGORY |
#      | 101      | JSP                    |

  @scenario2 @domain_discovery @xbrowser @xbrowser_one @data_dependency
  Scenario Outline: Category Splash page - Verify row101 are contextualized on desktop in ISHIP mode for contextual media
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media                        |
      | 101      | AD                           |
      | 101      | VIDEO                        |
      | 101      | IMAGE_MAP                    |
      | 101      | PRODUCT_PANEL_CATEGORY       |
#      | 101      | JSP                          |

  @scenario4 @domain_discovery @xbrowser @xbrowser_two @data_dependency
  Scenario Outline: Browse page - Verify row101 are contextualized in ISHIP mode for contextual media
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on browse page
    Examples:
      | row_type | media                  |
      | 101      | AD                     |
      | 101      | VIDEO                  |
      | 101      | IMAGE_MAP              |
      | 101      | MEDIA_ADS              |
#      | 101      | JSP                    |
      | 101      | PRODUCT_PANEL_CATEGORY |

  @scenario5 @domain_discovery @xbrowser @xbrowser_two @data_dependency
  Scenario Outline: Browse page - Verify row101 are contextualized on desktop in REGISTRY mode for contextual media
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on browse page
    Examples:
      | row_type | media                  |
      | 101      | AD                     |
      | 101      | PRODUCT_PANEL_CATEGORY |