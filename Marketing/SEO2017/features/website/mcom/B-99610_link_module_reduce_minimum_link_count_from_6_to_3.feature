# Author: SEO Optimization 2017
# Date Created: 12/19/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM Link Module: Reduce Minimum Link Count from 6 to 3
# Versionone story numbers::B-99610
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to enhance the Link Module by having it appear on more pages.  This can be achieved by reducing the minimum link count within any page's Link Module from 6 to 3
  #################################################################### Browse Page - site mode ########################################################################################
  # Prerequisite: Navigate to a browse page which has 3 links in the link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that only 3 links should be displayed in link module in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY_WITH_3_LINKS" category
    Then I should see 3 links in the link module

  # Prerequisite: Navigate to a browse page which has 10 links in the link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that ten links should be displayed in link module in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY_WITH_10_LINKS" category
    Then I should see 10 links in the link module

 #################################################################### PDP Page - site mode ########################################################################################
  # Prerequisite: Navigate to a browse page which has 3 links in the link module
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that only 3 links should be displayed in link module in PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to "member_product and link_module" product PDP page
    Then I should see 3 links in the link module

  # Prerequisite: Navigate to a browse page which has 10 links in the link module
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that ten links should be displayed in link module in PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    And  I navigate to "master_product and link_module" product PDP page
    Then I should see 10 links in the link module

     #################################################################### Subsplash Page - site mode ########################################################################################
  # Prerequisite: Navigate to a subsplash page which has 3 links in the link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that only 3 links should be displayed in link module in Subsplash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "SUBSPLASH_CATEGORY_WITH_3_LINKS" category
    Then I should see 3 links in the link module

  # Prerequisite: Navigate to a subsplash page which has 10 links in the link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that ten links should be displayed in link module in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "SUBSPLASH_CATEGORY_WITH_10_LINKS" category
    Then I should see 10 links in the link module

     #################################################################### SLP Page - site mode #######################################################################################
  # Prerequisite: Navigate to a SLP page which has 10 links in the link module
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that ten links should be displayed in link module in SLP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see 10 links in the link module

    #################################################################### Catsplash Page - site mode ########################################################################################
  # Prerequisite: Navigate to a catsplash page which has 3 links in the link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that only 3 links should be displayed in link module in Catsplash Page in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I should see 3 links in the link module

  # Prerequisite: Navigate to a catsplash page which has 10 links in the link module
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that ten links should be displayed in link module in Catsplash Page in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I should see 10 links in the link module