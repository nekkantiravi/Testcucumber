#Author: Discovery QE
#Date Created: 12/16/2016

Feature: Verifying Price Facets in DynamicLanding Page


  ############################### Domestic & Registry MODES ##########################################################

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that products are displayed based on the selected price
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that products are displayed based on the 2 selected price
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic - Verify unselecting of price from breadcrumb
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify price choice is retained when paginated
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    And I navigate to the last page
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that deselecting a price from overlay displays all the products
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that the product count is updated
    When I deselect the Price from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that clear all button is updating the product assortment back to original for price facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 price in the "Price" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that product counts in overlay and results match for price facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    Then I verify that product count for the selected Price in the overlay and results match
 #Note : Hovering on a color in the color facet will provide the product count for that color.
 #Note : The product count can be off by 2
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that the quick peek is showing the selected price
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    And I select the quick peek of random product
    Then I verify that the product price falls in the selected price range on quickview
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that the PDP is showing the selected price
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    And I select random product from thumbnail grid
    Then I verify that the product price falls in the selected price range on pdp
    Examples:
      | shopping_mode |
      | Domestic      |
    
  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify that the price selection is retained after selecting 120 item count option for price facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select the first price in the Price facet
    And I filter the result set to show "120" items per page
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario Outline: DynamicLandingPage - Domestic - Verify that the error message is shown on selecting max price range 1$ for price facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    And I select 1 for max option
    Then I verify that the error message is shown
    Examples:
      | shopping_mode |
      | Domestic      |
    