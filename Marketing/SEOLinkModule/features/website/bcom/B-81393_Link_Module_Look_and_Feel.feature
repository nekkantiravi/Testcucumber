# Author: SEO SLP Leftnav
# Date Created: 08/06/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: BCOM::UI:: Link Module Look and Feel updates in Browse and Catsplash pages
# Versionone story numbers::B-81393
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to improve the overall look and feel of the word cloud for Browse and Catsplash pages.
########### ###################################################### Category Splash Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the position of seo tag cloud in Category Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "WOMEN" category page
    Then I should see the tag cloud above the footer
    And  I should see the tag cloud below the copy block

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the count of tag cloud links in Category Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I should see the tag cloud above the footer
    And  I should see six links in the tag cloud
    And  I should see all the links are underlined
    And  I should see tag cloud links font size is twelve pixels

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the styles of tag cloud title in Category Splash in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    And I should see tag cloud title is in uppercase
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on Category Splash
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I should see the tag cloud above the footer
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

    ########### ###################################################### Browse Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the position of seo tag cloud in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see the tag cloud above the footer
    And  I should see the tag cloud below the copy block

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the count of tag cloud links in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see the tag cloud above the footer
    And  I should see six links in the tag cloud
    And  I should see all the links are underlined
    And  I should see tag cloud links font size is twelve pixels

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the styles of tag cloud title in Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    And I should see tag cloud title is in uppercase
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on Browse Page
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see the tag cloud above the footer
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

########### ###################################################### PDP  - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the position of seo tag cloud in standard PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I should see the tag cloud above the footer

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the count of tag cloud links in standard PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to a random product
    Then I should see the tag cloud above the footer
    And  I should see six links in the tag cloud
    And  I should see all the links are underlined
    And  I should see tag cloud links font size is twelve pixels

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the styles of tag cloud title in standard PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to a random product
    And I should see tag cloud title is in uppercase
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on standard PDP
    Given I visit the web site as a guest user
    When  I navigate to a random product
    Then I should see the tag cloud above the footer
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the position of seo tag cloud in Master PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the count of tag cloud links in Master PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer
    And  I should see six links in the tag cloud
    And  I should see all the links are underlined
    And  I should see tag cloud links font size is twelve pixels

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify the styles of tag cloud title in Master PDP in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I should see tag cloud title is in uppercase
    And I should see tag cloud title font size is forteen pixels
    And I should see tag cloud title font weight is Bold

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17M @use_regression
  Scenario: Verify that clicking on links in tag cloud should navigate to relevant page in DOMESTIC mode on Master PDP
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I should see the tag cloud above the footer
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

