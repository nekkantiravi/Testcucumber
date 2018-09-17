# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017

Feature: Verify CHANEL Pages in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_high @use_regression @mode_domestic @mode_registry @mode_iship @discovery_daily_run
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify media links and popup links are not resulting any error page
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to the "<browse>" browse page under "<catsplash>"
    Then I verify all media links and popups are not resulting error page
    Examples:
      | mode     | catsplash              | browse               |
      | domestic | HANDBAGS               | Backpacks            |
      | domestic | BED & BATH             | Hotel Collection     |
      | registry | BED & BATH             | Hotel Collection     |
      | domestic | BEAUTY                 | Makeup               |
      | iship    | HANDBAGS & ACCESSORIES | MICHAEL Michael Kors |
  # Navigate to any browse page in each mode
  # Identify all links from media content section on page.
  # Then verify all media links are resulting '200' response code with 'Mechanize' agent request.