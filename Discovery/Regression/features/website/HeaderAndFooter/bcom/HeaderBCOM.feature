Feature: Verification of BCOM Header functionality in DOMESTIC, ISHIP and REGISTRY modes

  #########################################################  Bloomingdales Logo link ###########################################

  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: Header - Verify the Bloomingdales Logo on catsplash page in DOMESTIC mode
    Given I visit the web site as a "guest" user in "site" mode
    When I navigate directly to category splash page
    Then I verify that Bloomingdales logo takes to Home Page


  @use_regression @artifact_navapp @domain_discovery @priority_high @s4a_stable @use_regression_1 @mode_iship
  Scenario: Header - Verify the Bloomingdales Logo on browse page in ISHIP mode
    Given I visit the web site as a "guest" user in "iship" mode
    When I navigate to category browse page
    Then I verify that Bloomingdales logo takes to Home Page

  @use_regression @artifact_navapp @domain_discovery @priority_high @s4a_stable @use_regression_1 @mode_registry
  Scenario: Header - Verify the Bloomingdales Logo on search results page in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I navigate to search result page through "plates" keyword
    Then I verify that Bloomingdales logo takes to Home Page

  ################ Verification GNA and GFA in domestic, iship and registry modes ######################################

  @use_regression @priority_high @use_dsv @artifact_navapp @domain_discovery @mode_domestic @use_regression_1 @dsv_desktop_sev2
  Scenario: BrowsePage -  Verify GNA in Home Page, Cat Splash Page and Category Browse Page in DOMESTIC Mode
    Given I visit the web site as a guest user
    When I verify GNA for "DOMESTIC" mode
    Then I navigate to "WOMEN" category page
    When I verify GNA for "DOMESTIC" mode
    Then I navigate directly to "Dresses" page
    When I verify GNA for "DOMESTIC" mode

  @use_dsv @use_regression @priority_low @artifact_navapp @domain_discovery @mode_domestic @dsv_desktop_sev2
  Scenario: HomePage - Verify that the global banner appears with content on homepage in domestic mode
    Given I visit the web site as a guest user
    Then I verify header global banner appears without errors and contains the text "FREE SHIPPING"
    And I verify footer global banner appears without errors and contains the free shipping text

  @B-45479 @use_dsv @use_regression @priority_low @artifact_navapp @domain_discovery @mode_iship @dsv_desktop_sev2
  Scenario: HomePage - Verify that the header and footer global banner does not display in iShip mode
    Given I visit the web site as a "guest" user in "iship" mode
    When I close the welcome mat if it's visible
    Then I verify that header global banner is not displayed
    And I verify that footer global banner is not displayed

  @bcom_regression @use_dsv @use_regression @priority_low @artifact_navapp @domain_discovery @mode_iship @dsv_desktop_sev2
  Scenario Outline: HomePage - Verigy that GIFTS and BEAUTY fobs are suppressed in international mode
    Given I visit the web site as a "guest" user in "iship" mode
    Then I should not see the following "<Category>" FOBs
    Examples:
      |Category|
      | GIFTS  |
      | BEAUTY |

  @use_regression @priority_low @artifact_navapp @domain_discovery @mode_iship
  Scenario: BrowsePage -  Verify suppressed icons in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I should see ISHIP Header or Footer with "AU" flag
    Then I verify the footer links in "Iship" Mode
    And I navigate registry home page in iship mode
    Then I verify the ability to go to US site from International Context page

  @use_regression @priority_high @use_dsv @artifact_navapp @domain_discovery @mode_iship @iship_1 @s4a_stable @use_regression_1
  Scenario: BrowsePage -  Verify GNA in Home Page, Cat Splash Page and Category Browse Page in ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify GNA for "iship" mode
    Then I navigate to "WOMEN" category page
    And I verify GNA for "iship" mode
    Then I navigate directly to "Dresses" page
    And I verify GNA for "iship" mode

  @use_regression @priority_high @use_dsv @artifact_navapp @domain_discovery @mode_registry @use_regression_1
  Scenario: BrowsePage - Verify GNA in Home Page, Cat Splash Page and Category Browse Page in REGISTRY Mode
    Given I visit the web site as a registry user
    And I verify GNA for "registry" mode
    Then I navigate to "KITCHEN" category page
    And I verify GNA for "registry" mode
    Then I navigate directly to "CUTLERY" page
    And I verify GNA for "registry" mode