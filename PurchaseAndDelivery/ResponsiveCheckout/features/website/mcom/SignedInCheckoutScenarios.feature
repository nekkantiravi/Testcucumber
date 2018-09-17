Feature: MCOM Signed In checkout regression scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Place an order with Macys's American Express card as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I click change in payment section
    Then I place order with "Macy's American Express" card

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify shipping section validation messages with invalid shipping information as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I click change in shipping address section
    And I click add new address in shipping address section
    And I click save in shipping address section
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

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify payment section validation messages with invalid payment information as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I click change in payment section
    And I click add new card in credit card section
    And I click save in credit card section
    Then I verify below field level error messages in payment section
      | Please select a card type.  |
      | Please enter a card number. |
      | Please select a month.      |
      | Please select a year.       |

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify the display of sections when user switches between paypal and credit card options as a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see credit card section in responsive checkout page
    When I select paypal as tender type
    Then I should not see credit card section in responsive checkout page
    And I should see paypal disclaimer text in payment section as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    And I should see paypal login disclaimer text in paypal section as "You will login on PayPal's site on the next page and review your order, then you will finish the transaction at macy's.com"
    When I select credit card as tender type
    Then I should see credit card section in responsive checkout page

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify the display of error message in shopping bag page when registered user tries to apply an invalid promo code
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I apply an invalid promo code in shopping bag page
    Then I should see promo error message on the top of bag page
    And I should see promo inline error message

  @domain_purchase_and_delivery @signedin @shopping_bag @phase2
  Scenario: Move an item to Wishlist & Add an item to bag from wishList as a signed in user
    Given I visit the website as a registered user using rest services
    When I navigate to a product having "orderable and available" attributes
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP
    Then I should see wishlist landing page as a registered user
    When I add product to my bag from wishlist page and continue shopping
    Then I should see wishlist landing page as a registered user
    When I add product to my bag from wishlist page and checkout
    Then I should see the "shopping bag" page

  @domain_purchase_and_delivery @signedin @sdd
  Scenario: Place an order as a signed in user with SDD selected as shipping method
    Given I visit the website as a registered user using rest services
    When I add an "sdd_eligible and orderable" product to my bag using rest service
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I enter sdd_eligible address on shipping page for signed in user
    And I select sdd shipping method option
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | false |
      | express_shipping  | false |
      | sdd_shipping      | true  |
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see following estimated delivery message on "order review" page:
      | sdd_estimated_delivery                                                      |
      | Order by 1pm & get it today. Order by 11am for Same-Day Delivery on Sundays |
    And I should see following delivery ship note for same day delivery shipping method on "order review" page:
      | sdd_shipping_method                                      |
      | Note: We'll send you an email to schedule your delivery. |
    When I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see following estimated delivery message on "order confirmation" page:
      | sdd_estimated_delivery                                                      |
      | Order by 1pm & get it today. Order by 11am for Same-Day Delivery on Sundays |
    And I should see following delivery ship note for same day delivery shipping method on "order confirmation" page:
      | sdd_shipping_method                                                                     |
      | Note: Same-Day Delivery orders over $500 require a signature when the delivery arrives. |