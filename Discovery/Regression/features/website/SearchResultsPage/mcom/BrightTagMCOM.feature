#Author: Discovery QE
#Date Created: 11/17/2016

Feature: Verifying BrightTag in SearchResultsPage

############################### DOMESTIC MODE ##########################################################

#Version One: B-46723
#Hashed email hE tag should not populate on navapp pages for guest users
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search @discovery_daily_run
  Scenario: SearchResultsPage - Verify he tag is not populated in bright tag data dictionary object
    Given I am on SearchResultsPage for "jeans" in domestic mode
    Then I verify that hE tag is not populated in bright tag data dictionary object

  ############################### ISHIP MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in ISHIP mode as well

  ############################### REGISTRY MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in registry mode as well
