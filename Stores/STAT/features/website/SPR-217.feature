# Author: Stat Team
# Story: SPR-217 - Apply - Alter PENDING_ITEMS table
# Date Created: 11/02/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-217
Feature: As an application I need to alter the definition of the PENDING_ITEMS table so I can correctly
  capture the pending return information from Site/MSA

@Macy's
  Scenario: QE to verify the size of the ORDER_NBR column in PENDING_ITEMS table
    Given I am in SQL developer
    When I am in PENDING_ITEMS table
    Then I can see ORDER_NBR column is now VARCHAR2 (30 BYTE)

  Scenario: QE to verify the size of the RESERVATION_NBR column in PENDING_ITEMS table
    Given I am in SQL developer
    When I am in PENDING_ITEMS table
    Then I can see RESERVATION_NBR column is now VARCHAR2 (100 BYTE)

  Scenario: QE to verify the 02_CREATE_PENDING_TRANS.sql script
    Given I am in SQL developer
    When I am in 02_CREATE_PENDING_TRANS.sql script
    Then I can see ORDER_NBR column is now VARCHAR2 (30 BYTE)
    And  I can see RESERVATION_NBR column is now VARCHAR2 (100 BYTE)




