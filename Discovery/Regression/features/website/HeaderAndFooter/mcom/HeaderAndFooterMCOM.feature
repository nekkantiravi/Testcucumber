# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of header and footer functionality in DOMESTIC, ISHIP and REGISTRY modes


  #Test Case ID: MCOM-92022 MCOM-92027 MCOM-92032
  @use_regression @priority_medium @domain_discovery @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify new Header footer in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I verify the display of the category splash page
    And I verify New Header Redesign implementation for clean in "domestic" mode
    
  ########################################################  No Footer Ad on Search #########################################

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: Search Page - Verify Footer Ad - Not present in DOMESTIC mode
    Given I visit the web site as a guest user
    And I note the Footer Ad count on Home Page
    When I search for any category "Shoes"
    Then I verify Footer Ads are not present on current page

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: Search Page - Verify Footer Ad - Not present in REGISTRY mode
    Given I visit the web site as a guest user
    And I note the Footer Ad count on Home Page
    And I navigate to registry home page
    When I search for any category "Shoes"
    Then I verify Footer Ads are not present on current page

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: Search Page - Verify Footer Ad - Not present in ISHIP mode
    Given I visit the web site as a guest user
    And I note the Footer Ad count on Home Page
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I search for any category "Shoes"
    Then I verify Footer Ads are not present on current page

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: PDPPage - Verify Footer Ad - Not present in DOMESTIC mode on PDP
    Given I visit the web site as a guest user
    And I note the Footer Ad count on Home Page
    And I search for any category "Shoes"
    When I select a random product from the search results page
    Then I verify Footer Ads are not present on current page