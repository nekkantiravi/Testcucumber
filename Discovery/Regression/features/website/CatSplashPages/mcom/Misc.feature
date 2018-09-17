# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify CatSplash Pages - Miscellaneous

  #Testlink-MCOM-72946 Vone - RT-07341
  @domain_discovery @feature_catsplash @use_regression @mode_domestic @priority_medium @wip
  Scenario: CategorySplashPage - Verify footer icons in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "HOME" category page
    And  I Navigate to "eSSENTIAL Accessibility" footer links
    Then I verify navigated to "eSSENTIAL Accessibility" page
    When I select browse 'back' button
    And I navigate to random category splash page
    And I verify alt text of the "eSSENTIAL accessibility" icon
    #Notes: Verify that the Alt text should display
   #Alt text: "This Web Accessibility icon serves as a link to download eSSENTIAL Accessibility assistive technology software for individuals with physical disabilities."

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @priority_medium
  Scenario: CategorySplashPage - Verify footer icon in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "HOME" category page
    And I Navigate to "macy's backstage" footer links
    Then I verify navigated to "macy's backstage" page

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @mode_iship @migrated_to_sdt
  Scenario Outline: CategorySplashPage - Domestic|Iship|Registry - Verify cat splash page redirection with fob search
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to random category splash page
    And I search for "home"
    Then I should be navigated to "catsplash" page
    And I verify the basic attributes of cat splash page
    Examples:
      | mode     |
      | domestic |
      | iship    |
  # Navigate to any category splash page in each mode.
  # Search with 'home', 'kids', ,'handbags', 'beauty', 'men' FOB names.
  # Remaining FOB's will not re-direct to those pages.
  # Then verify keywords are redirected to respective category splash page.

  @domain_discovery @feature_catsplash @use_regression @mode_registry @migrated_to_sdt @discovery_daily_run
  Scenario: CategorySplashPage - Registry - Verify cat splash page redirection with fob search
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to random category splash page
    And I search for "KITCHEN"
    Then I should be navigated to "catsplash" page
    And I verify the basic attributes of cat splash page