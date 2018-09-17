Feature: Facet Brand verification on Category Browse Page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify filtering products with brand facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify error message display with brand facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I search for "unavailable brand" keyword in brand facet section
    Then I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run @xbrowser_browse
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify 'X' icon in search box with brand facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I search for 'available brand' keyword in brand facet section
    Then I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify facet selection persistence with special characters facet
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I search for "<brand_name>" keyword in brand facet section
    And I select "<brand_name>" facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      |shopping_mode|Category_id  |brand_name   |
      |Domestic     |30522        | Estée Lauder|
      |Registry     |53629        | Nambé       |
      |Iship        |53629        | Nambé       |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify search box does not appear under Brand facet in browse page when no of brands is 10 or less
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that "Brand" facet is listed on left nav
    And I verify that number of Brands displayed under brand facet should be less than or equal to 10
    And I verify that search box is not displayed under Brand facet
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Minions       | Kids    |
      | Registry      | Luggage Sets  | Luggage |
      | Iship         | Minions       | Kids    |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify Brand facet (filtering by character) in browse page when no of brands is more than 10
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I verify that "Brand" facet is listed on left nav
    And I type a character "'" in brand search box
 # Enter a character that is not the begining of the any brand
    Then I verify that error message 'No brands match your search.' is not displayed below search box
    And I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that search filter is not applied
    When I append first character in search box with other character(s) "'s" under brand facet
    Then I verify that brand facets is displayed according to the text contains "s" entered in brand search box
    And I verify that characters "'s" is highlighted in the displayed brands under brand facet
    And I verify that search box should contain character(s) "'s" under brand facet
    And I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    And I verify that 'X' icon is not displayed in search box under brand facet section
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 71247       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify Brand facet (filtering by character) in browse page when no of brands is more than 10
    Given I am on CategoryBrowsePage for "7502" category id in <shopping_mode> mode
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
      | Registry      |
      | Iship         |

  @snbc_comp @use_regression @use_dsv @artifact_navapp @domain_discovery @priority_high @mode_domestic
  Scenario: BrowsePage - Verify Brand Search box in Left Navigation in DOMESTIC mode
    Given I am on CategoryBrowsePage for "7502" category id in Domestic mode
    And I clear existing class variable data to avoid data issues
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of All Brands is collapsed
    When I type a character "C" in brand search box
    Then subfacet header "All Brands" should be expanded under Brand facet
    And I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that brand facets is displayed according to the text contains "C" entered in brand search box
    And I verify that characters "C" is highlighted in the displayed brands under brand facet
    When I select the first Brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify error message display when no brand matches with the entered searched term in browse page in DOMESTIC and Registry mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I verify that "Brand" facet is listed on left nav
    When I collect total brands under brand facet
    And I type a character "4" in brand search box
    Then I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that search filter is not applied
    When I append first character in search box with other character(s) "TX" under brand facet
    Then I verify that the brands are not displayed under Featured Brands and All Brands
    And I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario: CategoryBrowsePage - Domestic - Verify Brand Facet for Browse Page
    Given I am on CategoryBrowsePage for "5449" category id in Domestic mode
    And I clear existing class variable data to avoid data issues
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of Featured Brands is expanded
    And I verify that default state of All Brands is collapsed
    And I verify that number of Brands displayed under brand facet should be less than or equal to 10
    When I select "collapse" button on left of "Featured Brands"
    Then I verify that "Featured Brands" got "collapsed"
    When I select "expand" button on left of "Featured Brands"
    Then I verify that "Featured Brands" got "expanded"
    When I select "expand" button on left of "All Brands"
    Then I verify that "All Brands" got "expanded"
    And I verify that brands are duplicated within Featured Brands
    And I verify that sequencing of all brand values under All Brands is alpha numeric

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic - Verify Brand facet list when searched with standard characters in brand facet search box
    Given I am on CategoryBrowsePage for "5449" category id in Domestic mode
    And I clear existing class variable data to avoid data issues
    Then I verify that search box is displayed under Brand facet
    When I type a character "<search_char>" in brand search box
    Then I verify that the search results also list special characters
    Examples:
      | search_char |
      | e           |
      | n           |
      | o           |
      | 2           |
      | a           |
      | E           |
      | U           |
      | u           |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that products are displayed based on the selected brand
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 71247       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_low
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected brands
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first two brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify brand selection is retained when paginated
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first brand in the Brand facet
    And I navigate to the last page
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify facet de-selection with check box under Brand facet section
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I deselect the Brand from the overlay
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify clear all
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first two brand in the Brand facet
    And I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify clear all and product count
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that deselecting the brand one by one from breadcrumbs displays products accordingly
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 3 brand in the "Brand" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first brand facet from the breadcrumb
    Then I verify that the product count is updated
    When I remove second brand facet from the breadcrumb
    Then I verify that the product count is updated
    When I remove last brand facet from the breadcrumb
    Then I verify that the product count is updated
  #Note : Verify that the product assortment is getting changed after the deselection of each brand
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that product counts in overlay and results match
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select the first brand in the Brand facet
    Then I verify that product count for the selected Brand in the overlay and results match
  #Note : The product count can be off by 2
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario: CategoryBrowsePage - Domestic - Verify the visibility of search box on the Designer Facet
    Given I am on CategoryBrowsePage for "11221" category id in Domestic mode
    And I clear existing class variable data to avoid data issues
    And I verify that search box is displayed under Brand facet
    When I search for "Jeans"
    And I verify that search box is displayed under Brand facet
    When I search for "Pants"
    And I verify that search box is displayed under Brand facet

  @domain_discovery @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario: CategoryBrowsePage - Iship - Verify the visibility of search box on the Designer Facet
    Given I am on CategoryBrowsePage for "11221" category id in Iship mode
    And I clear existing class variable data to avoid data issues
    And I verify that search box is displayed under Brand facet
    When I search for "Jeans"
    And I verify that search box is displayed under Brand facet
    When I search for "Pants"
    And I verify that search box is displayed under Brand facet

  @domain_discovery @mode_registry @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario: CategoryBrowsePage - Registry - Verify the visibility of search box on the Designer Facet
    Given I am on CategoryBrowsePage for "53629" category id in Registry mode
    And I clear existing class variable data to avoid data issues
    And I verify that search box is displayed under Brand facet
    When I search for "Cookware"
    And I verify that search box is displayed under Brand facet
    When I search for "Mugs"
    And I verify that search box is displayed under Brand facet
