# Author: SEO SLP Leftnav
# Date Created: 06/09/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: BCOM:MEW XAPI: Add Coremetrics tagging to all Browse pages Link Module
# Versionone story numbers::B-91851
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to tag the link module to understand user interaction/conversion/traffic and bounce rate
###################################################### Catsplash Page ##################################################################################

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @artifact_MeW2.0 @use_coremetrics
  Scenario: Verify the query parameters for link module on category splash page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | WOMEN |
    Then I verify that SEO tag clouds are displayed in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas


  ###################################################### Browse Page ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @artifact_MeW2.0 @use_coremetrics
  Scenario: Verify the query parameters for link module on browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | WOMEN   |
      | dresses |
      | casual  |
    Then I verify that SEO tag clouds are displayed in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas

    ###################################################### PDP Page ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @artifact_MeW2.0 @use_coremetrics
  Scenario: Verify the query parameters for link module on PDP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | WOMEN   |
      | dresses |
      | casual  |
    And I click on any random PDP
    Then I verify that SEO tag clouds are displayed in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas