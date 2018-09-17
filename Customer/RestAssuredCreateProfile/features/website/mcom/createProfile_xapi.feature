Feature: Create profile Post Rest API test Scenarios

  Scenario: Verify xapi create profile post call status and body message
    Given I set rest api server
    Then I execute create profile post call to create user profile
    And I should validate the response status code and body message

  Scenario: Verify to create profile call with existing user
    Given I set rest api server
    Then I execute create profile post call to create existing user profile
    And I should validate the response status code and body message for existing user

  Scenario: Verify to create profile call with empty payload
    Given I set rest api server
    Then I execute create profile post call with empty payload
    And I should validate the response status code and body message for empty payload

  Scenario: Verify to create profile call with DOB less than 13 years
    Given I set rest api server
    Then I execute create profile post call with DOB less than 13 years
    And I should validate the response status code and body message for DOB less than 13 years

