Feature: Facet Brand verification on Search Landing Page

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with brand facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select multiple facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |keyword    |
      | Domestic      |dresses    |
      | Registry      |Plates     |
      | Iship         |dresses    |

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify error message display with brand facet
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I search for "unavailable brand" keyword in brand facet section
    Then I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify 'X' icon in search box with brand facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I search for 'available brand' keyword in brand facet section
    Then I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | shopping_mode |keyword    |
      | Domestic      |dresses    |
      | Registry      |Plates     |
      | Iship         |dresses    |

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with special characters facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "3" Column Grid icon
    Then I verify that the product count is displayed
    When I search for "<brand_name>" keyword in brand facet section
    And I select "<brand_name>" facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      |shopping_mode|keyword   |brand_name   |
      |Domestic     |eyes      | Estée Lauder|
      |Registry     |Plates    | Nambé       |
      |Iship        |Plates    | Nambé       |

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_2 @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify search box does not appear under Brand facet in SLP when no of brands is 10 or less
    Given I am on SearchResultsPage for "bread knife" in <shopping_mode> mode
    And I verify that "Brand" facet is listed on left nav
    And I verify that number of Brands displayed under brand facet should be less than or equal to 10
    And I verify that search box is not displayed under Brand facet
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |
    # Notes:
    # Ensure that you search for a term which has ten or less than 10 brands under brand facet

  @use_regression @artifact_navapp @domain_discovery @priority_medium @use_regression_2 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Brand facet (filtering by character) in SLP when no of brands is more than 10
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
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
      | shopping_mode | keyword   |
      | Domestic      | dresses   |
      | Iship         | dresses   |
      | Registry      | cookware  |

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @project_snb
  Scenario Outline:  SearchResultsPage - Domestic|Iship|Registry - Verify Brand facet (filtering by character) in SLP when no of brands is more than 10 in DOMESTIC,REGISTRY and ISHIP mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of All Brands is collapsed
    When I type a character "L" in brand search box
    Then subfacet header "All Brands" should be expanded under Brand facet
    And I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that brand facets is displayed according to the text contains "L" entered in brand search box
    And I verify that characters "L" is highlighted in the displayed brands under brand facet
    And I verify that search box should contain character(s) "L" under brand facet
    And I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    And I verify that 'X' icon is not displayed in search box under brand facet section
    Examples:
      | shopping_mode | keyword |
      | Domestic      | jeans   |
      | Iship         | plates  |
      | Registry      | cookware|

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_2 @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify error message display when no brand matches with the entered searched term in SLP in DOMESTIC and Registry mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that "Brand" facet is listed on left nav
    When I collect total brands under brand facet
    And I type a character "4" in brand search box
    Then I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that search filter is not applied
    When I append first character in search box with other character(s) "TX" under brand facet
    Then I verify that the brands are not displayed under Featured Brands and All Brands
    And I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | shopping_mode | keyword |
      | Domestic      | pants   |
      | Registry      | flatware|
      | Iship         | pants   |

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_2 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Brand Facet for Search Landing Page
    Given I am on SearchResultsPage for "dress" in Domestic mode
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

  @use_regression @priority_low @artifact_navapp @domain_discovery @mode_domestic @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify Brand facet list when searched with standard characters in brand facet search box
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that search box is displayed under Brand facet
    When I type a character "<search_char>" in brand search box
    Then I verify that the search results also list special characters
    Examples:
      |search_char|
      |e|
      |n|
      |o|
      |2|
      |a|
      |E|
      |U|
      |u|

  @use_regression @artifact_navapp @domain_discovery @priority_low @release_13J @mode_iship @use_regression_1 @project_snb
  Scenario: SearchResultsPage - Domestic - Verify the visibility of search box on the Designer Facet
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I verify that search box is displayed under Brand facet
    When I navigate to the "Jeans" browse page under "Women"
    And I verify that search box is displayed under Brand facet
    When I navigate to the "Activewear" browse page under "Women"
    And I verify that search box is displayed under Brand facet

  @use_regression @artifact_navapp @domain_discovery @priority_low @release_13J @mode_iship @use_regression_1 @project_snb
  Scenario: SearchResultsPage - Iship - Verify the visibility of search box on the Designer Facet
    Given I am on SearchResultsPage for "jeans" in Iship mode
    And I verify that search box is displayed under Brand facet
    When I navigate to the "Jeans" browse page under "Women"
    And I verify that search box is displayed under Brand facet
    When I navigate to the "Activewear" browse page under "Women"
    And I verify that search box is displayed under Brand facet

  @use_regression @artifact_navapp @domain_discovery @priority_low @release_13J @mode_iship @use_regression_1 @project_snb
  Scenario: SearchResultsPage - Registry - Verify the visibility of search box on the Designer Facet
    Given I am on SearchResultsPage for "cookware" in Registry mode
    And I verify that search box is displayed under Brand facet
    When I navigate to the "Cookware" browse page under "KITCHEN"
    And I verify that search box is displayed under Brand facet
    When I navigate to the "Mugs" browse page under "DINING"
    And I verify that search box is displayed under Brand facet

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the selected brand
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    When I select the first brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that products are displayed based on the 2 selected brands
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    When I select the first two brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify brand selection is retained when paginated
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    When I select the first brand in the Brand facet
    And I navigate to the last page
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting a brand from overlay displays all the products
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I deselect the Brand from the overlay
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first two brands in the Brand facet
    And I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the brand one by one from breadcrumbs displays products accordingly
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
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
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match
    Given I am on SearchResultsPage for "cookware" in <Site_Mode> mode
    When I select the first brand in the Brand facet
    Then I verify that product count for the selected Brand in the overlay and results match
  #Note : The product count can be off by 2
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |