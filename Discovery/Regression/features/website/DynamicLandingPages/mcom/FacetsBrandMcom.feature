Feature: Facet Brand verification on DynamicLanding Page

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry - Verify filtering products with brand facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify error message display with brand facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    When I search for "unavailable brand" keyword in brand facet section
    Then I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic - Verify 'X' icon in search box with brand facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    And I search for 'available brand' keyword in brand facet section
    Then I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline:  DynamicLandingPage - Domestic - Verify Brand facet (filtering by character) in dlp page when no of brands is more than 10
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of All Brands is collapsed
    When I type a character "C" in brand search box
    Then subfacet header "All Brands" should be expanded under Brand facet
    And I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that brand facets is displayed according to the text contains "C" entered in brand search box
    And I verify that characters "C" is highlighted in the displayed brands under brand facet
    And I verify that search box should contain character(s) "C" under brand facet
    And I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    And I verify that 'X' icon is not displayed in search box under brand facet section
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that deselecting a brand from overlay displays all the products
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I deselect the Brand from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify clear all for brand facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first two brand in the Brand facet
    And I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify clear all and product count
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that deselecting the brand one by one from breadcrumbs displays products accordingly
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 brand in the "Brand" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first brand facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second brand facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last brand facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
  #Note : Verify that the product assortment is getting changed after the deselection of each brand
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that product counts in overlay and results match
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with brand facet
    And I clear existing class variable data to avoid data issues
    When I select the first brand in the Brand facet
    Then I verify that product count for the selected Brand in the overlay and results match
  #Note : The product count can be off by 2
    Examples:
      | shopping_mode |
      | Domestic      |
