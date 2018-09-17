Feature: Reviews header XAPI scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for review statistics for products with the review
    Given I call pdp-xapi service for "2998957" product
    Then I verify pdp-xapi service against fcc service for review statistics

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for review statistics for products without reviews
    Given I call pdp-xapi service for "4834993" product
    Then I verify pdp-xapi service against fcc service for no review statistics
