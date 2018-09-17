# Author: SEO Link Module
# Date Created: 12/7/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM: MEW: Create links for DLP facets on WSSG Search results
# Version One story numbers::   B-84127
#########################################################################################################################

Feature: As an SEO architect, I want facet links to be available in navigation to users and bots through the html snapshot.
  
###################################################### DLP Page  ########################################################################################

 #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17O @priority_high
  Scenario: Verify that facet links should display for crawble facets in DOM when 'CONFIG_SEO_CREATE_FACETS_LINKS' is set to 'ON' for DLP page
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    And I refresh current page
    Then I should see the anchor tags created for all facet type/value combinations in DOM

    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17O @priority_high
  Scenario: Verify that facet links should not display for noncrawble size facets in DOM when 'CONFIG_SEO_CREATE_FACETS_LINKS' is set to 'ON' for DLP page
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    When I refresh current page
    Then I should not see the anchor tags for noncrawable facet type/value combinations in DOM

    #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17O @priority_high
  Scenario: Verify that facet link URLS should not display in DOM when page url has facets.
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    And I refresh current page
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see the facet links URLS in DOM

     #Pre-requisite:- KS(CONFIG_SEO_CREATE_FACETS_LINKS) = ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17O @priority_high
  Scenario: Verify that facet link URLS should not display in DOM for page index more than 1
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    And I refresh current page
    And I should be able to navigate using pagination functionality using mobile website
    Then I should not see the facet links URLS in DOM
