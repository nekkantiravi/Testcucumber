Feature: Signedin checkout regression scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify empty shopping bag page after placing an order
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as an "signed in" user
    Then I get order details
    And I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see shopping bag with no products

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place an order with BVR item as a registry user
    Given I visit the website as a bvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I get order details
    And I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify Warning msg for multiple registry items checkout
    Given I visit the website as a bvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    And I add another registry item from different registry user to my bag using rest service
    And I checkout until I reach the shipping & payment page as an "signed in" user
    And I should see below warning message in checkout page:
      | Currently, you can pick up items at the store from only one registry at a time. Please place as separate registry orders for in-store pickup. |

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with Normal item in standard paypal flow as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with Normal item in express paypal flow as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with Registry item in standard paypal flow as a signed in user
    Given I visit the website as a bvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    Then I verify the registrant and co registrant name details in shopping bag
    And I checkout until I reach the shipping & payment page as an "signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with Registry item in express paypal flow as a signed in user
    Given I visit the website as a bvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    Then I verify the registrant and co registrant name details in shopping bag
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place an order with VGC item as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place an order with VGC item in standard paypal flow as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with VGC item in express paypal flow as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I should see 1 shipment with ship method type as E in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify error msg for unavailable items in signed in flow
    Given I visit the website as a registered user using rest services
    When I add a product to my bag using rest service that is not "available"
    And I checkout until I reach the shipping & payment page as an "signed in" user
    Then I should see error message as:
      | MCOM | Some of the items in your bag are unavailable. Please remove them and proceed to checkout.       |
      | BCOM | Some items in your bag are currently unavailable. Please remove them to proceed with your order. |

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place an order with EGC item as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @checkout @xbrowser
  Scenario: Place order with normal and VGC items as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 2 shipments in database
    And I should see 1 shipment with ship method type as E in database
    And I should see 1 shipment with ship method type as G in database

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place order with normal and EGC items as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see 2 shipments with ship method type as G in database

  @domain_purchase_and_delivery @signedin @checkout @xbrowser
  Scenario: Place order with VGC and EGC items as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 2 shipments in database
    And I should see 1 shipments with ship method type as G in database
    And I should see 1 shipments with ship method type as E in database

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place order with normal, VGC and EGC items as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 3 shipments in database
    And I should see 2 shipments with ship method type as G in database
    And I should see 1 shipments with ship method type as E in database

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place an order with normal and registry items as a signed in user
    Given I visit the website as a bvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as an "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 1 shipments in database

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place an order with registry and VGC items as a signed in user
    Given I visit the website as a bvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as an "signed in" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 2 shipments in database
    And I should see 1 shipments with ship method type as G in database
    And I should see 1 shipments with ship method type as E in database

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with bops item as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with registry bops item as a bvr user
    Given I visit the website as a bvr user using rest services
    When I add an available_bops and registrable product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with bops and normal items as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an available_bops product to bag
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping and normal shipping in order summary section
    And I check for bops item availability
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with registry bops and normal items as a bvr user
    Given I visit the website as a bvr user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I add an available_bops and registrable product to bag
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping and normal shipping in order summary section
    And I check for bops item availability
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify device info of an order when order placed as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as an "signed in" user
    And I get order details
    Then I verify the order details in database
    And I verify device info details in database

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with registry bops and normal bops items as a bvr user
    Given I visit the website as a bvr user using rest services
    When I add an available_bops and registrable product to bag
    And I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with bops and VGC items as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @signedin @bops
  Scenario: Place order with registry bops and normal and vgc items as a bvr user
    Given I visit the website as a bvr user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an available_bops and registrable product to bag
    And I checkout until I reach the shipping & payment page as a "bops and signed in" user
    And I check for bops item availability
    And I checkout until I reach the order confirmation page as an "bops and signed in" user
    And I get order details
    Then I verify the order details in database
    And I should see a total of 3 shipments in database

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with BOPS item in standard paypal flow as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available_bops and orderable" product to my bag using rest service that is not "registrable"
    And I checkout until I reach the shipping & payment page as a "bops and signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @paypal
  Scenario: Place an order with BOPS item in express paypal flow as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available_bops and orderable" product to my bag using rest service that is not "registrable"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    And I submit bops shipping section
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place a 3d secure order with visa card as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add 3d secure "Visa" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_purchase_and_delivery @signedin @checkout
  Scenario: Place a 3d secure order with master card as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add 3d secure "MasterCard" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_purchase_and_delivery @signedin @shopping_bag
  Scenario: Verifying the Merge bag scenario
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I sign out from my current profile
    And I clear all the cookies
    And I click on signIn link
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I sign in during checkout
    Then I verify the functionality of merge bag

  @domain_purchase_and_delivery @signedin @shopping_bag
  Scenario: Verifying the Merge bag scenario for existing signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I sign out from my current profile
    And I clear all the cookies
    And I click on signIn link
    And I sign in to my existing profile
    And I navigate to shopping bag page
    Then I verify the functionality of merge bag

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify user able edit and update the shipping address as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see shipping section in summary state
    When I click change in shipping address section
    And I update the customer name as "QA Testing"
    Then I should see "QA Testing" in shipping summary section

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify order summary is updated when there is any change in the shipping details section as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and premium_shipping" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I select premium shipping method
    Then I should see selected shipping charge amount in order summary

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify the display of RC shipping sections when user have only VGC item in bag as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should not see shipping address section
    And I should not see shipping method section
    And I should not see gift options section
    And I should see vgc shippping copy in checkout page

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify user should see a checkout page footer with links as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I get bag id in shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I verify the footer section details in checkout page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I verify the footer section details in order confirmation page

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify back to bag functionality as a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    And I click back to bag link in checkout page
    Then I should be redirected to "shopping_bag" page

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify the Base fee and adjusted base fee persisted in DB when order total is > $99 as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and free_shipping" product to my bag using rest service that is not "beauty and registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I should see free base and adjusted base fee in DB

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify the Base fee and adjusted base fee persisted in DB when order total is < $99 as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available" product to my bag using rest service that is not "beauty and free_shipping and registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I get order details
    Then I should see standard base and adjusted base fee in DB

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify available shipping methods are displayed with name, transit time and the price as a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see available shipping methods with name, transit time and the price

  @domain_purchase_and_delivery @signedin @shopping_bag @phase2
  Scenario: Verify Shopping bag update quantity as a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I update shopping bag items with quantity 2
    Then I should see updated total in shopping bag page for updated quantity 2

  @domain_purchase_and_delivery @signedin @shopping_bag @phase2
  Scenario: Remove shopping Bag item as a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I remove all items in shopping bag
    Then I should see shopping bag with no products

  @domain_purchase_and_delivery @signedin @shopping_bag @phase2
  Scenario: Verify shopping bag page with a product qualifying for Free Shipping as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and free_shipping" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    Then I should see free shipping in shopping bag page

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify user is able to edit or modify contact information in responsive checkout page as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see contact info section in summary state
    When I click change in contact info section
    And I update the contact phone as "(867) 921-2222"
    Then I should see contact phone as "(867) 921-2222" in payment summary

  @domain_purchase_and_delivery @signedin @paypal @phase2
  Scenario: Verify contact information is displayed on order review page when user checkouts with PayPal as tender type as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal section in summary state
    And I should see paypal information in payment summary
    And I should see contact information in payment summary

  @domain_purchase_and_delivery @signedin @paypal @phase2
  Scenario: Verify contact information is displayed on order review page when user checkout using express PayPal as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal section in summary state
    And I should see paypal information in payment summary
    And I should see contact information in payment summary

  @domain_purchase_and_delivery @signedin @paypal @phase2
  Scenario: Verify user is able to place order with credit card after paypal authorization as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal section in summary state
    When I click change in paypal section
    Then I should not see paypal login disclaimer text in paypal section
    And I select credit card as tender type
    And I place an order
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @signedin @paypal @phase2
  Scenario: Verify user is able to edit or modify paypal contact information after paypal authentication as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal section in summary state
    When I click change in paypal section
    Then I should not see paypal login disclaimer text in paypal section
    And I update paypal contact details

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify that UserID of signed-in user is shown when the user continues as Signed-in but previously as a signed in user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping page as an "guest" user
    And I capture the UserID
    And I click back to bag link in checkout page
    Then I should be redirected to "shopping_bag" page
    When I visit the web site as a registered user
    Then I verify secure user token and cookie values after sign in

  @domain_purchase_and_delivery @signedin @checkout @phase2
  Scenario: Verify gift option indicators and gift box fee when gift options are selected as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand select gift options and gift box for signed in user
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I should see gift option indicators in order confirmation page

  @domain_purchase_and_delivery @signedin @checkout @phase2 @xbrowser
  Scenario: Verify apply and remove promo code in domestic registered shopping bag page
    Given I visit the website as a registered user using rest services
    When I add an "available and promo_eligible" product to my bag using rest service
    And I apply a valid promo code in shopping bag page
    When I remove the promo code
    Then I should not see the applied promo code

  @domain_purchase_and_delivery @signedin @sdd @phase2
  Scenario: Verify availability of SDD option when user changes the shipping address of SDD to Non SDD shipping address in the responsive signed in checkout page
    Given I visit the website as a registered user using rest services
    When I add an available_sdd product to bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I enter sdd_eligible address on shipping page for signed in user
    Then I should see sdd shipping option in enabled state
    When I enter sdd_ineligible address on shipping page for signed in user
    Then I should see sdd shipping option in disabled state

  @domain_purchase_and_delivery @signedin @sdd @phase2
  Scenario: Verify the selected gift options when signed in user selects SDD as the shipping method for the order
    Given I visit the website as a registered user using rest services
    When I add an available_sdd product to bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand select gift options and gift box for signed in user
    Then I should see gift box fee in order summary section
    And I should see gift box indicator in gift options summary
    When I enter sdd_eligible address on shipping page for signed in user
    And I select sdd shipping method option
    Then I should not see gift box fee in order summary section
    And I should see gift box option in disabled state

  @domain_purchase_and_delivery @signedin @checkout @phase2 @wip
  Scenario: Verify the saved addresses in responsive shipping address section
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add new address during checkout
    Then I should see the saved address in shipping address summary
    When I click change in shipping address section
    Then I should see the saved address in the list of available shipping addresses

  @domain_purchase_and_delivery @signedin @checkout @phase2 @wip
  Scenario: Verify the payment billing address is updated from paypal to credit card
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal section in summary state
    And I verify the paypal billing address
    When I click change in paypal section
    And I select credit card as tender type
    Then I verify the credit card billing address