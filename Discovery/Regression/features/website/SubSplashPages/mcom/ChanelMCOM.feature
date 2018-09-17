# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017


Feature: Verify CHANEL Pages in DOMESTIC, ISHIP and REGISTRY mode

  #Test Case ID: MCOM-86698
  #Testlink-MCOM-86704 Vone - RT-07326
  @use_regression @mode_domestic @domain_discovery @priority_high @chanel_regression @discovery_daily_run @xbrowser_subsplash
  Scenario: SubSplashPage - Verify Chanel Contents in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I verify the basic attributes of Chanel Brand Shop Page
    # Notes:
    # Do full validation - should not display any broken image or link
    # TBD:
    # Following content should be displayed in the CHANEL Home Page
    # Brand Logo Banner "CHANEL" left aligned, "categories" below on left side,
    # Category thumbnails with name links below them, Padding between brand logo banner,
    # Rotating ad feature banner below logo banner
    # All the images , links, pools and baners should be displayed , aligned and navigated as designed


  #Test Case ID: MCOM-86683 TestLink -MCOM-86683 Vone - RT-07486
  @use_regression @mode_domestic @domain_discovery @priority_high @chanel_regression @discovery_daily_run @xbrowser_subsplash
  Scenario Outline: SubSplashPage - Verify Chanel Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    And I verify the basic attributes of chanel browse page
  Examples:
    | leftnav           |
    | WHAT'S NEW        |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKINCARE          |
    | GIFTS             |
  #  Notes:
  #Need to update step definition to verify
  #a. Brand Logo Banner "CHANEL" left aligned
  #b. breadcrumb trail
  #c. horizontal rule between breadcrumb trail and categories
  #d.  brand name
  #e.  product name
  #f. product description
  #g. "product_thumbnail_price" value
  #h.  QuickView button below price
  #i. No reviews
  #  Brand Logo Banner is displayed
  #  SUBCATEGORY text is displayed
  #  "View All <CATEGORY>" link with text is displayed
  #  More than 2 SUBCATEGORIES on the left navigation are displayed
  #  products are displayed

  @use_regression @mode_domestic @domain_discovery @priority_high @chanel_regression
  Scenario: SubSplashPage - Verify Chanel RVI & Tag Cloud is NOT displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I should not see recently viewed items section
    And I should not see the Popular Searches section above footer
    # Notes:
    # VIEW ALL BEAUTY link should NOT appear, explicitly check this
    # Recently Viewed is not displayed
    # Social Shopping/Polling Widget is not displayed
    # Tag Cloud is not displayed

  @use_regression @mode_domestic @domain_discovery @priority_high @chanel_regression
  Scenario: SubSplashPage - Search for Chanel and navigate to PDP from quick View in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "CHANEL"
    Then I should see page navigated to "/chanel?id=61916&edge=hybrid&cm_kws=chanel" pattern url
    And I select any CHANEL subcategory
    Then I verify the display and functionality of the chanel PDP
    # Notes:
    # Should not display social shopping links.
    # 1. Macys homepage should display.
    # 2. Chanel main page should display.
    # 3. Chanel pdp page should display.
    # 4. Should not display social shopping links .
    # 5. Auto Zoom functionality should not work.
    # 6. Larger view functionality should work.
    # 7. url should not contain the term "syndicated".

  @use_regression @priority_high @mode_domestic @chanel_regression @domain_discovery
  Scenario: SubSplashPage - Ensure product thumbnails are displayed Chanel Browse Page with old browse grid implementation when navigated through browser back button in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "WHAT'S NEW" browse page from cat splash page
    Then I store product list temporarily
    When I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    Then I should see same stored products are displaying


  #Test Case ID: MCOM-86704
  @use_regression @priority_high @mode_domestic @chanel_regression @domain_discovery
  Scenario Outline: SubSplashPage - Verify RVI & Tag Cloud is NOT displayed on Chanel Browse Page
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    Then I should not see recently viewed items section
    And I should not see the Popular Searches section above footer
  Examples:
    | leftnav           |
    | WHAT'S NEW        |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKINCARE          |
    # Notes:
    # Do full validation - should not display any broken image or link

  @use_regression @mode_domestic @artifact_navapp @domain_discovery @priority_high @chanel_regression
  Scenario Outline: SubSplashPage - Verify Chanel Color Swatches functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    Then I verify the that color swatches are displayed on chanel browse
  Examples:
    | leftnav |
    | MAKEUP  |
    # Notes:
    # Just verify that at-least more than 5 products have color swatches

  @use_regression @use_regression_1 @mode_domestic @artifact_navapp @domain_discovery @priority_high @chanel_regression @review_stability
  Scenario Outline: SubSplashPage - Verify Chanel Left Nav View ALL CHANEL functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    When I select "View All CHANEL" link in left nav section
    Then I should see page navigated to "/chanel?id=61916&edge=hybrid" pattern url
  Examples:
    | leftnav           |
    | WHAT'S NEW        |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKINCARE          |
    # Notes:
    # Verify that link lands back on CHANEL brand shop page

  @domain_discovery @priority_medium @mode_domestic @use_regression @discovery_daily_run @xbrowser_subsplash
  Scenario Outline: Chanel Quickview - Verify ADD TO BAG button on quick view overlay in Chanel Browse page
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    When I select the chanel quick peek of 'member' product
    Then I verify the that QuickView is displayed for chanel product
    When I select 'ADD TO BAG' button on 'chanel quick view' overlay
    Then I should be navigated to "add to bag" page
  Examples:
    | leftnav           |
    | WHAT'S NEW        |

  @domain_discovery @priority_medium @mode_domestic @use_regression
  Scenario Outline: Chanel Quickview - Verify ADD TO BAG button on chanel quick view overlay
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    When I select the chanel quick peek of 'member' product
    Then I verify the that QuickView is displayed for chanel product
    When I select 'ADD TO BAG' button on 'chanel quick view' overlay
    Then I should be navigated to "add to bag" page
    Examples:
      | leftnav           |
      | WOMEN'S FRAGRANCE |
      | MEN'S FRAGRANCE   |
      | MAKEUP            |
      | SKINCARE          |

  @domain_discovery @artifact_navapp @priority_medium @mode_domestic @mode_registry @mode_iship @adding_missing_scope @please_automate @wip
  Scenario Outline: Chanel Quickview - Verify ADD TO LIST button on quick view overlay in Chanel Browse page
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    When I select the chanel quick peek of 'member' product
    Then I verify the that QuickView is displayed for chanel product
    When I select 'ADD TO LIST' button on 'chanel quick view' overlay
    Then I should see "Added to your guest list.Sign in to save it to your account." success message on overlay
  Examples:
    | leftnav           |
    | WHAT'S NEW        |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKINCARE          |

  @domain_discovery @priority_medium @mode_domestic @use_regression
  Scenario Outline: Chanel PDP - Verify ADD TO BAG button on chanel PDP
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    When I select the chanel quick peek of 'member' product
    Then I verify the that QuickView is displayed for chanel product
    When I select 'see full product details' link from the quickview dialog
    And I select 'ADD TO BAG' button on chanel PDP page
    Then I should be navigated to "add to bag" page
    Examples:
      | leftnav           |
      | WHAT'S NEW        |
      | WOMEN'S FRAGRANCE |
      | MEN'S FRAGRANCE   |
      | MAKEUP            |
      | SKINCARE          |

  @domain_discovery @priority_medium @mode_domestic @use_regression
  Scenario Outline: Chanel PDP - Verify ADD TO LIST button on chanel PDP
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" browse page from cat splash page
    When I select the chanel quick peek of 'member' product
    Then I verify the that QuickView is displayed for chanel product
    When I select 'see full product details' link from the quickview dialog
    And I select 'ADD TO LIST' button on chanel PDP page
    Then I should see "Added to your guest list.Sign in to save it to your account." success message on overlay
    Examples:
      | leftnav           |
      | WHAT'S NEW        |
      | WOMEN'S FRAGRANCE |
      | MEN'S FRAGRANCE   |
      | MAKEUP            |
      | SKINCARE          |
