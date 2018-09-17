#Author: Suman Gour
#Date Created: 28/08/2015
#Date Signed Off:

Feature: Verify miscellaneous functionality


  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @defect_D-29135
  Scenario: CategorySubSplashPage - Verify JSESSIONID cookie in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
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




