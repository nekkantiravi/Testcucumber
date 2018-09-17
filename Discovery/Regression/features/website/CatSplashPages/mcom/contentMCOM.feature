#Author: Discovery QE
#Date Created: 08/28/2015


Feature: Verify content of special shop and feature brand categories in DOMESTIC mode

  #Testlink-MCOM-66705
  #vone-RT-07327
  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic @migrated_to_sdt
  Scenario: CategorySplashPage - Domestic -  Verify content of special shop pages (HOME -> Custom Windows)
    Given I visit the web site as a guest user
    When I navigate to the "Custom Windows" browse page under "HOME"
    And I should see page navigated to "/social/custom-decorator/" pattern url
    # Notes:
    # VERIFY
    # Ex : Custom Windows & Carpet, Game Day Essentials, Macy's Heritage Shops, The Pink Shop, The Wedding Shop etc.

  #Testlink-MCOM-66705
  #vone-RT-07327
  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic @migrated_to_sdt @discovery_daily_run
  Scenario: CategorySplashPage - Domestic -  Verify content of special shop pages (HOME -> Macy's Heritage Shops)
    Given I visit the web site as a guest user
    When I navigate to the "Macy's Heritage Shops" browse page under "HOME"
    And I should see page navigated to "/social/brand-heritage/" pattern url
    # Notes:
    # VERIFY
    # Ex : Custom Windows & Carpet, Game Day Essentials, Macy's Heritage Shops, The Pink Shop, The Wedding Shop etc.

  #Testlink-MCOM-96555
  #vone-RT-07329
  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic @migrated_to_sdt @discovery_daily_run
  Scenario: CategorySplashPage - Domestic - Verify content of Featured brands (HANDBAGS -> DKNY)
    Given I visit the web site as a guest user
    When I navigate to the "DKNY" browse page under "HANDBAGS"
    Then I verify the basic attributes of Browse page
    # Notes:
    # VERIFY:
    # Click on one of the Brands Shops link from left navigation
    # Verify appropriate content for the selected link is loaded for the Brands shop

  #Testlink-MCOM-96555
  #vone-RT-07329
  @domain_discovery @feature_catsplash @use_regression @priority_medium @mode_domestic @migrated_to_sdt @discovery_daily_run
  Scenario: CategorySplashPage - Domestic - Verify content of Featured brands (WOMEN -> Alfani)
    Given I visit the web site as a guest user
    When I navigate to the "Alfani" browse page under "WOMEN"
    Then I verify the basic attributes of Browse page
    # Notes:
    # VERIFY:
    # Click on one of the Brands Shops link from left navigation
    # Verify appropriate content for the selected link is loaded for the Brands shop