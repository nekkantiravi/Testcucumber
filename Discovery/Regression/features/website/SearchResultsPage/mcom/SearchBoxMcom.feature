Feature: Search Box functionality

   #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify Invalid Search strings validation with single space
    Given I search for " " in Domestic mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Domestic - Verify Invalid Search strings validation for more than single space
    Given I search for "  " in Domestic mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Domestic - Verify Invalid Search strings validation with scripts
    Given I search for "<script>alert('hi there')</script>" in Domestic mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

    #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Registry - Verify Invalid Search strings validation with single space
    Given I search for "  " in Registry mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify Invalid Search strings validation for more than single space
    Given I search for "  " in Registry mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: PDP page - Verify the placeholder in PDP in Domestic mode for registered user in mode Domestic mode
    Given I visit the web site as a registered user
    When I search for "Jeans"
    Then I should be in Search Landing page
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    And I verify that "Search or enter web ID" default message is displayed in search box


     #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priorityhigh @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: HomePage - Domestic|Iship|Registry - Verify search strings containing the placeholder text at the end are submitted correctly
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "michael korsSearch or enter web ID" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode    |
      | domestic         |
      | registry         |
      | iship            |

      #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify Invalid Search strings validation with scripts
    Given I search for "<script>alert('hi there')</script>" in Registry mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario Outline: HomePage - Domestic|Iship|Registry - Verify search strings containing the placeholder text at the beginning (e.g. "Search or enter web IDmichael kors") are submitted correctly
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "Search or enter web IDmichael kors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode    |
      | domestic         |
      | registry         |
      | iship            |

    #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario Outline: HomePage - Domestic|Iship|Registry - Verify Search strings containing the placeholder text in middle (e.g. "michael Search or enter web IDkors") are submitted correctly
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "michael Search or enter web IDkors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode    |
      | domestic         |
      | registry         |
      | iship            |

    #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario: BrowsePage - Domestic - Verify Search strings containing the placeholder text at the end are submitted correctly
    Given I am on Browse Page for "Dress Shirts" under "MEN"
    And I search for "michael korsSearch or enter web ID" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"

        #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario: BrowsePage - Domestic - Verify Search strings containing the placeholder text in middle (e.g. "michael Search or enter web IDkors") are submitted correctly
    Given I am on Browse Page for "Dress Shirts" under "MEN"
    And I search for "michael Search or enter web IDkors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"

    #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario Outline: CategorySubSplashPage - Domestic|Iship - Verify Search strings containing the placeholder text in middle (e.g. "michael Search or enter web IDkors") are submitted correctly
    Given I am on CatSplash Page for "MEN" on "<shopping_mode>" mode
    And I search for "michael Search or enter web IDkors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

        #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario: BrowsePage - Domestic|Iship|Registry - Verify search strings containing the placeholder text at the beginning (e.g. "Search or enter web IDmichael kors") are submitted correctly
    Given I am on Browse Page for "Dress Shirts" under "MEN"
    And I search for "Search or enter web IDmichael kors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"

    #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario Outline: CategorySubSplashPage - Domestic|Iship - Verify Search strings containing the placeholder text at the beginning (e.g. "Search or enter web IDmichael kors") are submitted correctly
    Given I am on CatSplash Page for "MEN" on "<shopping_mode>" mode
    And I search for "Search or enter web IDmichael kors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

    #Testlink-MCOM-96165 Vone - RT-07511
  @domain_discovery @priority_medium @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @defect_D-44333
  Scenario Outline: CategorySubSplashPage - Domestic|Iship - Verify Search strings containing the placeholder text at the end are submitted correctly
    Given I am on CatSplash Page for "MEN" on "<shopping_mode>" mode
    And I search for "michael korsSearch or enter web ID" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @domain_discovery @priority_medium @mode_domestic @use_regression @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify gift cards search redirected to gift card category page
    Given I visit the web site as a guest user
    When I search for "gift cards"
    Then I should see page navigated to "/shop/gift-cards?id=1405&edge=hybrid&cm_kws=gift%20cards || /social/holiday-gift-guide" pattern url

  @domain_discovery @priority_medium @mode_domestic @use_regression @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify 'brands' keyword search redirected to designer index page
    Given I visit the web site as a guest user
    When I search for "brands"
    Then I should see page navigated to "/shop/all-brands?id=63538&edge=hybrid&cm_kws=brands" pattern url

  @domain_discovery @priority_medium @mode_registry @use_regression @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify 'brands' keyword search redirected to designer index page
    Given I visit the web site as a guest user in "registry" mode
    When I search for "brands"
    Then I should see page navigated to "/shop/wedding-registry/all-brands?id=63538&edge=hybrid&cm_kws=brands" pattern url

  @domain_discovery @priority_medium @mode_iship @use_regression @feature_search_results_page
  Scenario: SearchResultsPage - Iship - Verify 'brands' keyword search redirected to designer index page
    Given I visit the web site as a guest user in "iship" mode
    When I search for "brands"
    Then I should see page navigated to "/shop/all-brands?id=63538&edge=hybrid&cm_kws=brands" pattern url

  @domain_discovery @priority_medium @mode_iship @use_regression @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify search on legacy(/ce/splash) page
    Given I am on CatSplash Page for "HOME" on "<shopping_mode>" mode
    And I navigate to "Top Home Trends" browse page from cat splash page
    And I search for "100%"
    Then I should be in Search Landing page
    And I verify that page have search keyword as "100%"
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @domain_discovery @priority_medium @mode_iship @use_regression @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify search on legacy(/ce/splash) page
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "/campaign/splash/" pattern url link from footer
    And I search for "100% "
    Then I should be in Search Landing page
    And I verify that page have search keyword as "100%"

  @domain_discovery @priority_medium @mode_iship @use_regression @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify search on campaign page
    Given I am on CatSplash Page for "HOME" on "<shopping_mode>" mode
    And I navigate to "Custom Windows" browse page from cat splash page
    And I search for "100% "
    Then I should be in Search Landing page
    And I verify that page have search keyword as "100%"
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @domain_discovery @priority_medium @mode_iship @use_regression @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify search on campaign page
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "/social?campaign_id=" pattern url link from footer
    And I search for "100% "
    Then I should be in Search Landing page
    And I verify that page have search keyword as "100%"
