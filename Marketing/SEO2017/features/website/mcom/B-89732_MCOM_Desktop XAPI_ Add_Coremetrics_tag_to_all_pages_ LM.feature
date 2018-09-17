# Author: SEO SLP Leftnav
# Date Created: 24/08/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM:Desktop XAPI: Add Coremetrics tagging to all Browse & SLP pages Link Module
# Versionone story numbers::B-89732
########################################################################################################################################################
Feature:As an SEO Business Manager, I would like to tag the link module to understand user interaction/conversion/traffic and bounce rate
###################################################### SLP Page ########################################################################################
  @coremetrics @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on SLP page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to SLP browse category page
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
   ######################################################### Catsplash Page ########################################################################################
  @coremetrics @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on category splash page
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
####################################################### Subsplash Page ########################################################################################
  @coremetrics @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on subsplash page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Activewear" sub splash page under "WOMEN"
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
   ########################################################## Browse Page ########################################################################################
  @coremetrics @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on browse page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to Browse page type in SITE mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
########################################################## Master PDP Page ########################################################################################
  @coremetrics @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on Master PDP page
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
########################################################## Memeber PDP Page ########################################################################################
  @coremetrics @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on Member PDP page
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas