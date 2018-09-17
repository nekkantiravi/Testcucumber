# Author: SEO Improvements 2017
# Date Created: 08/29/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM: Navapp: Desktop: Add noindex,nofollow on all Keyword DLP pages with less than 3 assortments
# Versionone story numbers::  B-89297
#########################################################################################################################
Feature: As a BCOM SEO Business Manager, I want a noindex,nofollow tag on all BCOM Keyword DLP pages with less than 3 assortments (pages with 2 or less assortments will get this tag).

########################################################## DLP Page -site mode #########################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for more than 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to the keyword with "more_than_three_assortments" on the page
       # for e.g: https://m.bloomingdales.com/buy/tory-burch-reva-flat
    Then I should not see robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for exactly 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to the keyword with "equal_to_three_assortments" on the page
      # for e.g: https://m.bloomingdales.com/buy/flying-monkey-jeans
    Then I should not see robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for less than 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to the keyword with "less_than_three_assortments" on the page
      # for e.g: https://www.qa9codebloomingdales.fds.com/buy/aquis
    Then I should see "noindex,nofollow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

######################################## DLP from Designer FOB #########################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for more than 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    When I navigate to left nav "KIDS" category
    And I navigate to "Bestever" DLP page
    Then I should not see robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for exactly 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    When I navigate to left nav "KIDS" category
      # Ex with url pattern : /buy/all-designers/[category]/[brand]/Brand,Fob,Productsperpage
    And I navigate to "Birkenstock" DLP page
    Then I should not see robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for less than 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    When I navigate to left nav "KIDS" category
      # Ex with url pattern : /buy/all-designers/[category]/[brand]/Brand,Fob,Productsperpage
    And I navigate to "Breville" DLP page
    Then I should see "noindex,nofollow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

##################################### Parent Brand DLP from Designer FOB ###################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for more than 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    When I navigate to left nav "ALL DESIGNERS" category
    And I navigate to "Abyss" DLP page
    # Ex with url pattern : /buy/[brand-name]/Brand/brand%20name?id=xxxxx
    Then I should not see robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for exactly 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    When I navigate to left nav "ALL DESIGNERS" category
      # Ex with url pattern : /buy/[brand-name]/Brand/brand%20name?id=xxxxx
    And I navigate to "Aireloom" DLP page
    Then I should not see robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">

  @domain_marketing @artifact_navapp @project_SEO_2017 @priority_high @release_17T 
  Scenario: Verify the robots meta tag section in DOM for less than 3 assortments on DLP Page
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    When I navigate to left nav "ALL DESIGNERS" category
    And I navigate to "Altea" DLP page
    Then I should see "noindex,nofollow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex,nofollow">



