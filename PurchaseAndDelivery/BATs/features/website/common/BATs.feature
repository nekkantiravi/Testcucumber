@order_promotion_cache_loaded
Feature: BATs for Purchase and Delivery


  @domain_purchase_and_delivery @use_bat
  Scenario: Verify User ables to submit valid shipping information
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    Then I should see shipping summary section with "Edit" button

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify User ables to submit edited shipping information
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    Then I should see shipping summary section with "Edit" button
    When I tap "Edit" button in "shipping" section
    And I select premium in shipping methods
    And I change name for shipping address to "QA Testing"
    Then I should see shipping summary section with selected premium method, name "QA Testing" and "Edit" button

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify user should see shipping methods displayed in shipping section
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see same shipping methods displayed with same order

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify User ables to submit valid payment information
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "guest" user
    Then I should see payment summary section

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify checkout summary displayed for guest responsive checkout page
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    Then I should see checkout summary values displayed with "dollar($)" and "2 decimals" points as guest

  @domain_purchase_and_delivery @use_bat @dsv_desktop_sev2
  Scenario: Verify order summary is updated when there is any change in the Shipping details section
    Given I visit the web site as a guest user
    And I add a "available and orderable" product to my bag
    When I checkout until I reach the payment page as a "guest" user
    And I should see shipping summary section with "Edit" button
    And I tap "Edit" button in "shipping" section
    And I select premium in shipping methods
    And I change name for shipping address to "QA Testing"
    Then I should see shipping charge "Updated" in checkout summary with Premium shipping method

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify whether user is able to place the order successfully
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "guest" user
    Then I should see "place order" button in "enabled" mode
    When I tap "place order" button in "order review" section
    Then I should see order confirmation section displayed with order details

  @domain_purchase_and_delivery @use_bat
  Scenario:Verify shopping bag is empty once the order is placed successfully
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "guest" user
    When I return to bag page
    Then I should see zero items on bag page

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify checkout summary displayed for signed in responsive checkout page as a signed in user
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see checkout summary values displayed with "dollar($)" and "2 decimals" points

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify order status once order is placed successfully
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "guest" user
    And I should see order confirmation section displayed with order details
    Then I verify the order status

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify User should be able to see shipping summary section with default shipping address as a signed in user
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see shipping summary section with "default" shipping address

  @domain_purchase_and_delivery @use_bat
  Scenario: Place a BOPS order with bops item as a guest user
    Given I visit the web site as a guest user
    When I add a "available_bops and orderable" product to my bag
    And I select pick up option for bops item after selecting available store
    And I checkout until I reach the shipping page as a "guest" user
    And I enter pick up contact details in responsive shipping page
    And I continue checkout until I reach the order confirmation page as a "guest" user
    Then I should see order confirmation section displayed with order details

  @domain_purchase_and_delivery @use_bat
  Scenario: Verify the display of selected credit card details in summary state as a signed in user
    Given I visit the web site as a registered user with credit cards
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see payment section in summary state
    When I click "change" in payment summary section
    And I select a random credit card
    And I click "save" in payment section
    Then I should see payment summary section with selected credit card