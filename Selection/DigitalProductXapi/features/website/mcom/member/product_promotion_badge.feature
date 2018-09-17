Feature: Product Promotion Badge scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with promotions badges
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service of "728155" against fcc service for promotion badge

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with more than one promotion badge
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for promotion badge

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with free shipping and returns
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service of "2193061" against fcc service for promotion badge

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with free GWP
    Given I call pdp-xapi service for "1965762" product
    Then I verify pdp-xapi service of "1965762" against fcc service for promotion badge

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with 40% off
    Given I call pdp-xapi service for "2242674" product
    Then I verify pdp-xapi service of "2242674" against fcc service for promotion badge

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product with 2 for $12
    Given I call pdp-xapi service for "4432619" product
    Then I verify pdp-xapi service of "4432619" against fcc service for promotion badge

  @service_only
  Scenario: Verify pdp-xapi service against fcc service product without promotions badges
    Given I call pdp-xapi service for "1749924" product
    Then I verify pdp-xapi service of "1749924" against fcc service for promotion badge