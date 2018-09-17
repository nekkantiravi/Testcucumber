
Feature: PDPxAPI Not found Scenarios

  @service_only
  Scenario: Verify pdp-xapi service for Product Not Found
    Given I call pdp-xapi service for "1" product
    Then I verify pdp-xapi service for error messages
