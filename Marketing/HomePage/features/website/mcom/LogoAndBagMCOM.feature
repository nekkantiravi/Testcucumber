#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page functionality in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @mode_iship @backlog_discovery
  Scenario: Home Page - Verify LOGO is displayed in DOMESTIC & ISHIP mode
    Given I visit the web site as a guest user
    Then I verify logo is displayed and returns a 200 OK
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify logo is displayed and returns a 200 OK
    #Notes:
    #Verify that the logo image links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify LOGO is displayed in REGISTRY mode
    Given I visit the web site as a registry user
    Then I verify logo is displayed and returns a 200 OK
    #Notes:
    #Verify that the log image links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @mode_iship @backlog_discovery
  Scenario: Home Page - Verify MINI BAG overlay in DOMESTIC & ISHIP modes
    Given I visit the web site as a registered user
    Then I hover over on the MINI BAG
    And I verify an overlay is displayed with "0 items in your bag. Shop great deals now!" message
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I hover over on the MINI BAG
    And I verify an overlay is displayed with "0 items in your bag. Shop great deals now!" message
    #Notes:

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify MINI BAG is displayed in REGISTRY mode
    Given I visit the web site as a registry user
    Then I hover over on the MINI BAG
    And I verify an overlay is displayed with "0 items in your bag. Shop great deals now!" message
    #Notes:

  #Testlink-MCOM-96546
  #Vone - RT-07339
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery @prod
  Scenario: Home Page - Verify GNA in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify GNA is displayed and returns 200 OK
    # Notes: Replace existing step def to look for the expected assets for GNA and GFA from DB
    # and validate the appropriate assets are displayed in site.

  #Testlink-MCOM-96546
  #Vone - RT-07339
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery @prod
  Scenario: Home Page - Verify GFA in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify GFA is displayed and returns 200 OK
    # Notes: Replace existing step def to look for the expected assets for GNA and GFA from DB
    # and validate the appropriate assets are displayed in site.

  #TBD - what about ISHIP and REGISTRY mode GNA & GFA verification

