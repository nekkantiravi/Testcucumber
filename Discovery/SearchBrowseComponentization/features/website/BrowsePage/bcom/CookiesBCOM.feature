#Author: Discovery QE

Feature: Verify miscellaneous functionality

############################### DOMESTIC MODE ##########################################################

  @domain_discovery @priority_high @use_dsv @mode_domestic @use_regression @migrated_to_sdt @snbc_comp
  Scenario: CategoryBrowsePage - Domestic - Verify JSESSIONID cookie in DOMESTIC mode
    Given I am on CategoryBrowsePage for "21683" category id in Domestic mode
    And I clear existing class variables to avoid data issues
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

  ############################### ISHIP MODE ##########################################################

  #Note : Find out if these scenarios need to be executed in ISHIP mode as well

  ############################### REGISTRY MODE #######################################################

  #Note : Find out if these scenarios need to be executed in registry mode as well


