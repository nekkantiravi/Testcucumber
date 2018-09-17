# Author: SEO SLP Leftnav
# Date Created: 06/09/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: BCOM:Desktop XAPI: Add Coremetrics tagging to all Browse, Subsplash, Catsplash pages Link Module
# Versionone story numbers::B-91852
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to tag the link module to understand user interaction/conversion/traffic and bounce rate
    ###################################################### Catsplash Page ########################################################################################
  @coremetrics @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on category splash page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to Category Splash page in SITE mode
    Then I should see six links in the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
      #And I should see that link click tag and page view tags are generated in coremetrics tool


        ###################################################### Subsplash Page ########################################################################################
  @coremetrics @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on subsplash page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to Sub Splash page in SITE mode
    Then I should see six links in the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
      #And I should see that link click tag and page view tags are generated in coremetrics tool


            ###################################################### Browse Page ########################################################################################
  @coremetrics @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on browse page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to Browse page in SITE mode
    Then I should see six links in the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
      #EX:: cm_sp=link_module-_-n-_-addidas
      #And I should see that link click tag and page view tags are generated in coremetrics tool

      ###################################################### PDP Page ########################################################################################
  @coremetrics @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T @use_coremetrics
  Scenario: Verify the query parameters for link module on PDP page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to Browse page in SITE mode
    And I navigate to PDP of the first product
    Then I should see six links in the tag cloud
    When I click on any keyword in the link module
    Then I should see "cm_sp=link_module-_-n-_-{link text}" query parameter
    #EX:: cm_sp=link_module-_-n-_-addidas