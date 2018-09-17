#Author: Beatrice Mendoza
#Date Created: 10/13/2016
#Date Signed Off: N/A
#Version One: B-62279
  #Author: Maria Malygina
  #Date Updated: 12/15/2016
  #Version One: B-63573

Feature: Monetate Banners

  @use_regression @BCOM_Monetate_Banners
  Scenario: Verify Monetate is visible to the customer and that it can be closed on the HomePage in DOMESTIC Mode
    Given I visit the web site as a guest user
    When I remove the following cookies from the browser:
      | Cookie Name    |
      | mt.BMQADisable |
    When I append "?monetatetest=qiq5ct0elorcbat" to the current URL
    Then I verify the monetate banner is visible and can be closed
    And I refresh current page
    Then I verify monetate banner can be closed by clicking outside of the banner

  @use_regression @BCOM_Monetate_Banners
  Scenario: Verify Monetate top banner is visible to the customer on the HomePage in DOMESTIC Mode
    Given I visit the web site as a guest user
    When I remove the following cookies from the browser:
      | Cookie Name    |
      | mt.BMQADisable |
    When I append "?monetatetest=88vzugc4pmn3pvg" to the current URL
    Then I verify that monetate top banner is visible to the customer