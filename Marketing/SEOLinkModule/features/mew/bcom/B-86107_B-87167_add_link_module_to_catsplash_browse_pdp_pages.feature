# Author: SEO Link Module
# Date Created: 20/07/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: BCOM::MEW:: Add Link Module to Catsplash pages
# Versionone story numbers::B-86107
########################################################################################################################################################
Feature:  As an SEO Business Manager, I would like to add the link module functionality and improve the overall look and feel of the word cloud for MEW Catsplash pages.

   ################################## Catsplash Page - Site Mode ###################################################
# pre-condition : seo_add_link_module kill switch should be turned ON for all the scenarios
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is displayed on Catsplash page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see the tag cloud below the copy block in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud on Catsplash page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page
    And  I should see six links in the tag cloud in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title on Catsplash page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see tag cloud title is in uppercase in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on Category Splash
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the indent of seo tag cloud on Catsplash page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see the tag cloud above the footer in the navigated page
    And   I should see indent from the left & right of seo tag cloud is 12px
    And   I should see indent from the top and bottom of seo tag cloud is 15px

    ################################## Browse - Site Mode ###################################################
 # pre-condition : seo_add_link_module kill switch should be turned ON for all the scenarios
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is displayed on Browse page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see the tag cloud below the copy block in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud on Browse page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title on Browse page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see tag cloud title is in uppercase in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on Browse page
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the indent of seo tag cloud on Browse page in SITE mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    And   I should see indent from the left & right of seo tag cloud is 12px
    And   I should see indent from the top and bottom of seo tag cloud is 15px

    ################################## Master PDP page - Site Mode ###################################################
 # pre-condition : seo_add_link_module kill switch should be turned ON for all the scenarios
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is displayed on Master PDP in SITE mode
    Given I visit the mobile web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud on Master PDP in SITE mode
    Given I visit the mobile web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title on Master PDP in SITE mode
    Given I visit the mobile web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see tag cloud title is in uppercase in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on Master PDP
    Given I visit the mobile web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the indent of seo tag cloud on Master PDP in SITE mode
    Given I visit the mobile web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer in the navigated page
    And   I should see indent from the left & right of seo tag cloud is 12px
    And   I should see indent from the top and bottom of seo tag cloud is 15px

    ################################## Member PDP page - Site Mode ###################################################
 # pre-condition : seo_add_link_module kill switch should be turned ON for all the scenarios
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is displayed on PDP page in SITE mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud on PDP page in SITE mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title on PDP page in SITE mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see tag cloud title is in uppercase in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on PDP page
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the indent of seo tag cloud on PDP page in SITE mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    And   I should see indent from the left & right of seo tag cloud is 12px
    And   I should see indent from the top and bottom of seo tag cloud is 15px
