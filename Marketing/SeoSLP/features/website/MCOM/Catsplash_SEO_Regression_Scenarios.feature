# Author: SEO Regression
# Date Created: 23/10/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM/BCOM : SEO : QE Automation Regression
# Version One story numbers::  B-95220
#########################################################################################################################

Feature: This is a QE only Automation story where we will address all the SEO functionality Automation back log. Will add the confluence page here which will have the SEO functionality that is Automated and yest to be Automated.

###################################################### Catsplash Page  ########################################################################################

  omain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the Page title, Page Header, Page Description, Canonical Tag, Alternate Tag on Catsplash Pages
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I should see page title matches with Stella page title
    And I should see page header is matched the with "WOMEN" name
    And I verify the title for "WOMEN" category page
    And I verify the "canonical" tag in HTML view source code
    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And I should see page description as expected

  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the link module functionality in Catsplash page in DOMESTIC mode
    Given I am on CatSplash Page for "women" on "domestic" mode
    Then I should see the tag cloud
    And  I should see tag cloud title font family is Helvetica Neue LT Std
    And  I should see tag cloud title font size is forteen pixels
    And  I should see tag cloud title font weight is Bold
    And  I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And  I should see all the keywords are underlined
    And  I should see tag cloud links font family is Helvetica Neue LT Std
    And  I should see tag cloud links font size is 14px
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the copy block functionality in Catsplash page in DOMESTIC mode
    Given I am on CatSplash Page for "women" on "domestic" mode
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source

      #################################### Catsplash page - Iship Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the tags(title,header,canonical,alternate) and page description in Catsplash page in International mode
    Given I am on CatSplash Page for "WOMEN" on "iship" mode
    Then I should see page title matches with Stella page title
    And I should see page header is matched the with "WOMEN" name
    And I verify the title for "WOMEN" category page
    And I verify the "canonical" tag in HTML view source code
    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And I should see page description as expected


  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the copy block functionality in Catsplash page in International mode
    Given I am on CatSplash Page for "women" on "iship" mode
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
