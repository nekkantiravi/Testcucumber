#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page functionality in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @mode_iship @backlog_discovery
  Scenario: Home Page - Verify LOGO is displayed in DOMESTIC and ISHIP modes
    Given I visit the web site as a guest user
    Then I verify logo is displayed and returns a 200 OK
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify logo is displayed and returns 200 OK
    #Notes:
    #Verify that the logo image links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify LOGO is displayed in REGISTRY mode
    Given I visit the web site as a registry user
    Then I verify logo is displayed and returns 200 OK
    #Notes:
    #Verify that the log image links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery @use_domain_qual
  Scenario: Home Page - Verify MINI BAG overlay in DOMESTIC and ISHIP modes
    Given I visit the web site as a new registered user
    Then I hover over on the MINI BAG
    And I verify an overlay is displayed with "Your brown bag is empty." message
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I hover over on the MINI BAG
    And I verify an overlay is displayed with "Your brown bag is empty." message
    #Notes:

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify MINI BAG is displayed in REGISTRY mode
    Given I visit the web site as a registry user
    Then I hover over on the MINI BAG
    And I verify an overlay is displayed with "Your brown bag is empty." message
    #Notes:

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page - Verify INFO and EXCLUSIONS popup in DOMESTIC mode
    Given I visit the web site as a guest user
   # Then I close popUp on home page
    When I click on INFO and EXCLUSIONS link on top of search field
    And I verify Info and Exclusions Popup is rendered with content
  #Notes:

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify INFO and EXCLUSIONS is not diaplyed in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify INFO and EXCLUSIONS link is not displayed
    #Notes:

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify INFO and EXCLUSIONS popup in REGISTRY Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
   # Then I close popUp on home page
    When I click on INFO and EXCLUSIONS link on top of search field
    And I verify Info and Exclusions Popup is rendered with content
    #Notes:

  #TBD - INFO and EXCLUSIONS for REGISTRY mode

  #Test Case ID: BLCOM-77477
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page  - Verify GNA in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify the display of GNA
    # And I verify GNA is displayed and returns 200 OK
    # Notes: Replace existing step def to look for the expected assets for GNA and GFA from DB
    # and validate the appropriate assets are displayed in site.

  #Test Case ID: BLCOM-77477
  @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page  - Verify GFA in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify the display of GFA
    # And I verify GFA is displayed and returns 200 OK
    # Notes: Replace existing step def to look for the expected assets for GNA and GFA from DB
    # and validate the appropriate assets are displayed in site.

  #TBD - add scenarios for ISHIP and REGISTRY mode GNA & GFA verification


