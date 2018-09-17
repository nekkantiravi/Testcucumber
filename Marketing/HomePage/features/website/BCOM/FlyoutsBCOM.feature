#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Flyouts on Home Page in DOMESTIC, ISHIP and REGISTRY mode


  @use_regression @priority_high @domain_marketing @domain_discovery @s4a_stable @mode_domestic @use_domain_qual
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for all FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | DESIGNERS             |
      | WHAT'S NEW            |
      | MEN                   |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |
      | SHOES                 |
      | HANDBAGS              |
      | SALE                  |
      | HOME                  |
      | WOMEN                 |
      | BEAUTY                |

  @use_regression @priority_high @domain_marketing @domain_discovery @s4a_stable @mode_domestic @use_domain_qual
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for all FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | DESIGNERS             |
      | WHAT'S NEW            |
      | MEN                   |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |
      | SHOES                 |
      | HANDBAGS              |
      | SALE                  |
      | HOME                  |
      | WOMEN                 |

  @use_regression @priority_high @domain_marketing @domain_discovery @s4a_stable @mode_registry @backlog_discovery
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for all FOBs in REGISTRY mode
    Given I visit the web site as a registry user
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | GETTING STARTED       |
      | BRANDS                |
      | DINING & ENTERTAINING|
      | KITCHEN              |
      | BED & BATH           |
      | HOME DECOR           |
      | LUGGAGE              |
      | HOME CARE & TECH     |

  @use_regression @priority_high @domain_marketing @domain_discovery @s4a_stable @mode_registry @backlog_discovery
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for all FOBs on REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | GETTING STARTED       |
      | BRANDS                |
      | DINING & ENTERTAINING|
      | KITCHEN              |
      | BED & BATH           |
      | HOME DECOR           |
      | LUGGAGE              |
      | HOME CARE & TECH     |


############################################### DESIGNER - Grid Layout  ##########################################

  @use_regression @priority_high @domain_marketing @domain_discovery @use_bat_next @release_13J
  Scenario: Header - Verify TOP NAV - DESIGNERS FLYOUT displays a grid layout in DOMESTIC mode
    Given I visit the web site as a guest user
    When I mouse over on "DESIGNERS" category from top navigation
    Then I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "site" mode


  @use_regression @priority_high @mode_iship @iship_1 @domain_marketing @domain_discovery @use_bat_next @release_13J @disable_env
  Scenario: Header - Verify TOP NAV - DESIGNERS FLYOUT displays a grid layout in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    When I mouse over on "DESIGNERS" category from top navigation
    Then I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "iship" mode

  @use_regression @priority_high @domain_marketing @domain_discovery @use_bat_next @release_13J @mode_registry @disable_env
  Scenario: Header - Verify TOP NAV - DESIGNERS FLYOUT displays a grid layout in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I mouse over on "BRANDS" category from top navigation
    And I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "registry" mode

  @use_regression @priority_high @domain_marketing @domain_discovery @mode_domestic @use_preview
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for main FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | WHAT'S NEW            |
      | WOMEN                 |
      | BEAUTY                |

  @use_regression @priority_high @domain_marketing @domain_discovery @mode_registry @use_preview
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for main FOBs on REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | BRANDS                |
      | DINING & ENTERTAINING|
      | KITCHEN              |

  @use_regression @priority_high @domain_marketing @domain_discovery @mode_registry @use_preview
  Scenario Outline: Home Page - Verify FLYOUTS menu is displayed for main FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I mouse over on the below "<fob>" fob's
    Then I should see flyout menu
    Examples:
      | fob                   |
      | DESIGNERS             |
      | MEN                   |
      | KIDS                  |


