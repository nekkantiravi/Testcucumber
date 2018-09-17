#Author: Discovery QE

Feature: Verifying Customer Ratings Facets in category browse page

############################### ALL MODES ##########################################################
  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the ratings in product thumbnail matches the first facet selection
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the ratings in product thumbnail matches the facet selection
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 2 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify deselecting of ratings from breadcrumb
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the products are displayed with the selected rating
    When I remove the selected facet from the breadcrumb
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify ratings choice is retained when paginated
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 1 rating in the "Customer Ratings" facet
    And I navigate to the last page
    Then I verify that the products are displayed with the selected rating
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that deselecting a rating from overlay displays all the products
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 1 rating in the "Customer Ratings" facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @xbrowser_browse
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify clear all
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 2 rating in the "Customer Ratings" facet
    And I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify clear all and product count for customer top rated facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 2 rating in the "Customer Ratings" facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that deselecting the rating one by one from breadcrumbs displays products accordingly
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 rating in the "Customer Ratings" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
  #Note : Verify that the product assortment is getting changed after the deselection of each rating
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 rating in the "Customer Ratings" facet
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    And I verify that the product count is updated
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that product counts in overlay and results match
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I select the first rating in the Customer Ratings facet
    And I verify that product count for the selected CUSTRATINGS in the overlay and results match
  #Note : The product count can be off by 2
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the quick peek is showing the selected rating
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first rating in the Customer Ratings facet
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I verify that the product rating in quick peek
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the PDP is showing the selected rating
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first rating in the Customer Ratings facet
    And I select random product from thumbnail grid
    Then I verify that the product rating in PDP
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  #Version One: B-81654
  @mode_domestic @release_17J @priority_medium @domain_discovery @project_UFT @use_regression
  Scenario Outline: CategoryBrowsePage - Domestic - Verify that the customer review is displayed for Chanel brands
    Given I visit the web site as a guest user
    And I clear existing class variable data to avoid data issues
    When I navigate to the "CHANEL" browse page under "BEAUTY"
    And I select "<category>" in the subsplash page
    Then I should see "customer ratings" is displayed for the "CHANEL" brand
    Examples:
      | category          |
      | WOMEN'S FRAGRANCE |
      | GIFTS             |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Registry - Verify facet de-selection with check box under Customer Ratings facet section
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first rating in the Customer Ratings facet
    Then I verify that the product count is updated
    When I deselect the Customer Ratings from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |