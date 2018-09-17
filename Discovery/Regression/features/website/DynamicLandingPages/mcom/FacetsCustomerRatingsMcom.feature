#Author: Discovery QE

Feature: Verifying Customer Ratings Facets in DynamicLanding Page

############################### ALL MODES ##########################################################
  
  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that the ratings in product thumbnail matches the first facet selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that the ratings in product thumbnail matches the facet selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    When I select 2 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify deselecting of ratings from breadcrumb
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    When I remove the selected facet from the breadcrumb
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify ratings choice is retained when paginated
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I navigate to the last page
    Then I verify that the products are displayed with the selected rating
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify clear all for customer rating gacet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 2 rating in the "Customer Ratings" facet
    And I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that the quick peek is showing the selected rating
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I verify that the product rating in quick peek
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify that the PDP is showing the selected rating
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I select random product from thumbnail grid
    Then I verify that the product rating in PDP
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry - Verify facet de-selection with check box under Customer Ratings facet section
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with customer rating facet
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the product count is updated
    When I deselect the Customer Ratings from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |