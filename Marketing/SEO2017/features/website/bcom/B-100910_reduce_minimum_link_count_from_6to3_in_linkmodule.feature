# Author: SEO Improvements 2017
# Date Created: 12/19/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM: Desktop: PDP: TECH: Reduce Minimum Link Count from 6 to 3 in Link Module , BCOM: Desktop: Non PDP: QE: Reduce Minimum Link Count from 6 to 3 in Link Module
# Versionone story numbers::  B-100910 , B-100911
#########################################################################################################################
Feature: As an SEO Business Manager, I would like to enhance the Link Module by having it appear on more pages.This can be achieved by reducing the minimum link count within any page's Link Module from 6 to 3
  ########### ###################################################### Browse Page  ########################################################################################
 # Prerequisite: Category Browse page should have 3 links in link module section
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that only 3 links should be displayed in link module section on Category Browse Page
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY_WITH_3_LINKS" category
    Then I should see 3 links in the link module

  #Prerequisite: Category browse page should have 6 links in link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario:  Verify that 6 links should be displayed in link module section on Category Browse Page
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY_WITH_6_LINKS" category
    Then I should see 6 links in the link module

########### ###################################################### PDP  - site mode ########################################
 # Prerequisite: Member PDP page should have 3 links in link module section
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that only 3 links should be displayed in link module section on Master PDP page
    Given I visit the web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see 3 links in the link module

  #Prerequisite: Master PDP page should have 6 links in link module section
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that 6 links should be displayed in link module section on Master PDP page
    Given I visit the web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then I should see 6 links in the link module