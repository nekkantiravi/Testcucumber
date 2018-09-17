#Author: UFT team
#Date Created: 12/01/2015
#Date Signed Off:
#Version One Card: D-16893

Feature: Price text is missing from Demo UI page in both MCOM and BCOM

  @sst
  Scenario: Verify Price text is visible on PDP demo UI Page
    Given I login into mass portal as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "PDP Demo UI" page under Omni Channel Preview Services section
    And I search with valid product id
    Then I should see the price details on the demo page