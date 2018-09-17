# Author: SEO Improvements 2016
# Date Created: 6/15/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::NavApp:Tech: Change on-site search url pattern to /shop/featured/ and MCOM::UI:Tech: Update all keyword search results page to use RESTful URL for SEO
# Versionone story numbers:: B-49512 and B-51796
# Note:: Prerequisite: seoImprovements2016Enabled kill switch should set to TRUE
#########################################################################################################################

Feature: Business want to change on-site search url pattern from /shop/search?keyword=<search phrase> to /shop/featured/<search Phrase>.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that search shows the new url pattern in Site mode
    Given I visit the web site as a guest user
    When I search for "Dresses"
    Then I should see the new search url pattern
    Then I should not see "keyword" in the search base url
    #Ex: .../shop/featured/<search phrase>

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that autocomplete suggestions shows the new url pattern in Site mode
    Given I visit the web site as a guest user
    When I type "sh" in search box
    Then I should see autocomplete suggestions
    When I select random autocomplete suggestion
    Then I should see the new search url pattern
    Then I should not see "keyword" in the search base url

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that the new url is persisted when user selects a facet value fron left navigation in Site mode
    Given I visit the web site as a guest user
    When I search for "Boots"
    Then I should see the new search url pattern
 #Ex: .../shop/featured/Boots
    When I select "Black" item from "Color" facet on left nav
    Then I should see the new url pattern appended with the facet type and value details
    #Ex: .../shop/featured/Boots/facet-type/facet-value

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that the new url is persisted when user navigates to a PDP page and then clicks on back button to search page in Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    #Then I should see the new search url pattern
    #Ex: .../shop/featured/Jeans
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    Then I should see the new search url pattern

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that search shows the new url pattern in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "Dresses"
    Then I should see the new search url pattern
    Then I should not see "keyword" in the search base url
    #Ex: .../shop/featured/<search phrase>

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that autocomplete suggestions shows the new url pattern in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I type "sh" in search box
    Then I should see autocomplete suggestions
    When I select random autocomplete suggestion
    Then I should see the new search url pattern
    Then I should not see "keyword" in the search base url
    #Ex: .../shop/featured/<search phrase>

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that the new url is persisted when user selects a facet value fron left navigation in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "Boots"
    Then I should see the new search url pattern
 #Ex: .../shop/featured/Boots
    When I select "Black" item from "Color" facet on left nav
    Then I should see the new url pattern appended with the facet type and value details
    #Ex: .../shop/featured/Boots/facet-type/facet-value

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that the new url is persisted when user navigates to a PDP page and then clicks on back button to search page in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "Jeans"
    #Then I should see the new search url pattern
    #Ex: .../shop/featured/Jeans
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    Then I should see the new search url pattern
