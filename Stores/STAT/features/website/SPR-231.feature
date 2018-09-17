# Author: Stat Team
# Story: SPR-231- PR - Pkg_Return - Retrieve Pending Items Cursor
# Date Created: 1/2/2018
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-231
Feature: As an application I want to return pending item information so I can pass this data to
  POS during return lookups

  Scenario: QE to ring a NPOP return using fedfil UPC that has a corresponding pending transaction
    Given I am at the Smart main menu
    When I input the associate number and pin
    And I Press T2 Return/Exchange
    And I Press T1 NPOP
    And I input a fedfil UPC
    Then I should see the pending item information

  Scenario: QE to ring a NPOP return using order number UPC that has a corresponding pending transaction
    Given I am at the Smart main menu
    When I input the associate number and pin
    And I Press T2 Return/Exchange
    And I Press T1 NPOP
    And I input a order number UPC
    Then I should see the pending item information

  Scenario: QE to ring a NPOP return using Tracking number UPC that has a corresponding pending transaction
    Given I am at the Smart main menu
    When I input the associate number and pin
    And I Press T2 Return/Exchange
    And I Press T1 NPOP
    And I input a Tracking number UPC
    Then I should see the pending item information

  Scenario: QE to ring a NPOP return using Pending Trans UPC that has a corresponding pending transaction
    Given I am at the Smart main menu
    When I input the associate number and pin
    And I Press T2 Return/Exchange
    And I Press T1 NPOP
    And I input a Pending trans UPC
    Then I should see the pending item information


