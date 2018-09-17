Feature: Color, type, size interaction PDPxAPI Scenarios

  @service_only
  Scenario: Verify pdp-xapi service for color size interaction
    Given I call pdp-xapi service for "2351352" product
    Then I verify pdp-xapi service has color size interaction

  @service_only
  Scenario: Verify pdp-xapi service upc and trait interaction for a product without type
    Given I call pdp-xapi service for "3341455" product
    Then I verify pdp-xapi service does not have the size trait

  @service_only
  Scenario: Verify pdp-xapi service has color type interaction
    Given I call pdp-xapi service for "3341455" product
    Then I verify pdp-xapi service has color type interaction

  @service_only
  Scenario: Verify pdp-xapi service upc and trait interaction for a product without type
    Given I call pdp-xapi service for "3703971" product
    Then I verify pdp-xapi service does not have the type trait

  @service_only
  Scenario: Verify pdp-xapi service upc and trait interaction for a product without type and size
    Given I call pdp-xapi service for "4517800" product
    Then I verify pdp-xapi service does not have the type and size trait

  @service_only
  Scenario: Verify pdp-xapi service response for color,size and type relation under traits.
    Given I call pdp-xapi service for "1921587" product
    Then  I verify pdp-xapi service response has color size and type interaction

  @service_only
  Scenario: Verify pdp-xapi service response for swatch sprite url.
    Given I call pdp-xapi service for "1921587" product
    Then  I verify pdp-xapi service response has swatch sprite url