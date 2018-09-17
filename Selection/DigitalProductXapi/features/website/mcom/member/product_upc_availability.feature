Feature: Color, type, size availability for PDPxAPI Scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product color availability
    Given I call pdp-xapi service for "4894704" product
    Then I verify pdp-xapi service of "4894704" against fcc service for product color availability

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product type and it's availability
    Given I call pdp-xapi service for "3341455" product
    Then I verify pdp-xapi service of "3341455" against fcc service for product type and it's availability

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product size and it's availability
    Given I call pdp-xapi service for "1942014" product
    Then I verify pdp-xapi service of "1942014" against fcc service for product size and it's availability
