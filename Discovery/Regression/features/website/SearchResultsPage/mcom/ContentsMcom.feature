Feature: Contents verification on Search Landing Page

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Domestic - Verify Edit button is displayed on RVI Panel on search results page
    Given I am on SearchResultsPage for "Dresses" in Domestic mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    And I navigate to bottom of page
    And  I should see edit option inside Recently Viewed panel
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify header is displayed
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that logo is displayed and returns a 200 OK
    Then I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in SRP

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify footer is displayed
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that the footer elements are displayed
  # Notes: Verifies that footer links are all present in SRP

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify FOBs
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    And I verify that fobs are displayed and return a 200 OK


  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify Left Nav is displayed
    Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
    Then I verify that facets are listed on left nav
    Then I verify that search result page Facets displayed match with production

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Domestic - Verify Content
    Given I am on SearchResultsPage for "jeans" in DOMESTIC mode
    Then I verify that Search Results contents are displayed
  #Notes:
  #Verify you see results that match the keyword
  #Verify all images and links all return 200 OK on http party
  #Verify leftnav is displayed, sort by and next pages are displayed

    # @13g
  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic - Verify that meta keywords tag is correctly populated on slp, zero result page
    Given I am on SearchResultsPage for "<keyword>" in Domestic mode
    Then I verify that meta "keywords" tag as "<meta_keywords>"
    Examples:
      | keyword | meta_keywords                             |
      | Jeans   | jeans, shop for jeans, buy jeans, Macy's. |
      | xtyz    | xtyz, shop for xtyz, buy xtyz, Macy's.    |

     # @13g
  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic - Verify that title tag are correctly populated on slp, zero result page and preview enabled redirect pages
    Given I am on SearchResultsPage for "<keyword>" in Domestic mode
    Then I verify that the title tag is displayed as below
      | site | 3 column                                               |
      | MCOM | <keyword> - Shop for and Buy <keyword> Online - Macy's |
    Examples:
      | keyword |
      | jeans   |
      | xtyz    |

  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify that title tag are correctly populated on slp with single facet selection
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I select 'single' facet value from 'any' facet section
    Then I verify that the title tag is displayed with selected facet

  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify tag are correctly populated on slp with multiple facet selection
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that the title tag is displayed with selected facet

  @domain_discovery @priority_low @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify Search URL should not contain x and y coordinates while search is done using SEARCH button
    Given I am on SearchResultsPage for "jeans" in DOMESTIC mode
    And I verify that the URL should not contain x and y coordinates

  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that search results page google ads functionality
    Given I am on SearchResultsPage for "jeans" in <Site_Mode> mode
    And I verify GoogleAdSense functionality in search results page
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  #TestLink - MCOM-56903
  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Google ads are displayed after facet selection
    Given I am on SearchResultsPage for "knife" in <Site_Mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that google ads section is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

    #TestLink - MCOM-56903
  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Google ads are displayed on zero search result page
    Given I am on ZeroSearchResultPage for "xyztx" in <Site_Mode> mode
    And I verify that google ads section is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_low @mode_domestic @mode_registry @mode_iship @use_regression
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify bookmarked faceted URL loading without any error
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    Then I should be navigated to "search result" page
    Examples:
      | mode     | url                                                                                                                                     |
      | domestic | /shop/featured/estée-lauder-blue/Custratings,Product_department,Special_offers/3%20stars%20%26%20up,Makeup,Gift%20with%20Purchase       |
      | domestic | /shop/featured/100%25-green/Brand,Price,Sortby/Martha%20Stewart%20Collection,100%257C250,PRICE_HIGH_TO_LOW                              |
      | domestic | /shop/featured/beauty-makeup/Brand,Price,Sortby/Estée%20Lauder,0%257C19.99,NEW_ITEMS                                                    |
      | domestic | /shop/featured/beauty-makeup/Special_offers,Pageindex,Productsperpage,Sortby/Sales%20%26%20Discounts,2,120,TOP_RATED                    |
      | domestic | /shop/featured/dresses/Brand,Women_regular_size_t/G.H.%20Bass%20%26%20Co.%7CLevi%27s%7CLove%252C%20Fire,18%252C%20XXL                   |
      | registry | /shop/registry/wedding/search/Color_normal,Custratings,Special_offers/Multi,4%20stars%20%26%20up,Sales%20%26%20Discounts?keyword=plates |
      | registry | /shop/registry/wedding/search/Brand,Sortby/Carter%27s%7CINK%2BIVY,PRICE_LOW_TO_HIGH?keyword=bedding                                     |
      | iship    | /shop/featured/dresses/Brand,Women_regular_size_t,Sortby/1.STATE,16%252C%20XL%7C6%252C%20M,PRICE_LOW_TO_HIGH                            |
      | iship    | /shop/featured/knife/Brand,Color_normal/Anolon%7CJ.A.%20Henckels,Black%7CSilver%7CWhite                                                 |
      | iship    | /shop/featured/100%25/Pageindex,Sortby/2,PRICE_HIGH_TO_LOW                                                                              |