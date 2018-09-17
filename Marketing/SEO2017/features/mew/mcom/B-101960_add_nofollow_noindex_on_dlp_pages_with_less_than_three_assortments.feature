# Author: SEO Improvements 2017
# Date Created: 01/10/2018
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:XAPI:MEW: Add noindex,nofollow on all DLP pages with less than 3 assortments
# Versionone story numbers::  B-101960
#########################################################################################################################
Feature: As an MCOM SEO Business Manager, I want a noindex,nofollow tag on all MCOM DLP pages with less than 3 assortments (pages with 2 or less assortments will get this tag)
########################################################## DLP Page - domestic mode #########################################
  # Pre-requisite: KS property CONFIG_SEO_DLP_NOINDEX = "ON"
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi
  Scenario: Verify the robots meta tag in DOM for a whitelist keyword which has more than 3 assortments on DLP Page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to the keyword with "whitelist_and_more_than_three_assortments" on the page
       # for e.g: https://m.qa20codemacys.fds.com/shop/featured/22%22%20green%20softside%20hardside
    Then I should see "index, follow" attribute is appended in robots meta tag in view source page

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi
  Scenario: Verify the robots meta tag section in DOM for a whitelist keyword with exactly 3 assortments on DLP Page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to the keyword with "whitelist_and_three_assortments" on the page
  # for e.g: https://m.qa20codemacys.fds.com/shop/featured/30%22%20&%20up%20red%20lightweight%20spinner
    Then I should see "index, follow" attribute is appended in robots meta tag in view source page

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi
  Scenario: Verify the robots meta tag section in DOM for a whitelist keyword with less than 3 assortments on DLP Page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to the keyword with "whitelist_and_less_than_three_assortments" on the page
      # for e.g: https://m.qa20codemacys.fds.com/shop/featured/10x13%20multi%20animal%20prints%20rugs
#    And I should see the meta name tag created in DOM
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view source page
      # <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi
  Scenario: Verify the robots meta tag section in DOM for a whitelist keyword with zero products on DLP Page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to the keyword with "blacklist_and_zero_assortments" on the page
    # for e.g: https://m.qa20codemacys.fds.com/shop/featured/xyz0
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view source page
      # <meta id="dlp-noindex-nofollow" name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi
  Scenario: Verify the robots meta tag section in DOM for a blacklist keyword with more than 3 assortments on DLP Page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the keyword with "blacklist_and_more_than_three_assortments" on the page
        # for e.g: https://m.qa20codemacys.fds.com/shop/featured/jeans
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view source page

################################## Kill Switch False cases ######################################################################

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi @use_manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for more than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the whitelisted keyword with morethan3 DLP page through Brand in mobile site
    # for e.g: https://m.macys.com/shop/featured/tory-burch-reva-flat
    Then I should not see the meta name tag created in DOM

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi @use_manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the whitelisted keyword with exactly3 DLP page through Brand in mobile site
      # for e.g: https://m.macys.com/shop/featured/flying-monkey-jeans
    Then I should not see the meta name tag created in DOM

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi @use_manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for less than 3 assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the whitelisted keyword with lessthan3 DLP page through Brand in mobile site
      # for e.g: https://m.macys.com/shop/featured/rivieras-shoes
    Then I should not see the meta name tag created in DOM

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17ZA @priority_high @artifact_xapi @use_manual
  Scenario: Verify the META NAME="ROBOTS" Tag section in DOM for zero item assortments while KS(CONFIG_SEO_DLP_NOINDEX) is set to False on DLP Page
    Given I visit the mobile web site as a guest user
    When I navigate to the whitelisted keyword with "zero_product" DLP page through Brand in mobile site
  # for e.g: https://m.macys.com/shop/featured/rivieras-shoes
    Then I should not see the meta name tag created in DOM





