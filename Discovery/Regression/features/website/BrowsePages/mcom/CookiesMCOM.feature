#Author:Discovery SNBC QE
#Date Created: 29/08/2017

Feature: Verify miscellaneous functionality


  @use_regression @artifact_navapp @domain_discovery @priority_high @mode_domestic @snbc_comp
  Scenario: BrowsePage - Verify JSESSIONID cookie in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Pants" under "WOMEN" in Domestic mode
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

