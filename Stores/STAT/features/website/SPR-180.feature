# Author: STAT Team
# Story: SPR-180 -Create Pending Return - Database (add column to pending_items table) - Happy Path
# Date Created: 10/23/2017
# Date Signed Off:

@STAT @pending_returns @SPR-180 @manual
Feature:As an application I want to add a QTY_RETURNED column to the PENDING_ITEMS table in the PT schema so
  I can accurately track the number of items returned for an item with a quantity greater than 1



  Scenario: QE to verify QTY_RETURNED column is added to the PENDING_ITEMS table
    Given I am in SQL Developer
    When I am in the PENDING_ITEMS Table
    Then I can see the QTY_RETURNED column




