
Feature: As a user signed in or guest, I should be able to cancel what I am doing and return to the Order Details page.

  Mingle Story: http://mingle/projects/customer_management/cards/6963
  http://mingle/projects/customer_management/cards/6964

  @returns_regression @14H @use_regression @mode_domestic @returns_mgt_3 @credit-6963 @credit-6964 @artifact_shopapp @s4a_stable @domain_customer @feature_giftreturn_returnsubmit @migrated_to_sdt
  Scenario Outline: Payment Information, Billing Addres, Line Item Price, and Total are not visible when canceling an order on the Order Details page as a Guest user
    Given I visit order history page as a guest user
    When I lookup with "<order_type>" order with gift return
    And I fetch order details from OD page
    Then I select return items button in "OD" page
    And I select items and continue to submit page
    And I select Yes in cancel return overlay
    And I verify details related to payment and billing address should not be displayed
    Then I verify price and total for "All" shipment

    Examples:
      | order_type                                                                       |
      | shipping_and_billing_address_different_for_creditcard                            |
      | mixed_order_with_creditcard_and_same_shipping_and_billing_address                |