#Author: Suman Gour
#Date Created: 12/04/2013
#Date Signed Off:

Feature: Verify miscellaneous functionality


  @use_regression @artifact_navapp @domain_marketing @priority_high @backlog_discovery @mode_domestic
  Scenario: Home Page - Verify JSESSIONID cookie in DOMESTIC mode
    Given I visit the web site as a guest user
    And I should not be able to see "JSESSIONID" cookie
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

  #Vone - RT-06263
  #Testlink-BLCOM-84127
  @please_automate @backlog_discovery @priority_high @artifact_navapp @domain_marketing @mode_domestic
  Scenario: Home Page - Verify bloomingdales online cookie in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to sign in page
    Then I should see "bloomingdales_online" cookie value and expiration date
    When  I navigate to random category splash page
    Then I should see "bloomingdales_online" cookie value and expiration date are same as before
    When I navigate to my account page
    Then I should see "bloomingdales_online" cookie value and expiration date are different


    #Notes: Verify cookie value and expiration date




