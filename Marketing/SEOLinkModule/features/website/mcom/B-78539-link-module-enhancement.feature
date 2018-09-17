# Author: SEO SLP Leftnav
# Date Created: 03/05/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM::UI:: COMP Integration: Link Module Enhancement (PDP and SLP)
# Versionone story numbers::B-78539
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to enhance the link module functionality and improve the overall look and feel of the word cloud.
###################################################### SLP Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the UI of keywords in tag cloud in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Static_Browse" SLP category which is under "71233" parent category
    Then I should see the tag cloud
    And I should see keywords in the seo tag cloud section one after the another
    And I should see ten links in the tag cloud
    And I should see all the keywords are underlined
    And I should see tag cloud links font family is Helvetica Neue LT Std
    And I should see tag cloud links font size is 14px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the styles of tag cloud title in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate the "Browse" SLP category which is under "71233" parent category
    And I should see tag cloud title font family is Helvetica Neue LT Std
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @manual
  Scenario: Verify the leading between tag cloud links,width and indent of seo tag cloud in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate the "Browse" SLP category which is under "71233" parent category
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see tag cloud width is 275px
    And   I should see indent of seo tag cloud is 20px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @use_seo_coremetrics
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on SLP
    Given I visit the web site as a guest user
    When  I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

    ###################################################### PDP Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the UI of keywords in tag cloud in PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I should see the tag cloud
    And I should see keywords in the seo tag cloud section one after the another
    And I should see ten links in the tag cloud
    And I should see all the keywords are underlined
    And I should see tag cloud links font family is Helvetica Neue LT Std
    And I should see tag cloud links font size is 14px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the styles of tag cloud title in PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    And I should see tag cloud title font family is Helvetica Neue LT Std
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @use_seo_coremetrics
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on PDP
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @manual
  Scenario: Verify the leading between tag cloud links,width and indent of seo tag cloud in PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see tag cloud width is 275px
    And   I should see indent of seo tag cloud is 20px

     ###################################################### Category Splash Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the UI of keywords in tag cloud in Category Splash in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I should see the tag cloud
    And I should see keywords in the seo tag cloud section one after the another
    And I should see ten links in the tag cloud
    And I should see all the keywords are underlined
    And I should see tag cloud links font family is Helvetica Neue LT Std
    And I should see tag cloud links font size is 14px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the styles of tag cloud title in Category Splash in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    And I should see tag cloud title font family is Helvetica Neue LT Std
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @use_seo_coremetrics
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Category Splash
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @manual
  Scenario: Verify the leading between tag cloud links,width and indent of seo tag cloud in Category Splash in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see tag cloud width is 275px
    And   I should see indent of seo tag cloud is 20px


      ################################## Browse/Subsplash/ - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the UI of keywords in tag cloud in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see the tag cloud
    And I should see keywords in the seo tag cloud section one after the another
    And I should see ten links in the tag cloud
    And I should see all the keywords are underlined
    And I should see tag cloud links font family is Helvetica Neue LT Std
    And I should see tag cloud links font size is 14px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the styles of tag cloud title in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    And I should see tag cloud title font family is Helvetica Neue LT Std
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @use_seo_coremetrics
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Browse Page
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @manual
  Scenario: Verify the leading between tag cloud links,width and indent of seo tag cloud in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see tag cloud width is 275px
    And   I should see indent of seo tag cloud is 20px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the UI of keywords in tag cloud in Subsplash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Sub Splash page type in SITE mode
    Then I should see the tag cloud
    And I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And I should see all the keywords are underlined
    And I should see tag cloud links font family is Helvetica Neue LT Std
    And I should see tag cloud links font size is 14px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @use_seo_coremetrics
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Subsplash Page
    Given I visit the web site as a guest user
    When I navigate to Sub Splash page type in SITE mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @manual
  Scenario: Verify the leading between tag cloud links,width and indent of seo tag cloud in Browse or Subsplash Pages in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Sub Splash page type in SITE mode
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see tag cloud width is 275px
    And   I should see indent of seo tag cloud is 20px

    ################################## Master PDP - Site Mode ###################################################

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the UI of keywords in tag cloud in Master Product detailed Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud
    And I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And I should see all the keywords are underlined
    And I should see tag cloud links font family is Helvetica Neue LT Std
    And I should see tag cloud links font size is 14px

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression
  Scenario: Verify the styles of tag cloud title in Master Product detailed Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I should see tag cloud title font family is Helvetica Neue LT Std
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @use_seo_coremetrics
  Scenario: Verify that clicking on keywords in tag cloud should navigate to relevant page in DOMESTIC mode on Master Product detailed Page
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17K @use_regression @manual
  Scenario: Verify the leading between tag cloud links,width and indent of seo tag cloud in Master Product detailed Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then  I should see tag cloud links in seo tag cloud
    And   I should see 25px leading between tag cloud links
    And   I should see tag cloud width is 275px
    And   I should see indent of seo tag cloud is 20px