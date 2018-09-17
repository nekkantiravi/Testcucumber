# Author: STAT Team
# Story: SPR-92 - PR - Parms - Add Tracking Number
# Date Created: 11/30/2017
# Date Signed Off:

@STAT @pending_returns @SPR-91
Feature: As a POS business partner I want to be able to select/deselect Tracking Number in the Refund Table (RF1)
  Unreceipted Return Options so I can use Tracking Number as a return lookup method in stores

  @Macys
  Scenario: QE to verify Tracking number is available as a lookup method
    Given I am in SmartParms
    When I log into SmartParms
    And I click on the Macys image
    Then I click on the Refunds table
    And I click on the RFone Table
    Then I should see that Tracking Number is a refund option under unreceipted Returns
    When I click inside the checkbox
    Then I should see the Tracking number box is checked
    When I click inside the checkbox
    Then I should see the Tracking Number box is unchecked


  @BLM
  Scenario: QE to verify Tracking number is available as a lookup method
    Given I am in SmartParms
    When I log into SmartParms
    And I click on the Bloomingdales image
    Then I click on the Refunds table
    And I click on the RFone Table
    Then I should see that Tracking Number is a refund option under unreceipted Returns
    When I click inside the checkbox
    Then I should see the Tracking number box is checked
    When I click inside the checkbox
    Then I should see the Tracking Number box is unchecked








