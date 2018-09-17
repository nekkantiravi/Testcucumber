# Author: Kasun Alwis
# Date Created: 11/18/2013
# Date Signed Off:

Feature: Verify Mass URL feature

  #Test Case ID: MCOM-66592 , BLCOM-65404
  @sst @prod
  Scenario: Verify Mass URL in the Site Admin Utility
    Given I login into site admin portal as a valid user
    When I navigate to "Delete cart items" page using appropriate link in Misc Maintenance section
    Then I should be navigated Mass portal after selecting Mass URL in site admin page
    #Notes:
    #verify following on mass url
    #Verify Mass URL is presented in the top right corner
    #SiteAdmin application should change to Mass environment without asking login credentials
