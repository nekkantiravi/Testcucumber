# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify SEO tags on CatSplash Page - DOMESTIC, ISHIP and REGISTRY modes

  #http://mingle/projects/seo/cards/4641 & SEO_4641

  @use_regression @artifact_navapp @domain_discovery @priority_high @release_13J @use_regression_3 @mode_domestic @bcom_seo
   Scenario Outline: CategorySplashPage - Verify rel="canonical" tag is added in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to <pagetype>
    Then I "<condition>" see canonical tag pointing to the corresponding URL added in the header section
  Examples:
    | pagetype             | condition |
    | category splash page | should    |

  @use_regression @artifact_navapp @domain_discovery @priority_high @release_13J  @mode_registry @use_regression_3 @deprecated
  Scenario: Category Splash & Sub Splash page - Verify rel="canonical" tag is added in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to wedding registry by clicking registry link
    When I navigate to random category splash page
    Then I "should" see canonical tag pointing to the corresponding URL added in the header section

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_3 @mode_domestic
   Scenario Outline: CategorySplashPage - CategorySplashPage - Verify alternative tags are added for mobile URLs on desktop pages for guest user in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to <pagetype>
    Then I "<condition>" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.bloomingdales.com/..."> tag pointing to the corresponding URL added in the header section
  Examples:
    | pagetype             | condition |
    | category splash page | should    |
    | subsplash page       | should    |


  @use_regression @artifact_navapp @domain_discovery @priority_high @mode_registry @use_regression_3 @deprecated
  Scenario: CategorySplashPage - Verify alternative tags are added on REGISTRY PDP and Category Browse Pages in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to wedding registry by clicking registry link
    When I navigate to random category splash page
    Then I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.bloomingdales.com/..."> tag pointing to the corresponding URL added in the header section

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_3 @mode_domestic @bcom_seo
  Scenario Outline: CatSplashPage - Verify that Category Splash page have a char-count > 70 but < 170 for the meta description in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<top_level_fobs>" category page
    Then I verify that Meta Description should have between 70 - 170 characters
  Examples: Top Level FOBs
    | top_level_fobs        |
    | DESIGNERS             |
    | EDITORIAL             |
    | WOMEN                 |
    | SHOES                 |
    | HANDBAGS              |
    | JEWELRY & ACCESSORIES |
    | BEAUTY                |
    | MEN                   |
    | KIDS                  |
    | HOME                  |
    | GIFTS                 |
    | SALE                  |
    | THE REGISTRY          |

  @use_regression @artifact_navapp @domain_discovery @priority_high @bcom_title_tags @use_regression_3 @mode_domestic @bcom_seo
  Scenario Outline: CategorySplashPage - Verify that DESIGNERS Category Splash page have a char-count > 13 but < 75 and Bloomingdale's in the page title in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<top_level_fobs>" category page
    Then I verify that Page Title should have between 13 - 75 characters
    And I verify that the page title contains "ALL DESIGNERS"
  Examples: Top Level FOBs
    | top_level_fobs        |
    | DESIGNERS             |

  @use_regression @artifact_navapp @domain_discovery @priority_high @bcom_title_tags @use_regression_3 @mode_domestic @bcom_seo
  Scenario Outline: CategorySplashPage - Verify that Category Splash page have a char-count > 13 but < 75 and Bloomingdale's in the page title in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<top_level_fobs>" category page
    Then I verify that Page Title should have between 13 - 75 characters
    And I verify that the page title contains "Bloomingdale's"
  Examples: Top Level FOBs
    | top_level_fobs        |
    | EDITORIAL             |
    | WOMEN                 |
    | SHOES                 |
    | HANDBAGS              |
    | JEWELRY & ACCESSORIES |
    | BEAUTY                |
    | MEN                   |
    | KIDS                  |
    | HOME                  |
    | GIFTS                 |
    | SALE                  |
    | THE REGISTRY          |

  @use_regression @artifact_navapp @domain_discovery @priority_high @release_13J @use_regression_3  @mode_domestic
  Scenario: CategorySplashPage - Verify a suppressed Category Page is still active in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    When I navigate to the bookmarked url:
      | BCOM | /shop/makeup-perfume-beauty/lancome?id=6864 |
    Then I ensure to see the suppressed category message is displayed

