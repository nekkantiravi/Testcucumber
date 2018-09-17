# Author: Kasun Alwis
# Date Created: 11/18/2013
# Date Signed Off:

Feature: Verify site admin enhancements

  #Test Case ID: MCOM-71599
  @sst
  Scenario: Verify enhancement of portal menu by using expand/collapse buttons
    Given I login into site admin portal as a valid user
      And I verify menu items panel on site admin page
    When I click on any "collapsed" menu item on site admin page
    Then I Should see "expanded" menu item
    When I click on any "expanded" menu item on site admin page
    Then I Should see "collapsed" menu item
    # Notes:
    # verify following on menu items panel
    # By default all the menu items in the left hand side should be in collapse mode
    # verify menu's getting expands when click on them