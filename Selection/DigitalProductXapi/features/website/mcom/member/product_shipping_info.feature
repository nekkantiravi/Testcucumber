Feature: Product Shipping scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for free shipping product
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service of "728155" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for beauty shipping product
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for shipping method exclusion
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service of "728155" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for ship state exclusion
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for return constraints
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for shipping return links
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for click to call
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for coach
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for gift card
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for surcharge message
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for special gift box exclusion
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for gift wrap exclusions
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for product shipping info