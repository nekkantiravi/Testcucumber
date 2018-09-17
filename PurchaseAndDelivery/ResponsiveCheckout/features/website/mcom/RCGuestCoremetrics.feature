Feature: Responsive Checkout Guest Coremetrics scenarios.

  @project_responsive_checkout @domain_purchase_and_delivery
  Scenario: verify coremetrics tags for normal item as a guest user.
    Given I visit the web site as a guest user
    When I add a "orderable and available" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I checkout until I reach the payment page as a "guest" user
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page
    And I checkout until I reach the order confirmation page as a "guest" user

  @project_responsive_checkout @domain_purchase_and_delivery
  Scenario: verify coremetrics tags for normal item as a guest user with paypal as tender type.
    Given I visit the web site as a guest user
    When I add a "orderable and available" product to my bag
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order

  @project_responsive_checkout @domain_purchase_and_delivery
  Scenario: verify coremetrics tags for bops item as a guest user.
    Given I visit the web site as a guest user
    When I add an "available_bops" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    When I checkout until I reach the shipping page as a "bops" user
    When I checkout until I reach the payment page as a "bops" user
    When I checkout until I reach the order review page as a "bops" user
    When I place an order

  @project_responsive_checkout @domain_purchase_and_delivery
  Scenario: verify coremetrics tags for bops and normal items as a guest user.
    Given I visit the web site as a guest user
    When I add an "available_bops" product to my bag
    And I add a "available and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    When I checkout until I reach the shipping page as a "bops" user
    When I checkout until I reach the payment page as a "bops" user
    When I checkout until I reach the order review page as a "bops" user
    When I place an order

  @project_responsive_checkout @domain_purchase_and_delivery
  Scenario: verify coremetrics tags for 3d secure as a guest user.
    Given I visit the web site as a guest user
    And I change the cookie to "responsive"
    When I add a "available and orderable" product to my bag that is not a "master_product"
    And I checkout until I reach the payment page as a "guest" user
    And I add 3d secure "Visa" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully