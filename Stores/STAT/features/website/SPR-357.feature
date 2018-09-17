# Author: Stat Team
# Story: SPR-357 - GVB - UI - Advisor Message Shipping Info
# Date Created: 12/29/2017
# Date Signed Off:

@STAT @pending_returns @SPR-357
Feature: As a Gift Registry Advisor I want an information message with the Registrant's information when creating a
  bonus request so I can know how to update the shipping information

  Scenario: Verify Registrant accordion contains confirmation message about Shipping Address
    Given I am in the Gift website
    When I log in as an Advisor
    And I see the hamburger/main menu
    And I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    And I click on the Create Request Link
    And I input a registry number 2330989
    And  I click search
    Then I see an accordion view opened of the registrants information
    And I should see Registrant accordion confirmation message
