#Author: UFT team
#Date Created: 05/16/2016
#Date Signed Off:
#Version One Card: B-79039


Feature: Add new LDAP regions on portal login page

  @sst
  Scenario: Verify new LDAP regions on mass portal login page
    Given I am on mass portal login page
    Then I should see new LDAP regions in drop down:
      | Home_Office     |
      | Macys           |
      | MMG             |
      | Macys_Backstage |
      | Fed_Logistics   |


