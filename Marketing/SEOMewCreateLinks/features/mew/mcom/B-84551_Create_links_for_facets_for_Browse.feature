# Author: SEO Improvements 2017
# Date Created: 6/9/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM/BCOM: MEW: Create links for facets for Browse
# Versionone Story#B-81383
#########################################################################################################################

Feature: As an SEO architect, I want facet links to be available in navigation to users and bots through the html snapshot..

  ###################################################### Browse Page  ########################################################################################
  #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet links should display for crawble facets in DOM when 'CONFIG_SEO_CREATE_FACETS_LINKS' is set to 'ON' for category page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I refresh current page
    Then I should see the anchor tags created for all facet type/value combinations in DOM

    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet links should not display for noncrawble size facets in DOM when 'CONFIG_SEO_CREATE_FACETS_LINKS' is set to 'ON' for category page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    When I refresh current page
    Then I should not see the anchor tags for noncrawable facet type/value combinations in DOM

    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet link URLS should not display in DOM when page url has facets.
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I refresh current page
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see the facet links URLS in DOM


    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet link URLS should not display in DOM for page index more than 1
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I refresh current page
    And I should be able to navigate using pagination functionality using mobile website
    Then I should not see the facet links URLS in DOM

###################################################### SLP Page  ########################################################################################
  #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet links should display for crawble facets in DOM when 'CONFIG_SEO_CREATE_FACETS_LINKS' is set to 'ON' for SLP page
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    And I refresh current page
    Then I should see the anchor tags created for all facet type/value combinations in DOM

    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet links should not display for noncrawble size facets in DOM when 'CONFIG_SEO_CREATE_FACETS_LINKS' is set to 'ON' for SLP page
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    When I refresh current page
    Then I should not see the anchor tags for noncrawable facet type/value combinations in DOM

    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet link URLS should not display in DOM when page url has facets on SLP page.
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    And I refresh current page
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see the facet links URLS in DOM


    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17N @priority_high
  Scenario: Verify that facet link URLS should not display in DOM for page index more than 1 on SLP page
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    And I refresh current page
    And I should be able to navigate using pagination functionality using mobile website
    Then I should not see the facet links URLS in DOM

