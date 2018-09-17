# Author: SEO Auto Facet
# Date Created: 24/3/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM::MEW: Facet selection in browse page should clear copy blocks
# Version One story numbers::  B-74345
#########################################################################################################################

Feature: As an SEO Business Manager, I would like to remove Copy Block when a facet is selected in a BCOM browse page.

  ###################################################### Browse Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the Browse page after user selects a facet in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    And I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block display functionality in the UI for the Browse page when user performs pagination action (2,3..) in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the Browse page after user performs sort by action in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |
    Then I should not see copy block on the page

#
#    ###################################################### Browse Page on MEW - iship mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the Browse page after user selects a facet in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    And I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block display functionality in the UI for the Browse page when user performs pagination action (2,3..) in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the Browse page after user performs sort by action in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |
    Then I should not see copy block on the page

   ###################################################### DLP Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the DLP page after user selects a facet in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    And I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block display functionality in the UI for the DLP page when user performs pagination action (2,3..) in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the DLP page after user performs sort by action in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |
    Then I should not see copy block on the page

#
#    ###################################################### DLP Page on MEW - iship mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the DLP Page after user selects a facet in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    And I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block display functionality in the UI for the DLP Page when user performs pagination action (2,3..) in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the DLP Page after user performs sort by action in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |
    Then I should not see copy block on the page