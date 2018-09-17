Feature: PDPxAPI Scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product name,brand name
    Given I call pdp-xapi service for "2351352" product
    Then I verify pdp-xapi service of "2351352" against fcc service for product name,brand name

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product name,brand name
  In case of Brand name in the middle of the product name, which should not be truncated
    Given I call pdp-xapi service for "2426172" product
    Then I verify pdp-xapi service of "2426172" against fcc service for product name,brand name

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product name,brand name chanel product
    Given I call pdp-xapi service for "4795405" product
    Then I verify pdp-xapi service of "4795405" against fcc service for product name,brand name

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product name,brand name furniture name
    Given I call pdp-xapi service for "1878856" product
    Then I verify pdp-xapi service of "1878856" against fcc service for product name,brand name

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product name,brand name furniture name
    Given I call pdp-xapi service for "1878856" product
    Then I verify pdp-xapi service of "1878856" against fcc service for product name,brand name

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product name,brand name secondary description
    Given I call pdp-xapi service for "4813330" product
    Then I verify pdp-xapi service for secondary description
