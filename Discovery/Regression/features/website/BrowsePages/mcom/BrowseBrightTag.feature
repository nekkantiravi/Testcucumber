#Author: Discovery QE
#Date Created: 08/03/2017

Feature: Verifying BrightTag in SearchResultsPage

############################### DOMESTIC MODE ##########################################################

#Hashed email hE tag should not populate on navapp pages for guest users
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @feature_catsplash @discovery_daily_run
  Scenario: CategorySplash - Verify he tag is not populated in bright tag data dictionary object
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to random category splash page
    Then I verify that hE tag is not populated in bright tag data dictionary object

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @feature_browse_page @discovery_daily_run
  Scenario: Browse page - Verify he tag is not populated in bright tag data dictionary object
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to random browse page
    Then I verify that hE tag is not populated in bright tag data dictionary object

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @feature_subsplash_page @discovery_daily_run
  Scenario: SubSplash - Verify he tag is not populated in bright tag data dictionary object
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" sub splash page under "MEN"
    Then I verify that hE tag is not populated in bright tag data dictionary object

  ############################### ISHIP MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in ISHIP mode as well

  ############################### REGISTRY MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in registry mode as well
