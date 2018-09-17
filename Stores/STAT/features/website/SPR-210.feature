# Author: Stat Team
# Story: SPR-210 - Apply - Alter pending_trans_id table
# Date Created: 11/02/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-210
Feature: As an application I want to add additional columns (tracking_nbr, order_nbr, reservation_nbr, and purged_f)
  to the PENDING_TRANS_ID table so I can distinguish between a pending return that has been purged vs not found

@Macy's
  Scenario: QE to verify the size of the APPL_id column in PENDING_TRANS_ID table
    Given I am in SQL developer
    When I am in PENDING_TRANS_ID table
    Then I can see APPL_ID column is now CHAR (3 BYTE) NOT NULL
    And the comment on the column adjusted accordingly


  Scenario: QE to verify the size of the CLIENT_ID column in PENDING_TRANS_ID table
    Given I am in SQL developer
    When I am in PENDING_TRANS_ID table
    Then I can see CLIENT_ID column is now VARCHAR2 (30 BYTE) NOT NULL
    And the comment on the column adjusted accordingly

  Scenario: QE to verify ORDER_NBR column created in PENDING_TRANS_ID table
    Given I am in SQL developer
    When I am in PENDING_TRANS_ID table
    Then I can see ORDER_NBR column is VARCHAR2 (30 BYTE)

  Scenario: QE to verify RESERVATION_NBR column created in PENDING_TRANS_ID table
    Given I am in SQL developer
    When I am in PENDING_TRANS_ID table
    Then I can see RESERVATION_NBR column is VARCHAR2 (100 BYTE)

  Scenario: QE to verify TRACKING_NBR column created in PENDING_TRANS_ID table
    Given I am in SQL developer
    When I am in PENDING_TRANS_ID table
    Then I can see TRACKING_NBR column is VARCHAR2 (30 BYTE) NOT NULL

  Scenario: QE to verify PURGED_F column created in PENDING_TRANS_ID table
    Given I am in SQL developer
    When I am in PENDING_TRANS_ID table
    Then I can see PURGED_F column is CHAR (1 BYTE) DEFAULT 'N' NOT NULL

  Scenario: QE to verify the 01_Create_PENDING_TRANS_ID.sql script
    Given I am in SQL developer
    When I am in 01_Create_PENDING_TRANS_ID.sql script
    Then I can see APPL_ID column is now CHAR (3 BYTE) NOT NULL
    And I can see CLIENT_ID column is now VARCHAR2 (30 BYTE) NOT NULL
    And I can see ORDER_NBR column is VARCHAR2 (30 BYTE)
    And I can see RESERVATION_NBR column is VARCHAR2 (100 BYTE)
    And I can see TRACKING_NBR column is VARCHAR2 (30 BYTE) NOT NULL
    And I can see PURGED_F column is CHAR (1 BYTE) DEFAULT 'N' NOT NULL



