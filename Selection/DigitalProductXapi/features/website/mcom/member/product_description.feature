Feature: Product Description scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product with only description
    Given I call pdp-xapi service for "2331450" product
    Then I verify pdp-xapi service against fcc service for product description

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with description and bullets
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for product description

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product with less than 10 lines of bullets
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service against fcc service for product description

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with more than 10 lines of bullets
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for product description

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with warranty link
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for warranty link

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with learn more about furniture
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for learn more about furniture.

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with travel guide
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for travel guide

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with rugs return policy
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for rugs return policy
