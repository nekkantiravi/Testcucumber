# Author: BGV QE Team
# Date Created :
# Date Modified :
# Date Signed Off: TBD

Feature:C2 :: P2 :: BCOM :: Browse Grid Variations manual scenarios for Browse, Sub Splash, Cat Splash, Search and DLP pages

##############################################################################################################
# Story B-14714: C2 P2 :: Browse :: BCOM Back to Top Search
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-14714
# Story B-11986: C2 P2 :: Browse :: BCOM Back to Top Browse
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-11986
##############################################################################################################

  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify user should not see back to top button immediately when navigated to page on TABLET
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see back to top button immediately after navigating to page
    When I scroll 'down' the page until reach bottom of footer panel
    Then I should see back to top button appears above footer
    When I select 'back to top' button
    Then I should see page is navigated to top of the page
    When I scroll 'down' with '1000' pixel size on the page
    And I scroll 'up' the page until reach top of header panel
    Then I should see back to top button fades away
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  ##############################################################################################################
  # Story B-17202 : C2 P2 :: Browse :: BCOM Breadcrumb/Nav Adjustments
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-17202
  # Story B-14161 : C2 P2 :: Browse :: BCOM Nav - Breadcrumb
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-14161
  ##############################################################################################################

  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify secondary breadcrumb text is not displaying anymore under current page title on sub splash and browse, search results pages in tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see secondary breadcrumb text under current page title
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify pixel spacing between current page title and first link of new breadcrumb navigation on sub splash and browse, search results pages in tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should 14 pixel spacing between current page title and first link of new breadcrumb row
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15F @use_manual @priority_medium
  Scenario Outline: BrowsePage - Verify breadcrumb is underlined except current category in browse, sub splash, search results page on tablet
    Given I visit the web site as a guest user
    When I navigate to a random '<page_type>' page with "<n-level>" category in "<mode>"
    Then I should see breadcrumb upto "<n-level>" of category name except level-2 category name
    And I should see all categories are underlined in breadcrumb except current page
    And I should see breadcrumb displayed at top of browse media
    And I should see breadcrumb displayed with '11 pixel' font size
    And I should see breadcrumb displayed with '#666' font color
    And I should see all categories are underline in breadcrumb except current '<page_type>'
    Examples:
      | page_type      | n-level | mode     |
      | Browse         | 3       | SITE     |
      | Browse         | 3       | INTL     |
      | Browse         | 4       | SITE     |
      | Browse         | 4       | INTL     |
      | Browse         | 4       | REGISTRY |

  ##############################################################################################################
  # Story B-10372 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Quick Peek Icon/Alt image
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10372
  # Story B-16248 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: QE ONLY Quick Peek Icon -- Coremetrics
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-16248
  ##############################################################################################################

  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify static quick peek icon (quick peek icon needs to display without hovering on product image) on product images on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see quick peek icon on product image without hovering on product image
    And I should see quick peek tool tip 'Quick Peek' message on page
    And I should not see video icon below product thumbnail on page
    When I select 'member' product quick peek icon on product image
    Then I should see 'member' product quick peek overlay with existing features
    When I select 'master' product quick peek icon on product image
    Then I should see 'master' product quick peek overlay with existing features
    Examples:
      | page_type             | mode          |
      | Browse                | normal        |
      | Browse                | registry      |
      | Browse                | international |


  @artifact_navapp @domain_discovery @release_15G @use_manual @priority_medium
  Scenario Outline: BrowsePage - Verify static quick peek icon is not displaying on chanel pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see quick peek icon on product image without hovering on product image
    Examples:
      | page_type     | mode   |
      | Chanel Browse | normal |

  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify first sequenced alt image is displayed without fade when mouse hovered on product image on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    When I hovered on a product thumbnail image which has alt image
    And I should see first sequenced alt image is hold without fade
    When I hovered off on same product image icon
    Then I should see default product thumbnail image for same product
    Examples:
    Examples:
      | page_type             | mode          |
      | Browse                | normal        |
      | Browse                | registry      |
      | Browse                | international |

  ##############################################################################################################
  # Story B-16245 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Color Swatches
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-16245
  # Story B-10364 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Color Swatches -- Coremetrics
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10364
  ##############################################################################################################

  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify maximum 7 color swatches displayed for any product when we have exactly 7 color swatches on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with exactly 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see maximum '7 color' swatches for any product
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify maximum 5 color swatches displayed for any product when we have more than 7 color swatches on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see maximum '5 color' swatches for any product
    And I should see 'More' link for color swatches
    When I select 'More' link under color swatches
    Then I should be redirected to respective PDP page
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify member product color swatch styles and positions for any product on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any member product
    Then I should be landed on "<page_type>" page
    And I should see color swatch with '27X27' pixel size
    And I should see '17 pixel' color swatch spacing with top and bottom sections
    And I should see '7 pixel' spacing or equally distributed between each color swatch
    And I should see color swatch and more links with 'stroke #ccc' border style
    And I should see 'more' link with '60X27' pixel size
    And I should see 'more' link with 'arial' font style
    And I should see 'more' link with '10px' font size
    And I should see 'more' link is 'middle' aligned
    When I select a random color swatch
    Then I should see respective color swatch
    And I should see selected symbol with '27X2' pixel size
    And I should see selected symbol with '#000' color
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify color swatches are left aligned for any product when we have less then 5 color swatches on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with less than 5 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see color swatches displayed for any product
    And I should not see 'More' link for color swatches
    And I should see color swatches are left aligned for any product
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify color swatches section collapsed for any product when we don't have additional colors on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page without additional colors
    Then I should be landed on "<page_type>" page
    And I should see color swatches section is collapsed for any product
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify color swatches section position when 'Color Swatch Placement' cookie id value equal to '359' on Browse, Sub Splash, Search results and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see 'Color Swatch Placement' cookie id value equal to '359'
    And I should see color swatch section below the product thumbnail
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15G @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify color swatches section position when 'Color Swatch Placement' cookie id value equal to '360' on Browse, Sub Splash, Search results and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see 'Color Swatch Placement' cookie id value equal to '360'
    And I should see color swatch section below the product information
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  #Testlink-BLCOM-84132
  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @release_15G @use_manual @priority_medium
  Scenario Outline: BrowsePage - Verify all existing color swatch behaviours on search results pages in SITE mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I verify hover behaviour for color swatch
    And I verify colorized images behaviour for color swatch
    And I verify color facet behaviour for color swatch
    And I should see all existing behaviour for color swatch
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15G @use_manual @priority_medium
  Scenario Outline: BrowsePage - Verify color swatch updates are excluded from on chanel pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see updated color swatch behaviour
    Examples:
      | page_type         | mode   |
      | Chanel Browse     | normal |


  @artifact_navapp @domain_discovery @release_15G @use_coremetrics @please_automate @priority_medium
  Scenario Outline: BrowsePage - Verify attribute37 is reserved for testing on browse, sub splash, search results and DLP pages and shop 5 and 9 actions
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'attribute37' tag reserved for 'test_number-group (Ex: 0010-A(cookie 359) or 0010-B(cookie 360)' on "<page_type>" page
    When I navigate to a random product page from "<page_type>" page
    Then I should see 'attribute37' tag reserved for 'test_number-group (Ex: 0010-A(cookie 359) or 0010-B(cookie 360)' on pdp page
    When I navigate to add to bag page from pdp page
    Then I should see 'attribute37' tag reserved for 'test_number-group (Ex: 0010-A(cookie 359) or 0010-B(cookie 360)' on Add to Bag page
    When I navigate to checkout page from add to bag page
    Then I should see 'attribute37' tag reserved for 'test_number-group (Ex: 0010-A(cookie 359) or 0010-B(cookie 360)' on Checkout page
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15G @use_coremetrics @use_manual @priority_medium
  Scenario Outline: BrowsePage - Verify coremetrics when color swatches are selected on advance product pool
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE   | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | <re_code> | <mode>        | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    Then I find a "random" product having "color" swatches
    When I "select" any color in color swatches section
    And I save the page and category id data in variables for "<page_type>" page on "<mode>"
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
      | page_type | row_type | media                 | mode             | re_code |
      | Browse    | 101      | ADVANCED_PRODUCT_POOL | SITE             | INTL    |
      | Browse    | 101      | ADVANCED_PRODUCT_POOL | SITE             | US      |
      | Browse    | 101      | ADVANCED_PRODUCT_POOL | WEDDING_REGISTRY | US      |
    #Note: Data is not available for advance product pool with color swatches which have more button. Hence commented scenario


  ##############################################################################################################
  # Story B-10897 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Thumbnail badges-experimentation
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10897
  ##############################################################################################################

  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify all badges as today for 25% of traffic users with 355 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify only promo text badge displays in gray color for 25% of traffic users with 356 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should see 'Promo Text (Ex: Bonus Offers)' badge in '#999' color
    And I should see 'Promo Text (Ex: Bonus Offers)' badge in 'heavy' font
    And I should not see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should not see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify only promo text badge displays in color as today for 25% of traffic users with 357 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should see 'Promo Text (Ex: Bonus Offers)' badge in 'red' color
    And I should not see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should not see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_registry @priority_medium
  Scenario Outline: BrowsePage - Verify no badges are displayed for 25% of traffic users with 358 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should not see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should not see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should not see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15F @use_coremetrics @please_automate @priority_medium
  Scenario Outline: BrowsePage - Verify attribute37 is reserved for testing on browse, sub splash and DLP pages(page view action)
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'attribute37' tag as 'test_number-group (Ex: 0009-A)'
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |


  @artifact_navapp @domain_discovery @release_15F @use_coremetrics @please_automate @priority_medium
  Scenario Outline: BrowsePage - Verify attribute37 is reserved for testing on product display pages(product view action)
    Given I visit the web site as a guest user
    When I navigate to any category browse page in '<mode>'
    Then I should be landed on "browse" page
    When I select any product thumbnail on browse page
    Then I should see 'attribute37' tag as 'test_number-group (Ex: 0009-A)'
    Examples:
      | mode          |
      | SITE          |
      | REGISTRY      |
      | INTERNATIONAL |

  @artifact_navapp @domain_discovery @release_15F @use_coremetrics @please_automate @priority_medium
  Scenario Outline: BrowsePage - Verify attribute37 is reserved for testing on AddToBag pages(shop action-5)
    Given I visit the web site as a guest user
    When I navigate to product display page in '<mode>'
    Then I should be landed on "product" page
    When I select 'AddToBag' button on product page
    Then I should see 'attribute37' tag as 'test_number-group (Ex: 0009-A)'
    Examples:
      | mode          |
      | SITE          |
      | REGISTRY      |
      | INTERNATIONAL |

  @artifact_navapp @domain_discovery @release_15F @use_coremetrics @please_automate @priority_medium
  Scenario Outline: BrowsePage - Verify attribute37 is reserved for testing on placing order pages(shop action-9)
    Given I visit the web site as a guest user
    When I navigate to checkout page in '<mode>'
    Then I should be landed on "checkout" page
    When I select 'place order' button on checkout page
    Then I should see 'attribute37' tag as 'test_number-group (Ex: 0009-A)'
    Examples:
      | mode          |
      | SITE          |
      | REGISTRY      |
      | INTERNATIONAL |

  ####################################################################################################################################################
  # Story B-15439: C2 P2 :: Browse :: M&BCOM :: CAP :: QE :: Test Browse Grid Variations UI Enhancements KS
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-15439
  # Story B-15435: C2 P2 :: Browse :: M&BCOM :: CAP :: QE :: Test Browse Grid Variations KS
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-15435
  ####################################################################################################################################################

  @artifact_navapp @domain_discovery @use_galen @please_automate  @release_15F @mode_registry @priority_medium
  Scenario: BrowsePage - Verify UI enhancements in browse, sub splash, DLP and search pages when BGV UI Enhancements Enabled KS is true in site, registry and international mode
    Given I visit the web site as a guest user
    When I navigate to browse, sub splash, cat splash, DLP and search pages
    Then I should see breadcrumbs in applicable pages
    And I should see thumbnail experimentation
    And I should see Quick peak and alt image
    And I should see back to top button
    And I should see Breadcrumb Nav Adjustments

  @artifact_navapp @domain_discovery @use_galen @please_automate  @release_15F @mode_registry @priority_medium
  Scenario: BrowsePage - Verify UI enhancements in browse, sub splash, cat splash, DLP and search pages when BGV UI Enhancements Enabled KS is false in site, registry and international mode
    Given I visit the web site as a guest user
    When I navigate to browse, sub splash, cat splash, DLP and search pages
    Then I should not see breadcrumbs in applicable pages
    And I should  not see thumbnail experimentation
    And I should not see Quick peak and alt image
    And I should not see back to top button
    And I should not see Breadcrumb Nav Adjustments


##############################################################################################################
# Story B-23172: C2 P2 :: Browse :: BCOM Default Grid :: Creative Styling Updates for 15H
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-23172
##############################################################################################################

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate  @mode_registry @mode_iship @priority_medium
  Scenario Outline: BrowsePage - Verify Sort By drop down and pagination arrows are replaced as arrow image asset on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see 'Sort by drop down' arrows are replaced with arrow image asset
    And I should see 'Pagination' arrows are replaced with arrow image asset
    When I hovered on 'arrow icons' under pagination
    And I should see arrows icons are not highlighted in pink color
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate  @mode_registry @mode_iship @priority_medium
  Scenario Outline: BrowsePage - Verify Sale prices font is displying with red color and not in bold style at the bottom of product thumbnails on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see 'Sales price' value with 'red' color font
    And I should not see 'Sales price' value with 'bold' font style
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate @mode_registry @mode_iship @priority_medium
  Scenario Outline: BrowsePage - Verify Product count is center aligned in between the Sort By dropdown and pagination values when show 180 and all options are suppressed on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page with less than 180 products
    Then I should be landed on "<page_type>" page
    And I should see show '180' and 'All' options are suppressed
    And I should see Product count is center aligned in between 'Sort by' and pagination section
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate @mode_registry @mode_iship @priority_medium
  Scenario Outline: BrowsePage - Verify horizontal grey line at the bottom of thumbnail images on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see horizontal grey line at the bottom of thumbnail images
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate @mode_registry @mode_iship @priority_medium
  Scenario Outline: BrowsePage - Verify creative style changes are excluded for chanel pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see creative style changes on chanel product thumbnail grid
    Examples:
      | page_type         | mode   |
      | Chanel Browse     | normal |

  ####################################################################################################################################################
  # Story B-24506: C2 P2 :: Browse :: BCOM :: CAP :: QE :: Test BCOM Browse Grid Variations UI Enhancements (2) KS
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-24506
  ################################## Site Mode ########################################################################################################

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate @mode_registry @mode_iship @priority_medium
  Scenario: BrowsePage - Verify UI enhancements in browse, sub splash, DLP and search pages when BGV BCOM UI Enhancements 2 Enabled KS is true
    Given I visit the web site as a guest user
    When I navigate to browse, sub splash, DLP and search pages in all modes
    Then I should be navigated to respective pages
    And I should not see media ads after the first page
    And I should see new sort by style in respective page
    And I should see new breadcrumbs style in respective page
    And I should see new pagination style in respective page
    And I should see new font styles in respective page

  @artifact_navapp @domain_discovery @release_15H @use_galen @please_automate @mode_registry @mode_iship @priority_medium
  Scenario: BrowsePage - Verify UI enhancements in browse, sub splash, DLP and search pages when BGV BCOM UI Enhancements 2 Enabled KS is false
    Given I visit the web site as a guest user
    When I navigate to browse, sub splash, DLP and search pages in all modes
    Then I should be navigated to respective pages
    And I should see media ads in 1,3,5 pages
    And I should see old sort by style in respective page
    And I should see old breadcrumbs style in respective page
    And I should see old pagination style in respective page
    And I should see old font styles in respective page

  ##############################################################################################################
  # Story B-17519 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: View 180/View All -- Coremetrics
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-17519
  ##############################################################################################################
# Below scenarios is no more valid(old functionality)
#  @artifact_navapp @domain_discovery @release_15H @use_coremetrics @please_automate @mode_registry @mode_iship
#  Scenario Outline: BrowsePage - Verify view 180 coremetric tags for view 180 option on Browse, Sub Splash and DLP pages
#    Given I visit the web site as a guest user
#    When I navigate in "<mode>" mode to a "<page_type>" page with one product
#    Then I should be landed on "<page_type>" page
#    And I should see 'Element Category' tag with 'Browse Grid' value
#    And I should see 'Element ID' tag with 'VIEW_180' value
#    And I should see 'Attribute 2' tag with 'PAGE_ID' value
#  Examples:
#    | page_type      | mode          |
#    | Browse         | normal        |
#    | Sub Splash     | normal        |
#    | DLP            | normal        |
#    | Browse         | registry      |
#    | Sub Splash     | registry      |
#    | DLP            | registry      |
#    | Browse         | international |
#    | Sub Splash     | international |
#    | DLP            | international |
#    | Search Results | normal        |
#    | Search Results | registry      |
#    | Search Results | international |

  ##############################################################################################################
  # Story B-19724 : C2 P2 :: Browse :: BCOM :: CAP :: Persist Customer Location
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-19724
  ##############################################################################################################

  # If we implement this scenario for automation, scenarios would not be stable to pass. Because finding a target page with more products and select random facets with more products to verify location persistance.
  # So we are keeping this as manual for verification.
  @artifact_navapp @domain_discovery @use_manual @release_15H @mode_registry @mode_iship @priority_medium
  Scenario Outline: BrowsePage - Verify customer location persistance with single/multiple facet selection and pagination in sub splash, browse, search results and DL pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page with "more than 1000 products" in thumbnail grid
    And I select 'single or multiple' facets with more than 300 products on page
    Then I should see faceted products are displayed with '180' product count per page
    When I select a product on thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "<page_type>" page and same product location with selected facet values
    When I select next page from pagination
    And I select a product on thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "<page_type>" page and same product location with selected facet values
    Examples:
      | page_type      | mode          |
      | Browse         | normal        |
      | Browse         | registry      |
      | Browse         | international |

