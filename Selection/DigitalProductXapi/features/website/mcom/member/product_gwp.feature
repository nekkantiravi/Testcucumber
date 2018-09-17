Feature: Product Gift With Purchase scenarios

  @service_only
  Scenario: Verify pdp-xapi service against promotions service for product with gift purchase scenario.
    Given I call pdp-xapi service for "1965762" product
    Then I verify pdp-xapi gift with purchase details against promotions service for same product