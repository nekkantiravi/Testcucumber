Feature: Verify all new UI pages and components

  @scenario1
  Scenario: Report Page - Verify Page Up
    Given I login to Execution Engine
    When I visit Execution Engine Report Page
    Then I should be on Execution Engine Report Page

  @scenario2
  Scenario: Report Page - Verify Page Elements
    Given I login to Execution Engine
    When I visit Execution Engine Report Page
    Then I should verify existance of all elements on Execution Engine Report Page

  @scenario3
  Scenario: Login scenario
    Given I visit the execution engine page
    And I log into Execution Engine

  @scenario4
  Scenario: Run tests
    Given I visit the execution engine page
    And I log into Execution Engine
    And I load my template "Madhu_102"