Feature: Search Box functionality

   #Testlink-MCOM-96165 Vone - RT-07511
  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Invalid Search strings validation with single space
    Given I am on SearchResultsPage for " " in Domestic mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Invalid Search strings validation for more than single space
    Given I am on SearchResultsPage for "   " in Domestic mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @project_snb
  Scenario: SearchResultsPage - Domestic - Verify Invalid Search strings validation with scripts
    Given I am on SearchResultsPage for "<script>alert('hi there')</script>" in Domestic mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

    #Testlink-MCOM-96165 Vone - RT-07511
  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @project_snb
  Scenario: SearchResultsPage - Registry - Verify Invalid Search strings validation with single space
    Given I am on SearchResultsPage for " " in Registry mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @project_snb
  Scenario: SearchResultsPage - Registry - Verify Invalid Search strings validation for more than single space
    Given I am on SearchResultsPage for "   " in Registry mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared

     #Testlink-MCOM-96165 Vone - RT-07511
  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @project_snb
  Scenario: SearchResultsPage - Registry - Verify Invalid Search strings validation with scripts
    Given I am on SearchResultsPage for "<script>alert('hi there')</script>" in Registry mode
    #Then I should be in zero result page
    # in valid since when we search with empty string or script page not redirects to zero search results page
    Then I verify that main search field cleared


    #Testlink-MCOM-96165 Vone - RT-07511
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Search strings containing the placeholder text at the end are submitted correctly
    Given I am on BrowsePage in <shopping_mode> mode
    And I search for "michael korsSearch or enter web ID" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

     #Testlink-MCOM-96165 Vone - RT-07511
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3 @project_snb
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
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
  Scenario Outline: CategorySubSplashPage - Domestic|Iship|Registry - Verify Search strings containing the placeholder text at the end are submitted correctly
    Given I am on CategorySubSplashPage in <shopping_mode> mode
    And I search for "michael korsSearch or enter web ID" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

    #Testlink-MCOM-96165 Vone - RT-07511
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
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
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify search strings containing the placeholder text at the beginning (e.g. "Search or enter web IDmichael kors") are submitted correctly
    Given I am on BrowsePage in <shopping_mode> mode
    And I search for "Search or enter web IDmichael kors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | Domestic          |
      | Registry      |
      | Iship         |

    #Testlink-MCOM-96165 Vone - RT-07511
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
  Scenario Outline: CategorySubSplashPage - Domestic|Iship|Registry - Verify Search strings containing the placeholder text at the beginning (e.g. "Search or enter web IDmichael kors") are submitted correctly
    Given I am on CategorySubSplashPage in <shopping_mode> mode
    And I search for "Search or enter web IDmichael kors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | Domestic          |
      | Iship         |

    #Testlink-MCOM-96165 Vone - RT-07511
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
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
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Search strings containing the placeholder text in middle (e.g. "michael Search or enter web IDkors") are submitted correctly
    Given I am on BrowsePage in <shopping_mode> mode
    And I search for "michael Search or enter web IDkors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | Domestic          |
      | Registry      |
      | Iship         |

    #Testlink-MCOM-96165 Vone - RT-07511
  @release_14E @domain_discovery @use_regression @priority_high @artifact_navapp  @mode_Domestic @use_regression_3  @project_snb
  Scenario Outline: CategorySubSplashPage - Domestic|Iship|Registry - Verify Search strings containing the placeholder text in middle (e.g. "michael Search or enter web IDkors") are submitted correctly
    Given I am on CategorySubSplashPage in <shopping_mode> mode
    And I search for "michael Search or enter web IDkors" in search box
    Then I should be in Search Landing page
    And I verify that page have search keyword as "michael kors"
    Examples:
      | shopping_mode |
      | Domestic          |
      | Iship         |

  @release_14K @domain_discovery @14K_RELEASE  @use_regression @priority_high @use_regression_3 @mode_Domestic  @project_snb
  Scenario: PDP page - Verify the placeholder in PDP in Domestic mode for registered user in mode Domestic mode
    Given I visit the web site as a registered user
    When I search for "Jeans"
    Then I should be in Search Landing page
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    And I verify that "Search or enter web ID" default message is displayed in search box

    # @13g
  @use_regression @unifiednavigation_9589 @artifact_navapp @domain_discovery @priority_low @use_regression_1 @mode_Domestic  @project_snb
  Scenario Outline: Search Result page - Ability to confirm that meta keywords tag is correctly populated on slp, zero result page in Domestic mode
    Given I am on SearchResultsPage for "<keyword>" in Domestic mode
    Then I verify that meta "keywords" tag as "<meta_keywords>"
    Examples:
      | keyword | meta_keywords                             |
      | Jeans   | jeans, shop for jeans, buy jeans, Macy's. |
      | xtyz    | xtyz, shop for xtyz, buy xtyz, Macy's.    |
