#Author: UFT team
#Date Created: 05/24/2017
#Date Signed Off:
#Version One: B-78193

Feature: As a product owner, I would like to ensure that Badge text should no longer be suppressed for brands

  @mode_domestic @release_17J @priority_medium @domain_discovery @project_UFT @use_regression @discovery_daily_run
  Scenario Outline: BrowsePage - Domestic - Verify Badge text is not suppressed for Dior and Tory Burch brands
    Given I visit the web site as a guest user
    When I navigate to the "All Beauty Brands" browse page under "BEAUTY"
    And I select "<brand_type>" brand in all beauty brands page
    Then I should see "batch text" is displayed for the "specific beauty" brand
    Examples:
      | brand_type |
      | Dior       |
      | Tory Burch |

  @mode_domestic @release_17J @priority_medium @domain_discovery @project_UFT @use_regression @xbrowser_browse
  Scenario Outline: BrowsePage - Domestic - Verify Badge text is not suppressed for Tom Ford brand
    Given I visit the web site as a guest user
    When I navigate to the "All Beauty Brands" browse page under "BEAUTY"
    And I select "<brand_type>" brand in all beauty brands page
    Then I should see "batch text" is displayed for the "specific beauty" brand
    Examples:
      | brand_type |
      | Tom Ford   |