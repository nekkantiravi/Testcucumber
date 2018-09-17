# Author: SEO Link Module
# Date Created: 01/06/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM::MEW:: Add Link Module to Catsplash pages
# Versionone story numbers::B-84387
########################################################################################################################################################
Feature:  As an SEO Business Manager, I would like to add the link module functionality and improve the overall look and feel of the word cloud for MEW Browse and SLP pages.

###################################################### SLP Page - site mode ########################################################################################
 # pre-condition : seo_add_link_module kill switch should be turned ON for all the scenarios
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is added in SLP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see the tag cloud above the footer in the navigated page

  # pre-condition : seo_add_link_module kill switch should be turned ON
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And I should see all the keywords are underlined in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page


  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the leading between tag cloud links and indent of seo tag cloud in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see indent from the left of seo tag cloud is 30px
    And   I should see indent from the top and bottom of seo tag cloud is 50px

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on SLP
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

     ################################## Browse - Site Mode ###################################################
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is added in Browse page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud in Browse Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And I should see all the keywords are underlined in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title in Browse Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Browse Page
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the leading between tag cloud links and indent of seo tag cloud in Browse Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see indent from the left of seo tag cloud is 30px
    And   I should see indent from the top and bottom of seo tag cloud is 50px

  ################################## Catsplash Page - Site Mode ###################################################
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is added in Catsplash page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    Then I should see the tag cloud above the footer in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud in Catsplash Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And I should see all the keywords are underlined in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title in Catsplash Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Catsplash Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the leading between tag cloud links and indent of seo tag cloud in Catsplash Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see indent from the left of seo tag cloud is 30px
    And   I should see indent from the top and bottom of seo tag cloud is 50px

################################## Master PDP page - Site Mode ###################################################
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is added in Master PDP in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud in Master PDP in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And  I should see all the keywords are underlined in the navigated page
    And  I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title in Master PDP in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Master PDP
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the leading between tag cloud links and indent of seo tag cloud in PDP Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see indent from the left of seo tag cloud is 30px
    And   I should see indent from the top and bottom of seo tag cloud is 50px

    ################################## Member PDP page - Site Mode ###################################################
  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that seo tag cloud section is added in PDP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the UI of keywords in tag cloud in PDP Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    And  I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And  I should see all the keywords are underlined in the navigated page
    And  I should see tag cloud links font size is 14px in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify the styles of tag cloud title in PDP Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on PDP Page
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then I should see the tag cloud above the footer in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

  @domain_marketing @artifact_MEW2.0 @project_SEO_Link_Optimization @priority_high @release_17P @manual
  Scenario: Verify the leading between tag cloud links and indent of seo tag cloud in PDP Page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see indent from the left of seo tag cloud is 30px
    And   I should see indent from the top and bottom of seo tag cloud is 50px