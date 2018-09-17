# Author: Stat Team
# Story: SPR-178  - PR - Status - Modify - by Reservation Number
# Date Created: 01/02/2018
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-178
Feature: As an application I want to modify the Pending Return service so I can facilitate the STATUS of a
  Pending Return from a non-stores channel by Reservation Number in order to view Pending Return information

  @Macy's

  Scenario: Retrieve a Pending Return request (GET) with a valid ResvNbr and X-Macys-ClientId = 01, 02, 03 or 04
    Given I am in Postman
    When I GET a PendingReturn with a valid <RESV> and valid <CLIENTID>
    Then I can see a PendingReturn is returned
    And I can validate the data for the transaction

  Examples:
  |CLIENTID| RESV|
  |01| 9999999999|
  |02| 9999999999|
  |03| 9999999999|
  |04| 9999999999|

  Scenario: Retrieve a Pending Return request (GET) with a valid ResvNbr and X-Macys-ClientId NOT = 01, 02, 03 or 04
    Given I am in Postman
    When I GET a PendingReturn with a valid ResvNbr and X-Macys-ClientId NOT = 01, 02, 03 or 04
    Then I can see the appropiate error message is returned

  Scenario: Retrieve a Pending Return request (GET) using 1 valid ResvNbr
    Given I am in Postman
    When I GET a PendingReturn with 1 valid ResvNbr
    Then I can see 1 PendingReturn is returned
    And I can validate data for the transaction

  Scenario: Retrieve a Pending Return request (GET) using > 1 and < 10 valid ResvNbrs
    Given I am in Postman
    When I GET a PendingReturn using > 1 valid and < 10 ResvNbrs
    Then I can see the number of PendingReturns requested are all returned
    And I can validate data for the transactions

  Scenario: Retrieve a Pending Return request (GET) using > 10 valid ResvNbrs
    Given I am in Postman
    When I GET a PendingReturn using > ResvNbrs
    Then I can see the first 10 PendingReturns requested are returned
    And I can validate data for the transactions

  Scenario: Retrieve a Pending Return request (GET) by ResvNbr without the ResvNbr
    Given I am in Postman
    When I GET a PendingReturn by ResvNbr without an ResvNbr
    Then I can see the appropriate error message is returned

  Scenario: Retrieve a Pending Return request (GET) with 1 or more invalid ResvNbrs
    Given I am in Postman
    When I GET a PendingReturn with 1 or more invalid ResvNbrs
    Then I can see PendingReturn is returned for valid ResvNbrs
    And I can validate data for the transaction







