#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify COACH SubSplash Pages in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @use_domain_qual @bcom
  Scenario: CategorySubSplashPage - Verify COACH Browse Page functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify the basic attributes of COACH browse page
    # Notes:
    # Verification required:
    # Product thumbnails image should display properly
    # Facets should display on the left navigation

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @please_automate @mode_domestic
  Scenario: CategorySubSplashPage - Verify COACH  Quick Peek in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    #Then I verify that Quick Peek overlay is displayed upon hover
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    # Notes:
    # Just verify that a popup is displayed with non empty contents when hovered on random product on page

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario Outline: CategorySubSplashPage - Verify COACH contents in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    And I navigate to COACH "<leftnav>"
    Then I verify the basic attributes of COACH Brand browse Page
  Examples:Page
    | leftnav       |
    | Women's Shoes |
    | Handbags      |
    | Men           |
    # Notes:
    # Do full validation - should not display any broken image or link


  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario Outline: CategorySubSplashPage - Verify COACH Left Nav functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    And I navigate to COACH "<leftnav>"
    Then I verify the basic attributes of COACH browse page
#    Then I should see Narrow by link ## retired from 16K
  Examples:
    | leftnav       |
    | Women's Shoes |
    | Handbags      |
    | Men           |
    # Notes:
    # Do full validation - should not display any broken image or link
    # COACH left nav is different than regular leftnav
    # Verify NARROW BY, COACH HANDBAGS & WALLETS appears

  @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage - Verify COACH Color Swatches functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify that color swatches on Coach SubSplash Page are displayed

     # Notes:
    # Just verify that at-least more than 5 products have color swatches

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario Outline: CategorySubSplashPage - Verify COACH Sort By functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    And I navigate to COACH "<leftnav>"
    Then I verify that the Sort By displayed with all options
  Examples:
    | leftnav       |
    | Women's Shoes |
    | Handbags      |

    # Notes:
    # Just verify sort by options are displayed

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario Outline: CategorySubSplashPage - Verify Sort By Customer Top Rated functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    And I select "<sort_option>" in sort by drop down
    Then I verify that the Sort By "<sort_option>" functionality
  Examples:
    | sort_option        |
    | Customer Top Rated |
    | Price (low-high) |
    | Price (high-low) |
    | Best Sellers |
    | New Arrivals |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @defect_ECOMSST-50029
  Scenario Outline: CategorySubSplashPage - Verify COACH Left Nav Narrow By functionality in DOMESTIC mode
    Given I am on SubSplash page for "COACH" under "HANDBAGS"
    Then I verify the Left Nav Narrow By "<type>" functionality
  Examples:
    | type           |
    #| STYLE             |
    | Item Type      |
    | Sales & Offers |
#    | Size       |
#    | Material   |
    | Price          |
#    | Promotions |
#    | More Ways To Shop |
#   # | IN-STORE PICKUP   |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links after applying the facet
    # Verify that the number of products displayed matches with the count displayed on left nav
    # Verify only STYLE, WALLET TYPE and SIZE facets are expanded, verify other facets are collapsed

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario: CategorySubSplashPage - Verify Left Nav View ALL COACH functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify the facets on Coach left nav
    # Notes:
    # Verify that link lands back on COACH brand shop page

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @please_automate @mode_domestic
  Scenario: CategorySubSplashPage - Verify COACH Next Pages functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify the next page functionality
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links
    # after clicking next page

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_low @mode_domestic
  Scenario: CategorySubSplashPage - Verify COACH Items text functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify the items text
    # Notes:
    # Verify that xxx items in COACH Handbags & Wallets is displayed


  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario: CategorySubSplashPage - Verify COACH RVI & Social Icons & Tag Cloud functionality is NOT displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify that RVI & Social Icons are NOT displayed
    # Notes:
    # Recently Viewed is not displayed
    # Social Shopping/Polling Widget is not displayed
    # Tag Cloud is not displayed


  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario: CategorySubSplashPage - Verify COACH Product thumbnail functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify the Coach Product thumbnail
    # Notes:
    # Verify that image, color swatch, title text, price, outline is displayed
    # Choose a random product


  #TBD - add scenarios for iship mode
  #TBD - add scenarios for registry mode