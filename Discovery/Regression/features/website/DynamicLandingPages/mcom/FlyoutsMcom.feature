#Author: Discovery QE

Feature: Verify Flyouts on DLP in DOMESTIC and ISHIP mode

############################### DOMESTIC MODE ##########################################################

  @domain_discovery @priority_high @mode_domestic @use_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify FLYOUT appears
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    When I hover on any category
    Then I verify that flyout menu is displayed
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
  # Notes: Verifies that flyout menu appears on hovering a random FOB
