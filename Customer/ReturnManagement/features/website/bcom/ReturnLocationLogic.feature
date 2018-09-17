Feature: As a business, we would like to return any dotcom order filled out of a single warehouse to return to the filled location.

  @14I @artifact_shopapp @credit-6603 @priority_high @use_regression @mode_domestic @returns_mgt_3 @domain_customer @run_this @migrated_to_sdt
  Scenario Outline: Return Address on the order details page is correct as a Signed-in user
  Given I visit the web site as a guest user
  When I navigate to confirmation page using "<order_type>" order as a "signed in" user
  And I navigate to order details page as a "signed" user
  Then I verify return address on Order details
  Examples:
    |order_type                      |
    | order_delivered_from_vendor    |
    | order_delivered_from_warehouse |

  @14I @artifact_shopapp @credit-6603 @priority_high @use_regression @mode_domestic @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario Outline: Return Address on the order details page is correct as a guest user
  Verify that return address when order is full filled from different locations as a guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "<order_type>" order as a "guest" user
    And I navigate to order details page as a "guest" user
    Then I verify return address on Order details
    Examples:
      |order_type                      |
      | order_delivered_from_store     |
      | esend                          |
      | bops_delivered_from_store      |