# Author: Discovery Regression QE Team
# Created Date: 10/10/2017

Feature: Brand Facet verification on SubSplashPage

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify filtering products with brand facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And  I select multiple facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify error message display with brand facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And I search for "unavailable brand" keyword in brand facet section
    Then I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page @discovery_daily_run
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify 'X' icon in search box with brand facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And I search for 'available brand' keyword in brand facet section
    Then I verify that 'X' icon is displayed in search box under brand facet section
    When I select 'X' icon in search box
    Then I verify that brand search box is empty
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression  @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify facet selection persistence with special characters facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I search for "<brand_name>" keyword in brand facet section
    And I select "<brand_name>" facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SubSplashPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
    Examples:
      | mode     | subcategory | category | brand_name |
      | domestic | Activewear  | WOMEN    | Style & Co |
      | iship    | Activewear  | MEN      | G-Star Raw |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship- Verify Brand facet (filtering by character) in subsplash page when no of brands is more than 10 in DOMESTIC and ISHIP mode
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
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
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Verify Brand Search box in Left Navigation in DOMESTIC and ISHIP mode
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of All Brands is collapsed
    When I type a character "C" in brand search box
    Then subfacet header "All Brands" should be expanded under Brand facet
    And I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that brand facets is displayed according to the text contains "C" entered in brand search box
    And I verify that characters "C" is highlighted in the displayed brands under brand facet
    When I select the first Brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify error message display when no brand matches with the entered searched term in subsplash page in DOMESTIC and ISHIP mode
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And I verify that "Brand" facet is listed on left nav
    When I collect total brands under brand facet
    And I type a character "4" in brand search box
    Then I verify that the brands are displayed under Featured Brands and All Brands
    And I verify that search filter is not applied
    When I append first character in search box with other character(s) "TX" under brand facet
    Then I verify that the brands are not displayed under Featured Brands and All Brands
    And I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify Brand Facet for SubSplash Page
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And I verify that "Brand" facet is listed on left nav
    And I verify that default state of Featured Brands is expanded
    And I verify that default state of All Brands is collapsed
    When I select "collapse" button on left of "Featured Brands"
    Then I verify that "Featured Brands" got "collapsed"
    When I select "expand" button on left of "Featured Brands"
    Then I verify that "Featured Brands" got "expanded"
    When I select "expand" button on left of "All Brands"
    Then I verify that "All Brands" got "expanded"
    And I verify that brands are duplicated within Featured Brands
    And I verify that sequencing of all brand values under All Brands is alpha numeric
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that products are displayed based on the selected brand
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And  I select the first brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that products are displayed based on the 2 selected brands
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select the first two brand in the Brand facet
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify brand selection is retained when paginated
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I select the first brand in the Brand facet
    And I navigate to the last page
    Then I verify that the product thumbnails are displayed with the selected brand
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify facet de-selection with check box under Brand facet section
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I deselect the Brand from the overlay
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify clear all
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select the first two brand in the Brand facet
    And I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify clear all and product count
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select the first brand in the Brand facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that deselecting the brand one by one from breadcrumbs displays products accordingly
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
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
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that product counts in overlay and results match
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select the first brand in the Brand facet
    Then I verify that product count for the selected Brand in the overlay and results match
#Note : The product count can be off by 2
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

