# Author: SEO Improvements 2016
# Date Created: 08/30/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Title: MCOM: Keyword Search : Func: New URL pattern for search results page.
# Version One story numbers:: B-49088
# Pre-requisite: seoImprovements2016Enabled kill switch set to TRUE
#########################################################################################################################

Feature:  For Keyword Search, new URL pattern should be displayed for search results page and for registry it should be the Old url.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify search on Registry home page is showing the old url
    Given I visit the web site as a guest user in "registry" mode
    And  I search for "Cookware"
    Then I verify that old search url for registry is displayed

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that search from registry catsplash is showing old url
    Given I visit the web site as a guest user in "registry" mode
    When I click on any random FOB
    And  I search for "Grill"
    Then I verify that old search url for registry is displayed
    
  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that search from registry manager page is showing old url
    Given I visit the website as a bvr user using rest services
    And I navigate to registry manager page
    And  I search for "Grill"
    Then I verify that old search url for registry is displayed



