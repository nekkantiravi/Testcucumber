#Author: Discovery QE
#Date Created: 12/16/2016

Feature: Verifying Color Facets in DynamicLanding Page


  ############################### ALL MODES ##########################################################

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that color swatch is highlighted based on the selected color
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry|Iship - Verify that products are displayed based on the selected color
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry|Iship - Verify that color swatches on product are highlighted when 2 colors are selected
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected colors in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that products are displayed based on the 2 selected colors
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected colors
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
  #Notes: may need to check with fcc response

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify unselecting of color from overlay
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify that deselecting a color from overlay displays all the products
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I deselect the Color from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify clear all for color facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first two color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that the quick peek is highlighting the selected color
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I select the quick peek of that product
    Then I verify that the selected color is highlighted in the color swatches on quick view
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that the PDP is highlighting the selected color
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I navigate to PDP of that product
    Then I verify that the selected color is highlighted in the color swatches on pdp
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry|Iship - Verify that the Master Quick View is highlighting the selected color
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I select the quick peek of that product
    Then I verify that the selected color is highlighted in the color swatches on quick view
    Examples:
      | mode     | url                                     |
      | iship    | /shop/featured/bath-towles              |
      | domestic | /shop/featured/bath-towles              |
      | registry | /shop/featured/bath-towles?mode=wedding |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry|Iship - Verify that Color Swatches specific jumbo swatch on top of product Thumbnail for Master Product
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    When I select the first color in the Color facet
    And I select a product having jumbo swatches
    Then I should see jumbo swatch on top of thumbnail image for product
    Examples:
      | mode     | url                                     |
      | domestic | /shop/featured/bath-towles              |
      | iship    | /shop/featured/bath-towles              |
      | registry | /shop/featured/bath-towles?mode=wedding |
