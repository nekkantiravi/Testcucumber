# Author: SEO Auto Facet
# Date Created: 03/10/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM::MEW:Add Copy Block to Category Pages
# Version One story numbers::  B-71268
#########################################################################################################################

Feature: As a SEO Business User I want add a Copy Block in Category Browse Pages for BCOM MEW.

  ###################################################### Browse Page on MEW -site mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the page view source in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source

    # pre-condition : Copy block attribute MEW_Copy_block_Always_expand should be False i.e copy block should be collapsed
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Browse Page:Verify that Copy Block should contain more or less option in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media

   ###################################################### Browse Page on MEW -intl mode #########################################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should be displayed in UI and should be displayed higher up in the page view source in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source

    #pre-condition : Copy block attribute MEW_Copy_block_Always_expand should be False i.e copy block should be collapsed
  @domain_marketing @artifact_MeW2.0 @project_SEO_Auto_Facets_MeW @release_17H @priority_high
  Scenario: Verify that Copy Block should contain more or less option in INTL mode on MEW
    Given I visit the mobile web site as a guest user in iship mode
    When  I navigate to category browse page that has copy block
    Then I should see copyblock displayed in UI
    And I should see more option in the copyblock media
    When I click on more option
    Then I should see the copy block expanded with less option
    When I click on less option
    Then I should see more option in the copyblock media