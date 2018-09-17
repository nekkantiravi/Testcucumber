Feature: Facet verification on Search Landing Page

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that color swatch is highlighted based on the selected color in ALL modes
    Given I am on SearchResultsPage for "red green plates" in <Site_Mode> mode
    When I select the first color in the Color facet
    And I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @use_manual @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the selected color in ALL modes
    Given I am on SearchResultsPage for "red green plates" in <shopping_mode> mode
    When I select the first color in the Color facet
    Then I verify that the product thumbnails are displayed with the selected color
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that color swatches on product are highlighted when 2 colors are selected in ALL modes
    Given I am on SearchResultsPage for "red green plates" in <shopping_mode> mode
    When I select the first two colors in the Color facet
    And I select a product having color swatches
    Then I verify that the selected colors in the color swatch is highlighted
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @use_manual @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected colors in ALL modes
    Given I am on SearchResultsPage for "red green plates" in <shopping_mode> mode
    When I select the first two colors in the Color facet
    Then I verify that the product thumbnails are displayed with the selected colors
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  #Notes: may need to check with fcc response

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify color swatch is highlighted when paginated in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first color in the Color facet
    And I navigate to the last page
    And I select a product having color swatches
    Then I verify that the selected color in the color swatch is highlighted
    Examples:
      | shopping_mode | keyword   |
      | Domestic      | black pant|
      | Registry      | plates    |
      | Iship         | black pant|

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting a color from overlay displays all the products in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |


  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first two color in the Color facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @use_manual @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the color one by one from breadcrumbs displays products accordingly in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select 3 facets in the "Color" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first color facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second color facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last color facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
#Note : Verify that the product assortment is getting changed after the deselection of each color
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select 3 facets in the "Color" facet
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

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the selected price in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
     # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected price in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first two price in the Price facet
    Then I verify that products are filtered with selected price facet values
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
     # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify unselecting of price from breadcrumb in ALL modes
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
     # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify price choice is retained when paginated in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I navigate to the last page
    Then I verify that products are filtered with selected price facet value
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
     # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting a price from overlay displays all the products in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
     # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all in ALL modes
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
     # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count in ALL modes
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
      # | Iship      | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic @use_manual @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the price one by one from breadcrumbs displays products accordingly in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the product count is displayed
    When I select 3 facets in the "Price" facet
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
    # | Iship      | Dress       |


  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the quick peek is highlighting the selected color in ALL modes
    Given I am on SearchResultsPage for "red green plates" in <shopping_mode> mode
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I select the quick peek of that product
    Then I verify that the selected color is highlighted in the color swatches on quick view
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original in ALL modes
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

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filter products when we select any one random facet value
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |

     # Notes:
     # Select facet value from any section other than Pick-up InStore(BOPS), Size(GROUPED FACET) facet sections.
     # Verify product count is updated on top of thumbnail grid.
     # Verify breadcrumb section displayed with single facet value.
     # Verify product count in thumbnail grid matched with selected facet value product count.
     # Verify pagination updated as per current product count and defaulted to first page

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filter products when we select multiple facet values
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |
    # Notes:
    # Select facet value from any section other than Pick-up InStore(BOPS), Size(GROUPED FACET) facet sections.
    # Verify product count is updated on top of thumbnail grid.
    # Verify all selected facet values displayed in facet breadcrumb section.
    # Verify product count in thumbnail grid matched with selected item count option or total product count.
    # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
    # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry -Verify filtering products with size facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select multiple facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword |
      | Domestic      | pink dress|
      | Registry      | luggage  |
      | Iship         | pink dress   |
 # Notes:
 # Select multiple facet values from size facet section
 # Verify 'CLEAR ALL' button displaying on top of facet
 #section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values
 # Verify 'X' icon displayed for size facet section.
 # Verify pagination updated as per current product count and
 #defaulted to first page.

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with color facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I select multiple facet value from "Color" facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Color Facet - Verify that the PDP is highlighting the selected color in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select the first color in the Color facet
    And I select a product having color swatches
    When I navigate to PDP of that product
    Then I verify that the selected color is highlighted in the color swatches on pdp
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |


  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify unavailable facet values are removed after selecting any facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that remaining facets are updated its facet values and product count availability
    Examples:
      | shopping_mode |keyword|
      | Domestic          |jeans      |
      | Registry      |Plates     |
      | Iship         |dresses    |


  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with single price facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "single" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | sheets  |
  # Notes:
  # Select facet value from price facet section.
  # Verify all products are displayed only withing selected price range only
  #  (Ex: if we select $50-$100 price facet value, then all product prices should be within this range)
  # Verify selected price facet value displayed in facet breadcrumb section.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify pagination updated as per current product count and defaulted to first page.

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select the first color in the Color facet
    Then I verify that product count for the selected Color in the overlay and results match
   #Note : Hovering on a color in the color facet will provide the product count for that color.
   #Note : The product count can be off by 2
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select the first price in the Price facet
    Then I verify that product count for the selected Price in the overlay and results match
   #Note : Hovering on a color in the color facet will provide the product count for that color.
   #Note : The product count can be off by 2
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
   #   | Iship |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the quick peek is showing the selected price in ALL mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select the quick peek of random product
    Then I verify that the product price falls in the selected price range on quickview
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | sheets  |
     # | Iship      | coats   |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @please_automate @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the PDP is showing the selected price in ALL mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select the first price in the Price facet
    And I select random product member product from thumbnail grid
    Then I verify that the product price falls in the selected price range on pdp
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | sheets  |
     # | Iship      | coats   |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify multi level pricing in ALL mode
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select the first price in the Price facet
    Then I verify that multi level pricing is present in the results
    #Note : Verify that couple of products are having WAS or REG or SALE price
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
   #   | Iship |

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify products are filtered from each facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select 'single' facet value from 'each' facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic |
      | Iship |
      | Registry |
    # Notes:
    # Select facet value from each of the facet sections.
    # Verify product count is updated on top of thumbnail grid.
    # Verify breadcrumb section displayed with single facet value.
    # Verify product count in thumbnail grid matched with selected facet value product count.
    # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
    # Verify pagination updated as per current product count and defaulted to first page.

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the selected colors appears on the top in the color facet overlay in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select 3 facets in the "Color" facet
    Then I verify that the selected Color appears on top
     #And I verify that the dividing line appears
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | sheets  |
      | Iship         | jeans   |

  @artifact_navapp @domain_discovery @priority_high @mode_Domestic  @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the selected Price appears on the top in the color facet overlay in ALL modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select 3 facets in the "Price" facet
    Then I verify that the selected Price appears on top
     #And I verify that the dividing line appears
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | sheets  |
    #  | Iship         |dresses    |

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify canonical tags after multiple facet selections
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    And I verify that canonical tag contains facet value of same facet
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    And I verify that facet values in canonical tag with alpha order for "<search_text>"
    Examples:
      | shopping_mode |search_text|
      | Domestic      |jeans      |
      | Registry      |Plates     |
      | Iship         |dresses    |
      # Notes:
      # Verify canonical tags after multiple facet selections


  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify products are filtered for faceted category
    Given I am on SearchResultsPage for "Armani Collezioni" in <shopping_mode> mode
    Then I verify that facet breadcrumb are listed as per faceted url
    And I verify that products are filtered as per faceted url
    Examples:
      |shopping_mode |
      | Domestic |
      | Iship |
      # Notes:
      # Verify products are filtered for faceted category

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with 'pagination' selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select 'random' page number from pagination
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous pagination selection persist
    Examples:
      | shopping_mode |
      | Domestic |
      | Iship |
      | Registry |
 # Notes:
 # Verify facet selection persists when we navigate back from PDP page
