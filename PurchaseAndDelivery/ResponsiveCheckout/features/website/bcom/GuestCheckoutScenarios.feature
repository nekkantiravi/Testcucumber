Feature: BCOM Guest checkout regression scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Place an order with Bloomingdale's American Express card
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as a "guest" user
    Then I place order with "Bloomingdale's American Express" card

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify shipping section validation messages with invalid shipping information as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping page as a "guest" user
    And I select continue button on guest shipping page
    Then I verify below field level error messages in shipping section
      | Please enter first name.     |
      | Please enter last name.      |
      | Please enter an address.     |
      | Please enter a city.         |
      | Please select a state.       |
      | Please enter a zip code.     |
      | Please enter a phone number. |
    And I verify field level error message as "Please enter a 10-digit phone number." for invalid phone number
    And I verify field level error message as "Please remove any special characters." for invalid data

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify payment section validation messages with invalid payment information as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as a "guest" user
    And I select continue button on guest payment page
    Then I verify below field level error messages in payment section
      | Please select card type.                                            |
      | Please enter a credit card number.                                  |
      | Please enter the security code from the front or back of your card. |

  @domain_purchase_and_delivery @guest @bops @phase2
  Scenario: Verify the pick up contact fields validations in guest checkout page
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    And I checkout until I reach the shipping page as a "bops" user
    And I select continue button on guest shipping page
    Then I verify below field level error messages in shipping section
      | Please enter first name.       |
      | Please enter last name.        |
      | Please enter an email address. |
      | Please enter a phone number.   |

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the display of sections when user switches between paypal and credit card options as a guest user
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the payment page as a "guest" user
    When I select paypal as tender type
    Then I should not see credit card section in responsive checkout page
    And I should see paypal disclaimer text in payment section as "Note: PayPal can't be used with Gift Cards, Rewards Cards or Credit Cards."
    And I should see paypal login disclaimer text in paypal section as "You will be directed to the PayPal site to review your order, then complete your transaction at bloomingdales.com."
    And I should see gift card section in disabled state
    When I select credit card as tender type
    Then I should see credit card section in responsive checkout page
    And I should see gift card section in enabled state
    And I should see paypal disclaimer text in gift card section as "Note: PayPal can't be used with Gift Cards, Rewards Cards or Credit Cards."

  @domain_purchase_and_delivery @guest @bops @phase2
  Scenario: Verify SMS legal copy update on shipping and on OCP page as a guest user for BOPS order
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    And I checkout until I reach the shipping page as a "bops" user
    Then I verify SMS legal copy "Message & data rates may apply" on shipping page

  @domain_purchase_and_delivery @guest @bops @phase2
  Scenario: Verify Order modify/cancel message on Order confirmation page as a guest user for BOPS product
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    And I checkout until I reach the payment page as a "bops" user
    Then I check for bops item availability
    When I checkout until I reach the order confirmation page as a "bops" user
    And I get order details
    Then I should see "You can make changes to your order up to 15 minutes after it was placed" order modified or cancel message

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the display of error message in shopping bag page when guest user tries to apply an invalid promo code
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I apply an invalid promo code in shopping bag page
    Then I should see promo error message on the top of bag page

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify the display of promotion error message in Iship shopping bag page
    Given I visit the website as a iship user from "IN" with currency "INR" using rest services
    When I add an "iship_eligible and promo_eligible" product to my bag using rest service
    And I apply an invalid promo code in shopping bag page
    Then I should see promo error message on the top of bag page

  @domain_purchase_and_delivery @guest @sdd
  Scenario: Place an order as a guest user with SDD selected as shipping method
    Given I visit the website as a guest user using rest services
    When I add an available_sdd product to bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter sdd_eligible address on shipping page for guest user
    And I select sdd shipping method option
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | false |
      | express_shipping  | false |
      | sdd_shipping      | true  |
    When I select continue button on guest shipping page
    And I checkout until I reach the order review page as a "guest" user
    Then I should see following estimated delivery message on "order review" page:
      | sdd_estimated_delivery                                                      |
      | Today (if placed by 1PM Mon–Sat; 11AM Sun). Orders placed after these times will be delivered the next day. |
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see following estimated delivery message on "order confirmation" page:
      | sdd_estimated_delivery                                                      |
      | Today (if placed by 1PM Mon–Sat; 11AM Sun). Orders placed after these times will be delivered the next day. |
