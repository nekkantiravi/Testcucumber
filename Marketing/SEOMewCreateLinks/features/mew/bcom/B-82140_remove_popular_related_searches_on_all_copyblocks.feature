# Author: SEO Link Module
# Date Created: 03/07/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM: MEW: Remove "Popular Related Searches" on all Copy Blocks
# Version One story numbers::   B-82140
#########################################################################################################################

Feature: As an SEO Business Manager, I would like to NOT see the words "Popular Related Searches" on top of any copy block

  ###################################################### Browse Page on MEW  #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17M @priority_high
  Scenario: Verify that "Popular Related Searches" should not be displayed on the top of Copy Block content in UI in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see copy block without the header Popular Related Searches
   

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW@release_17M @priority_high
  Scenario: Verify that "Popular Related Searches" should not be displayed on the top of Copy Block content in UI in INTERNATIONAL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see copy block without the header Popular Related Searches

    ###################################################### DLP Page on MEW  #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW@release_17M @priority_high
  Scenario: Verify that "Popular Related Searches" should not be displayed on the top of Copy Block content in UI in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see copy block without the header Popular Related Searches

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW@release_17M @priority_high
  Scenario: Verify that "Popular Related Searches" should not be displayed on the top of Copy Block content in UI in INTERNATIONAL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And I should see copy block without the header Popular Related Searches