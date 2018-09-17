# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017

Feature: Verify SubSplash Pages - Recently Viewed Panel

  @domain_discovery @priority_medium @use_regression @mode_domestic @discovery_daily_run
  Scenario: SubSplashPage - Verify Edit button is displayed on RVI Panel in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 2 products and reload the page
    When I navigate to the "Activewear" sub splash page under "MEN"
    Then I verify the display of RVI in "category subsplash" page
    And I should see edit option inside Recently Viewed panel
    When I click "edit" button in RVI panel
    Then I should see highlighted Remove button on each product and Edit button displayed as Done in RVI panel
    When I click on "Remove" button on any product
    Then I verify product is removed temporally

  #Test Case ID: MCOM-80941
  @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario: SubSplashPage - Verify RVI panel on Brand Shop(HANDBAGS -> MICHAEL Michael Kors) in DOMESTIC mode
    Given I visit the web site as a guest user
    When I construct RVI cookie with 3 products and reload the page
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    Then I verify the display of RVI in "category subsplash" page
    And I remove all Recently viewed items
    Then I should not see recently viewed items section
    # Notes
    # Recently view items panel is in collapsed mode
    # "recently viewed items (0)" should be displayed
    # Recently view items panel arrows should be inactive


  #Test Case ID: MCOM-80941
  #Testlink-MCOM-96651
  #vone-RT-07332
  @domain_discovery @priority_medium @use_regression @mode_iship
  Scenario: SubSplashPage - Verify RVI panel in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    When I construct RVI cookie with 2 products and reload the page
    When I navigate to "WOMEN" category page
    And I navigate to "Activewear" subsplash page from cat splash page
    Then I verify the display of RVI in "category subsplash" page
    And I verify the currency is "INR" on the RVI
    When I select a product from RVI panel
    Then I should be navigated to respective PDP page from RVI panel