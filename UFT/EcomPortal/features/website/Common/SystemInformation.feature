# Author: SST Regression
# Date Created: 11/13/2013
# Date Modified: 12/11/2013
# Date Signed Off:

Feature: Verify system information display correctly

#Test Case ID: MCOM-66585 , BLCOM-65401
  @sst
  Scenario: Verify midtier url and database connection url display
    Given I login into site admin portal as a valid user
    When I navigate to "System Information" page under System Information menu
    Then I should see required urls exist in the page
    # Notes:
    # Breadcrumb should be displayed as "Home | System Information"
    # On "System Information" function:
    # verify midtier url display
    # verify siteadmin db url display

