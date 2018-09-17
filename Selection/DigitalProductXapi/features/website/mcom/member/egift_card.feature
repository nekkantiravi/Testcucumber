Feature: E-Gift card scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for gift card product
    Given I call pdp-xapi service for "4391437" product
    Then I verify pdp-xapi service for gift card product
