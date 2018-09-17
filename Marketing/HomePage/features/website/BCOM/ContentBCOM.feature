#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page - Verify Content in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify content is displayed properly
    #Notes:
    #Verify bl_main_content div images and links all return 200 OK on http party

  @use_regression @artifact_navapp @domain_marketing @priority_high @use_prod @mode_iship @backlog_discovery
  Scenario: Home Page - Verify Content in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify content is displayed properly
    #Notes:
    #Verify bl_main_content div images and links all return 200 OK on http party

  @use_regression @artifact_navapp @domain_marketing @priority_high @use_prod @mode_registry @backlog_discovery
  Scenario: Home Page - Verify Content in REGISTRY mode
    Given I visit the web site as a registry user
    And I verify content is displayed properly
    #Notes:
    #Verify bl_main_content div images and links all return 200 OK on http party

  @artifact_navapp @domain_marketing @priority_low @use_prod @mode_registry @backlog_discovery
  Scenario: Home Page - Verify Content in REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I verify content is displayed properly
    #Notes:
    #Verify bl_main_content div images and links all return 200 OK on http party

  #TBD - add scenarios to verify Header is displayed in all modes
  #TBD - add scenarios to verify Footer is displayed in all modes
