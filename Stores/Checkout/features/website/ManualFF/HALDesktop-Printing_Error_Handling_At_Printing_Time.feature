# Author: Ana Ghergut - Stores Domain Checkout Team
# Story: SDU-1274 - Printing error handling at printing time
# Date Created: 12/04/2017
# Date Signed Off:

@domain_stores @project_checkout @manual @HALDesktop @release_1723 @story_SDU-1274
Feature:  As an associate using uPOS at a wrap stand, I want to be informed if there is an issue with the printer uPOS is trying to print to
  and be instructed on what to do, so I am equipped to best assist my customer.

  Scenario: HALDesktop - "The printer lid is open. Close the lid to print." error message is displayed if the lid is open.
    Given I am on Hal Desktop
    When I have completed a transaction at a fixed register
    And The lid is opened
    Then "The printer lid is open. Close the lid to print." error message is displayed
    And the background screen is disabled
    And The message overlay can't be closed
    When The lid is closed
    Then The error message is no longer displayed
    And The receipt is printed

  Scenario: HALDesktop - "The printer is out of paper. Add more paper to print." error message is displayed if thereis no more paper in the printer
    Given I am on Hal Desktop
    When I have completed a transaction at a fixed register
    And The printer is out of paper
    Then "The printer is out of paper. Add more paper to print" error message is displayed
    And the background screen is disabled
    And The message overlay can't be closed
    When The paper is added
    Then The error message is no longer displayed
    And The receipt is printed

  Scenario: HALDesktop - "A printer error occurred. Please open a First Choice ticket and contact a manager." error message is displayed if there is an unresolvable issue with the printer
    Given I am on Hal Desktop
    When I have completed a transaction at a fixed register
    And There is a communication error with the printer
    Then "A printer error occurred. Please open a First Choice ticket and contact a manager." error message is displayed
    And The background screen is disabled
    And The message overlay can be closed
    When The message overlay is closed
    Then The landing page for the fixed register is displayed