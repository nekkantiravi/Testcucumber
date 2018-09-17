Feature: Unified Login XAPI  test Scenarios

  Scenario: Verify Unified Pre-signIn xAPI response get call status and body message
    Given I set rest api server
    Then I execute pre sign in xAPI get call
    And I should validate the Pre-signIn xAPI response status code and body message

  Scenario: Verify Unified Post-signIn xAPI response get call status and body message
    Given I set rest api server
    Then I execute post sign in xAPI get call
    And I should validate the Post-signIn xAPI response status code and body message


