# Author: SEO Auto Facet
# Date Created: 03/10/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM::MEW::Integration: Add Copy Block in DLP Pages
# Version One story numbers::  B-74343
#########################################################################################################################

Feature: As a SEO Business Manager, I want add a Copy Block in DLP Pages for MEW pages in BCOM.
  ###################################################### DLP Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the page view source in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see copy blocks displayed higher up in the page view source
    When I refresh current page
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

###################################################### DLP Page on MEW - iship mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the page view source in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source

  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should contain more or less option in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media