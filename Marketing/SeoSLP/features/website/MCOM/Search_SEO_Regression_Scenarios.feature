# Author: SEO Regression
# Date Created: 23/10/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM/BCOM : SEO : QE Automation Regression
# Version One story numbers::  B-95220
#########################################################################################################################

Feature: This is a QE only Automation story where we will address all the SEO functionality Automation back log. Will add the confluence page here which will have the SEO functionality that is Automated and yest to be Automated.

    #################################### Search page - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the Alternate, Robots and Canonical tag in Onsite search page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "august hats"
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url

  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the copy block functionality in Onsite search page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "august hats"
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I select 'single' facet value from 'any' facet section
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI
    And  I select "Price: High to Low" in sort by drop down
    Then I should see copyblock displayed in UI
    When I select "4" Column Grid icon
    Then I should see copyblock displayed in UI
    When I select "3" Column Grid icon
    Then I should see copyblock displayed in UI
    And  I select "60" item count option
    Then I should see copyblock displayed in UI

    #################################### Search page - Iship Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the tags(canonical,alternate,robots) in Onsite search page in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "august hats"
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url

  @domain_marketing @artifact_navapp @project_SEO_Regression @priority_high @release_17T
  Scenario: Verify the copy block functionality in Onsite search page in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "august hats"
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I select 'single' facet value from 'any' facet section
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI
    And  I select "Price: High to Low" in sort by drop down
    Then I should see copyblock displayed in UI
    When I select "4" Column Grid icon
    Then I should see copyblock displayed in UI
    When I select "3" Column Grid icon
    Then I should see copyblock displayed in UI
    And  I select "60" item count option
    Then I should see copyblock displayed in UI


