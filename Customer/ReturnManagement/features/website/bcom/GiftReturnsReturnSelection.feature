
Feature: Gift Returns - Return selection Page

  @returns_regression @migrated_to_sdt
  Scenario Outline: Refund method and Order total is not visible on the Return Selection page as a Guest user
    Given I visit order history page as a guest user
    When I lookup with "<order_type>" order with gift return
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    And I should verify that refund method section is not visible
    And I verify order total in order header should not be displayed on the return selection page.
    Examples:
      | order_type                                                                           |
      | mixed_order_with_credit_card_gift_card_and_different_shipping_and_billing_address    |
      | EGC                                                                                  |
      | shipping_and_billing_address_different_for_creditcard                                |