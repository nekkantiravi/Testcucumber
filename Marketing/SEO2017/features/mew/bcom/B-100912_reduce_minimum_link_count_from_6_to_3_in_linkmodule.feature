# Author: SEO Improvements 2017
# Date Created: 12/19/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM: MEW: QE: Reduce Minimum Link Count from 6 to 3 in Link Module For all Pages
# Versionone story numbers::  B-100912
#########################################################################################################################
Feature: As an SEO Business Manager, I would like to enhance the Link Module by having it appear on more pages.This can be achieved by reducing the minimum link count within any page's Link Module from 6 to 3.
  ########### ###################################################### Browse Page  ########################################################################################
 # Prerequisite: Category Browse page should have 3 links in link module section
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Z @priority_high @artifact_wssg
  Scenario: Verify that only 3 links should be displayed in link module section on Category Browse Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women   |
      | Lingerie, Bras & Panties |
      | Matching Sets |
    Then I should see 3 links in the link module section

  #Prerequisite: Category browse page should have 6 links in link module
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Z @priority_high @artifact_wssg
  Scenario: Verify that 6 links should be displayed in link module section on Category Browse Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women   |
      | Dresses |
    Then I should see 6 links in the link module section

    ########### ###################################################### PDP  - site mode ########################################################################################
 # Prerequisite: Member PDP page should have 3 links in link module section
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Z @priority_high @artifact_wssg
  Scenario: Verify that only 3 links should be displayed in link module section on member PDP Page
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see 3 links in the link module section

  #Prerequisite: Master PDP page page should have 6 links in link module section
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Z @priority_high @artifact_wssg
  Scenario: Verify that 6 links should be displayed in link module section on master PDP Page
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then I should see 6 links in the link module section
