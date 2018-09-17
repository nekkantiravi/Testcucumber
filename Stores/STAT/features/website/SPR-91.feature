# Author: STAT Team
# Story: SPR-91 - PR - Parms - Add Pending Return Number
# Date Created: 11/30/2017
# Date Signed Off:

@STAT @pending_returns @SPR-91
Feature: As a SmartParms business owner I want the ability to turn on and off the inclusion of Pending Return
  Number as a Return lookup method at POS

  @Macys
  Scenario: QE to verify Pending Return number is available and checked
    Given I am in SmartParms
    When I log into SmartParms
    And I click on the Macys image
    Then I click on the Refunds table
    And I click on the RFone Table
    Then I should see that Pending Returns is a refund option under unreceipted returns
    When I click inside the checkbox
    Then I should see the Pending Returns number box is checked
    When I click inside the checkbox
    Then I should see the Pending Returns number box is unchecked


#  @BLM
#  Scenario: QE to verify Pending Return number is available and checked
#    Given I am in SmartParms
#    When I log into SmartParms
#    And I click on the Bloomingdales image
#    Then I click on the Refunds table
#    And I click on the RFone Table
#    Then I should see that Pending Returns is a refund option under unreceipted returns
#    When I click inside the checkbox
#    Then I should see the Pending Returns number box is checked
#    When I click inside the checkbox
#    Then I should see the Pending Returns number box is unchecked






