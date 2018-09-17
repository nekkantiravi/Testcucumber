#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page functionality in DOMESTIC, ISHIP and REGISTRY mode

  @backlog_discovery @artifact_navapp @domain_marketing @priority_high
  Scenario: Home Page - Verify Top Reviewer Star in DOMESTIC mode
    Given I visit the web site as a registered user
    And I select Top Reviewer Star element
    Then I verify Top Reviewer Star overlay is displayed
    #Notes:

  @backlog_discovery @artifact_navapp @domain_marketing @priority_high @mode_registry
  Scenario: Home Page - Verify Top Reviewer Star in REGISTRY mode
    Given I visit the web site as a registry user
    And I select Top Reviewer Star element
    Then I verify Top Reviewer Star overlay is displayed
    #Notes:

  @backlog_discovery @artifact_navapp @domain_marketing @priority_high @mode_registry
  Scenario: Home Page - Verify Top Reviewer Star in REGISTRY Home Page
    Given I visit the web site as a registered user
    When I navigate to the registry home page
    And I select Top Reviewer Star element
    Then I verify Top Reviewer Star overlay is displayed
    #Notes:

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page - Verify TOPNAV elements are displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify TOPNAV elements are displayed and return a 200 OK
      | TOPNAV           |
      | sign in          |
      | my account       |
      | stores           |
      | deals            |
      | lists            |
      | gifts            |
      | wedding registry |
    #Notes:
    #Verify that the links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify TOPNAV elements are displayed in REGISTRY mode
    Given I visit the web site as a registry user
    Then I verify TOPNAV elements are displayed and return a 200 OK
      | TOPNAV           |
      | sign in          |
      | my account       |
      | stores           |
      | customer service |
    #Notes:
    #Verify that the links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify TOPNAV elements are displayed in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    Then I verify TOPNAV elements are displayed and return a 200 OK
      | TOPNAV           |
      | stores           |
      | customer service |
      | shipping to      |
      | IN flag          |

    #Notes:
    #Verify that the image and links returns a 200 OK using httpparty


  #TBD - registered user scenarios for all modes

  #Testlink-MCOM-85391
  #Vone - RT-07342
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery @prod
  Scenario: Home Page - Verify global adpools in Registry mode
    Given I visit the web site as a guest user
    And I verify GNA is displayed and returns 200 OK
    And I verify GFA is displayed and returns 200 OK
    # Notes: Replace existing step def to look for the expected assets for GNA and GFA from DB
    # and validate the appropriate assets are displayed in site.

  #TBD - scenarios for ISHIP and REGISTRY mode GNA & GFA verification