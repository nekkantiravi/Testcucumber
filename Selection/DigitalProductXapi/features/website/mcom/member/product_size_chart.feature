Feature: Product Size Chart Scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for Size chart for a product with size chart
    Given I call pdp-xapi service for "1380110" product
    Then I verify pdp-xapi service against fcc service for size chart

  @service_only
  Scenario: Verify pdp-xapi service upc Size trait interaction for a product with size chart
    Given I call pdp-xapi service for "1380110" product
    Then I should not see the canvas id in the upc traits

  @service_only
  Scenario: Verify pdp-xapi service upc Size trait interaction for a product without size chart like jewellry
    Given I call pdp-xapi service for "4922965" product
    Then I should not see the canvas id in the size map