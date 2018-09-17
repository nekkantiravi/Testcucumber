Feature: Digital Product XAPi scenarios with wiremock
#Work in progress until there are Master products in the resources folder.
  @wip
    Scenario: Verify pdp-xapi service product type for a Master product
    Given I call wiremock pdp-xapi service for "" product
    Then I verify is master product
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service data for a Master product that is not a Big Ticket
    Given I call wiremock pdp-xapi service for "" product
    Then I verify is not big ticket
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service data for a Master product that is Big Ticket
    Given I call wiremock pdp-xapi service for "" product
    Then I verify is big ticket
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_id to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product id is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_name to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product name is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_original_price to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_original_price is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_price to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_price is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_pricing_state to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_pricing_state is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_rating to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_rating is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_reviews to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_reviews is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_video to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify there is video
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_gwp_available to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_gwp_available is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends product_pwp_available to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify product_pwp_available is not empty
    And stop the wiremock

  @wip
  Scenario: Verify pdp-xapi service for a Master product sends true_fit_size to Tealium
    Given I call wiremock pdp-xapi service for "" product
    Then I verify there is true_fit_size
    And stop the wiremock

