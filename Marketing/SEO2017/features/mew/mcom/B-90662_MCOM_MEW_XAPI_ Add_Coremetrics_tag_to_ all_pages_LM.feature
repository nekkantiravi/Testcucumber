# Author: SEO SLP Leftnav
# Date Created: 07/09/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM:MEW XAPI: Add Coremetrics tagging to all Browse & SLP pages Link Module
# Versionone story numbers::B-90662
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to tag the link module to understand user interaction/conversion/traffic and bounce rate
###################################################### SLP Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17T @priority_high @artifact_xapi @use_coremetrics
  Scenario: Verify the query parameters for link module on SLP
    Given I visit the mobile web site as a guest user
    When I navigate to SLP browse category page
    Then I should see the link module on UI
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
   ######################################################### Catsplash Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17T @priority_high @artifact_xapi @use_coremetrics
  Scenario: Verify the query parameters for link module on category splash page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop        |
      | Kids & Baby |
    Then I should see the link module on UI
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
####################################################### Subsplash Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17T @priority_high @artifact_xapi @use_coremetrics
  Scenario: Verify the query parameters for link module on subsplash page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop       |
      | Men        |
      | Activewear |
    Then  I should see the link module on UI
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
   ########################################################## Browse Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17T @priority_high @artifact_xapi @use_coremetrics
  Scenario: Verify the query parameters for link module on browse page
    Given I visit the mobile web site as a guest user
    When I navigate to category browse page that has copy block
    And I should see the link module on UI
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
########################################################## Master PDP Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17T @priority_high @artifact_wssg @use_coremetrics
  Scenario: Verify the query parameters for link module on Master PDP page
    Given I visit the mobile web site as a guest user
    And I navigate to "master_product and link_module" product PDP page
    And I should see the link module on UI
    When I click on any keyword in the link module in the navigated page
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas

########################################################## Memeber PDP Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17T @priority_high @artifact_wssg @use_coremetrics
  Scenario: Verify the query parameters for link module on Member PDP page
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I should see the link module on UI
    When I click on any link in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas