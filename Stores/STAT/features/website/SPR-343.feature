# Author: Stat Team
# Story: SPR-343  - Create Pending Return - Modify pendingReturn - Timestamps
# Date Created: 12/13/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-343
Feature: As an application I want to modify the logic related to the timestamp members of the pendingReturn service
   so that they are represented as a human readable date/time instead of the number of milliseconds
   since 01/01/1970 00:00:00 UTC

  @Macy's
  Scenario: Submit a Pending Return create request (POST) and make sure that the timestamp returned
  is a human readable date/time
    Given I am in Postman
    When I POST a PendingReturn
    Then I can see a valid pendingTransId generated
    And I can see the timestamp is formatted as DD-MON-YY HH:MI:SS.FF PM

  Scenario: Retrieve a Pending Return request (GET) and make sure that the timestamp returned
  is a human readable date/time
    Given I am in Postman
    When I GET a PendingReturn
    Then I can see a PendingReturn is returned
    And I can see the timestamp is formatted as DD-MON-YY HH:MI:SS.FF PM





