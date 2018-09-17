#Author: Discovery QE
#Date Created:06/12/2015


Feature: Verify Flyouts on Home Page in DOMESTIC, ISHIP and REGISTRY mode


  @use_regression @priority_high @artifact_navapp @domain_marketing @s4a_stable @mode_domestic @use_domain_qual
  Scenario: Home Page - Verify FLYOUTS menu is displayed for all FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu
    | fob                    |
    | HOME                   |
    | BED & BATH             |
    | WOMEN                  |
    | MEN                    |
    | JUNIORS                |
    | KIDS                   |
    | BEAUTY                 |
    | SHOES                  |
    | HANDBAGS               |
    | JEWELRY                |
    | WATCHES                |
    | BRANDS                 |

  @use_regression @priority_high @artifact_navapp @domain_marketing @s4a_stable @disable_env @mode_iship
  Scenario: Home Page - Verify FLYOUTS menu is displayed for all FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu

    | fob                    |
    | HOME                   |
    | BED & BATH             |
    | WOMEN                  |
    | MEN                    |
    | JUNIORS                |
    | KIDS                   |
    | SHOES                  |
    | HANDBAGS & ACCESSORIES |
    | JEWELRY                |
    | WATCHES                |
    | BRANDS                 |

  @use_regression @priority_high @artifact_navapp @domain_marketing @s4a_stable @mode_registry @backlog_discovery @use_domain_qual
  Scenario: Home Page - Verify FLYOUTS menu is displayed for all FOBs in REGISTRY mode
    Given I visit the web site as a registry user
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu

    | fob                   |
    | WEDDING REGISTRY      |
    | DINING                |
    | KITCHEN               |
    | BED & BATH            |
    | HOME DECOR            |
    | LUGGAGE               |
    | CLEANING & ORGANIZING |
    | WEDDING DAY          |

  @use_regression @priority_high @artifact_navapp @domain_marketing @s4a_stable @mode_registry
  Scenario: Home Page - Verify FLYOUTS menu is displayed for all FOBs on REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu
    | fob                   |
    | WEDDING REGISTRY      |
    | DINING                |
    | KITCHEN               |
    | BED & BATH            |
    | HOME DECOR            |
    | LUGGAGE               |
    | CLEANING & ORGANIZING |
    | WEDDING DAY           |

  @use_regression @priority_high @artifact_navapp @domain_marketing @mode_domestic @use_preview
  Scenario: Home Page - Verify FLYOUTS menu is displayed for main FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu

      | fob                    |
      | HOME                   |
      | WOMEN                  |
      | MEN                    |

  @use_regression @priority_high @artifact_navapp @domain_marketing @mode_iship @use_preview
  Scenario: Home Page - Verify FLYOUTS menu is displayed for main FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu

      | fob                   |
      | HOME                   |
      | WOMEN                  |
      | MEN                    |

  @use_regression @priority_high @artifact_navapp @domain_marketing @mode_registry @use_preview
  Scenario: Home Page - Verify FLYOUTS menu is displayed for main FOBs on REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over on the below fob's and validate flyout menu
   # Then I should see flyout menu

      | fob                   |
      | LUGGAGE               |
      | KITCHEN               |
      | BED & BATH            |

  @artifact_navapp @domain_marketing @release_16K @project_UFT @mode_domestic @use_regression
  Scenario: Verify flyouts from FOBs are expanded in home page
    Given I visit the web site as a guest user
    When I mouse hover on any category
    Then I should see and validate flyout menu


