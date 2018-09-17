# Author: SEO Improvements 2017
# Date Created: 01/11/2018
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:NavApp:Desktop: Add noindex,nofollow on all DLP pages with less than 3 assortments
# Versionone story numbers::  B-102105, B-101959
#########################################################################################################################
Feature: As an MCOM SEO Business Manager, I want a noindex,nofollow tag on all MCOM DLP pages with less than 3 assortments (pages with 2 or less assortments will get this tag)
########################################################## DLP Page - domestic mode #########################################
  @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17ZA
  Scenario: Verify the robots meta tag in DOM for a whitelist keyword which has more than 3 assortments on DLP Page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the keyword with "whitelist_and_more_than_three_assortments" on the page
       # for e.g: https://www.qa20codemacys.fds.com/shop/featured/22"%20green%20softside%20hardside
    Then I should see "index, follow" attribute is appended in robots meta tag in view page source
      # for e.g:  <meta name="robots" content="index, follow">

  @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17ZA
  Scenario: Verify the robots meta tag section in DOM for a whitelist keyword with exactly 3 assortments on DLP Page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the keyword with "whitelist_and_three_assortments" on the page
  # for e.g: https://www.qa20codemacys.fds.com/shop/featured/30%22%20&%20up%20red%20lightweight%20spinner
    Then I should see "index, follow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="index, follow">

  @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17ZA
  Scenario: Verify the robots meta tag section in DOM for a whitelist keyword with less than 3 assortments on DLP Page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the keyword with "whitelist_and_less_than_three_assortments" on the page
      # for e.g: https://www.qa20codemacys.fds.com/shop/featured/10x13%20multi%20animal%20prints%20rugs
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17ZA
  Scenario: Verify the robots meta tag section in DOM for a blacklist keyword with zero products on DLP Page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the keyword with "blacklist_and_zero_assortments" on the page
    # for e.g: https://www.qa20codemacys.fds.com/shop/featured/xyz0
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex, nofollow">

  @domain_marketing @artifact_xapi @project_SEO_2017 @priority_high @release_17ZA
  Scenario: Verify the robots meta tag section in DOM for a blacklist keyword with more than 3 assortments on DLP Page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the keyword with "blacklist_and_more_than_three_assortments" on the page
        # for e.g: https://www.qa20codemacys.fds.com/shop/featured/jeans
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
      # for e.g: <meta name="robots" content="noindex, nofollow">
