############################################
# Author: Discovery Regression QE Team
# Date: 16th June 2017
# Date Modified: None
############################################

Feature: Verify header and footer sections dsv features in DOMESTIC, ISHIP and REGISTRY mode

  #MCOM-92081
  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_registry @ifs
  Scenario: HeaderAndFooter - Verify Registry FOBs from Registry Home page
    Given I visit the web site as a guest user in "registry" mode
    Then I should see dynamic top navigation in "registry" mode
    And I should see new header and footer elements section in registry mode
    And I navigate to all fob categories from registry home page

  #Test Case ID: BLCOM-81521, MCOM-85392
  #Testlink-BLCOM-84107
  #vone-RT-06326
  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @priority_high @domain_discovery @mode_iship
  Scenario: HeaderAndFooter - Verify GNA in Home Page, Cat Splash Page and Category Browse Page in ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify GNA for "ISHIP" mode
    Then I navigate to "WOMEN" category page
    And I verify GNA for "ISHIP" mode
    Then I directly navigate to "Dresses" page
    And I verify GNA for "ISHIP" mode

  @use_regression @migrated_to_sdt @priority_high @domain_discovery @mode_registry @dsv_desktop_sev2
  Scenario: BrowsePage - Verify GNA in Home Page, Cat Splash Page and Category Browse Page in REGISTRY Mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I verify GNA for "REGISTRY" mode
    Then I navigate to "KITCHEN" category page
    And I verify GNA for "REGISTRY" mode
    Then I navigate directly to "CUTLERY" page
    And I verify GNA for "REGISTRY" mode

  # BAT Scenario for Launch Call
  #BLCOM-80275 BLCOM-80276
  @use_dsv @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium @launch_call  @dsv_desktop_sev2
  Scenario: SplashPage, SubSplashPage and BrowsePage - Verify FOBs and two browse page in each FOB - Iship Mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I verify the Iship FOBs in below table
      | WOMEN      | Dresses               |
      | MEN        | Polos                 |
      | KIDS       | Girls 7-16            |
      | SHOES      | Sneakers              |
      | HOME       | Cookware              |
      | HANDBAGS   | Hobos & Shoulder Bags |

  @dsv_desktop_sev2 @dsv_dryrun @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @dsv_desktop_sev2
  Scenario: As a customer, I want to verify map, weekdays, date and hours on store details page in domestic mode
    Given I visit the web site as a guest user
    When I navigate to Store events page
    Then I select "All Location" from dropdown and search pincode "10022"
    When I click on random Address on results page
    Then I verify the Address details displayed on the Page
    And I verify the STORE MAP on the page
    And I verify the Store Hours Block Data on the page

  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_iship
  Scenario: HeaderAndFooter - Verify Change country link
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I verify the international context page

  @use_regression @migrated_to_sdt @domain_discovery @mode_domestic
  Scenario: Browse Page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category browse page
    Then I should see dynamic top navigation in "domestic" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section

  @use_regression @migrated_to_sdt @domain_discovery @dsv_desktop_sev2 @use_domestic
  Scenario: CategorySplashPage - Verify TOPNAV - FLYOUTS on Non Secure and Secure Pages in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the non secure page category flyouts for all categories
    When I navigate to my account page
    Then I verify the secure page category flyouts for all categories