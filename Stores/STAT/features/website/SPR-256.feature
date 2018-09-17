# Author: Stat Team
# Story: SPR-256  - PR - Status - Modify - by Order Number
# Date Created: 01/02/2018
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-256
Feature: As an application I want to modify the Pending Return service so I can facilitate the STATUS of a
  Pending Return from a non-stores channel by Order Number in order to view Pending Return information

  @Macy's

  Scenario: Retrieve a Pending Return request (GET) with a valid OrderNbr and X-Macys-ClientId = 01, 02, 03 or 04
    Given I am in Postman
    When I GET a PendingReturn with a valid <ORDER> and valid <CLIENTID>
    Then I can see a PendingReturn is returned
    And I can validate the data for the transaction

  Examples:
  |CLIENTID| ORDER|
  |01| 9999999999|
  |02| 9999999999|
  |03| 9999999999|
  |04| 9999999999|

  Scenario: Retrieve a Pending Return request (GET) with a valid OrderNbr and X-Macys-ClientId NOT = 01, 02, 03 or 04
    Given I am in Postman
    When I GET a PendingReturn with a valid OrderNbr and X-Macys-ClientId NOT = 01, 02, 03 or 04
    Then I can see the appropiate error message is returned

  Scenario: Retrieve a Pending Return request (GET) using 1 valid OrderNbr
    Given I am in Postman
    When I GET a PendingReturn with 1 valid OrderNbr
    Then I can see 1 PendingReturn is returned
    And I can validate data for the transaction

  Scenario: Retrieve a Pending Return request (GET) using > 1 and < 10 valid OrderNbrs
    Given I am in Postman
    When I GET a PendingReturn using > 1 valid and < 10 OrderNbrs
    Then I can see the number of PendingReturns requested are all returned
    And I can validate data for the transactions

  Scenario: Retrieve a Pending Return request (GET) using > 10 valid OrderNbrs
    Given I am in Postman
    When I GET a PendingReturn using > OrderNbrs
    Then I can see the first 10 PendingReturns requested are returned
    And I can validate data for the transactions

  Scenario: Retrieve a Pending Return request (GET) by OrderNbr without the OrderNbr
    Given I am in Postman
    When I GET a PendingReturn by OrderNbr without an OrderNbr
    Then I can see the appropriate error message is returned

  Scenario: Retrieve a Pending Return request (GET) with 1 or more invalid OrderNbrs
    Given I am in Postman
    When I GET a PendingReturn with 1 or more invalid OrderNbrs
    Then I can see PendingReturn is returned for valid OrderNbrs
    And I can validate data for the transaction







