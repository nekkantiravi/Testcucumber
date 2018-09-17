
  #Author: Discovery QE

  Feature: Verify SubSplashPage functionality for DSV

  ############################### DOMESTIC MODE ##########################################################

    @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @dsv_desktop_sev2
    Scenario: SubSplashPage - Verify JSESSIONID cookie in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to the "Activewear" sub splash page under "MEN"
      Then I verify that "JSESSIONID" cookie is not displayed
    # Notes:
    # Test case description
    # View JSESSIONID cookie - HP
    # Test case steps (MCOM)
    # 1. Navigate to macys.com
    # 2. enter "javascript:document.cookie" in address bar and click enter and
    # Search for JSESSIONID cookie in the resulted page
    # Test case expected result (MCOM)
    # 1. Macys.com home page should display
    # 2. JSESSIONID should not be present.
    # Test case steps (BCOM)
    # 1. Navigate bloomingdales.com
    # 2. enter "javascript:document.cookie" in address bar and click enter and
    # Search for JSESSIONID cookie in the resulted page
    # Test case expected result (BCOM)
    # 1. Bloomingdales.com home page should display
    # 2. JSESSIONID should not be present.


    #Test Case ID: MCOM-79676
    @domain_discovery @s4a_stable @use_regression @migrated_to_sdt @mode_domestic @priority_medium  @dsv_desktop_sev2 @discovery_daily_run
    Scenario: SubSplashPage - Verify Gift Card Category Splash Page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to gift card category splash page
      Then I verify the basic attributes of gift card category splash page
      # Notes:
      #  Test case description:
      #  1.Navigate to macys.com
      #  2.Click on Gift Cards
      #  Test case expected results:
      #  1.macys.com home page should be displayed.
      #  2a.It should navigate to Gift Cards page
      #  2b.It should display Main ads and sub ads) Card type & show by Occasion)