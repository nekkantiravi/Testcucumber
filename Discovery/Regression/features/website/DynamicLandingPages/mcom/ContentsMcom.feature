Feature: Contents verification on DynamicLanding Page

  @domain_discovery @priority_medium @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @discovery_daily_run
  Scenario: DynamicLandingPage - Domestic - Verify Edit button is displayed on RVI Panel on dlp page
    Given I am on DynamicLandingPage in "Domestic" mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    And I navigate to bottom of page
    And  I should see edit option inside Recently Viewed panel
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario: DynamicLandingPage - Domestic - Verify header is displayed
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that logo is displayed and returns a 200 OK
    Then I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in SRP

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario: DynamicLandingPage - Domestic - Verify footer is displayed
    Given I am on DynamicLandingPage in "Domestic" mode
    Then I verify that the footer elements are displayed
  # Notes: Verifies that footer links are all present in SRP

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify FOBs
    Given I am on DynamicLandingPage in "Domestic" mode
    And I verify that fobs are displayed and return a 200 OK

  @domain_discovery @priority_low @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario: DynamicLandingPage - Domestic - Verify that title tag are correctly populated on slp with single facet selection
    Given I am on DynamicLandingPage in "Domestic" mode
    When I select 'single' facet value from 'any' facet section
    Then I verify that the title tag is displayed with selected facet

  @domain_discovery @priority_low @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario: DynamicLandingPage - Domestic - Verify tag are correctly populated on dlp with multiple facet selection
    Given I am on DynamicLandingPage in "Domestic" mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that the title tag is displayed with selected facet

  @domain_discovery @priority_high @use_regression @mode_domestic @mode_registry @mode_iship
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify media links and popup links are not resulting any error page
    Given I am on DynamicLandingPage in "<mode>" mode
    Then I verify all media links and popups are not resulting error page
    Examples:
      | mode     |
      | Domestic |
      | Iship    |
      | Registry |
  # Navigate to any dynamic landing page in each mode
  # Identify all links from media content section on page.
  # Then verify all media links are resulting '200' response code with 'Mechanize' agent request.

  @domain_discovery @priority_low @mode_domestic @mode_registry @mode_iship @use_regression
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify bookmarked faceted URL loading without any error
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    Then I should be navigated to "search result" page
    Examples:
      | mode     | url                                                                                                                                     |
      | domestic | /shop/featured/aden-by-aden-anais?cm_sp=shop_by_brand-_-All%20Brands-_-aden%20by%20aden%20%2B%20anais       |
      | domestic | /shop/featured/2xist?cm_sp=shop_by_brand-_-All%20Brands-_-2%28x%29ist                                       |
      | iship    | /shop/featured/1state?cm_sp=shop_by_brand-_-All%20Brands-_-1.STATE                                          |
      | iship    | /shop/featured/2028?cm_sp=shop_by_brand-_-All%20Brands-_-2028                                               |
      | registry | /shop/featured/100-good-deeds?mode=wedding&cm_sp=shop_by_brand-_-All%20Brands-_-100%20Good%20Deeds          |
      | registry | /shop/featured/swell?mode=wedding&cm_sp=shop_by_brand-_-All%20Brands-_-S%27Well%AE                          |
