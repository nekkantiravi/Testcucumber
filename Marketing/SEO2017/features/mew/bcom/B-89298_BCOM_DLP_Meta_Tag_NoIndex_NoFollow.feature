# Author: SEO Improvements 2017
# Date Created: 08/29/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM:MEW:WSSG: Add noindex, nofollow on all DLP pages with less than 3 assortments
# Versionone story numbers::  B-89298
#########################################################################################################################
Feature: As a BCOM SEO Business Manager, I want a noindex,nofollow tag on all BCOM DLP pages with less than 3 assortments (pages with 2 or less assortments will get
  this tag).  "Assortments" is the same thing as "Products"

########################################################## DLP Page on MEW -site mode #########################################

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAMEhttps://code.devops.fds.com/CAP/SDT/merge_requests/2515="ROBOTS" Tag section in DOM for more than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the keyword with "more_than_three_assortments" on the page
       # for e.g: https://m.bloomingdales.com/buy/tory-burch-reva-flat
    Then I should not see the meta name tag created in DOM
      # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for exactly 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the keyword with "equal_to_three_assortments" on the page
      # for e.g: https://m.bloomingdales.com/buy/flying-monkey-jeans
    Then I should not see the meta name tag created in DOM
      # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for less than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the keyword with "less_than_three_assortments" on the page
      # for e.g: https://m.bloomingdales.com/buy/aervana/Brand/Aervana?brand=Aervana&cm_sp=shop_by_brand-_-ALL%20BRANDS-_-Aervana
    Then I should see the meta name tag created in DOM
      # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

######################################## DLP from Designer FOB #########################################################

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for more than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS |
      | KIDS          |
    Then I should be redirected to designer page using mobile website
      # Ex with url pattern : /buy/all-designers/[category]/[brand]/Brand,Fob,Productsperpage
    When I navigate to "Bestever" designer category browse page using mobile website
    Then I should be landing on DLP Page
    And I should not see the meta name tag created in DOM
      # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for exactly 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS |
      | KIDS          |
    Then I should be redirected to designer page using mobile website
      # Ex with url pattern : /buy/all-designers/[category]/[brand]/Brand,Fob,Productsperpage
    When I navigate to "Birkenstock" designer category browse page using mobile website
    Then I should be landing on DLP Page
    And I should not see the meta name tag created in DOM
    # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for less than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS |
      | KIDS          |
    Then I should be redirected to designer page using mobile website
      # Ex with url pattern : /buy/all-designers/[category]/[brand]/Brand,Fob,Productsperpage
    When I navigate to "Breville" designer category browse page using mobile website
    Then I should be landing on DLP Page
    And I should see the meta name tag created in DOM
    # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

##################################### Parent Brand DLP from Designer FOB ###################################################

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for more than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS |
    Then I should be redirected to designer page using mobile website
      # Ex with url pattern : /buy/[brand-name]/Brand/brand%20name?id=xxxxx
    When I navigate to "111SKIN" designer category browse page using mobile website
    Then I should be landing on DLP Page
    And I should not see the meta name tag created in DOM
      # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for exactly 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS |
    Then I should be redirected to designer page using mobile website
      # Ex with url pattern : /buy/[brand-name]/Brand/brand%20name?id=xxxxx
    When I navigate to "Aireloom" designer category browse page using mobile website
    Then I should be landing on DLP Page
    And I should not see the meta name tag created in DOM
    # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for less than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to True on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS |
    Then I should be redirected to designer page using mobile website
      # Ex with url pattern : /buy/[brand-name]/Brand/brand%20name?id=xxxxx
    When I navigate to "Frigo" designer category browse page using mobile website
    Then I should be landing on DLP Page
    And I should see the meta name tag created in DOM
    # for e.g: <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

################################## Kill Switch False cases ######################################################################

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0 @use_manual @manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for more than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigates morethan3 DLP page through Brand in mobile site
    # for e.g: https://m.bloomingdales.com/buy/tory-burch-reva-flat
    Then I should not see the meta name tag created in DOM

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0 @use_manual @manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigates exactly3 DLP page through Brand in mobile site
      # for e.g: https://m.bloomingdales.com/buy/flying-monkey-jeans
    Then I should not see the meta name tag created in DOM

  @domain_marketing @artifact_navapp @project_SEO_optimization @priority_high @release_17T @artifact_MeW2.0 @use_manual @manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for less than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigates lessthan3 DLP page through Brand in mobile site
      # for e.g: https://m.bloomingdales.com/buy/rivieras-shoes
    Then I should not see the meta name tag created in DOM

