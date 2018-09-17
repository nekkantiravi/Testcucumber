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

  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify user should not see back to top button immediately when navigated to page on TABLET
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
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  ##############################################################################################################
  # Story B-17202 : C2 P2 :: Browse :: BCOM Breadcrumb/Nav Adjustments
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-17202
  # Story B-14161 : C2 P2 :: Browse :: BCOM Nav - Breadcrumb
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-14161
  ##############################################################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify secondary breadcrumb text is not displaying anymore under current page title on sub splash and browse, search results pages in tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see secondary breadcrumb text under current page title
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify pixel spacing between current page title and first link of new breadcrumb navigation on sub splash and browse, search results pages in tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should 14 pixel spacing between current page title and first link of new breadcrumb row
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify breadcrumb is underlined except current category in browse, sub splash, search results page on tablet
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
      | Sub Splash     | 3       | SITE     |
      | Sub Splash     | 3       | INTL     |
      | Sub Splash     | 4       | SITE     |
      | Sub Splash     | 4       | INTL     |
      | Sub Splash     | 4       | REGISTRY |


  ##############################################################################################################
  # Story B-10372 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Quick Peek Icon/Alt image
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10372
  # Story B-16248 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: QE ONLY Quick Peek Icon -- Coremetrics
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-16248
  ##############################################################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify static quick peek icon (quick peek icon needs to display without hovering on product image) on product images on tablet
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
      | ADVANCED_PRODUCT_POOL | normal        |
      | ADVANCED_PRODUCT_POOL | registry      |
      | ADVANCED_PRODUCT_POOL | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify first sequenced alt image is displayed without fade when mouse hovered on product image on tablet
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
      | ADVANCED_PRODUCT_POOL | normal        |
      | ADVANCED_PRODUCT_POOL | registry      |
      | ADVANCED_PRODUCT_POOL | international |

  ##############################################################################################################
  # Story B-16245 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Color Swatches
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-16245
  # Story B-10364 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Color Swatches -- Coremetrics
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10364
  ##############################################################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify maximum 7 color swatches displayed for any product when we have exactly 7 color swatches on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with exactly 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see maximum '7 color' swatches for any product
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify maximum 5 color swatches displayed for any product when we have more than 7 color swatches on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see maximum '5 color' swatches for any product
    And I should see 'More' link for color swatches
    When I select 'More' link under color swatches
    Then I should be redirected to respective PDP page
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify member product color swatch styles and positions for any product on Browse, Sub Splash, Search results and DLP pages on tablet
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
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify color swatches are left aligned for any product when we have less then 5 color swatches on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with less than 5 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see color swatches displayed for any product
    And I should not see 'More' link for color swatches
    And I should see color swatches are left aligned for any product
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify color swatches section collapsed for any product when we don't have additional colors on Browse, Sub Splash, Search results and DLP pages on tablet
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page without additional colors
    Then I should be landed on "<page_type>" page
    And I should see color swatches section is collapsed for any product
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify color swatches section position when 'Color Swatch Placement' cookie id value equal to '359' on Browse, Sub Splash, Search results and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see 'Color Swatch Placement' cookie id value equal to '359'
    And I should see color swatch section below the product thumbnail
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify color swatches section position when 'Color Swatch Placement' cookie id value equal to '360' on Browse, Sub Splash, Search results and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I should see 'Color Swatch Placement' cookie id value equal to '360'
    And I should see color swatch section below the product information
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  #Testlink-BLCOM-84132
  @use_regression @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify all existing color swatch behaviours on search results pages in SITE mode
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page with more than 7 color swatches for any product
    Then I should be landed on "<page_type>" page
    And I verify hover behaviour for color swatch
    And I verify colorized images behaviour for color swatch
    And I verify color facet behaviour for color swatch
    And I should see all existing behaviour for color swatch
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_manual @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify color swatch updates are excluded from on chanel pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see updated color swatch behaviour
    Examples:
      | page_type         | mode   |
      | Chanel Sub Splash | normal |


  @artifact_navapp @domain_discovery @priority_medium @release_15G @use_coremetrics @please_automate  @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify attribute37 is reserved for testing on browse, sub splash, search results and DLP pages and shop 5 and 9 actions
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
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  ##############################################################################################################
  # Story B-10897 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Thumbnail badges-experimentation
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10897
  ##############################################################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify all badges as today for 25% of traffic users with 355 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify only promo text badge displays in gray color for 25% of traffic users with 356 id value in cookie on browse, sub splash and DLP pages
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
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify only promo text badge displays in color as today for 25% of traffic users with 357 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should see 'Promo Text (Ex: Bonus Offers)' badge in 'red' color
    And I should not see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should not see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_manual @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify no badges are displayed for 25% of traffic users with 358 id value in cookie on browse, sub splash and DLP pages
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should not see 'Promo Text (Ex: Bonus Offers)' as badge on thumbnail grid
    And I should not see 'Flex Text (Ex: Smart Living)' as badge on thumbnail grid
    And I should not see 'Thumbnail icon callout (Ex: New Arrival)' as badge on thumbnail grid
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


  @artifact_navapp @domain_discovery @priority_medium @release_15F @use_coremetrics @please_automate @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify attribute37 is reserved for testing on browse, sub splash and DLP pages(page view action)
    Given I visit the web site as a guest user
    When I navigate in "<mode>" mode to a "<page_type>" page in which a category is unsuppressed
    Then I should be landed on "<page_type>" page
    And I should see 'attribute37' tag as 'test_number-group (Ex: 0009-A)'
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |


##############################################################################################################
# Story B-23172: C2 P2 :: Browse :: BCOM Default Grid :: Creative Styling Updates for 15H
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-23172
##############################################################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15H @use_galen @please_automate @mode_registry @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify Sort By drop down and pagination arrows are replaced as arrow image asset on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see 'Sort by drop down' arrows are replaced with arrow image asset
    And I should see 'Pagination' arrows are replaced with arrow image asset
    When I hovered on 'arrow icons' under pagination
    And I should see arrows icons are not highlighted in pink color
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15H @use_galen @please_automate  @mode_registry @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify Sale prices font is displying with red color and not in bold style at the bottom of product thumbnails on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see 'Sales price' value with 'red' color font
    And I should not see 'Sales price' value with 'bold' font style
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15H @use_galen @please_automate @mode_registry @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify Product count is center aligned in between the Sort By dropdown and pagination values when show 180 and all options are suppressed on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page with less than 180 products
    Then I should be landed on "<page_type>" page
    And I should see show '180' and 'All' options are suppressed
    And I should see Product count is center aligned in between 'Sort by' and pagination section
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15H @use_galen @please_automate @mode_registry @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify horizontal grey line at the bottom of thumbnail images on sub splash, browse and DLP pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see horizontal grey line at the bottom of thumbnail images
    Examples:
      | page_type      | mode          |
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |

  @artifact_navapp @domain_discovery @priority_medium @release_15H @use_galen @please_automate @mode_registry @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify creative style changes are excluded for chanel pages on desktop
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should not see creative style changes on chanel product thumbnail grid
    Examples:
      | page_type         | mode   |
      | Chanel Sub Splash | normal |


  ##############################################################################################################
  # Story B-19724 : C2 P2 :: Browse :: BCOM :: CAP :: Persist Customer Location
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-19724
  ##############################################################################################################

  # If we implement this scenario for automation, scenarios would not be stable to pass. Because finding a target page with more products and select random facets with more products to verify location persistance.
  # So we are keeping this as manual for verification.
  @artifact_navapp @domain_discovery @priority_medium @use_manual @release_15H @mode_registry @mode_iship @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify customer location persistance with single/multiple facet selection and pagination in sub splash, browse, search results and DL pages
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
      | Sub Splash     | normal        |
      | Sub Splash     | registry      |
      | Sub Splash     | international |



    ###Manual Scenario

  @artifact_navapp @domain_discovery @release_15F @use_manual @mode_domestic @wip @deprecated
  Scenario Outline: Multiple pages - Verify navapp ignores the canvas id if canvas id returned by getCategoryTree method for all chanel pages in both M&BCOM brands in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random "<page_type>" page with canvas page layout
    Then I should see navapp ignores the canvas id on page
    And I should not see any canvas related media on chanel pages
    Examples:
      | page_type             |
      | Chanel Sub Splash     |
