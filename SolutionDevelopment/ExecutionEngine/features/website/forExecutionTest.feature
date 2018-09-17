Feature: For testing Execution Engine capability to trigger execution

  @scenario1 @execution_testing
  Scenario: Testing passed scenario in Execution Engine - Scenario 1
    Then Step that should pass always

  @scenario2 @execution_testing
  Scenario: Testing failed scenario in Execution Engine - Scenario 2
    Then Step that should fail always with ENV error

  @scenario3 @execution_testing
  Scenario: Testing passed scenario in Execution Engine - Scenario 3
    Then Step that should pass always

  @scenario4 @execution_testing
  Scenario: Testing failed scenario in Execution Engine - Scenario 4
    Then Step that should fail always with ENV error

  @scenario5 @execution_testing
  Scenario: Testing passed scenario in Execution Engine - Scenario 5
    Then Step that should pass always

  @scenario6 @execution_testing
  Scenario: Testing failed scenario in Execution Engine - Scenario 6
    Then Step that should fail always with Test error

  @scenario7 @execution_testing
  Scenario: Testing passed scenario in Execution Engine - Scenario 7
    Then Step that should pass always

  @scenario8 @execution_testing
  Scenario: Testing failed scenario in Execution Engine - Scenario 8
    Then Step that should fail always with TBD error

  @scenario9 @execution_testing
  Scenario: Testing passed scenario in Execution Engine - Scenario 9
    Then Step that should pass always

  @scenario10 @execution_testing
  Scenario: Testing passed scenario in Execution Engine - Scenario 10
    Then Step that should pass always
