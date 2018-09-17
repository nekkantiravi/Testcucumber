# Author: SEO Improvements 2016
# Date Created: 7/15/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::UI::Tech: HUB Signal data dictionary update
# Versionone story numbers:: B-54477
# Pre-requisite: seoImprovements2016Enabled kill switch set to TRUE
#########################################################################################################################

Feature:  Social marketing business head has requested that we provide via a tag on the keyword search results page, the first 6 product IDs from the results shown to the customer to Facebook. this will help them retarget better to the customers who use keyword search on our site.

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @use_manual @release_16K @use_regression
  Scenario: Verify the product IDs of search keywords should be displayed in topProducts field of bright tag
    Given I visit the web site as a guest user
    When I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products

  @domain_marketing @artifact_navapp @project_SEO_imrugsprovements @priority_high @release_16K @use_regression
  Scenario: Verify the product IDs of search keywords should be displayed in topProducts field of bright tag in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify the product IDs of search keywords should be displayed in topProducts field of bright tag in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    When I search for "cookware"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products


  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @manual
  Scenario: Verify that the product IDs of search keywords(which has less than 6 results) should be displayed in topProducts field of bright tag
    Given I visit the web site as a guest user in "domestic|iship" mode
    When I search for "Dresses"
    Then I should see the product IDs(less than 6 that are shown) under category in topProducts field of bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that the product IDs of search keywords(which return zero results) should not be displayed in topProducts field of bright tag in Site mode
    Given I visit the web site as a guest user
    When I search for "xyza"
    Then I should not see the product IDs under category in topProducts field of bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that the product IDs of search keywords(which return zero results) should not be displayed in topProducts field of bright tag in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "xyza"
    Then I should not see the product IDs under category in topProducts field of bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that the product IDs of search keywords(which return zero results) should not be displayed in topProducts field of bright tag in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "xyza"
    Then I should not see the product IDs under category in topProducts field of bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs filter functionality
    Given I visit the web site as a guest user
    When I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    When I select one facet value in facet panel with crawlable facet
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs filter functionality in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    When I select one facet value in facet panel with crawlable facet
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs filter functionality in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "bed sheets"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    When I select one facet value in facet panel with crawlable facet
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs sort by funtionality
    Given I visit the web site as a guest user
    When I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    When I select "Price: High to Low" in sort by drop down
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs sort by funtionality in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    When I select "Price: High to Low" in sort by drop down
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs sort by funtionality in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "Cookware"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    When I select "Price: High to Low" in sort by drop down
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that topProducts field should be displayed blank in bright tag, when search keyword redirected to browse category
    Given I visit the web site as a guest user
    When I search for "rugs"
    Then I should see it is redirected to browse category
    And I should not see the product IDs under category in topProducts field of bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs pagination
    Given I visit the web site as a guest user
    When I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    And I navigate to the next page
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs performs pagination in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "Dresses"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    And I navigate to the next page
    Then I should not see any change in product IDs under category of search keywords in bright tag

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16K @use_regression
  Scenario: Verify that first 6 product IDs of search keywords should not be changed in bright tag when user performs pagination in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "Cookware"
    Then I should see product IDs under category in topProducts field of bright tag
    And I should see products under bright tag should match with UI top products
    And I navigate to the next page
    Then I should not see any change in product IDs under category of search keywords in bright tag