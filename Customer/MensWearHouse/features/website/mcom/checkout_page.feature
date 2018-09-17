Feature: Validation of Tux features on Checkout page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium
  Scenario: Verify paypal button is disabled on checkout page for Tux only in bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I "should not" see paypal button on checkout page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium
  Scenario: Verify customer should see the Gift Options for tuxedo rental items
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I click on gift options section
    Then I should see hide prices on packing slip checkbox is pre-selected
    And I should see the below checkbox is disabled in gift options
      | gift_message |
      | gift_box     |
    And I should see below message for tuxedo rental as expected in gift options section
      | Gift options do not apply to your Tuxedo Rental. |


  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16L @B-56511
  Scenario: Verify change button in Shipping method as disabled,when tuxedo rental item in the bag
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should not see change button in shipping method section
    And I should see "Everyday Free Shipping FREE" method selected by default
    And I should see below message for tuxedo rental as expected in shipping method section
      | Tuxedo Rentals will ship 10-14 days prior to event date. |


  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16L @B-56511
  Scenario: Verify shipping methods on RC page when accordian open view for Mixed items.
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    And I add a random product to bag
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I edit shipping_option in shipping method section
    Then I should see below message for tuxedo rental as expected in shipping method section after clicking on change button
      | Shipping methods do not apply to your Tuxedo Rental. |

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-56515
  Scenario: Verify rental damage viewer and shipping value in rc page
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "shipping & payment" page
    And I should see shipping value as free on "responsive_order_summary" section on "responsive_checkout_signed_in" page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-56510
  Scenario: Verify customer should see Customer service phone numbers in the footer when a tux item in bag
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I should see mkp reservation id in footer
    Then I should see the "tux" customer service phone numbers in footer in "responsive_checkout_signed_in" page
      | tux_phone_number_message                          |
      | Need help? For Tuxedo Rentals Call 1-844-MCYS-TUX |

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-56513
  Scenario: Verify paypal button is disbaled on checkout page for mixed bag
    Given I visit the web site as a guest user
    And I create a new profile
    And I should be navigated to My Account Page
    And I add a random product to bag
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I "should not" see paypal button on checkout page


  @project_menswearhousedigital @use_wip @feature_checkout @domain_customer_management @priority_medium @release_16T @B-56512
  Scenario: Verify customer should see the Gift Options for tuxedo rental items with Macys items in bag
    Given I visit the web site as a guest user
    And I create a new profile
    And I should be navigated to My Account Page
    And I add a random product to bag
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I click on gift options section
    And I select gift box in gift options
    And I save gift options
    Then  I should see below message on "responsive_checkout_signed_in" page
      | Please note the items below cannot be gift wrapped in your order. |
    And I select gift options on shipping page
    And I save gift options
    Then  I should see below message on "responsive_checkout_signed_in" page
      | Please note the items below cannot be gift messaged in your order. |

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-56510
  Scenario: Verify customer should see Customer service phone numbers in the footer when item is mixed bag
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    And I add a random product to bag
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see the "tux and macys" customer service phone numbers in footer in "responsive_checkout_signed_in" page
      | tux_phone_number_message               | macys_phone_number_message     |
      | For Tuxedo Rentals Call 1-844-MCYS-TUX | Need help? Call 1-800-289-6229 |

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16Rould  @B-67842
  Scenario: Verify Placement of Rental Agreement Checkbox for only tux items in bag on RC page
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Verify Placement of Rental Agreement Checkbox for mixed bag on RC page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should verify tuxedo rental terms and conditions label as below
      | I agree to the Tuxedo Shop @ Macy's Rental Agreement |
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Verify Rental Agreement terms and conditions link for mixed bag on RC page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I click on tux_terms and conditions checkbox
    When I click on "tux_terms_and_conditions" link on responsive checkout page
    Then I should see tux_terms_and_conditions overlay
    And I close the tux_terms_and_conditions overlay
    And I checkout until I reach the order confirmation page as a "signed in" user

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Verify Rental Agreement terms and conditions link for tux only item on RC page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I click on tux_terms and conditions checkbox
    When I click on "tux_terms_and_conditions" link on responsive checkout page
    Then I should see tux_terms_and_conditions overlay
    And I close the tux_terms_and_conditions overlay
    And I checkout until I reach the order confirmation page as a "signed in" user

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Plenti as payment for for tux only item on RC page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    And I lookup plenti id using valid usl phone number on payment page
    Then I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Plenti as payment for for tux + macys item on RC page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    And I lookup plenti id using valid usl phone number on payment page
    Then I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page


  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Verify Plenti points earn for Tux item in order confirmation page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    When I lookup plenti id using valid usl phone number on shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see USL information on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_16R @B-67842
  Scenario: Verify Plenti points earn for Tux item+macys item in order confirmation page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    When I lookup plenti id using valid usl phone number on shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see USL information on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-64940
  Scenario: Verify Tuxedo message on order confirmation page for tux item only
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see "Tuxedo rentals ship 10-14 days prior to event date" message on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-64940
  Scenario: Verify Tuxedo message on order conformation page for mixed order.
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see "Tuxedo rentals ship 10-14 days prior to event date" message on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-64914
  Scenario: Verify customer should see Customer service phone numbers in the order conformation page footer when item is mixed bag
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    And I add a random product to bag
    And I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see the "tux and macys" customer service phone numbers in footer in "order_confirmation" page
      | tux_phone_number_message               | macys_phone_number_message     |
      | For Tuxedo Rentals Call 1-844-MCYS-TUX | Need help? Call 1-800-289-6229 |


  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-64914
  Scenario: Verify customer should see Customer service phone numbers in the order conformation page footer when item is only tuxedo
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see the "tux" customer service phone numbers in footer in "order_confirmation" page
      | tux_phone_number_message               |
      | For Tuxedo Rentals Call 1-844-MCYS-TUX |

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67845
  Scenario: Verify the Signed in flow for Asynch checkout for Mixed Bag as a signed user
    Given I visit the web site as a guest user
    When I add a tuxedo product to bag
    And I add a random product to bag
    And async payment experiment cookie is set
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I should see shipping value as free on "responsive_order_summary" section on "responsive_checkout_signed_in" page
    Then I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see "Tuxedo rentals ship 10-14 days prior to event date" message on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67845
  Scenario: Verify the Signed in flow for Asynch checkout for Tuxedo Item in bag
    Given I visit the web site as a guest user
    When I add a tuxedo product to bag
    And async payment experiment cookie is set
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    And I should see shipping value as free on "responsive_order_summary" section on "responsive_checkout_signed_in" page
    Then I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see "Tuxedo rentals ship 10-14 days prior to event date" message on order confirmation page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67843
  Scenario:Verfy user is able to place the order with tux and VGC item
    Given I visit the web site as a guest user
    And I create a new profile
    And I navigate to PDP page of an "VGC" product
    And I verify the product details of an "VGC" product in PDP page
    And I add a tuxedo product to bag
    When I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "shipping & payment" page
    And I should see shipping value as free on "responsive_order_summary" section on "responsive_checkout_signed_in" page
    And I click on tuxedo rental terms and conditions checkbox
    Then I place an order

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67843
  Scenario: Verfiy user is able to place the order with tux and EGC item
    Given I visit the web site as a guest user
    And I create a new profile
    And I navigate to PDP page of an "EGC" product
    And I verify the product details of an "EGC" product in PDP page
    And I add a tuxedo product to bag
    When I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "shipping & payment" page
    And I should see shipping value as free on "responsive_order_summary" section on "responsive_checkout_signed_in" page
    And I click on tuxedo rental terms and conditions checkbox
    Then I place an order


  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67843
  Scenario: Verfiy user is able to place the order with tux and registry item
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    And I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "shipping & payment" page
    And I should see shipping value as free on "responsive_order_summary" section on "responsive_checkout_signed_in" page
    And I click on tuxedo rental terms and conditions checkbox
    Then I place an order

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67843
  Scenario: Verify continue as guest button is not displayed in chekout flow
    Given I visit the web site as a guest user
    And I add a random product to bag
    And I remove all items in shopping bag
    And I navigate to shopping bag page
    And I add a tuxedo product to bag
    When I  click on continue checkout button on shoppping bag page
    And I should be redirected to Checkout SignIn page
    And I should not see continue as guest button in checkout sign in page

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67843
  Scenario:Verify the MB money redeem for tuxedo item
    Given I visit the web site as a guest user in mMoney burn period
    And I create a new profile
    And current user has icw mmoney card in their wallet
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I apply 1st gift card from the list
    Then there's 1 mmoney card applied
    And I should see mmoney card is successfully applied in order summary section

  @project_menswearhousedigital @feature_checkout @use_wip @domain_customer_management @priority_medium @release_17C @B-67843
  Scenario:Verify the MB money redeem for tuxedo item+Normal item
    Given I visit the web site as a guest user in mMoney burn period
    And I create a new profile
    And current user has icw mmoney card in their wallet
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I apply 1st gift card from the list
    Then there's 1 mmoney card applied
    And I should see mmoney card is successfully applied in order summary section