#Author: Discovery QE
#Date Created: 12/16/2016

Feature: Verifying Price Facets in Search Results Page


  ############################### ALL MODES ##########################################################

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the selected price
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected price
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first two price in the Price facet
    Then I verify that products are filtered with selected price facet values
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify unselecting of price from breadcrumb
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify price choice is retained when paginated
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I navigate to the last page
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting a price from overlay displays all the products
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that the product count is updated
    When I deselect the Price from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Jeans       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all for price facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first two price in the Price facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count for price facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first two price in the Price facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

    @artifact_navapp @domain_discovery @priority_high @mode_Domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the price one by one from breadcrumbs displays products accordingly
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select 3 price in the "Price" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first price facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second price facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last price facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
#Note : Verify that the product assortment is getting changed after the deselection of each price
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original for price facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select 3 facets in the "Price" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Then I verify that all of the products are displayed
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match for price facet
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select the first price in the Price facet
    Then I verify that product count for the selected Price in the overlay and results match
 #Note : Hovering on a color in the color facet will provide the product count for that color.
 #Note : The product count can be off by 2
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      #| Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the quick peek is showing the selected price
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select the quick peek of random product
    Then I verify that the product price falls in the selected price range on quickview
    Examples:
      | shopping_mode | search_text |
      | Domestic      | jeans       |
      | Registry      | Plates      |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the PDP is showing the selected price
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select random product from thumbnail grid
    Then I verify that the product price falls in the selected price range on pdp
    Examples:
      | shopping_mode | search_text |
      | Domestic      | jeans       |
      | Registry      | Plates      |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify multi level pricing for price facet
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    Then I verify that multi level pricing is present in the results
#Note : Verify that couple of products are having WAS or REG or SALE price
    Examples:
      | shopping_mode | search_text |
      | Domestic      | dresses     |
      | Registry      | bedding     |
      #| Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the price selection is retained after selecting 3 column grid for price facet
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select "3" Column Grid icon
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | search_text |
      | SITE          | dresses     |
      | Registry      | Plates      |
     #| Iship         | pants       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the price selection is retained after selecting 4 column grid for price facet
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select "4" Column Grid icon
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | search_text |
      | SITE          | coats       |
      | Registry      | Plates      |
     #| Iship         | pants       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the price selection is retained after selecting 120 item count option for price facet
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select "3" Column Grid icon
    And I filter the result set to show "120" items
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | search_text |
      | SITE          | coats       |
      | Registry      | Plates      |
     #| Iship         | pants       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the price selection is retained after selecting ALL item count option for price facet
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I filter the result set to show "All" items
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | search_text |
      | SITE          | coats       |
      | Registry      | Plates      |
     #| Iship         | pants       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the error message is shown on selecting max price range 1$ for price facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I select 1 for max option
    Then I verify that the error message is shown
    Examples:
      | shopping_mode | keyword |
      | Domestic      | dress   |
      | Registry      | plates  |
      #| Iship         | jeans   |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the custom price range works
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | dinning  |
      #|Iship        |Jeans      |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that collapsing/expanding the price facet retains selection
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    When I "collapse" the "Price" facet on left nav
    Then I verify that products are filtered with selected price facet value
    When I "expand" the "Price" facet on left nav
    Then I verify that only custom price facet is selected from price facet section
    Examples:
      | shopping_mode | search_text |
      | Domestic      | coats       |
      | Registry      | Plates      |
      #|Iship        |Jeans      |