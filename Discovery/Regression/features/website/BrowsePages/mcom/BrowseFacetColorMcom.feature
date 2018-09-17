#Author: Discovery QE
#Date Created: 08/03/2016

Feature: Verifying Color Facets in Category browse page


  ############################### ALL MODES ##########################################################

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that color swatch is highlighted based on the selected color
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @xbrowser_browse
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that products are displayed based on the selected color in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that color swatches on product are highlighted when 2 colors are selected in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected colors in the color swatch is highlighted
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected colors in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected colors
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |
  #Notes: may need to check with fcc response

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify unselecting of color from breadcrumb
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify color swatch is highlighted when paginated in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I navigate to the last page
    Then I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify facet de-selection with check box under Color facet section
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I deselect the Color from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify clear all for color facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first two color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify clear all and product count for color facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that deselecting the color one by one from breadcrumbs displays products accordingly in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 color in the "Color" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first color facet from the breadcrumb
    Then I verify that the product count is updated
    When I remove second color facet from the breadcrumb
    Then I verify that the product count is updated
    When I remove last color facet from the breadcrumb
    Then I verify that the product count is updated
    #Note : Verify that the product assortment is getting changed after the deselection of each color
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original for color facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 facets in the "Color" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that product counts in overlay and results match for color facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    Then I verify that product count for the selected Color in the overlay and results match
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the quick peek is highlighting the selected color in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I select the quick peek of that product
    Then I verify that the selected color is highlighted in the color swatches on quick view
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the PDP is highlighting the selected color
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I navigate to PDP of that product
    Then I verify that the selected color is highlighted in the color swatches on pdp
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the color swatches is highlighting the selected color after selecting 120 item count option for color facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first color in the Color facet
    And I filter the result set to show "120" items per page
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 3310        |
      | Registry      | 17799       |
      | Iship         | 3310        |