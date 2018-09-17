# Author: SEO Optimization Regression
# Date Created: 10/24/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: QE Only : Add Automation for SEO MeW Regression
# Versionone story numbers::B-98674
########################################################################################################################################################
Feature: In this, we will address all the SEO functionality Automation.
  
    #################################### DLP page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the copy block functionality in DLP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    And I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI
