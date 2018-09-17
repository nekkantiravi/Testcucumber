#Author: Suman Gour
#Date Created: 28/08/2015
#Date Signed Off:

Feature: Verify miscellaneous functionality

  @domain_discovery @feature_catsplash @use_regression @priority_high @mode_domestic @migrated_to_sdt
  Scenario: CategorySplashPage - Verify JSESSIONID cookie in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
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




