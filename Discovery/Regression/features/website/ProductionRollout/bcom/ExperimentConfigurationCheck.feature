Feature: BCOM - Experiment Configuration Validations

  @domain_discovery @experiment_check
  Scenario: Verify discovery domain experiment configuration values on production
    Given I fetch all configured experiment values for bcom production
    Then I verify below experiments details:
      | ComponentizedSnB |
