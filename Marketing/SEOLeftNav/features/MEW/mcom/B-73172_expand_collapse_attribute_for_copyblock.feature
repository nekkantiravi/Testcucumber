# Author: SEO Improvements 2016
# Date Created: 4/4/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:MEW: Consume expand/collapse attribute for Copy Block display in SLP pages
# Versionone story numbers::  B-73172
#########################################################################################################################

Feature: MCOM:MEW: Consume expand/collapse attribute for Copy Block display in SLP pages
  ####################################################### SLP Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @artifact_MeW2.0
  Scenario: Verify that Copy Block should be expanded by default in UI when "COPY_BLOCK_ALWAYS_EXPAND_MEW" attribute is set to true in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see copyblock displayed in UI
    And I should see copy block is expanded by default

  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @artifact_MeW2.0
  Scenario: Verify that Copy Block should be collapsed by default in UI when "COPY_BLOCK_ALWAYS_EXPAND_MEW" attribute is set to false in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_FALSE" SLP category
    Then I should see copyblock displayed in UI
    And I should see each copy block is collapsed by default
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media

  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @artifact_MeW2.0
  Scenario: SLP Page: Verify that in every Copy Block "COPY_BLOCK_ALWAYS_EXPAND_MEW" attribute is set to false and contains expand/collapse options when there are multiple copy blocks in UI in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_FALSE" SLP category
    Then I should see copyblock displayed in UI
    And I should see each copy block is collapsed by default
#
# #######################################################  SLP Page on MEW - Iship mode #########################################################################################################
  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @artifact_MeW2.0
  Scenario: Verify that Copy Block should be expanded by default in UI when "COPY_BLOCK_ALWAYS_EXPAND_MEW" attribute is set to true in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see copyblock displayed in UI
    And I should see copy block is expanded by default

  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @artifact_MeW2.0
  Scenario: Verify that Copy Block should be collapsed by default in UI when "COPY_BLOCK_ALWAYS_EXPAND_MEW" attribute is set to false in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_FALSE" SLP category
    Then I should see copyblock displayed in UI
    And I should see each copy block is collapsed by default
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media

  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @artifact_MeW2.0
  Scenario: SLP Page: Verify that in every Copy Block "COPY_BLOCK_ALWAYS_EXPAND_MEW" attribute is set to false and contains expand/collapse options when there are multiple copy blocks in UI in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_FALSE" SLP category
    Then I should see copyblock displayed in UI
    And I should see each copy block is collapsed by default