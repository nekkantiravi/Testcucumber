Feature: Verify Back To Top functionality in DynamicLandingPage contents in DOMESTIC & ISHIP mode

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario: DynamicLandingPage - Domestic - Verify back to top button appears when bottom edge of sub nav passes viewable screen
    Given I visit the web site as a guest user
    And I navigate to brand index page in "domestic" mode
    When I select "City Chic" brand in all brands page
    When I navigate to bottom of left navigation panel
    Then I verify that back to top button is displayed on page

  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario: DynamicLandingPage - Registry - Verify back to top button appears when bottom edge of sub nav passes viewable screen
    Given I visit the web site as a guest user
    And I navigate to brand index page in "registry" mode
    When I select "City Chic" brand in all brands page
    When I navigate to bottom of left navigation panel
    Then I verify that back to top button is displayed on page

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship -  Verify back to top button is not displayed immediately on dlp page
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship -  Verify back to top button is displayed after scolled down
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    When I navigate to bottom of left navigation panel
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify DLP page is navigated to top on click back to top button
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    When I select back to top button
    Then I verify that dlp page navigated to top of the page
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
