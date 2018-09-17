Feature: Registry Product XAPI scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for registrable product
    Given I call pdp-xapi service for "2998957" product
    Then I verify pdp-xapi service against fcc service for registrable product

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for non registrable product
    Given I call pdp-xapi service for "147374" product
    Then I verify pdp-xapi service against fcc service for non registrable product
