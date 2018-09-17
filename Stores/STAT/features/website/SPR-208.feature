# Author: STAT Team
# Story: Create Pending Return - Database (alter pending_trans) - Happy Path
# Date Created: 11/01/2017
# Date Signed Off:

@STAT @vendor_bonus @manual @SPR-208
Feature: As an application I need to alter the definition of the PENDING_TRANS table
  so I can correctly capture the pending return information from Site/MSA


  Scenario:   Verify that ORDER_NBR column is now VARCHAR2 (30 BYTE) and that RESERVATION_NBR column is now VARCHAR2 (100 BYTE)
    Given I am an SQL Developer
    When I view PENDING_TRANS table
    Then I should see ORDER_NBR column as VARCHAR2 (30 BYTE)
    And I should see RESERVATION_NBR column as VARCHAR2 (100 BYTE)
    When I view 02_CREATE_PENDING_TRANS.sql script
    Then I should see the script is updated


