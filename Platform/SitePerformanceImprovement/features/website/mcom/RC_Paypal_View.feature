Feature: Regression scenarios for Paypal views on Responsive Checkout page

@domain_Site_performance_Optimization @guest_checkout @Rc_paypal
Scenario: Verify the paypal choosen as a payment option in payment section as guest user
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter details on the shipping section for "guest" and continue from shipping section
And I select paypal option in payment section
Then I verify the alert message displayed with note:
And I verify that Continue to Paypal button is available for guest user

@domain_Site_performance_Optimization @signedIn_checkout @Rc_paypal
Scenario: Verify the paypal choosen as a payment option in payment section as registered user
Given I visit the website as a registered user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "signed in" user
And I enter details on the shipping section for "registered" and continue from shipping section
And I select paypal option in payment section
Then I verify the alert message displayed with note:
And I verify that Continue to Paypal button is available for registered user


  @testfix @domain_Site_performance_Optimization @guest_checkout @Rc_paypal
  Scenario: Verify the paypal choosen as a payment option and continue to payment by paypal as guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "guest" user
    And I enter details on the shipping section for "guest" and continue from shipping section
    And I select paypal option in payment section
    Then I verify the alert message displayed with note:
    And I verify that Continue to Paypal button is available for guest user
    When I continue to paypal checkout as a "guest" user
    Then I verify that page navigated to paypal login
    When I enter the valid paypal credentials and continue from paypal page
    Then I verify the paypal account is mapped to checkout page for "guest" paypal account


  @domain_Site_performance_Optimization @signedIn_checkout @Rc_paypal
  Scenario: Verify the paypal choosen as a payment option and continue to payment by paypal as registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    And I select paypal option in payment section
    Then I verify the alert message displayed with note:
    And I verify that Continue to Paypal button is available for registered user
    When I continue to paypal checkout as a "registered" user
    Then I verify that page navigated to paypal login
    When I enter the valid paypal credentials and continue from paypal page
    Then I verify the paypal account is mapped to checkout page for "registered" paypal account

  @domain_Site_performance_Optimization @signedIn_guest_checkout @Rc_paypal
  Scenario Outline: As a guest/signed in user, verify suppression of paypal, gift card and plenti redeem for BT orders
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I checkout until I reach the <checkout_section> page as a "BT <user_type>" user
    Then I verify user able to pay with card only
    Examples:
      | user_type | checkout_section |
      | guest     | payment          |
      | signed in | shipping&payment |

  @domain_Site_performance_Optimization @guest_checkout @Rc_paypal
  Scenario: Verify the display of sections when user switches between paypal and credit card options as a guest user
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the payment page as a "guest" user
    And I select paypal as tender type
    Then I should not see credit card section in responsive checkout page
    And I should see paypal disclaimer text in payment section as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    And I should see paypal login disclaimer text in paypal section as "You will login on PayPal's site on the next page and review your order, then you will finish the transaction at macy's.com"

  @domain_Site_performance_Optimization @signed_in_checkout @Rc_paypal
  Scenario: Verify the display of sections when user switches between paypal and credit card options as a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see credit card section in responsive checkout page
    When I select paypal as tender type
    Then I should not see credit card section in responsive checkout page
    And I should see paypal disclaimer text in payment section as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    And I should see paypal login disclaimer text in paypal section as "You will login on PayPal's site on the next page and review your order, then you will finish the transaction at macy's.com"

  @domain_Site_performance_Optimization @guest_checkout @Rc_paypal
  Scenario: Verify place order when guest user selects paypal as tender type from payment page
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "guest" user
    And I enter paypal_eligible address on shipping page for guest user
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @domain_Site_performance_Optimization @signed_in_checkout @Rc_paypal
  Scenario: Verify place order when signed in user selects paypal from payment page
    Given I visit the web site as a registered user with checkout eligible address
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed in" user
    And I enter paypal_eligible address on shipping page for responsive signed in user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @domain_Site_performance_Optimization @guest_checkout @Rc_paypal
  Scenario: Verify to validate First view of Paypal section as a guest user
    Given I visit the web site as a registered user with checkout eligible address
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed in" user
    When I select paypal as tender type
    Then I should see the paypal icon
    Then I should not see credit card section in responsive checkout page
    And I should see paypal disclaimer text in payment section as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    And I should see paypal login disclaimer text in paypal section as "You will login on PayPal's site on the next page and review your order, then you will finish the transaction at macy's.com"
    And I should see "Phone Number" field
    And I should see "Email address" field
    And I verify "CONTINUE TO PAYPAL" button is disabled
    When I select checkout with paypal on payment page

  @domain_Site_performance_Optimization @guest_checkout @Rc_paypal
  Scenario: Verify place order when guest user selects paypal as tender type from payment page
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "guest" user
    And I enter paypal_eligible address on shipping page for guest user
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    And I validate Payement section is dispalying
    And I should see the paypal icon
    Then I should see paypal email is displaying under paypal icon
    And I should see macys email and phone number is displaying under Contact info header
    And I verify "Edit" button is enabled
