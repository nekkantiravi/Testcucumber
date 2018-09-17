Feature: Product Availability scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for member_pool
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service against fcc service for product availability

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for member_str1
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for product availability

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for member_drop
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service against fcc service for product availability

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for member_ordr
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for product availability

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for member_spec
    Given I call pdp-xapi service for "2193061" product
    Then I verify pdp-xapi service against fcc service for product availability


  @service_only
  Scenario: Verify pdp-xapi service against fcc service for in-store availability
    Given I call pdp-xapi service for "5137229" product
    Then I verify pdp-xapi service against fcc service for product in-store availability
