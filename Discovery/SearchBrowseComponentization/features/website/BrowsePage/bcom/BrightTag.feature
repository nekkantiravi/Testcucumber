#Author: Discovery QE
#Date Created: 11/17/2016

Feature: Verifying BrightTag in Category Browse Page

############################### DOMESTIC MODE ##########################################################

#Version One: B-46723
#Hashed email hE tag should not populate on xapi pages for guest users
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt @use_regression
  Scenario: CategoryBrowsePage - Verify he tag is not populated in bright tag data dictionary object on search results page
    Given I am on CategoryBrowsePage for "21683" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    Then I verify that hE tag is not populated in bright tag data dictionary object

  ############################### ISHIP MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in ISHIP mode as well

  ############################### REGISTRY MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in registry mode as well
