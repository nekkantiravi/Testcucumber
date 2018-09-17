 # Author: Discovery QE

 Feature: Verify Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

 ############################### DOMESTIC MODE ##########################################################

   @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
   Scenario: CategoryBrowsePage - Domestic - Verify header is displayed in DOMESTIC mode
     Given I am on CategoryBrowsePage for "21683" category id in Domestic mode
     And I clear existing class variables to avoid data issues
     Then I verify that logo is displayed and returns a 200 OK
     And I verify that the header elements are displayed

 # Notes: Verifies that the logo and the header elements are displayed in SRP

   @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
   Scenario: CategoryBrowsePage - Domestic - Verify footer is displayed in DOMESTIC mode
     Given I am on CategoryBrowsePage for "21683" category id in Domestic mode
     And I clear existing class variables to avoid data issues
     Then I verify that the footer elements are displayed
 # Notes: Verifies that footer links are all present in SRP

   @use_regression @domain_discovery @priority_high @snbc_comp @migrated_to_sdt
   Scenario: CategoryBrowsePage - Domestic - Verify Content in DOMESTIC mode
     Given I am on CategoryBrowsePage for "21683" category id in Domestic mode
     And I clear existing class variables to avoid data issues
     Then I verify that browse page contents are displayed
     #Then I verify the browse page contents
 #Notes:
 #Verify you see results that match the keyword
 #Verify all images and links all return 200 OK on http party
 #Verify leftnav is displayed, sort by and next pages are displayed

   @domain_discovery @high @snbc_comp @migrated_to_sdt @use_regression
   Scenario: CategoryBrowsePage - Domestic - Verify Left Nav is displayed in DOMESTIC mode
     Given I am on SearchResultsPage for "shoes" in DOMESTIC mode
     And I clear existing class variables to avoid data issues
     And I verify that browse page Facets displayed match with production
     #Then I should see facets listed on left nav
 # Notes: Verifies that left navigation facets are displayed


 ############################### ISHIP MODE ##########################################################

   @use_regression @domain_discovery @priority_high @migrated_to_sdt @snbc_comp
   Scenario: CategoryBrowsePage - Iship - Verify Content in ISHIP mode
     Given I am on CategoryBrowsePage for "21683" category id in Iship mode
     And I clear existing class variables to avoid data issues
     Then I verify that browse page contents are displayed
 #Notes:
 #Verify you see results that match the keyword
 #Verify all images and links all return 200 OK on http party
 #Verify leftnav is displayed, sort by and next pages are displayed

  
 ############################### REGISTRY MODE ##########################################################

   @use_regression @domain_discovery @priority_high @mode_domestic @migrated_to_sdt @snbc_comp
   Scenario: CategoryBrowsePage - Registry - Verify content in REGISTRY mode
   Given I am on CategoryBrowsePage for "8203" category id in Registry mode
     And I clear existing class variables to avoid data issues
     Then I verify that browse page contents are displayed
 #Notes:
 #Verify you see results that match the keyword
 #Verify all images and links all return 200 OK on http party
 #Verify leftnav is displayed, sort by and next pages are displayed