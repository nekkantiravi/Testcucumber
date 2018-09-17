#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Sep.10,2017
#---------------------------------------------------


# RunManually Scenarios should be V/V 2 days prior to any BranchCut/CodeFreeze
Feature: RunManually Validation & Verification


  @mcom_selection_pdp_manual
  Scenario: Verify video functionality on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify that "Video" is "loading" on PDP "site" mode
    And Manually verify Video, Audio, Play, Pause, rePlay, Fullscreen, ExitFullscreen, etc.


  @mcom_selection_pdp_manual
  Scenario: Verify that webCollage is displayed on PDP
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify that "webCollage" is "displayed" on PDP "site" mode
    And Manually verify webCollage Video, Audio, Play, Pause, rePlay, Fullscreen, ExitFullscreen, etc.


  @mcom_selection_pdp_manual
  Scenario: Verify zoomer & alternate images on member PDP Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    Then I verify the zoomer and altimages on member PDP site mode
    And Manually verify image changes for zoomer & alternate images on member PDP


  @mcom_selection_pdp_manual
  Scenario: Verify zoomer & alternate images on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I verify the zoomer and altimages on master PDP site mode
    And Manually verify image changes for zoomer & alternate images on master PDP


  @mcom_selection_pdp_manual
  Scenario: Verify TrueFit functionality on PDP in Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    Then I verify "TrueFit" functionality on member PDP site mode
    And Manually verify TrueFit overlay text boxes & E2E functionality on PDP


  @mcom_selection_pdp_manual
  Scenario: Verify the GIFTNOW on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productId>" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the GIFTNOW on member PDP
    And Manually verify GIFTNOW overlay text boxes & E2E functionality on PDP
