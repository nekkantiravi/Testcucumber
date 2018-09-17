# Author: STAT Team
# Story: SPR-279 PR - Retrieve by Reservation Nbr Non-Stores - Modify PR Service
# Date Created: 11/30/2017
# Date Signed Off:

@STAT @pending_returns @SPR-279 @manual
Feature: As an application I want to modify the Pending Return service so I can facilitate the
  RETRIEVAL of a Pending Return from a non-stores channel by Reservation Number in order to generate
  a financial transaction

  Scenario: QE to verify successful reservation number retrieval of Pending Return
    Given I am in postman
    When I GET a pending returns reservation number
    Then I should see a successful response


  Scenario: QE to verify error reservation number retrieval of Pending Return
    Given I am in postman
    When I GET a pending returns reservation number
    Then I should see an error response

  Scenario: QE to verify Not Found reservation number retrieval of Pending Return
    Given I am in postman
    When I GET a pending returns reservation number
    Then I should see an Not Found response

  Scenario: QE to verify successful reservation number and UPC retrieval of Pending Return
    Given I am in postman
    When I GET a pending returns reservation number
    Then I should see a successful response


  Scenario: QE to verify error reservation number retrieval and UPC of Pending Return
    Given I am in postman
    When I GET a pending returns reservation number
    Then I should see an error response

  Scenario: QE to verify Not Found reservation number retrieval and UPC of Pending Return
    Given I am in postman
    When I GET a pending returns reservation number
    Then I should see an Not Found response



