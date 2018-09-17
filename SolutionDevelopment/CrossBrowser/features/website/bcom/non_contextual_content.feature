Feature: Verify non contextual content data on UI pages

  @scenario1 @domain_discovery @xbrowser @xbrowser_one @data_dependency
  Scenario Outline: Category Splash page - Verify row101 are contextualized on desktop in DOMESTIC mode for non contextual media
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media                  |
      | 101      | SLIDESHOW              |
#      | 101      | WIDGET                 |

  @scenario2 @domain_discovery @xbrowser @xbrowser_one @data_dependency
  Scenario Outline: Category Splash page - Verify row101 are contextualized on desktop in ISHIP mode for non contextual media
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media                        |
      | 101      | SLIDESHOW                    |
#      | 101      | WIDGET                       |

  @scenario3 @domain_discovery @xbrowser @xbrowser_two @data_dependency @wip @deprecated
  Scenario Outline: Category Splash page - Verify row101 are contextualized on desktop in REGISTRY mode  on desktop for non contextual media
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Category Splash" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media            |
      | 101      | SLIDESHOW        |

  @scenario4 @domain_discovery @xbrowser @xbrowser_two @data_dependency
  Scenario Outline: Browse page - Verify row101 are contextualized in ISHIP mode for non contextual media
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" in "<row_type>" on browse page
    Examples:
      | row_type | media           |
      | 101      | SLIDESHOW       |
      | 101      | THUMBNAIL_GRID  |
#      | 101      | WIDGET          |

  @scenario5 @domain_discovery @xbrowser @xbrowser_two @data_dependency
  Scenario Outline: Browse page - Verify row101 are contextualized on desktop in REGISTRY mode for non contextual media
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" in "<row_type>" on browse page
    Examples:
      | row_type | media           |
      | 101      | SLIDESHOW       |
      | 101      | THUMBNAIL_GRID  |
      # As per business, widget media is no longer available. Hence removed for regression suit
#      | 101      | WIDGET          |