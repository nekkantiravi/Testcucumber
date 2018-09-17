#Date Created: 08/4/2015
#Date Signed Off:
# Version One URL: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26266

Feature: As a member of the support team, I would like to make sure that MCOM UPC Store availability replay is working in MASS

  @sst
  Scenario: Verify that new tab is displayed for UPC orders
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Inventory data replay" page under SmartPush section
    Then I should see all the necessary page elements when i select each of the replay type in the dropdown