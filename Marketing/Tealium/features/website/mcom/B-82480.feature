Feature: Verify the data layer attributes for Order confirmation page

  @Tealium_proxy @Datalayer
  Scenario: Verify the data layer attributes on order confirmation page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I checkout until I reach the payment page as a "guest" user
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page
    And I checkout until I reached the order confirmation page as a "guest" user
    Then I verify the network call
      | chkout/rcplaceorder |
    Then I verify the data attributes for tealium tags on order confirmation page from utagData for "rcplaceorder"
      | page_type              |
      | order_id               |
      | order_currency         |
      | order_subtotal         |
      | order_shipping         |
      | order_tax              |
      | order_discount         |
      | order_promocode        |
      | order_total            |
      | order_totalitems       |
      | payment_type           |
      | product_name           |
      | product_category_id    |
      | product_category_name  |
      | product_color          |
      | product_size           |
      | product_price          |
      | product_quantity       |
      | product_subtotal       |
      | product_upc            |
      | product_registry_flag   |
      | product_registry_number |


  @Tealium_proxy @Datalayer
  Scenario: Verify the order confirmation details attributes on order confirmation page
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    Then I checkout until I reached the order confirmation page as an "registered" user
    Then I verify the network call
      | chkout/placeorder |
    Then I verify the data attributes for tealium tags on order confirmation page from utagData for "placeorder"
      | page_type              |
      | order_id               |
      | order_currency         |
      | order_subtotal         |
      | order_shipping         |
      | order_tax              |
      | order_discount         |
      | order_promocode        |
      | order_total            |
      | order_totalitems       |
      | payment_type           |
      | product_name           |
      | product_category_id    |
      | product_category_name  |
      | product_color          |
      | product_size           |
      | product_price          |
      | product_quantity       |
      | product_subtotal       |
      | product_upc            |
      | product_registry_flag   |
      | product_registry_number |
    Then I verify the data attributes for tealium tags on order confirmation page from utagData for "placeorder"
      | orderCurrency |
      | orderDiscount |
      | orderId       |
