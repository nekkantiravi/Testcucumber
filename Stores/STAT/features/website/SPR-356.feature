# Author: Stat Team
# Story: SPR-356  - Create Pending Return - Modify pendingReturn - Location added to ADDRESS_INFO
# Date Created: 12/27/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-356
Feature: As an application I want to modify the mspPendingReturn service to accept location as part
  of the address information so that I can facilitate more accurate processing of the pending transaction

  @Macy's
  Scenario: Submit a Pending Return request (POST) with location as part of rtnToAddressInfo and view
  LOCATION in the ADDRESS_INFO table
    Given I am in Postman
    When I POST a PendingReturn with location as part of rtnToAddressInfo
    Then I can see a valid pendingTransId generated
    When I am in SQL developer
    And  I am in ADDRESS_INFO table
    Then I can see the LOCATION column has the correct value

  Scenario: Submit a Pending Return request (POST) without a location as part of rtnToAddressInfo and view
  LOCATION is null in the ADDRESS_INFO table
    Given I am in Postman
    When I POST a PendingReturn without a location as part of rtnToAddressInfo
    Then I can see a valid pendingTransId generated
    When I am in SQL developer
    And  I am in ADDRESS_INFO table
    Then I can see the LOCATION column has a null value

  Scenario: Retrieve a Pending Return request (GET) where LOCATION is not null in ADDRESS_INFO
    Given I am in Postman
    When I GET a PendingReturn where LOCATION is not null in ADDRESS_INFO
    Then I can see a PendingReturn is returned
    And I can see a value for location under rtnToAddressInfo within the JSON message

  Scenario: Retrieve a Pending Return request (GET) where LOCATION is null in ADDRESS_INFO
    Given I am in Postman
    When I GET a PendingReturn where LOCATION is null in ADDRESS_INFO
    Then I can see a PendingReturn is returned
    And I can see a value is not returned for location under rtnToAddressInfo within the JSON message





