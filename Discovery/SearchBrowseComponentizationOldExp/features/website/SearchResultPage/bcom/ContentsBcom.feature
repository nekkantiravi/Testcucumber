 # Author: Discovery QE

 Feature: Verify Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

 ############################### DOMESTIC MODE ##########################################################

   @use_domain_qual @domain_discovery @artifact_navapp @high @bcom @project_snb
   Scenario: SearchResultsPage - Domestic - Verify header is displayed in DOMESTIC mode
     Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
     Then I verify that logo is displayed and returns a "200" OK
     And I verify that the header elements are displayed

 # Notes: Verifies that the logo and the header elements are displayed in SRP

   @use_domain_qual @domain_discovery @artifact_navapp @high @bcom @project_snb
   Scenario: SearchResultsPage - Domestic - Verify footer is displayed in DOMESTIC mode
     Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
     Then I verify that the footer elements are displayed
 # Notes: Verifies that footer links are all present in SRP

   @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_1 @project_snb
   Scenario: SearchResultsPage - Domestic - Verify Content in DOMESTIC mode
     Given I am on SearchResultsPage for "jeans" in DOMESTIC mode
     Then I verify that Search Results contents are displayed
     #Then I verify the Search Results contents
 #Notes:
 #Verify you see results that match the keyword
 #Verify all images and links all return 200 OK on http party
 #Verify leftnav is displayed, sort by and next pages are displayed

   @use_domain_qual @domain_discovery @artifact_navapp @high @bcom @project_snb
   Scenario: SearchResultsPage - Domestic - Verify Left Nav is displayed in DOMESTIC mode
     Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
     And I verify that search result page Facets displayed match with production
     #Then I should see facets listed on left nav
 # Notes: Verifies that left navigation facets are displayed

 # @13g
   @use_regression @unifiednavigation_9588 @artifact_navapp @domain_discovery @priority_low @use_regression_1 @mode_domestic @project_snb
   Scenario Outline: SearchResultsPage - Domestic - Verify that title tag are correctly populated on slp, zero result page and preview enabled redirect pages in DOMESTIC mode
     Given I visit the web site as a guest user
     When I search for "<keyword>"
     Then I verify that the title tag of search results page
     Examples:
       | keyword |
       | jeans   |
       | xtyz    |

 ############################### ISHIP MODE ##########################################################

   @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_1 @project_snb
   Scenario: SearchResultsPage - Iship - Verify Content in ISHIP mode
     Given I visit the web site as a guest user
     When I change country to "Australia"
     And I close the welcome mat if it's visible
     When I search for "shoes"
     Then I verify that Search Results contents are displayed
 #Notes:
 #Verify you see results that match the keyword
 #Verify all images and links all return 200 OK on http party
 #Verify leftnav is displayed, sort by and next pages are displayed

   @use_regression @unifiednavigation_9588 @artifact_navapp @domain_discovery @priority_low @use_regression_1 @mode_iship @adding_missing_scope @project_snb
   Scenario Outline: SearchResultsPage - Iship - Verify that title tag are correctly populated on slp, zero result page and preview enabled redirect pages in ISHIP mode
     Given I visit the web site as a guest user
     And I change country to "a random country"
     And I close the welcome mat if it's visible
     When I search for "<keyword>"
     Then I verify that the title tag of search results page
       | site | 3 column                    |
       | BCOM | <keyword> \| Bloomingdale's |
     Examples:
       | keyword |
       | plates   |
       | xtyz    |

 ############################### REGISTRY MODE ##########################################################

   @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_1 @mode_domestic @project_snb
   Scenario: SearchResultsPage - Registry - Verify content in REGISTRY mode
     Given I am on SearchResultsPage for "plates" in REGISTRY mode
     Then I verify that Search Results contents are displayed
 #Notes:
 #Verify you see results that match the keyword
 #Verify all images and links all return 200 OK on http party
 #Verify leftnav is displayed, sort by and next pages are displayed

   @use_regression @unifiednavigation_9588 @artifact_navapp @domain_discovery @priority_low @use_regression_1 @mode_registry @adding_missing_scope @project_snb
   Scenario Outline: SearchResultsPage - Registry - Verify that title tag are correctly populated on slp, zero result page and preview enabled redirect pages in REGISTRY mode
     Given I visit the web site as a guest user
     And I navigate to registry home page
     When I search for "<keyword>"
     Then I verify that the title tag of search results page
     Examples:
       | keyword |
       | plates  |
       | xtyz    |