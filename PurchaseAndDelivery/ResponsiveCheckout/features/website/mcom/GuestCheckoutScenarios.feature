Feature: MCOM Guest checkout regression scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Place an order with Macy's American Express card
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as a "guest" user
    Then I place order with "Macy's American Express" card

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify guest shipping section validation messages with invalid shipping information and shipping section is not collapsed
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping page as a "guest" user
    And I select continue button on guest shipping page
    Then I verify below field level error messages in shipping section
      | Please enter a first name.     |
      | Please enter a last name.      |
      | Please enter a street address. |
      | Please enter a city.           |
      | Please enter a state.          |
      | Please enter a ZIP code.       |
      | Please enter a phone number.   |
    And I verify field level error message as "Please enter a valid 10-digit phone number." for invalid phone number
    And I verify field level error message as "Please remove any special characters." for invalid data
    And I verify field level error message as "Please enter a valid 5-digit ZIP code." for invalid zip code

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify guest payment section validation messages with invalid payment information and Payment section is not collapsed
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as a "guest" user
    And I select continue button on guest payment page
    Then I verify below field level error messages in payment section
      | Please select a card type.                   |
      | Please enter a card number.                  |
      | Please enter the security code for your card |

  @domain_purchase_and_delivery @guest @bops @phase2
  Scenario: Verify the pick up contact fields validations in guest checkout page
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    And I checkout until I reach the shipping page as a "bops" user
    And I select continue button on guest shipping page
    Then I verify below field level error messages in shipping section
      | Please enter a first name.     |
      | Please enter a last name.      |
      | Please enter an email address. |
      | Please enter a phone number.   |

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the display of sections when user switches between paypal and credit card options as a guest user
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the payment page as a "guest" user
    And I select paypal as tender type
    Then I should not see credit card section in responsive checkout page
    And I should see paypal disclaimer text in payment section as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    And I should see paypal login disclaimer text in paypal section as "You will login on PayPal's site on the next page and review your order, then you will finish the transaction at macy's.com"
    And I should see gift card section in disabled state
    When I select credit card as tender type
    Then I should see credit card section in responsive checkout page
    And I should see gift card section in enabled state
    And I should see paypal disclaimer text in gift card section as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."

  @domain_purchase_and_delivery @guest @bops @phase2
  Scenario: Verify SMS legal copy update on shipping and on OCP page as a guest user for BOPS order
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    And I checkout until I reach the shipping page as a "bops" user
    Then I verify SMS legal copy "Msg & Data rates may apply" on shipping page
    When I checkout until I reach the payment page as a "bops" user
    Then I check for bops item availability
    When I checkout until I reach the order confirmation page as a "bops" user
    And I get order details
    Then I verify SMS legal copy "We'll send an email and text message when your order is ready to be picked up. Msg and data rates may apply." on order confirmation page

  @domain_purchase_and_delivery @guest @bops @phase2
  Scenario: Verify Order modify/cancel message on Order confirmation page as a guest user for BOPS product
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    And I checkout until I reach the payment page as a "bops" user
    Then I check for bops item availability
    When I checkout until I reach the order confirmation page as a "bops" user
    And I get order details
    Then I should see "You can make changes to most orders up to 15 minutes after they have been placed." order modified or cancel message

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify apply and remove promo code in Iship shopping bag page
    Given I visit the website as a iship user from "IN" with currency "INR" using rest services
    When I add an "iship_eligible and promo_eligible" product to my bag using rest service
    And I apply a valid promo code in shopping bag page
    And I remove the promo code
    Then I should not see the applied promo code
    When I apply an invalid promo code in shopping bag page
    Then I should see promo error message on the top of bag page

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the display of error message in shopping bag page when guest user tries to apply an invalid promo code
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I apply an invalid promo code in shopping bag page
    Then I should see promo error message on the top of bag page
    And I should see promo inline error message

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
      | Order by 1pm & get it today. Order by 11am for Same-Day Delivery on Sundays |
    And I should see following delivery ship note for same day delivery shipping method on "order review" page:
      | sdd_shipping_method                                      |
      | Note: We'll send you an email to schedule your delivery. |
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see following estimated delivery message on "order confirmation" page:
      | sdd_estimated_delivery                                                      |
      | Order by 1pm & get it today. Order by 11am for Same-Day Delivery on Sundays |
    And I should see following delivery ship note for same day delivery shipping method on "order confirmation" page:
      | sdd_shipping_method                                                                     |
      | Note: Same-Day Delivery orders over $500 require a signature when the delivery arrives. |