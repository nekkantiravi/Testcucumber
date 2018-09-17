#Author: UFT team
#Date Created: 01/05/2016
#Date Signed Off:
#Version One: B-28795

Feature: As a visually-impaired user on Macys.com, I want the global search field to have a text label

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario: HomePage - Domestic - Verify accessibility label on global search field
    Given I visit the web site as a guest user
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: HomePage - Domestic - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user
    When I create a new profile
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario: SubSplashPage - Domestic - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" sub splash page under "MEN"
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: BrowsePage - Domestic - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user
    When I navigate to the "Backpacks" browse page under "HANDBAGS"
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario: CEPage - Domestic - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user
    When I navigate to "/m/campaign" pattern url link from footer
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario: CampaignPage - Domestic - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user
    When I navigate to "/social?campaign_id=" pattern url link from footer
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: SubSplashPage - Registry - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "Anolon" sub splash page under "KITCHEN"
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_registry @discovery_daily_run
  Scenario: BrowsePage - Registry - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "Dinnerware" browse page under "DINING"
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_registry @discovery_daily_run
  Scenario: CEPage - Registry - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "/m/campaign" pattern url link from footer
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_registry @discovery_daily_run
  Scenario: CampaignPage - Registry - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "/social?campaign_id=" pattern url link from footer
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_iship @discovery_daily_run
  Scenario: SubSplashPage - Iship - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "Activewear" sub splash page under "MEN"
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: BrowsePage - Iship - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "Dresses" browse page under "WOMEN"
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_iship @discovery_daily_run
  Scenario: CEPage - Iship - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "/m/campaign" pattern url link from footer
    Then the search field should have a label with the text "Search or enter web ID"

  @use_regression @domain_discovery @priority_high @mode_iship @discovery_daily_run
  Scenario: CampaignPage - Iship - Verify accessibility label on My Account Page Global Search Field
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "/social?campaign_id=" pattern url link from footer
    Then the search field should have a label with the text "Search or enter web ID"