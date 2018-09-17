# Author: SEO Auto Facet
# Date Created: 03/10/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::MEW::Integration: Add Copy Block in DLP Pages
# Version One story numbers::  B-70894
#########################################################################################################################

Feature: As a SEO Business Manager, I want add a Copy Block in DLP Pages for MEW pages in MCOM.

  ###################################################### DLP Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the page view source in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    Then I should see copy blocks displayed higher up in the page view source

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should contain more or less option in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media

    ###################################################### DLP Page on MEW -intl mode #########################################################################################################

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the page view source in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    Then I should see copy blocks displayed higher up in the page view source

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should contain more or less option in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media


  ###################################################### SLP Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for SLP page after user selects a facet in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "SLP_COPY_BLOCK_WITH_FACETS" SLP category
    Then I should see copyblock displayed in UI
    And I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    And I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block display functionality in the UI for SLP page when user performs pagination action (2,3..) in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "SLP_COPY_BLOCK_WITH_FACETS" SLP category
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for SLP page after user performs sort by action in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "SLP_COPY_BLOCK_WITH_FACETS" SLP category
    Then I should see copyblock displayed in UI
    And I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
    Then I should not see copy block on the page

#
#    ###################################################### SLP Page on MEW - iship mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the SLP page after user selects a facet in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the "SLP_COPY_BLOCK_WITH_FACETS" SLP category
    Then I should see copyblock displayed in UI
    When I select "Color" facet on left nav using mobile website
    And I select "Blue" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block display functionality in the UI for the SLP page when user performs pagination action (2,3..) in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the "SLP_COPY_BLOCK_WITH_FACETS" SLP category
    Then I should see copyblock displayed in UI
    When I click second page in pagination
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should not be displayed in the UI for the SLP page after user performs sort by action in INTL mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the "SLP_COPY_BLOCK_WITH_FACETS" SLP category
    Then I should see copyblock displayed in UI
    And I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
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
    When I deselect all facet values in facet panel
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
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
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
    When I deselect all facet values in facet panel
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
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
    Then I should not see copy block on the page