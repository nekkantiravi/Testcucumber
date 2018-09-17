# Author: SEO Improvements 2016
# Date Created: 6/15/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::UI:Tech: DLP and Onsite search using the same UI
# Versionone story numbers:: B-51795
# Pre-requisite: seoImprovements2016Enabled kill switch should set to TRUE
#########################################################################################################################

Feature: In the existing DLP page, H1 tag is used to display the result. Whereas in Onsite Search H3 tag with multiple child span tags are used to display the search result.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that product count message should display in H1 tag on search result page in Site mode
    Given I visit the web site as a guest user
    When I search for "Dresses"
    Then I should see the new search url pattern
    And I should see the above message displayed in "h1" tag instead of h three tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that product count message should display in H1 tag on search result page in International mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "Dresses"
    Then I should see the new search url pattern
    And I should see the above message displayed in "h1" tag instead of h three tag