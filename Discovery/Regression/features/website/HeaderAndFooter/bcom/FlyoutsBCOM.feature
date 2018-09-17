Feature: Verification of BCOM header TOPNAV - FLYOUTS functionality in DOMESTIC, ISHIP and REGISTRY modes

  #Adding @wip as responsive sing-in page won't have Header FOB menu & Flyouts for this scenario validation
  #Test Case ID: BLCOM-80210
  @use_regression @use_dsv @priority_high @artifact_navapp @domain_discovery @s4a_stable @use_regression_1 @mode_domestic @dsv_desktop_sev2 @wip
  Scenario: Header - Verify TOPNAV - FLYOUTS is displayed on secure pages - Sign In Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to signin page of "site" mode
    And I mouse over on the "random" fob
    Then I should see flyout menu for "random" fob

  #Test Case ID: BLCOM-80210
  @use_regression @use_dsv @priority_high @artifact_navapp @domain_discovery @s4a_stable @use_regression_1 @mode_domestic @dsv_desktop_sev2
  Scenario: Header - Verify the TOPNAV - FLYOUTS - Sub Category navigation in DOMESTIC mode
    Given I visit the web site as a guest user
    When I click on sub category link in flyout
    Then I should see respective category page

  @use_regression @use_dsv @priority_high @artifact_navapp @domain_discovery @stability @use_regression_1 @mode_domestic @dsv_desktop_sev2
  Scenario Outline: Header - Verify the TOPNAV - FLYOUTS menu is displayed in Search Results Page in DOMESTIC & REGISTRY & ISHIP mode
    Given I visit the web site as a "guest" user in "<mode>" mode
    When I search for "Plates"
    And I mouse over on the "random" fob
    Then I should see flyout menu for "random" fob
    Examples:
      | mode     |
      | site     |
      | iship    |
      | registry |

  @use_regression @priority_high @artifact_navapp @domain_discovery @s4a_stable @use_regression_1 @mode_domestic
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS menu is displayed for all FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I mouse over on the "<fob>" fob
    Then I should see flyout menu for "<fob>" fob
    Examples:
      | fob                   |
      | DESIGNERS             |
      | EDITORIAL             |
      | MEN                   |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |
      | SHOES                 |
      | HANDBAGS              |
      | SALE                  |
      | HOME                  |
      | WOMEN                 |
      | BEAUTY                |

  @use_regression @priority_high @artifact_navapp @domain_discovery @stability @s4a_stable @use_regression_1 @mode_domestic
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS menu is displayed for all FOBs in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I mouse over on the "<fob>" fob
    Then I should see flyout menu for "<fob>" fob
    Examples:
      | fob                   |
      | DESIGNERS             |
      | EDITORIAL             |
      | MEN                   |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |
      | SHOES                 |
      | HANDBAGS              |
      | SALE                  |
      | HOME                  |
      | WOMEN                 |

  @use_regression @priority_high @artifact_navapp @domain_discovery @stability @s4a_stable @use_regression_1 @mode_domestic
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS menu is displayed for all FOBs in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I mouse over on the "<fob>" fob
    Then I should see flyout menu for "<fob>" fob
    Examples:
      | fob                   |
      | DINING & ENTERTAINING |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | HOME CARE & TECH      |


############################################### DESIGNER - Grid Layout  ##########################################

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic @test
  Scenario: Header - Verify TOP NAV - DESIGNERS FLYOUT displays a grid layout in DOMESTIC mode
    Given I visit the web site as a guest user
    And I mouse over on the "DESIGNERS" fob
    Then I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "site" mode


  @use_regression @priority_high @mode_iship  @iship_1 @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario: Header - Verify TOP NAV - DESIGNERS FLYOUT displays a grid layout in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I mouse over on the "DESIGNERS" fob
    Then I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "iship" mode

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @mode_registry @use_regression_1
  Scenario: Header - Verify TOP NAV - DESIGNERS FLYOUT displays a grid layout in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I mouse over on the "BRANDS" fob
    And I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "registry" mode

  @B-47501 @priority_medium @please_automate
  Scenario Outline: As a customer, I want to verify that all flyouts appear with links in domestic mode
    Given I visit the web site as a "guest" user in "<mode>" mode
    Then I verify links appear in the flyouts
    Examples:
      | mode     |
      | site     |
      | iship    |
      | registry |
