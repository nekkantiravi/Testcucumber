# Author: STAT Team
# Story: SPR-151 - Apply - Modify TRANS_DTL table
# Date Created: 10/12/2017
# Date Signed Off:

@STAT @pending_returns @manual @SPR-151
Feature: As an application I want to add a column for the Pending Transaction ID
  in the TRANS_DTL table so I can support the financial completion of a Pending Return

  Scenario: QE to verify the PT ID column is added to the TRANS_DTL table
    Given I am in SQL Developer
    When I am in the TRANS_DTL table
    Then I can see the PT ID column

