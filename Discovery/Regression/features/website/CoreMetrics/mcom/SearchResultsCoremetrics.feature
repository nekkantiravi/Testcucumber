Feature: To verify coremetric tags in search results page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Page view tags for search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Product view tags for search results page member product
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Product view tags for search results page master product
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I select a random master product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword |
      | domestic | plates  |
      | iship    | plates  |
      | registry | plates  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for back to top button in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And  I select 'back to top' button
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for sort by in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I select "<sort by option>" in sort by drop down
    Examples:
      | mode     | keyword   | sort by option     |
      | domestic | jeans     | Price: Low to High |
      | iship    | shoes     | Best Sellers       |
      | registry | electrics | New Arrivals       |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And  I select 'single' facet value from 'any' facet section
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Customer Ratings facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Customer Ratings" facet is listed on left nav
    And I select the random value in the "Customer Ratings" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Product view tags when navigated to pdp with Brand facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Brand" facet is listed on left nav
    And  I select the random value in the "Brand" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Product view tags when navigated to pdp with Price facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Price" facet is listed on left nav
    And  I select the random value in the "Price" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Color facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    Then I verify that "Color" facet is listed on left nav
    And I select the random value in the "Color" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | shoes   |
      | registry | plates  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Size facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    When I select already selected facet sub header
    Then I verify that facet sub header gets "Collapsed"
    And I verify that the facet values are not displayed
    When I select "single" facet value from "Size" facet section
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | shoes   |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for 3 column grid selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I select "3" Column Grid icon
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for 4 column grid selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I select "4" Column Grid icon
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Page view tags for grid persistance in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I select a random member product
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for show previous button selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I filter the result set to show "All" items
    And I select a product from section '4' on thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same section
    When I navigate to top of page
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    And I select 'Show Previous Items' button
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for Customer Ratings facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Customer Ratings" facet is listed on left nav
    And I select the random value in the "Customer Ratings" facet
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for Brand facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Brand" facet is listed on left nav
    And  I select the random value in the "Brand" facet
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for Price facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Price" facet is listed on left nav
    And  I select the random value in the "Price" facet
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | shoes     |
      | registry | electrics |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for Color facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    And I verify that "Color" facet is listed on left nav
    And I select the random value in the "Color" facet
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | shoes   |
      | registry | plates  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify Element tags for Size facet selection in search results page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I search for "<keyword>"
    Then  I should be in Search Landing page
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    When I select already selected facet sub header
    Then I verify that facet sub header gets "Collapsed"
    And I verify that the facet values are not displayed
    Then I select "single" facet value from "Size" facet section
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | shoes   |

    #*****************************  Search Result Page Coremetrics Scenarios ************************************

  @srp_domestic_guest @snbc_coremetrics
  Scenario: SearchResultsPage - Domestic - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "je" keyword in search field
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "search_result" Page

  @srp_iship_guest @test @snbc_coremetrics
  Scenario: SearchResultsPage - iShip - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "je" keyword in search field
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "search_result" Page

  @srp_registry_guest @snbc_coremetrics
  Scenario: SearchResultsPage - Registry - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I search for "plates" in search box
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "search_result" Page

  @srp_domestic_registered @snbc_coremetrics
  Scenario: SearchResultsPage - Domestic - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "je" keyword in search field
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "search_result" Page

  @srp_registry_registered @snbc_coremetrics
  Scenario: SearchResultsPage - Registry - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "plates" in search box
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "search_result" Page

   #*******************     Dynamic Landing Page Coremetrics Scenarios ********************************

  @dlp_domestic_guest @snbc_coremetrics
  Scenario: DynamicLanding Page - Domestic - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "dynamic_landing" Page

  @dlp_iship_guest @snbc_coremetrics
  Scenario: DynamicLanding Page - iship - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "iship" mode
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "dynamic_landing" Page

  @dlp_registry_guest @snbc_coremetrics
  Scenario: DynamicLanding Page - Registry - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "dynamic_landing" Page

  @dlp_domestic_registered @snbc_coremetrics
  Scenario: DynamicLanding Page - Domestic - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "dynamic_landing" Page

  @dlp_registry_registered @snbc_coremetrics
  Scenario: DynamicLanding Page - Registry - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I modify the url to get in to snbc experiment
    Then I verify that landed on "dynamic_landing" Page

# *********************  PDP via SearchResultpage Coremetrics Scenarios *************************************

  @PDP_srp_domestic_guest @snbc_coremetrics
  Scenario: ProductDisplayPage - SRP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_iship_guest @snbc_coremetrics
  Scenario: ProductDisplayPage - SRP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_registry_guest @snbc_coremetrics
  Scenario: ProductDisplayPage - SRP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I search for "plates" in search box
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_domestic_registered @snbc_coremetrics
  Scenario: ProductDisplayPage - SRP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "je" keyword in search field
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_registry_registered @snbc_coremetrics
  Scenario: ProductDisplayPage - SRP - registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "plates" in search box
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  #****************************** PDP via Dynamic Landing Page Coremetrics Scenarios      *********************************

  @PDP_dlp_domestic_guest @snbc_coremetrics
  Scenario: ProductDisplayPage - DLP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_iship_guest @snbc_coremetrics
  Scenario: ProductDisplayPage - DLP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "iship" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_registry_guest @snbc_coremetrics
  Scenario: ProductDisplayPage - DLP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_domestic_registered @snbc_coremetrics
  Scenario: ProductDisplayPage - DLP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_registry_registered @snbc_coremetrics
  Scenario: ProductDisplayPage - DLP - Registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  #*************************** QuickView via SearchResultPage Coremetrics Scenarios ********************************

  @QV_srp_domestic_guest @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage  - SRP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "je" keyword in search field
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 2 th product
    Then I verify that landed on "quick_view" Page

  @QV_srp_iship_guest @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - SRP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page

  @QV_srp_registry_guest @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - SRP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I search for "plates" in search box
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page

  @QV_srp_domestic_registered @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - SRP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "je" keyword in search field
    When I select "Jeans" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page

  @QV_srp_registry_registered @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - SRP - registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "plates" in search box
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page

#   ***********************  QuickView via Dynamic Landing Page Coremetrics Scenarios **************************************

  @QV_dlp_domestic_guest @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - DLP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 2 th product
    Then I verify that landed on "quick_view" Page

  @QV_dlp_iship_guest @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - DLP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "iship" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 2 th product
    Then I verify that landed on "quick_view" Page

  @QV_dlp_registry_guest @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - DLP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 2 th product
    Then I verify that landed on "quick_view" Page

  @QV_dlp_domestic_registered @snbc_coremetrics
  Scenario: QuickView - ProductDisplayPage - DLP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 2 th product
    Then I verify that landed on "quick_view" Page

  @QV_dlp_registry_registered @snbc_coremetrics
  Scenario: QuickView ProductDisplayPage - DLP - registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 2 th product
    Then I verify that landed on "quick_view" Page
