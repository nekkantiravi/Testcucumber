
  #Author: Discovery QE

  Feature: Verify StaitcLandingPage functionality for DSV

  ############################### DOMESTIC MODE ##########################################################

    @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic
    Scenario: StaticLandingPage - Verify JSESSIONID cookie in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to "71145" category for static landing page
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
