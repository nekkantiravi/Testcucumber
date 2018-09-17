# Author: Stat Team
# Story: SPR-150 - Apply - Modify TRANS_HDR table
# Date Created: 10/12/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-150
Feature: As an application I want to add a column for ASSOC_PEND_TRANS_F in the TRANS_HDR table so I can
  support the financial completion of a Pending Return

@Macy's
  Scenario: Column is added for ASSOC_PEND_TRANS_F
    Given I am in SQL developer
    When I am in TRANS_HDR table
    Then I can see ASSOC_PEND_TRANS_F column
