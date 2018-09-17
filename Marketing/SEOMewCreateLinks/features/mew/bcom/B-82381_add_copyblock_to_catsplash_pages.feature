# Author: SEO Link Module
# Date Created: 6/19/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM: MEW:: Add Copy Block to CatSplash Pages
# Version One story numbers::  B-82381
#########################################################################################################################

Feature: As a SEO Business User I want add a Copy Block in Category Splash Pages for BCOM MEW.

  ###################################################### Category Splash Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17L @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the Category Splash page view source in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then  I should see copyblock displayed in UI
    And   I should see copy blocks displayed higher up in the page view source

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17L @priority_high
  Scenario: Verify that Copy Block should contain more or less option in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17L @priority_high
  Scenario: Verify that "Popular Related Searches" should not be displayed on the top of Copy Block content in UI in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see copyblock displayed in UI
    And I should see copy block without the header Popular Related Searches


    ###################################################### Category Splash Page on MEW -intl mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17L @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the Category Splash page view source in INTERNATIONAL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then  I should see copyblock displayed in UI
    And   I should see copy blocks displayed higher up in the page view source

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17L @priority_high
  Scenario: Verify that Copy Block should contain more or less option in INTERNATIONAL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17L @priority_high
  Scenario: Verify that "Popular Related Searches" should not be displayed on the top of Copy Block content in UI in INTERNATIONAL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then I should see copyblock displayed in UI
    And I should see copy block without the header Popular Related Searches