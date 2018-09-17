Feature: Product Price scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for colorway top price
    Given I call pdp-xapi service for "1103208" product
    Then I verify pdp-xapi service of "1103208" against fcc service for colorway top price

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for colorway pricing
    Given I call pdp-xapi service for "1103208" product
    Then I verify pdp-xapi service of "1103208" against fcc service for colorway pricing

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for no mark down colorway pricing
    Given I call pdp-xapi service for "1103208" product
    Then I verify pdp-xapi service of "1103208" against fcc service for colorway pricing

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for partial mark down colorway pricing
    Given I call pdp-xapi service for "1103208" product
    Then I verify pdp-xapi service of "1103208" against fcc service for colorway pricing

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for mark down colorway pricing
    Given I call pdp-xapi service for "1103208" product
    Then I verify pdp-xapi service of "1103208" against fcc service for colorway pricing